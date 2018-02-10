package ca.team4519.powerup.subsystems;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.Gains;
import edu.wpi.first.wpilibj.Talon;
import ca.team4519.lib.*;
import ca.team4519.lib.Thread;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;


public class Drivebase extends Subsystem implements Thread {

	public static Drivebase thisInstance = new Drivebase();
	
	private final Talon leftDrive;
	private final Talon rightDrive;
	
	private final Encoder leftDriveEncoder;
	private final Encoder rightDriveEncoder;
	
	private final Solenoid shifter;
	
	private final AHRS navX;
	
	private double QSA;
	private final double turnSensitivity = 1.0;
	
	private boolean isShifting;
	
	public static Drivebase grabInstance() {
		return thisInstance;
	}
	
	public interface Controllers {
		
	}
	
	private Controllers controller = null;

	
	public Drivebase() {	
		thisInstance = this;
		
		leftDrive = new Talon(Constants.leftDrive);
		rightDrive = new Talon(Constants.rightDrive);
		leftDriveEncoder = new Encoder(Constants.leftDriveEncoderA, Constants.leftDriveEncoderB, Constants.isLeftDriveEncoderFlipped, EncodingType.k4X);
		rightDriveEncoder = new Encoder(Constants.rightDriveEncoderA, Constants.rightDriveEncoderB, isShifting, EncodingType.k4X);
		shifter = new Solenoid(Constants.shifter);
		navX = new AHRS(SPI.Port.kMXP, (byte) 100);
		
	}
	
	public void shift(boolean triggerShift) {
		if(!triggerShift) {
			isShifting = true;
		}else if(isShifting) {
			shifter.set(!shifter.get());
			isShifting = false;
		}
	}
	
	public boolean isHighGear() {
		return shifter.get();
		
	}
	
	public void setLeftRightPower(DrivetrainOutput power) {
		leftDrive.set(-power.leftOutput);
		rightDrive.set(power.rightOutput);
	}

	public void clearSensors() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
	}

	
	public DrivetrainOutput cheesy(double throttle, double turn) {
		
		throttle = (Math.abs(throttle) > Math.abs(0.01))? throttle : 0.0;
		turn = (Math.abs(turn) > Math.abs(0.01))? turn : 0.0;
		
		double morePower;
		double turnPower;
		
		if(throttle == 0.0){
			if(Math.abs(throttle) < 0.2){
				double alpha = 0.1;
						QSA = (1-alpha) * QSA + alpha * ((Math.abs(turn) > Math.abs(1.0))? turn: 0.0) * 2;
			}
			morePower = 1.0;
			turnPower = turn;
		}else{
		
			morePower = 0.0;
			turnPower = Math.abs(throttle) * turn *turnSensitivity - QSA;
			if(QSA > 1) {
				QSA-=1;
			}else if(QSA < -1){
				QSA += 1;
			}else{
				QSA = 0.0;
			}
		}
		
		double right = throttle + turnPower;
		double left = throttle - turnPower;
		
		if(left > 1.0){
			right += morePower * (left-1.0);
			left = 1.0;
		}else if(right > 1.0){
			left += morePower * (right-1.0);
			right = 1.0;
		}else if(left < -1.0){
			right -= morePower * (-1.0 - left);
			left = -1.0;
		}else if(right < -1.0){
			left -= morePower * (-1.0 - right);
			right=1.0;
			
		}
		
		return new DrivetrainOutput(left, right);
	}

	public void disableSubsystem() {
		if(controller != null) {
			controller = null;
		}
		setLeftRightPower(new DrivetrainOutput(0.0, 0.0));
		
	}


	public void update() {
		SmartDashboard.putNumber("Left Encoder Dist (Inches)", leftDriveEncoder.get());
		SmartDashboard.putNumber("Left Encoder Velocity (Inches/Sec)", leftDriveEncoder.getRate());
		SmartDashboard.putNumber("right Encoder Dist (Inches)", rightDriveEncoder.get());
		SmartDashboard.putNumber("Right Encoder Velocity (Inches/Sec)", rightDriveEncoder.getRate());
		SmartDashboard.putNumber("nav x angle?", navX.getAngle());
		SmartDashboard.putNumber("nav x fused heading", navX.getFusedHeading());
		SmartDashboard.putBoolean("Drive Controller Status (Should on be Active in auton)", (controller == null)? true : false);
		
	}


	@Override	
	public void loops() {
		if(controller == null) {
			return;
		}
		
	}

}
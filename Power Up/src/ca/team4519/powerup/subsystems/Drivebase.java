package ca.team4519.powerup.subsystems;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.Gains;
import edu.wpi.first.wpilibj.Talon;
import ca.team4519.lib.*;
import ca.team4519.lib.Thread;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;


public class Drivebase extends Subsystem implements Thread {

	public static Drivebase thisInstance = new Drivebase();
	public static DriveSetup tokoyoDrift = new DriveSetup();
	
	private final Talon leftDrive;
	private final Talon rightDrive;
	
	private final Encoder leftDriveEncoder;
	private final Encoder rightDriveEncoder;
	
	private final Solenoid shifter;
	
	private double QSA;
	private final double turnSensitivity = 1.0;
	
	private boolean isShifting;
	
	public static Drivebase grabInstance() {
		return thisInstance;
	}
	
	public interface loops {
		
	}
	
	private loops loop = null;

	
	public Drivebase() {	
		thisInstance = this;
		
		leftDrive = new Talon(Constants.leftDrive);
		rightDrive = new Talon(Constants.rightDrive);
		leftDriveEncoder = new Encoder(Constants.leftDriveEncoderA, Constants.leftDriveEncoderB, Constants.isLeftDriveEncoderFlipped, EncodingType.k4X);
		rightDriveEncoder = new Encoder(Constants.rightDriveEncoderA, Constants.rightDriveEncoderB, isShifting, EncodingType.k4X);
		shifter = new Solenoid(Constants.shifter);
		
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
	
	public void setLeftRightpower(DrivetrainOutput power) {
		leftDrive.set(-power.leftOutput);
		rightDrive.set(power.rightOutput);
	}

	public void clearSensors() {
		
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

		
	}


	public void update() {

		
	}


	@Override	
	public void loops() {
		
	}

}
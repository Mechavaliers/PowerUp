package ca.team4519.powerup.subsystems;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.Gains;
import ca.team4519.powerup.subsystems.controllers.*;
import ca.team4519.lib.Pose;
import edu.wpi.first.wpilibj.Talon;
import ca.team4519.lib.*;
import ca.team4519.lib.Thread;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

import edu.wpi.first.wpilibj.AnalogGyro;


public class Drivebase extends Subsystem implements Thread {

	public static Drivebase thisInstance = new Drivebase();
	
	private final Talon leftDrive;
	private final Talon rightDrive;
	
	private final Encoder leftDriveEncoder;
	private final Encoder rightDriveEncoder;
	
	private final Solenoid shifter;
	
	private final AHRS navX;
	AnalogGyro gyro;
	
	private final BuiltInAccelerometer RIO_IMU;
	
	private boolean isShifting;
	
	private final double dt = Gains.Drive.CONTROL_LOOP_TIME;
	
	private double lastLeftVel = 0;
	private double lastRightVel = 0;
	private double leftAccel = 0;
	private double rightAccel =0;
	
	Pose pose = new Pose(0, 0, 0, 0, 0, 0);
	
	public static Drivebase grabInstance() {
		return thisInstance;
	}
	
	public interface Controllers {
		DrivetrainOutput update(Pose pose);
	}
	
	private Controllers controller = null;

	
	public Drivebase() {	
		thisInstance = this;
		
		leftDrive = new Talon(Constants.leftDrive);
		rightDrive = new Talon(Constants.rightDrive);
		leftDriveEncoder = new Encoder(Constants.leftDriveEncoderA, Constants.leftDriveEncoderB, Constants.isLeftDriveEncoderFlipped, EncodingType.k4X);
		leftDriveEncoder.setDistancePerPulse(Gains.Drive.EncoderTicksPerRev);
		rightDriveEncoder = new Encoder(Constants.rightDriveEncoderA, Constants.rightDriveEncoderB, Constants.isRightDruveEncoderFlipped, EncodingType.k4X);
		rightDriveEncoder.setDistancePerPulse(Gains.Drive.EncoderTicksPerRev);
		shifter = new Solenoid(Constants.shifter);
		navX = new AHRS(SPI.Port.kMXP);
		gyro = new AnalogGyro(Constants.gyro);
		RIO_IMU = new BuiltInAccelerometer();
		
	}
	
	public void setDistanceTarget(double distance, double velocity) {
		double whatVelocity = Math.min(Gains.Drive.ROBOT_MAX_VELOCITY, Math.max(velocity, 0));
		controller = new DriveLineController(getRobotPose(), distance, whatVelocity);
	}
	
	public void setDistanceTarget(double distance) {
		setDistanceTarget(distance,Gains.Drive.ROBOT_MAX_VELOCITY);
	}
	
	public void setTurnTarget(double angle, double velocity) {
		double whatVelocity = Math.min(Gains.Drive.ROBOT_MAX_ROTATIONAL_VELOCITY, Math.max(velocity,  0));
		controller = new TurnInPlaceController(getRobotPose(), angle, whatVelocity);
	}
	
	public void setTurnTarget(double angle) {
		setTurnTarget(angle, Gains.Drive.ROBOT_MAX_ROTATIONAL_VELOCITY);
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
		navX.reset();
	}

	
	public DrivetrainOutput cheesy(double throttle, double turn) {
		
		throttle = (Math.abs(throttle) > Math.abs(0.03))? throttle : 0.0;
		turn = (Math.abs(turn) > Math.abs(0.03))? turn : 0.0;
		
		double right = throttle + turn;
		double left = throttle - turn;	
				
		return new DrivetrainOutput(left, right);
	}

	public void disableSubsystem() {
		if(controller != null) {
			controller = null;
		}
		setLeftRightPower(new DrivetrainOutput(0.0, 0.0));
		
	}
	
	public double averageDistance() {
		return (leftDriveEncoder.getDistance() + rightDriveEncoder.getDistance())/2;
	}
	
	public void getAccel() {
		getLeftAccel();
		getRightAccel();
	}
	
	public void getLeftAccel() {
		double curVel = leftDriveEncoder.getRate();
		double dv = curVel - lastLeftVel;
		lastLeftVel = curVel;
		double accel = dv/dt;
		leftAccel = accel;
	}

	
	public void getRightAccel() {
		double curVel = rightDriveEncoder.getRate();
		double dv = curVel - lastRightVel;
		lastRightVel = curVel;
		double accel = dv/dt;
		rightAccel = accel;
	}

	public Pose getRobotPose() {
		pose.reset(leftDriveEncoder.getDistance(),
				rightDriveEncoder.getDistance(),
				leftDriveEncoder.getRate(),
				rightDriveEncoder.getRate(),
				navX.getAngle(),
				navX.getRate());
		return pose;
	}

	public void update() {
		SmartDashboard.putNumber("Left Encoder Dist (Inches)", leftDriveEncoder.getDistance());
		SmartDashboard.putNumber("Left Encoder Velocity (Inches per Sec)", leftDriveEncoder.getRate());
		SmartDashboard.putNumber("Left Encoder Acceleration (Inches per sec per sec)", leftAccel);
		SmartDashboard.putNumber("right Encoder Dist (Inches)", rightDriveEncoder.getDistance());
		SmartDashboard.putNumber("Right Encoder Velocity (Inches per Sec)", rightDriveEncoder.getRate());
		SmartDashboard.putNumber("Right Encoder Acceleration (Inches per sec per sec)", rightAccel);
		SmartDashboard.putBoolean("Is high gear", isHighGear());
		SmartDashboard.putBoolean("Drive Controller Status (Should on be True in auton)", (controller == null)? false : true);
        SmartDashboard.putNumber("NavX fused Angle", navX.getFusedHeading());	
        SmartDashboard.putNumber("NavX Angle", navX.getAngle());	
		
	}


	@Override	
	public void loops() {
		getAccel();
		if(controller == null) {
			return;
		}
		setLeftRightPower(controller.update(getRobotPose()));
	}

}
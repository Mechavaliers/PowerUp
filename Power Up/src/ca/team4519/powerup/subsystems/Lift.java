package ca.team4519.powerup.subsystems;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.Gains;
import ca.team4519.powerup.subsystems.controllers.LiftController;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Thread;
import ca.team4519.lib.LiftPose;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;

public class Lift  extends Subsystem implements Thread {

	public static Lift thisInstance = new Lift();
	
	private final Talon liftMotor;
	private final Encoder liftEncoder;
	private final DigitalInput liftSecondStageHomeSwitch;
	private final DigitalInput liftThirdStageHomeSwitch;
	private final DigitalInput cubeDetector;
	
	private boolean canMove;
	
	public LiftPose pose = new LiftPose(Constants.ElevatorConstants.clawOffset, 0, true);
	
	public static Lift grabInstance() {
		return thisInstance;
	}
	
	public interface Controllers {

		double update(LiftPose pose);

	}
	
	public Controllers controller = null;
		
	public Lift() {
		thisInstance = this;
		
		liftMotor = new Talon(Constants.liftMotor);
		liftEncoder = new Encoder(Constants.liftEncoderA, Constants.liftEncoderB, Constants.isLiftEncoderFlipped, EncodingType.k4X);
		liftEncoder.setDistancePerPulse(Gains.Lift.inchesPerTick);
		liftSecondStageHomeSwitch = new DigitalInput(Constants.liftSecondStageHomeSwitch);
		liftThirdStageHomeSwitch = new DigitalInput(Constants.liftThridStageHomeSwitch);
		cubeDetector = new DigitalInput(Constants.cubeDetectorSwitch);
	}
	
	public void liftACube(double targetHeight, double velocity) {
		double correctedVel = Math.min(Gains.Lift.LIFT_MAX_VELOCITY, velocity);
		controller = new LiftController(getLiftData(), targetHeight, correctedVel);
	}
	
	public void liftACube(double targetHeight) {
		liftACube(targetHeight, Gains.Lift.LIFT_MAX_VELOCITY);
	}
	
	public void liftALift(double targetHeight, double velocity) {
		double correctedVel = Math.min(Gains.Lift.LIFT_MAX_VELOCITY, velocity);
		controller = new LiftController(getLiftData(), targetHeight, correctedVel);
	}
	
	public void liftALift(double targetHeight) {
		liftALift(targetHeight, Gains.Lift.LIFT_MAX_VELOCITY);
	}
	
	public void lowerACube(double targetHeight, double velocity) {
		double correctedVel = Math.min(Gains.Lift.LIFT_MAX_VELOCITY, velocity);
		controller = new LiftController(getLiftData(), targetHeight, correctedVel);
	}
	
	public void lowerACube(double targetHeight) {
		lowerACube(targetHeight, Gains.Lift.LIFT_MAX_VELOCITY);
	}
	
	public void lowerALift(double targetHeight, double velocity) {
		double correctedVel = Math.min(Gains.Lift.LIFT_MAX_VELOCITY, velocity);
		controller = new LiftController(getLiftData(), targetHeight, correctedVel);
	}
	
	public void lowerALift(double targetHeight) {
		lowerALift(targetHeight, Gains.Lift.LIFT_MAX_VELOCITY);
	}
	
	
	/*
	public void setHeight(boolean wantHome, boolean wantSwitchHeight, boolean wantLowScale, boolean wantScale,boolean wantHighScale) {
		
		if(canMove && wantHome) {
			canMove = false;
			if(controller != null) {
				controller = null;
			}
			controller = new LiftController(pose, Constants.ElevatorConstants.homePos);
		}else if(canMove && wantSwitchHeight) {
			canMove = false;
			if(controller != null) {
				controller = null;
			}
			controller = new LiftController(pose, Constants.ElevatorConstants.switchHeight);
		}else if(canMove && wantLowScale) {
			canMove = false;
			if(controller != null) {
				controller = null;
			}
			controller = new LiftController(pose, Constants.ElevatorConstants.lowScaleHeight);
		}else if(canMove && wantScale) {
			canMove = false;
			if(controller != null) {
				controller = null;
			}
			controller = new LiftController(pose, Constants.ElevatorConstants.scaleHeight);
		}else if(canMove && wantHighScale) {
			canMove = false;
			if(controller != null) {
				controller = null;
			}
			controller = new LiftController(pose, Constants.ElevatorConstants.HighScaleHeight);
		}else if(!wantHome && !wantSwitchHeight && !wantLowScale && !wantScale && !wantHighScale) {
			canMove = true;
			
		}
		
	}
	*/

	
	public void setLiftPower(double power) {
		liftMotor.set(power);
	}
	
	public void loops() {
		if(controller == null) {
			return;
		}
		liftMotor.set(controller.update(getLiftData()));
		
	}
	
	public LiftPose getLiftData() {
		pose.reset(liftEncoder.getDistance(), liftEncoder.getRate(), cubeDetector.get());
		return pose;
	}
	
	public boolean isHomed() {
		return (liftSecondStageHomeSwitch.get() && liftThirdStageHomeSwitch.get())? true : false;
	}

	public void clearSensors() {
		liftEncoder.reset();
	}


	public void disableSubsystem() {
		liftMotor.stopMotor();
		controller = null;
		
	}


	public void update() {
		SmartDashboard.putNumber("Claw Height (Floor to Center)", liftEncoder.getDistance());
		
	}

}

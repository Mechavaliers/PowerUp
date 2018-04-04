package ca.team4519.powerup.subsystems;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.Gains;
import ca.team4519.powerup.subsystems.controllers.LiftController;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Thread;
import ca.team4519.lib.LiftPose;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team254.lib.trajectory.TrajectoryFollower;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;

public class Lift  extends Subsystem implements Thread {

	public static Lift thisInstance = new Lift();

	private final Encoder liftEncoder;
	private final TalonSRX liftTalon;
	
	private final DigitalInput cubeDetector;
		
	public LiftPose pose = new LiftPose(0, 0, true);
	
	public static Lift grabInstance() {
		return thisInstance;
	}
	
	public interface Controllers {

		double update(LiftPose pose);

	}
	
	public Controllers controller = null;
		
	public Lift() {
		thisInstance = this;
		liftTalon = new TalonSRX(Constants.liftMotor);
		liftEncoder = new Encoder(Constants.liftEncoderA, Constants.liftEncoderB, Constants.isLiftEncoderFlipped, EncodingType.k4X);
		liftEncoder.setDistancePerPulse(Gains.Lift.inchesPerTick);
		cubeDetector = new DigitalInput(Constants.cubeDetector);
	}
		
	public void killContorller() {
		controller = null;
	}
	
	public void changeHeight(double height) {
		if(!(controller instanceof LiftController)) {
			controller = new LiftController(getLiftData(), height, Gains.Lift.LIFT_MAX_VELOCITY);
		}
		
		((LiftController) controller).changeSetpoint(getSetpoint(), height);
	}
	
	public void setPower(double power) {
		if(liftEncoder.getDistance() <= 1 && power < 0) {
			liftTalon.set(ControlMode.PercentOutput, 0);
		}else {
			liftTalon.set(ControlMode.PercentOutput, power);
		}
		
	}

	public TrajectoryFollower.TrajectorySetpoint getSetpoint() {
		TrajectoryFollower.TrajectorySetpoint setpoint = null;
		if (controller instanceof LiftController) {
			setpoint = ((LiftController) controller).getSetpoint();
		}else {
			setpoint = new TrajectoryFollower.TrajectorySetpoint();
			setpoint.pos = pose.height();
		}
		return setpoint;
		
	}
	
	public boolean hasCube() {
		return !cubeDetector.get();
	}
	
	public void loops() {
		getLiftData();
		if(controller == null) {
			return;
		}
		setPower(controller.update(getLiftData()));
			
	}
	
	public LiftPose getLiftData() {
		pose.reset(liftEncoder.getDistance(), liftEncoder.getRate(), !cubeDetector.get());
		return pose;
	}

	public void clearSensors() {
		liftEncoder.reset();
	}

	public double getHeight() {
		return( liftEncoder.getDistance());
	}
	

	public void disableSubsystem() {
		liftTalon.set(ControlMode.Disabled, 0);
		controller = null;
		
	}


	public void update() {
		SmartDashboard.putNumber("Claw Height", liftEncoder.getDistance());
		SmartDashboard.putNumber("Claw Velocity", liftEncoder.getRate());
		SmartDashboard.putBoolean("Lift Controller Status", (controller == null)? false : true);
		SmartDashboard.putBoolean("Cube", !cubeDetector.get());
		SmartDashboard.putNumber("Lift Output Current", liftTalon.getOutputCurrent());

		
		if(controller != null) {
			SmartDashboard.putNumber("Lift Controller Output", liftTalon.getMotorOutputPercent());
		}else {
			SmartDashboard.putNumber("Lift Controller Output", 0.0);
		}
		
	}

}

package ca.team4519.powerup.subsystems;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.Gains;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Thread;
import ca.team4519.lib.LiftPose;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;

public class Lift  extends Subsystem implements Thread {

	public static Lift thisInstance = new Lift();
	
	private final Talon liftMotor;
	private final Encoder liftEncoder;
	
	public static Lift grabInstance() {
		return thisInstance;
	}
	
	public interface Controllers {
		//liftMotor.set(double output);
	}
		
	public Lift() {
		thisInstance = this;
		
		liftMotor = new Talon(Constants.liftMotor);
		liftEncoder = new Encoder(Constants.liftEncoderA, Constants.liftEncoderB, Constants.isLiftEncoderFlipped, EncodingType.k4X);
	}
	
	@Override
	public void loops() {
		// TODO Auto-generated method stub
		
	}


	public void clearSensors() {
		liftEncoder.reset();
	}


	public void disableSubsystem() {
		// TODO Auto-generated method stub
		
	}


	public void update() {
		// TODO Auto-generated method stub
		
	}

}

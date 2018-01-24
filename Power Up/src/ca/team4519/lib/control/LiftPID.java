package ca.team4519.lib.control;

import edu.wpi.first.wpilibj.Talon;

public class LiftPID {

	protected double kP, kI, kD, torqeConstant, deadband, output, target, error, iError, dError, pOut, iOut, dOut;
	protected double prevError = 0;
	protected boolean isEnabled = false;
	protected boolean isInverted;
	protected EncoderPIDSource source;
	protected Talon liftMotor;
	
	public LiftPID(Talon liftMotor, EncoderPIDSource source, double kP, double kI, double kD, double torqueConstant, boolean isInverted) {
		this.liftMotor = liftMotor;
		this.source = source;
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.torqeConstant = torqueConstant;
		this.isInverted = isInverted;
	}
	
	public void update() {
		
		error = target - source.get();
		prevError = error;
		iError += error;
		dError = prevError - error;
		
		pOut = error * kP;
		iOut = iError * kI;
		dOut = dError * kD;
		
		
	}
	
}

package ca.team4519.lib.control;

import edu.wpi.first.wpilibj.Talon;

public class LiftPID {

	protected double kP, kI, kD,kF, torqeConstant, deadband, output, target, error, iError, dError, pOut, iOut, dOut, fOut;
	protected double prevError = 0;
	protected boolean isEnabled = false;
	protected boolean isInverted;

	
	public LiftPID() {
		
	}
	
	public void update(double input) {
		
		error = target - input;
		prevError = error;
		iError += error;
		dError = prevError - error;
		
		pOut = error * kP;
		iOut = iError * kI;
		dOut = dError * kD;
		fOut = 0 * kF;
		
		output = pOut + iOut + dOut + fOut;
		
		
	}
	
	public void setTarget(double target) {
		this.target = target;
	}
	
	public void set_P(double kP) {
		this.kP = kP;
	}
	
	public void set_I(double kI) {
		this.kI = kI;
	}
	
	public void set_D(double kD) {
		this.kD = kD;
	}
	
	public void set_F(double kF) {
		this.kF = kF;
	}
	
	public double getOutput() {
		return 0.0;
	}
}

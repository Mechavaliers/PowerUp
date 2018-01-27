package ca.team4519.lib;

public class LiftPose {

	private double rawValue;
	private double liftVel;

	private boolean hasCube;
	
	public LiftPose (double rawValue, double velocity, boolean hasCube) {
		this.rawValue = rawValue;
		this.liftVel = velocity;
		this.hasCube = hasCube;

	}
	
	public void reset(double rawValue, double velocity, boolean hasCube) {
		this.rawValue = rawValue;
		this.liftVel = velocity;
		this.hasCube = hasCube;
	}

	public double height() {
		return rawValue;
	}
	
	public double getLiftVelocity() {
		return liftVel;
	}
		
	public boolean cube() {
		return hasCube;
	}

}

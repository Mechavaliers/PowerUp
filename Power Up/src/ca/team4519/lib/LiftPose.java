package ca.team4519.lib;

public class LiftPose {

	private double height;
	private double liftVel;

	private boolean hasCube;
	
	public LiftPose (double height, double velocity, boolean hasCube) {
		this.height = height;
		this.liftVel = velocity;
		this.hasCube = hasCube;

	}
	
	public void reset(double height, double velocity, boolean hasCube) {
		this.height = height;
		this.liftVel = velocity;
		this.hasCube = hasCube;
	}

	public double height() {
		return height;
	}
	
	public double getLiftVelocity() {
		return liftVel;
	}
		
	public boolean cube() {
		return hasCube;
	}

}

package ca.team4519.powerup.auton.tasks;

import ca.team4519.powerup.Gains;

public class WaitForLiftHeight extends TimeoutTask {

	protected double height;
	protected boolean up;
	
	public WaitForLiftHeight(double height, boolean up, double timeout) {
		super(timeout);
		this.height = height;
		this.up = up;
	}
	
	@Override
	public boolean done() {
		return ((up ? lift.getHeight() >= height-Gains.Lift.Lift_Tollerance : lift.getHeight() <= height-Gains.Lift.Lift_Tollerance) || super.done());
	}
}
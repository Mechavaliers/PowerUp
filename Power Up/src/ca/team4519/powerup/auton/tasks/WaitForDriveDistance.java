package ca.team4519.powerup.auton.tasks;

import ca.team4519.powerup.Gains;

public class WaitForDriveDistance extends TimeoutTask {

	protected double distance;
	protected boolean forwards;
	
	public WaitForDriveDistance(double distance, boolean forwards, double timeout) {
		super(timeout);
		this.distance = distance;
		this.forwards = forwards;
	}
	
	@Override
	public boolean done() {
		return ((forwards ? drive.averageDistance() >= distance-Gains.Drive.Dist_Tollerance : drive.averageDistance() <= distance-Gains.Drive.Dist_Tollerance) || super.done());
	}
}
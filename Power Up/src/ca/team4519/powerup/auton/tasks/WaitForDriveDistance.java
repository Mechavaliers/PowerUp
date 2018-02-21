package ca.team4519.powerup.auton.tasks;

public class WaitForDriveDistance extends TimeoutTask {

	protected double distance;
	protected boolean direction;
	
	public WaitForDriveDistance(double distance, boolean direction, double timeout) {
		super(timeout);
		this.distance = distance;
		this.direction = direction;
	}
	
	@Override
	public boolean done() {
		return (distance == drive.averageDistance()) || super.done();
	}

}

package ca.team4519.powerup.auton.tasks;

public class WaitForCube extends TimeoutTask{

	public WaitForCube(double timeout) {
		super(timeout);
	}

	@Override
	public boolean done() {
		return lift.hasCube() || super.done();
	}
}
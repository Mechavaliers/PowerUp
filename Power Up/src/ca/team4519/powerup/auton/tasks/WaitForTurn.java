package ca.team4519.powerup.auton.tasks;

import ca.team4519.powerup.Gains;

public class WaitForTurn extends TimeoutTask {
	
	protected double angle;
	protected boolean forwards;

	public WaitForTurn(double angle, boolean forwards, double timeout) {
		super(timeout);
		this.angle = angle;
		this.forwards = forwards;
	}

	@Override
	public boolean done() {
		return ((forwards ? drive.getRobotPose().robotAngle() >= angle-Gains.Drive.Turn_Tollerance : drive.getRobotPose().robotAngle() <= angle-Gains.Drive.Turn_Tollerance) || super.done());
	}
}
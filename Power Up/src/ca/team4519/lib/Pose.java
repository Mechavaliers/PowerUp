package ca.team4519.lib;

public class Pose {

	private double leftDist;
	private double rightDist;
	private double leftVel;
	private double rightVel;
	private double angularPos;
	private double angularVel;
	
	public Pose(double leftDist, double rightDist, double leftVel, double rightVel, double angularPos, double angularVel) {
		this.leftDist = leftDist;
		this.rightDist = rightDist;
		this.leftVel = leftVel;
		this.rightVel = rightVel;
		this.angularPos = angularPos;
		this.angularVel = angularVel;
	}
	
	public void setOrigin() {
		leftDist = 0;
		rightDist = 0;
		leftVel = 0;
		rightVel = 0;
		angularPos = 0;
		angularVel = 0;
	}
	
	public double robotDistance() {
		return (leftDist + rightDist) / 2;
	}
	
	public double robotVelocity() {
		return (leftVel + rightVel) / 2;
	}
	
	public double leftDistance() {
		return leftDist;
	}
	
	public double rightDistance() {
		return rightDist;
	}
	
	public double leftVelocity() {
		return leftVel;
	}
	
	public double rightVelocity() {
		return rightVel;
	}
	
	public double robotAngle() {
		return angularPos;
	}
	
	public double rotationalVelocity() {
		return angularVel;
	}
}

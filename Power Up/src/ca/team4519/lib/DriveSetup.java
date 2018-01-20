package ca.team4519.lib;

import ca.team4519.lib.DrivetrainOutput;

public class DriveSetup {

	public DrivetrainOutput arcade(double throttle, double turn) {
		
		throttle = (Math.abs(throttle) > 0.01)? throttle : 0.0;
		turn = (Math.abs(turn) > 0.01)? turn : 0.0;
		
		return new DrivetrainOutput(throttle + turn, throttle - turn);
	}
	
	public DrivetrainOutput cheesy(double throttle, double turn, boolean turnInPlace) {
		
		throttle = (Math.abs(throttle) > 0.01)? throttle : 0.0;
		turn = (Math.abs(turn) > 0.01)? turn : 0.0;
		
		double morePower;
		double turnPower;
		
		if(turnInPlace) {
			
		}else {
			
		}
		
		return new DrivetrainOutput(throttle, turn);
	}
	
	public DrivetrainOutput tank(double left, double right) {
	
		left = (Math.abs(left) > 0.01)? left : 0.0;
		right = (Math.abs(right) > 0.01)? right: 0.0;
		
		return new DrivetrainOutput(left, right);
	}
	
}

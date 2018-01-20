package ca.team4519.lib;

import ca.team4519.lib.DrivetrainOutput;

public class DriveSetup {

	private double QSA;
	private final double turnSensitivity = 1.0;
	
	public DrivetrainOutput arcade(double throttle, double turn) {
		
		throttle = (Math.abs(throttle) > 0.01)? throttle : 0.0;
		turn = (Math.abs(turn) > 0.01)? turn : 0.0;
		
		return new DrivetrainOutput(throttle + turn, throttle - turn);
	}
	
	public DrivetrainOutput cheesy(double throttle, double turn, boolean turnInPlace) {
		
		throttle = (Math.abs(throttle) > Math.abs(0.01))? throttle : 0.0;
		turn = (Math.abs(turn) > Math.abs(0.01))? turn : 0.0;
		
		double morePower;
		double turnPower;
		
		if(turnInPlace){
			if(Math.abs(throttle) < 0.2){
				double alpha = 0.1;
						QSA = (1-alpha) * QSA + alpha * ((Math.abs(turn) > Math.abs(1.0))? turn: 0.0) * 2;
			}
			morePower = 1.0;
			turnPower = turn;
		}else{
		
			morePower = 0.0;
			turnPower = Math.abs(throttle) * turn *turnSensitivity - QSA;
			if(QSA > 1) {
				QSA-=1;
			}else if(QSA < -1){
				QSA += 1;
			}else{
				QSA = 0.0;
			}
		}
		
		double right = throttle + turnPower;
		double left = throttle - turnPower;
		
		if(left > 1.0){
			right += morePower * (left-1.0);
			left = 1.0;
		}else if(right > 1.0){
			left += morePower * (right-1.0);
			right = 1.0;
		}else if(left < -1.0){
			right -= morePower * (-1.0 - left);
			left = -1.0;
		}else if(right < -1.0){
			left -= morePower * (-1.0 - right);
			right=1.0;
			
		}
		
		return new DrivetrainOutput(left, right);
	}
	
	public DrivetrainOutput tank(double left, double right) {
	
		left = (Math.abs(left) > 0.01)? left : 0.0;
		right = (Math.abs(right) > 0.01)? right: 0.0;
		
		return new DrivetrainOutput(left, right);
	}
	
}

package ca.team4519.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Talon;

import ca.team4519.lib.Subsystem;
import ca.team4519.powerup.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw extends Subsystem{

	public static Claw thisInstance = new Claw();
	
	public static Claw grabInstance() {
		return thisInstance;
	}
	
	TalonSRX leftIntake;
	TalonSRX rightIntake;
	Talon leftPlow;
	Talon rightPlow;
	
	public Claw() {
		leftIntake = new TalonSRX(Constants.leftIntake);
		rightIntake = new TalonSRX(Constants.rightIntake);
		leftPlow = new Talon(5);
		rightPlow = new Talon(6);
		
	}
	
	public void spinMe(boolean in, boolean out, boolean soft) {
		if (soft) {
			soft();
		}else if (in) {
			intake();
		}else if (out) {
			spit();
		}else {
			hold();
		}
		
	}
	
	public void intake() {
		leftIntake.set(ControlMode.PercentOutput, 0.5);
		rightIntake.set(ControlMode.PercentOutput, -0.5);
		leftPlow.set(0.5);
		rightPlow.set(0.5);
	}
	
	public void spit() {
		leftIntake.set(ControlMode.PercentOutput, -0.5);
		rightIntake.set(ControlMode.PercentOutput, 0.5);
		leftPlow.set(-0.5);
		rightPlow.set(-0.5);
	}
	
	public void soft() {
		leftIntake.set(ControlMode.PercentOutput, 0.5);
		rightIntake.set(ControlMode.PercentOutput, -0.5);
		leftPlow.set(0.5);
		rightPlow.set(0.0);
	}
	
	public void hold() {
		leftIntake.set(ControlMode.PercentOutput, 0.125);
		rightIntake.set(ControlMode.PercentOutput, -0.125);
		leftPlow.set(0.0);
		rightPlow.set(0.0);
	}
	
	public void off() {
		leftIntake.set(ControlMode.PercentOutput, 0.125);
		rightIntake.set(ControlMode.PercentOutput, -0.125);
		leftPlow.set(0.0);
		rightPlow.set(0.0);
	}
	
	public void clearSensors() {
	}

	public void disableSubsystem() {	
	}

	public void update() {	
		SmartDashboard.putNumber("Left Intake Current", leftIntake.getOutputCurrent());
		SmartDashboard.putNumber("Right Intake Current", rightIntake.getOutputCurrent());
	}

}

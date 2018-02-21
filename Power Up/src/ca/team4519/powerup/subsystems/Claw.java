package ca.team4519.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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
	
	public Claw() {
		leftIntake = new TalonSRX(Constants.leftIntake);
		rightIntake = new TalonSRX(Constants.rightIntake);
		
	}
	
	public void spinMe(boolean in, boolean out, boolean soft) {
		if (in) {
			intake();
		}else if (out) {
			spit();
		}else if(soft){
			soft();
		}else {
			hold();
		}
		
	}
	
	public void intake() {
		leftIntake.set(ControlMode.PercentOutput, 0.5);
		rightIntake.set(ControlMode.PercentOutput, -0.5);
	}
	
	public void spit() {
		leftIntake.set(ControlMode.PercentOutput, -0.5);
		rightIntake.set(ControlMode.PercentOutput, 0.5);
	}
	
	public void soft() {
		leftIntake.set(ControlMode.PercentOutput, -0.25);
		rightIntake.set(ControlMode.PercentOutput, 0.25);
	}
	
	public void hold() {
		leftIntake.set(ControlMode.PercentOutput, 0.125);
		rightIntake.set(ControlMode.PercentOutput, -0.125);
	}
	
	public void off() {
		leftIntake.set(ControlMode.PercentOutput, 0.125);
		rightIntake.set(ControlMode.PercentOutput, -0.125);
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

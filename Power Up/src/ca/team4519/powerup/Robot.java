package ca.team4519.powerup;

import ca.team4519.powerup.subsystems.Drivebase;
import ca.team4519.lib.MechaIterativeRobot;
import ca.team4519.lib.MultiThreader;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends MechaIterativeRobot {

	MultiThreader autonLoop = new MultiThreader("100Hz - Auton", 1.0/100.0);
	MultiThreader teleopLoop = new MultiThreader("100Hz - Teleop", 1.0/100.0);
	
	Joystick Ben = new Joystick(0);
	
	public void robotInit() {

	}


	public void autonomousInit() {

	}


	public void autonomousPeriodic() {

	}


	public void teleopInit() {
		Drivebase.grabInstance().clearSensors();
	}
	
	public void teleopPeriodic() {
		//Rollers.grabInstance().rollers(Ben.getRawAxis(1), Ben.getRawButton(1));
		Drivebase.grabInstance().setLeftRightpower(Drivebase.grabInstance().cheesy(Ben.getRawAxis(1), Ben.getRawAxis(4)));
		Drivebase.grabInstance().shift(Ben.getRawButton(6));
	}

	
	public void allPeriodic() {
		Drivebase.grabInstance().update();
	}

}

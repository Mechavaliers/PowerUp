package ca.team4519.powerup;

import ca.team4519.powerup.auton.AutoMode;
import ca.team4519.powerup.auton.AutonRunner;
import ca.team4519.powerup.subsystems.Drivebase;
import ca.team4519.powerup.subsystems.Lift;
import ca.team4519.lib.MechaIterativeRobot;
import ca.team4519.lib.MultiThreader;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends MechaIterativeRobot {

	MultiThreader autonLoop = new MultiThreader("100Hz - Auton", 1.0/100.0);
	MultiThreader teleopLoop = new MultiThreader("100Hz - Teleop", 1.0/100.0);
	
	AutonRunner autonLoopRunner = new AutonRunner();
	
	SendableChooser<AutoMode> auton = new SendableChooser<AutoMode>();
	
	Joystick Ben = new Joystick(0);
	
	public void robotInit() {
		autonLoop.addThread(Drivebase.grabInstance());;
		autonLoop.addThread(Lift.grabInstance());
		
		teleopLoop.addThread(Drivebase.grabInstance());
		teleopLoop.addThread(Lift.grabInstance());
		
		auton.addDefault("Cross Auto Line", null);
		auton.addObject("Mode Template (temp)", null);

	}


	public void autonomousInit() {
		Drivebase.grabInstance().clearSensors();
		Lift.grabInstance().clearSensors();
		
		AutoMode mode = auton.getSelected();
		autonLoopRunner.selectAuto(mode);
		
		autonLoop.start();
		mode.init();
		autonLoopRunner.start();

	}


	public void autonomousPeriodic() {
	}


	public void teleopInit() {
		Drivebase.grabInstance().clearSensors();
		Lift.grabInstance().clearSensors();
	}
	
	public void disabledInit() {
		Drivebase.grabInstance().disableSubsystem();
		Lift.grabInstance().disableSubsystem();
		autonLoop.stop();
		teleopLoop.stop();
	}
	
	public void teleopPeriodic() {
		Drivebase.grabInstance().setLeftRightPower(Drivebase.grabInstance().cheesy(Ben.getRawAxis(1), Ben.getRawAxis(4)));
		Drivebase.grabInstance().shift(Ben.getRawButton(6));
	}

	
	public void allPeriodic() {
		Drivebase.grabInstance().update();
		Lift.grabInstance().update();
	}

}

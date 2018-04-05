package ca.team4519.powerup;

import ca.team4519.powerup.auton.AutoMode;
import ca.team4519.powerup.auton.AutonRunner;
import ca.team4519.powerup.auton.mdoes.CrossAutoLine;
import ca.team4519.powerup.auton.mdoes.CrossAutoLineLate;
import ca.team4519.powerup.auton.mdoes.StartCenter;
import ca.team4519.powerup.auton.mdoes.StartLeft;
import ca.team4519.powerup.auton.mdoes.StartLeftPreferSwitch;
import ca.team4519.powerup.auton.mdoes.StartRight;
import ca.team4519.powerup.auton.mdoes.StartRightPreferSwitch;
import ca.team4519.powerup.subsystems.Claw;
import ca.team4519.powerup.subsystems.Drivebase;
import ca.team4519.powerup.subsystems.Lift;
import ca.team4519.powerup.subsystems.Plow;
import ca.team4519.lib.MechaIterativeRobot;
import ca.team4519.lib.MultiThreader;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DriverStation;
import ca.team4519.lib.RevAirPressureSensor;

import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends MechaIterativeRobot {

	MultiThreader autonLoop = new MultiThreader("100Hz - Auton", 1.0/100.0);
	MultiThreader teleopLoop = new MultiThreader("200Hz - Teleop", 1.0/200.0);
	
	AutonRunner autonLoopRunner = new AutonRunner();
	
	SendableChooser<AutoMode> auton = new SendableChooser<AutoMode>();
	
	Joystick Ben = new Joystick(0);
	Joystick Steven = new Joystick(1);
	
	RevAirPressureSensor PSI;
	DriverStation ds;
	
	private boolean safeReset;
	
	public void robotInit() {
		safeReset = true;
		PSI = new RevAirPressureSensor(0);
		autonLoop.addThread(Drivebase.grabInstance());;
		autonLoop.addThread(Lift.grabInstance());
		
		teleopLoop.addThread(Drivebase.grabInstance());
		teleopLoop.addThread(Lift.grabInstance());
		
		auton.addDefault("Cross Auto Line", new CrossAutoLine());
		auton.addObject("Last Second Cross", new CrossAutoLineLate());
		auton.addObject("Start Left", new StartLeft());
		auton.addObject("Start Left Prefer Switch", new StartLeftPreferSwitch());
		auton.addObject("Start Center", new StartCenter());
		auton.addObject("Start Right", new StartRight());	
		auton.addObject("Start Right Prefer Switch", new StartRightPreferSwitch());
		SmartDashboard.putData(auton);

	}

	public String getAutoFieldConfig() {
		return ds.getGameSpecificMessage();
	}

	public void autonomousInit() {
		Drivebase.grabInstance().clearSensors();
		Lift.grabInstance().clearSensors();
		
		AutoMode mode = auton.getSelected();
		autonLoopRunner.selectAuto(mode);
		safeReset = false;
		autonLoop.start();
		mode.init();
		autonLoopRunner.start();
		System.out.println(mode.readPlates());

	}


	public void autonomousPeriodic() {
	}


	public void teleopInit() {
		autonLoop.stop();
		Drivebase.grabInstance().disableSubsystem();
		Drivebase.grabInstance().clearSensors();
		if(safeReset) {
		Lift.grabInstance().clearSensors();
		}
		teleopLoop.start();
		SmartDashboard.putNumber("Lift Power", 0.0);
	}
	
	public void disabledInit() {
		Drivebase.grabInstance().disableSubsystem();
		Lift.grabInstance().disableSubsystem();
		autonLoop.stop();
		teleopLoop.stop();
	}
	
	public void teleopPeriodic() {
		
		if(Steven.getRawButton(2)) {
			Lift.grabInstance().changeHeight(Constants.ElevatorConstants.switchHeight);
		}else if (Steven.getRawButton(4)) {
			Lift.grabInstance().changeHeight(Constants.ElevatorConstants.lowScaleHeight);
		}else if (Steven.getRawButton(3)) {
			Lift.grabInstance().changeHeight(Constants.ElevatorConstants.scaleHeight);
		}else if (Steven.getRawButton(5)) {
			Lift.grabInstance().changeHeight(Constants.ElevatorConstants.HighScaleHeight);
		}else if (Steven.getRawButton(8)) {
			Lift.grabInstance().changeHeight(Constants.ElevatorConstants.homePos);
		}
	
		Plow.grabInstance().setPlow(Ben.getRawButton(5));
		Claw.grabInstance().spinMe(Steven.getRawButton(7), Steven.getRawButton(1), Steven.getRawButton(6));
		Drivebase.grabInstance().setLeftRightPower(Drivebase.grabInstance().cheesy(Ben.getRawAxis(1), Ben.getRawAxis(4)));
		Drivebase.grabInstance().shift(Ben.getRawButton(6));
	}

	
	public void allPeriodic() {
		Drivebase.grabInstance().update();
		Lift.grabInstance().update();
		Claw.grabInstance().update();
		SmartDashboard.putNumber("Stored Air Pressure", PSI.getAirPressurePsi());
		SmartDashboard.putBoolean("Safe Reset", safeReset);
	}

}

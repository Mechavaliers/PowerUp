package ca.team4519.powerup;

import ca.team4519.powerup.auton.AutoMode;
import ca.team4519.powerup.auton.AutonRunner;
import ca.team4519.powerup.auton.mdoes.CrossAutoLine;
import ca.team4519.powerup.auton.mdoes.CrossAutoLineLate;
import ca.team4519.powerup.auton.mdoes.OneCubeLeftSwitch;
import ca.team4519.powerup.auton.mdoes.OneCubeRightSwitch;
import ca.team4519.powerup.auton.mdoes.StartLeft;
import ca.team4519.powerup.auton.mdoes.StartRight;
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
	
	public void robotInit() {
		PSI = new RevAirPressureSensor(0);
		autonLoop.addThread(Drivebase.grabInstance());;
		autonLoop.addThread(Lift.grabInstance());
		
		teleopLoop.addThread(Drivebase.grabInstance());
		teleopLoop.addThread(Lift.grabInstance());
		
		auton.addObject("Start Left", new StartLeft());
		auton.addObject("Start Right", new StartRight());
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
		Lift.grabInstance().clearSensors();
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
		}else if (!Lift.grabInstance().hasCube() || Steven.getRawButton(8)) {
			Lift.grabInstance().changeHeight(Constants.ElevatorConstants.homePos);
		}else if(Lift.grabInstance().hasCube()) {
			Lift.grabInstance().changeHeight(Constants.ElevatorConstants.travelPos);
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
	}

}

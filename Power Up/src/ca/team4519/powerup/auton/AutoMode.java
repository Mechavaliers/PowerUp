package ca.team4519.powerup.auton;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.auton.tasks.TimeoutTask;
import ca.team4519.powerup.auton.tasks.WaitForDriveDistance;
import ca.team4519.powerup.subsystems.Drivebase;
import ca.team4519.powerup.subsystems.Lift;
import ca.team4519.powerup.subsystems.Claw;
import ca.team4519.powerup.subsystems.Plow;

public abstract class AutoMode extends BaseAutoMode {

	protected Drivebase drive = Drivebase.grabInstance();
	protected Lift lift = Lift.grabInstance();
	protected Claw claw = Claw.grabInstance();
	protected Plow plow  = Plow.grabInstance();
	
	protected double autoLine = Constants.AutoDistances.autoLine;

	public void wait(double seconds) throws AutonException {
		runTask(new TimeoutTask(seconds));
	}
	
	public void waitForDist(double distance, boolean direction,double timeout) throws AutonException {
		runTask(new WaitForDriveDistance(distance, direction, timeout));
	}
	

}

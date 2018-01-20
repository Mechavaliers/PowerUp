package ca.team4519.powerup.auton;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.auton.tasks.TimeoutTask;
import ca.team4519.powerup.subsystems.Drivebase;

public abstract class AutoMode extends BaseAutoMode {

	protected Drivebase drive = Drivebase.grabInstance();
	
	protected double autoLine = Constants.AutoDistances.autoLine;

	public void wait(double seconds) throws AutonException {
		runTask(new TimeoutTask(seconds));
	}
	

}

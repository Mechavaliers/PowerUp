package ca.team4519.powerup.auton.tasks;

import ca.team4519.powerup.subsystems.Claw;
import ca.team4519.powerup.subsystems.Drivebase;
import ca.team4519.powerup.subsystems.Lift;

public abstract class Task {
	
	protected Drivebase drive = Drivebase.grabInstance();
	protected Lift lift = Lift.grabInstance();
	protected Claw claw = Claw.grabInstance();
	
	public abstract boolean completed();
	
	public abstract void update();
	
	public abstract boolean done();
	
	public abstract void start();
	
}

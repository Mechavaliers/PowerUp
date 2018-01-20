package ca.team4519.powerup.auton.tasks;

import ca.team4519.powerup.subsystems.Drivebase;

public abstract class Task {
	
	protected Drivebase drive = Drivebase.grabInstance();
	
	public abstract boolean completed();
	
	public abstract void update();
	
	public abstract void done();
	
	public abstract void start();
	
}

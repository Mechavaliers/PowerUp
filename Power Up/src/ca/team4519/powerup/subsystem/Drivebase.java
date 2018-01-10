package ca.team4519.powerup.subsystem;

import ca.team4519.lib.Subsystem;

public class Drivebase extends Subsystem {

	public static Drivebase thisInstance = new Drivebase();
	
	public static Drivebase grabInstance() {
		return thisInstance;
	}
	
	public Drivebase() {	
		thisInstance = this;
		
	}

	public void clearSensors() {
		// TODO Auto-generated method stub
		
	}


	public void disableSubsystem() {
		// TODO Auto-generated method stub
		
	}


	public void update() {
		// TODO Auto-generated method stub
		
	}

}

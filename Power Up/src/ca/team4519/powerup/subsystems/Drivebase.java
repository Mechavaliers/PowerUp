package ca.team4519.powerup.subsystems;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.Gains;
import ca.team4519.lib.Pose;
import ca.team4519.lib.*;
import ca.team4519.lib.Thread;


public class Drivebase extends Subsystem implements Thread{

	public static Drivebase thisInstance = new Drivebase();
	
	public static Drivebase grabInstance() {
		return thisInstance;
	}
	
	public interface loops {

		
	}
	
	private loops loop = null;
	
	public Drivebase() {	
		thisInstance = this;	
	}
	

	public void clearSensors() {

		
	}


	public void disableSubsystem() {

		
	}


	public void update() {

		
	}


	@Override	
	public void loops() {
		
	}

}
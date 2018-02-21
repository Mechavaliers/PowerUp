package ca.team4519.powerup.subsystems;

import ca.team4519.lib.PlowPose;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Thread;
import ca.team4519.powerup.Constants;
import edu.wpi.first.wpilibj.Solenoid;

public class Plow extends Subsystem implements Thread {

	public static Plow thisInstance = new Plow();
	
	private final Solenoid plowControl;
	
	public PlowPose pose = new PlowPose(false, true);
	
	public static Plow grabInstance() {
		return thisInstance;
	}
	
	public interface Controllers {
		double update(PlowPose pose);
	}
	
	public Controllers controller = null;
	
	public Plow() {
		thisInstance = this;
		
		plowControl = new Solenoid(Constants.plowControl);
	}
	
	public void loops() {
		if(controller == null) {
			return;
		}
		
	}

	public void setPlow(boolean wantPlow) {
		plowControl.set(wantPlow);
	}
	
	public PlowPose getPlowPose() {
		pose.reset(plowControl.get(), !plowControl.get());
		return pose;
	}

	public boolean isPlowDeployed() {
		return pose.isPlowDown();
	}
	
	public void clearSensors() {

	}

	public void disableSubsystem() {
		
	}

	public void update() {
		
		
		
	}

}

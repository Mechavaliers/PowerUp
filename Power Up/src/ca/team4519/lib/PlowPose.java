package ca.team4519.lib;

public class PlowPose {

	private boolean isDown;
	private boolean isUp;
	
	public PlowPose (boolean isDown, boolean isUp) {
		this.isDown = isDown;
		this.isUp = isUp;
	}
	
	public void reset (boolean isDown, boolean isUp) {
		this.isDown = isDown;
		this.isUp = isUp;
	}
	
	public boolean isPlowDown() {
		return isDown;
	}
	
	public boolean isPlowUp() {
		return isUp;
	}
	
	
}

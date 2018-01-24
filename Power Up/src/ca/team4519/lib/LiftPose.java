package ca.team4519.lib;

public class LiftPose {

	private double gripCenter;
	private double distToTop;
	private double distToBottom;
	
	public LiftPose (double gripCenter, double distToTop, double distToBottom) {
		this.gripCenter = gripCenter;
		this.distToTop = distToTop;
		this.distToBottom = distToBottom;
	}
	
	public void setOrigin(double gripCenter, double distToTop, double distToBottom) {
		this.gripCenter = gripCenter;
		this.distToTop = distToTop;
		this.distToBottom = distToBottom;
	}
	
}

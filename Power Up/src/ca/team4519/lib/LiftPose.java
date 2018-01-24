package ca.team4519.lib;

public class LiftPose {

	private double rawValue;
	private double distToTop;
	private double distToBottom;
	private boolean hasCube;
	
	public LiftPose (double rawValue, double distToTop, double distToBottom, boolean hasCube) {
		this.rawValue = rawValue;
		this.distToTop = distToTop;
		this.distToBottom = distToBottom;
	}
	
	public void reset(double rawValue, double distToTop, double distToBottom, boolean hasCube) {
		this.rawValue = rawValue;
		this.distToTop = distToTop;
		this.distToBottom = distToBottom;
		this.hasCube = hasCube;
	}

	public double height() {
		return rawValue;
	}
	
	public double distToTop() {
		return distToTop;
	}
	
	public double distToBottom() {
		return distToBottom;
	}
	
	public boolean cube() {
		return hasCube;
	}
}

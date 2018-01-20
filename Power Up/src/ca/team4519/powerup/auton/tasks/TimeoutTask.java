package ca.team4519.powerup.auton.tasks;

import edu.wpi.first.wpilibj.Timer;

public class TimeoutTask extends Task {
	
	double timeout;
	double startTime;
	
	public TimeoutTask(double timeout) {
		this.timeout = timeout;
	}

	@Override
	public boolean completed() {
		return false;
	}

	@Override
	public void update() {
	}

	@Override
	public void done() {
	}

	@Override
	public void start() {
		startTime = Timer.getFPGATimestamp();
	}
	
}

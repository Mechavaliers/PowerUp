package ca.team4519.powerup.auton;

import ca.team4519.powerup.auton.tasks.Task;

public abstract class BaseAutoMode {
	
	protected double loopRate = 1.0/200.0;
	protected boolean isRunning = false;
	
	protected abstract void sequence() throws AutonException;
	
	public abstract void init();
	
	public abstract void LRL() throws AutonException;
	public abstract void RLR() throws AutonException;
	public abstract void LLL() throws AutonException;
	public abstract void RRR() throws AutonException;
	
	public void run() {
		isRunning = true;
		try {
			sequence();
		} catch (AutonException e) {
			System.out.println("Auton crashed: " + e);
			return;
		}
		System.out.println("Auton Successful");
	}
	
	public void stop() {
		isRunning = false;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public boolean isActiveWithGarbageCollection() throws AutonException {
		if(!isRunning()) {
			throw new AutonException();
		}
		return isRunning();
	}
	
	public void runTask(Task task) throws AutonException {
		isActiveWithGarbageCollection();
		task.start();
		while(isActiveWithGarbageCollection() && !task.completed()) {
			task.update();
			try {
				Thread.sleep((long) (loopRate * 1000));
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		task.done();
	}
}

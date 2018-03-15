package ca.team4519.powerup.auton;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.auton.tasks.TimeoutTask;
import ca.team4519.powerup.auton.tasks.WaitForCube;
import ca.team4519.powerup.auton.tasks.WaitForDriveDistance;
import ca.team4519.powerup.auton.tasks.WaitForTurn;
import ca.team4519.powerup.subsystems.Drivebase;
import ca.team4519.powerup.subsystems.Lift;
import ca.team4519.powerup.subsystems.Claw;
import ca.team4519.powerup.subsystems.Plow;
import edu.wpi.first.wpilibj.DriverStation;

public abstract class AutoMode extends BaseAutoMode {

	protected Drivebase drive = Drivebase.grabInstance();
	protected Lift lift = Lift.grabInstance();
	protected Claw claw = Claw.grabInstance();
	protected Plow plow  = Plow.grabInstance();
	
	protected double autoLine = Constants.AutoDistances.autoLine;
	protected double switchFront = Constants.AutoDistances.switchFront;
	protected double switchMid = Constants.AutoDistances.switchMid;
	protected double switchBack = Constants.AutoDistances.switchBack;
	protected double scaleFront = Constants.AutoDistances.scaleFront;
	
	protected double intake = Constants.ElevatorConstants.homePos;
	protected double switchPos = Constants.ElevatorConstants.switchHeight;
	protected double scalePos = Constants.ElevatorConstants.scaleHeight;

	
	public double readPlates() {
		
	String message = DriverStation.getInstance().getGameSpecificMessage();
		double plateAssignment = 0;
		
		if(message.equalsIgnoreCase("LRL")) {
			plateAssignment = 1;
		}else if(message.equalsIgnoreCase("RLR")) {
			plateAssignment = 2;
		}else if(message.equalsIgnoreCase("LLL")) {
			plateAssignment = 3;
		}else if(message.equalsIgnoreCase("RRR")) {
			plateAssignment = 4;
		}
		return plateAssignment;
	}
	
	public void wait(double seconds) throws AutonException {
		runTask(new TimeoutTask(seconds));
	}
	
	public void waitForDist(double distance, boolean direction,double timeout) throws AutonException {
		runTask(new WaitForDriveDistance(distance, direction, timeout));
	}
	
	public void waitForTurn(double angle, boolean positive, double timeout) throws AutonException {
		runTask(new WaitForTurn(angle, positive, timeout));
	}
	public void waitForCube(double seconds) throws AutonException{
		runTask(new WaitForCube(seconds));
	}
	

}

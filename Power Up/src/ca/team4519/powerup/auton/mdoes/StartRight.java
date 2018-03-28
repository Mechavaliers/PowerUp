package ca.team4519.powerup.auton.mdoes;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.auton.AutoMode;
import ca.team4519.powerup.auton.AutonException;
import edu.wpi.first.wpilibj.Timer;

public class StartRight extends AutoMode{

	
	protected double plateAssignment;
	@Override
	protected void sequence() throws AutonException {
		if(plateAssignment == 0) {
			System.out.println("Auto Failed to get plate assignment");
		}else if(plateAssignment == 1) {
			LRL();
		}else if(plateAssignment == 2) {
			RLR();
		}else if(plateAssignment == 3) {
			LLL();
		}else if(plateAssignment == 4) {
			RRR();
		}
	}

	@Override
	public void init() {
		plateAssignment = readPlates();
		
	}

	@Override
	public void LRL() throws AutonException {
		claw.hold();
		drive.setDistanceTarget(252.5);
		lift.changeHeight(switchPos);
		waitForDist(252.5, true, 3.5);
		drive.setTurnTarget(24.5);
		waitForTurn(24.5, false, 1.250);
		drive.clearSensors();
		drive.setDistanceTarget(30);
		waitForDist(30, true, 1.5);
		lift.changeHeight(Constants.ElevatorConstants.HighScaleHeight);
		Timer.delay(5.0);
		claw.spit();
		Timer.delay(1.0);
		claw.off();
		lift.changeHeight(intake);
		
	}

	@Override
	public void RLR() throws AutonException {
		claw.hold();
		drive.setDistanceTarget(155);
		lift.changeHeight(switchPos);
		waitForDist(155, true, 3.5);
		drive.setTurnTarget(-90.0);
		waitForTurn(-90.0, false, 1.750);
		drive.clearSensors();
		drive.setDistanceTarget(15);
		waitForDist(15, true, 1.5);
		claw.spit();
		Timer.delay(1.0);
		claw.off();
		lift.changeHeight(intake);
		
	}

	@Override
	public void LLL() throws AutonException {
		claw.hold();
		drive.setDistanceTarget(switchMid);
		
	}

	@Override
	public void RRR() throws AutonException {
		claw.hold();
		drive.setDistanceTarget(252.5);
		lift.changeHeight(switchPos);
		waitForDist(252.5, true, 3.5);
		drive.setTurnTarget(-24.5);
		waitForTurn(-24.5, false, 2.0);
		drive.clearSensors();
		lift.changeHeight(Constants.ElevatorConstants.scaleHeight);
		drive.setDistanceTarget(30);
		waitForDist(30, true, 1.5);
		lift.changeHeight(Constants.ElevatorConstants.HighScaleHeight);
		wait(3.125);
		claw.spit();
		Timer.delay(1.0);
		claw.off();
		lift.changeHeight(intake);
		
		// TODO Auto-generated method stub
		
	}

}

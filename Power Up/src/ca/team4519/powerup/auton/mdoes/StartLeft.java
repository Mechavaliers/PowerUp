package ca.team4519.powerup.auton.mdoes;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.auton.AutoMode;
import ca.team4519.powerup.auton.AutonException;
import edu.wpi.first.wpilibj.Timer;

public class StartLeft extends AutoMode {

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
		drive.setDistanceTarget(155);
		lift.changeHeight(switchPos);
		waitForDist(155, true, 3.5);
		drive.setTurnTarget(90.0);
		waitForTurn(90.0, true, 1.750);
		drive.clearSensors();
		drive.setDistanceTarget(15);
		waitForDist(15, true, 1.5);
		claw.spit();
		Timer.delay(1.0);
		claw.off();
		lift.changeHeight(intake);
	}

	@Override
	public void RLR() throws AutonException {
		claw.hold();
		drive.setDistanceTarget(252.5);
		lift.changeHeight(switchPos);
		waitForDist(252.5, true, 3.5);
		drive.setTurnTarget(24.5);
		waitForTurn(24.5, true, 1.250);
		drive.clearSensors();
		drive.setDistanceTarget(30);
		waitForDist(30, true, 1.5);
		lift.changeHeight(Constants.ElevatorConstants.HighScaleHeight);
		Timer.delay(4.0);
		claw.spit();
		Timer.delay(1.0);
		claw.off();
		lift.changeHeight(intake);
	}

	@Override
	public void LLL() throws AutonException {
		claw.hold();
		drive.setDistanceTarget(252.5);
		lift.changeHeight(switchPos);
		waitForDist(252.5, true, 3.5);
		drive.setTurnTarget(24.5);
		waitForTurn(24.5, true, 1.250);
		drive.clearSensors();
		drive.setDistanceTarget(30);
		waitForDist(30, true, 1.5);
		lift.changeHeight(Constants.ElevatorConstants.HighScaleHeight);
		Timer.delay(4.0);
		claw.spit();
		Timer.delay(1.0);
		claw.off();
		lift.changeHeight(intake);
	}

	@Override
	public void RRR() throws AutonException {
		claw.hold();
		drive.setDistanceTarget(switchMid);
		/*claw.hold();
		drive.setDistanceTarget(230);
		waitForDist(230, true, 3.5);
		drive.setTurnTarget(85.0);
		waitForTurn(85.0, true, 2.0);
		drive.clearSensors();
		drive.setDistanceTarget(196);
		lift.changeHeight(switchPos);
		waitForDist(196, true, 3.5);
		drive.setTurnTarget(-90);
		waitForTurn(-90, false, 2.0);
		drive.clearSensors();
		drive.setDistanceTarget(41);
		waitForDist(41, true, 2.0);
		lift.changeHeight(Constants.ElevatorConstants.HighScaleHeight);
		Timer.delay(2.5);
		claw.spit();
		Timer.delay(1.0);
		claw.off();
		lift.changeHeight(intake);*/
		
	}

	
	
}

package ca.team4519.powerup.auton.mdoes;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.auton.AutoMode;
import ca.team4519.powerup.auton.AutonException;
import edu.wpi.first.wpilibj.Timer;

public class StartCenter extends AutoMode {

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
		drive.setDistanceTarget(35.0);
		lift.changeHeight(switchPos);
		waitForDist(35.0, true, 2.0);
		drive.setTurnTarget(-45);
		waitForTurn(-45, false, 1.5);
		drive.clearSensors();
		drive.setDistanceTarget(20);
		waitForDist(20, true, 2.0);
		drive.setTurnTarget(45);
		waitForTurn(45, true, 1.5);
		drive.setDistanceTarget(40, 45);
		waitForDist(40, true, 5);
		claw.soft();
		wait(1.5);
		claw.off();
		lift.changeHeight(intake);
		

	}

	@Override
	public void RLR() throws AutonException {
		claw.hold();
		drive.setDistanceTarget(35.0);
		lift.changeHeight(switchPos);
		waitForDist(35.0, true, 2.0);
		drive.setTurnTarget(-45);
		waitForTurn(45, true, 1.5);
		drive.setDistanceTarget(75);
		waitForDist(75, true, 2.0);
		drive.setTurnTarget(0);
		waitForTurn(0, false, 1.0);
		drive.setDistanceTarget(120, 45);
		waitForDist(120, true, 5);
		claw.soft();
		wait(1.5);
		claw.off();
		lift.changeHeight(intake);
	}

	@Override
	public void LLL() throws AutonException {
		claw.hold();
		drive.setDistanceTarget(35.0);
		lift.changeHeight(switchPos);
		waitForDist(35.0, true, 2.0);
		drive.setTurnTarget(-45);
		waitForTurn(-45, false, 1.5);
		drive.setDistanceTarget(75);
		waitForDist(75, true, 2.0);
		drive.setTurnTarget(0);
		waitForTurn(0, true, 1.0);
		drive.setDistanceTarget(120, 45);
		waitForDist(120, true, 5);
		claw.soft();
		wait(1.5);
		claw.off();
		lift.changeHeight(intake);
	}

	@Override
	public void RRR() throws AutonException {
		claw.hold();
		drive.setDistanceTarget(35.0);
		lift.changeHeight(switchPos);
		waitForDist(35.0, true, 2.0);
		drive.setTurnTarget(-45);
		waitForTurn(45, true, 1.5);
		drive.setDistanceTarget(75);
		waitForDist(75, true, 2.0);
		drive.setTurnTarget(0);
		waitForTurn(0, false, 1.0);
		drive.setDistanceTarget(120, 45);
		waitForDist(120, true, 5);
		claw.soft();
		wait(1.5);
		claw.off();
		lift.changeHeight(intake);
	}

}
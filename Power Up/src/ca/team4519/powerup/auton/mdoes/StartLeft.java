package ca.team4519.powerup.auton.mdoes;

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
		drive.setDistanceTarget(switchFront);
		waitForDist(switchFront, true, 3.0);
		drive.setTurnTarget(90);
		lift.changeHeight(switchPos);
		waitForTurn(90, true, 1.0);
		drive.setDistanceTarget(170);
		waitForDist(170, true, 2.0);
		claw.spit();
		Timer.delay(0.5);
		lift.changeHeight(intake);
		drive.setDistanceTarget(150);
		waitForDist(150, false, 1.5);
		drive.setTurnTarget(180);
		waitForTurn(180, true, 1);
		drive.setDistanceTarget(100);
		waitForDist(100, false, 1.25);
		drive.setDistanceTarget(90);
		waitForTurn(90, false, 1.0);
		drive.setDistanceTarget(112);
		waitForDist(112, true, 1.0);
		drive.setTurnTarget(180);
		waitForTurn(180, true, 1.0);
		drive.setDistanceTarget(130);
		claw.intake();
		waitForCube(2.0);
		claw.hold();
		lift.changeHeight(switchPos);
		wait(1.0);
		claw.spit();
		wait(0.75);
		lift.changeHeight(intake);
	}

	@Override
	public void RLR() throws AutonException {
		drive.setDistanceTarget(scaleFront);
		waitForDist(scaleFront, true, 3.0);
	}

	@Override
	public void LLL() throws AutonException {
		drive.setDistanceTarget(scaleFront);
		waitForDist(scaleFront, true, 3.0);
	}

	@Override
	public void RRR() throws AutonException {
		drive.setDistanceTarget(switchBack);
		
	}

	
	
}

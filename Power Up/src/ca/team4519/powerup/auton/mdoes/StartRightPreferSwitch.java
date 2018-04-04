package ca.team4519.powerup.auton.mdoes;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.auton.AutoMode;
import ca.team4519.powerup.auton.AutonException;
import edu.wpi.first.wpilibj.Timer;

public class StartRightPreferSwitch extends AutoMode{

	private double startTime = 0;
	
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
		startTime = Timer.getFPGATimestamp();
		plow.deploy();
		plateAssignment = readPlates();
		claw.hold();
		
	}

	@Override
	public void LRL() throws AutonException {
		plow.deploy();
		claw.hold();
		drive.setDistanceTarget(252.5);
		lift.changeHeight(switchPos);
		waitForDist(252.5, true, 3.5);
		drive.setTurnTarget(-24.5);
		waitForTurn(-24.5, false, 1.250);
		drive.clearSensors();
		drive.setDistanceTarget(30);
		waitForDist(30, true, 1.5);
		lift.changeHeight(Constants.ElevatorConstants.HighScaleHeight);
		waitForLift(Constants.ElevatorConstants.HighScaleHeight, true, 3.125);
		Timer.delay(0.25);
		claw.spit();
		Timer.delay(1.0);
		System.out.println("Shot cube at: " + (Timer.getFPGATimestamp() - startTime));
		claw.off();
		lift.changeHeight(intake);
		waitForLift(intake, false, 3.125);
		System.out.println("Auto Complete. Runtime is: " + (Timer.getFPGATimestamp() - startTime));

		
	}

	@Override
	public void RLR() throws AutonException {
		plow.deploy();
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
		waitForLift(intake, false, 3.125);
		System.out.println("Auto Complete. Runtime is: " + (Timer.getFPGATimestamp() - startTime));
		
	}

	@Override
	public void LLL() throws AutonException {
		plow.deploy();
		claw.hold();
		drive.setDistanceTarget(switchMid);
		System.out.println("Auto Complete. Runtime is: " + (Timer.getFPGATimestamp() - startTime));
		
	}

	@Override
	public void RRR() throws AutonException {
		plow.deploy();
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
		waitForLift(intake, false, 3.125);
		System.out.println("Auto Complete. Runtime is: " + (Timer.getFPGATimestamp() - startTime));
		
		// TODO Auto-generated method stub
		
	}

}
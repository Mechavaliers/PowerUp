package ca.team4519.powerup.auton.mdoes;

import ca.team4519.powerup.Constants;
import ca.team4519.powerup.auton.AutoMode;
import ca.team4519.powerup.auton.AutonException;
import ca.team4519.powerup.subsystems.Plow;
import edu.wpi.first.wpilibj.Timer;

public class CrossAutoLine extends AutoMode{
	
	@Override
	protected void sequence() throws AutonException {
		claw.hold();
		Timer.delay(0.125);
		lift.changeHeight(5);
		Timer.delay(0.5);
		drive.setDistanceTarget(100);
		Timer.delay(2.25);
		
		drive.setTurnTarget(45);
		Timer.delay(1.125);
		drive.clearSensors();
		
		drive.setDistanceTarget(50);
		Timer.delay(2.0);
		drive.clearSensors();
		
		drive.setTurnTarget(-45);
		Timer.delay(1.5);
		drive.clearSensors();
		
		drive.setDistanceTarget(50);
		lift.changeHeight(Constants.ElevatorConstants.switchHeight);
		Timer.delay(1.5);
		claw.spit();
		Timer.delay(0.5);
		claw.off();
		lift.changeHeight(Constants.ElevatorConstants.homePos);
		
		
		
		
	//	claw.intake();
		//Timer.delay(2);
		//plow.setPlow(true);
		//lift.changeHeight(Constants.ElevatorConstants.travelPos);
		
	}

	@Override
	public void init() {
		
	}

}
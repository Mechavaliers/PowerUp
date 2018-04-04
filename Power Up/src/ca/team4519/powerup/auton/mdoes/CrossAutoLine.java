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
		drive.setDistanceTarget(switchFront);
		waitForDist(switchFront, true, 4.0);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void LRL() throws AutonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RLR() throws AutonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LLL() throws AutonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RRR() throws AutonException {
		// TODO Auto-generated method stub
		
	}

}
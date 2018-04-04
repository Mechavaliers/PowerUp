package ca.team4519.powerup.auton.mdoes;

import ca.team4519.powerup.auton.AutoMode;
import ca.team4519.powerup.auton.AutonException;

public class CrossAutoLineLate extends AutoMode{
	
	@Override
	protected void sequence() throws AutonException {
		wait(12.5);
		drive.setDistanceTarget(switchMid);
		
		waitForDist(switchMid, true, 2.0);
		//plow.setPlow(true);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void LRL() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RLR() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LLL() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RRR() {
		// TODO Auto-generated method stub
		
	}

}
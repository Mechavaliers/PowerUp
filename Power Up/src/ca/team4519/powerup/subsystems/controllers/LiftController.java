package ca.team4519.powerup.subsystems.controllers;

import ca.team4519.powerup.Gains;
import ca.team4519.lib.LiftPose;
import ca.team4519.lib.control.LiftPID;
import ca.team4519.powerup.subsystems.Lift.Controllers;

public class LiftController implements Controllers{

	LiftPID controller;
	
	public LiftController(LiftPose startingPose, double target) {
		
		controller = new LiftPID();
		
		if(startingPose.cube()) {
			if(startingPose.height() < target) {
				controller.set_P(Gains.Lift.liftAndCubeUp_P);
				controller.set_I(Gains.Lift.liftAndCubeUp_I);
				controller.set_D(Gains.Lift.liftAndCubeUp_D);
				controller.set_F(Gains.Lift.liftAndCube_F);
			}else if(startingPose.height() > target) {
				controller.set_P(Gains.Lift.liftAndCubeDown_P);
				controller.set_I(Gains.Lift.liftAndCubeDown_I);
				controller.set_D(Gains.Lift.liftAndCubeDown_D);
				controller.set_F(Gains.Lift.liftAndCube_F);
			}
		}else {
			if(startingPose.height() < target) {
				controller.set_P(Gains.Lift.justLiftUp_P);
				controller.set_I(Gains.Lift.justLiftUp_I);
				controller.set_D(Gains.Lift.justLiftUp_D);
				controller.set_F(Gains.Lift.lift_F);
			}else if(startingPose.height() > target) {
				controller.set_P(Gains.Lift.liftjustLiftDown_P);
				controller.set_I(Gains.Lift.liftjustLiftDown_I);
				controller.set_D(Gains.Lift.liftjustLiftDown_D);
				controller.set_F(Gains.Lift.lift_F);
			}
		}
		
		controller.setTarget(target);
		
	}
	
	public double update(LiftPose pose) {
		controller.update(pose.height());
		return controller.getOutput();
		
	}
	
}

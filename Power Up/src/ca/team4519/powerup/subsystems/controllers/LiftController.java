package ca.team4519.powerup.subsystems.controllers;

import ca.team4519.powerup.Gains;

import com.team254.lib.trajectory.TrajectoryFollower;
import com.team254.lib.trajectory.TrajectoryFollower.TrajectorySetpoint;

import ca.team4519.lib.LiftPose;
import ca.team4519.lib.control.LiftPID;
import ca.team4519.powerup.subsystems.Lift.Controllers;

public class LiftController implements Controllers{
	
	public TrajectoryFollowingController controller;
	
	public LiftController(LiftPose startingPos, double goalPos, double maxVel) {
		TrajectoryFollower.TrajectoryConfig configuration = new TrajectoryFollower.TrajectoryConfig();
		configuration.dt = Gains.Lift.CONTROL_LOOP_TIME;
		configuration.max_acc = Gains.Lift.LIFT_MAX_ACCELERATION;
		configuration.max_vel = maxVel;
		
		controller = new TrajectoryFollowingController(
				Gains.Lift.LiftAndCubeUp_P, 
				Gains.Lift.LiftAndCubeUp_I, 
				Gains.Lift.LiftAndCubeUp_D, 
				Gains.Lift.LiftAndCubeUp_V, 
				Gains.Lift.LiftAndCubeUp_A, 
				Gains.Lift.Lift_Tollerance, 
				configuration);
		
		TrajectorySetpoint startingPosition = new TrajectorySetpoint();
		startingPosition.pos = startingPos.height();
		startingPosition.vel = startingPos.getLiftVelocity();
		controller.setTarget(startingPosition, goalPos);
	}

	public void changeSetpoint(TrajectoryFollower.TrajectorySetpoint currentSetpoint, double newSetpoint) {
		controller.setTarget(currentSetpoint, newSetpoint);
	}
	
	public TrajectoryFollower.TrajectorySetpoint getSetpoint() {
		return controller.getSetpoint();
	}
	
	public double update(LiftPose pose) {
		controller.update(pose.height(), pose.getLiftVelocity());
		return controller.get();
		
	}
	
}

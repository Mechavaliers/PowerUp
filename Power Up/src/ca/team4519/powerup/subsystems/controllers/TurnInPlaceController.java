package ca.team4519.powerup.subsystems.controllers;

import ca.team4519.lib.DrivetrainOutput;
import ca.team4519.lib.Pose;
import ca.team4519.powerup.Gains;
import ca.team4519.powerup.subsystems.Drivebase.Controllers;

import com.team254.lib.trajectory.TrajectoryFollower;

public class TurnInPlaceController implements Controllers{
	
	private final TrajectoryFollowingController controller;
	
	public TurnInPlaceController(Pose startingPos, double angleGoal, double maxVel){
		TrajectoryFollower.TrajectoryConfig configuration = new TrajectoryFollower.TrajectoryConfig();
		configuration.dt = Gains.Drive.CONTROL_LOOP_TIME;
		configuration.max_acc = Gains.Drive.ROBOT_MAX_ROTATIONAL_ACCELERATION;
		configuration.max_vel = maxVel;
		controller = new TrajectoryFollowingController(
				Gains.Drive.Turn_P,
				Gains.Drive.Turn_I,
				Gains.Drive.Turn_D,
				Gains.Drive.Turn_V,
				Gains.Drive.Turn_A,
				Gains.Drive.Turn_Tollerance,
				configuration);
		
		TrajectoryFollower.TrajectorySetpoint startingPosition = new TrajectoryFollower.TrajectorySetpoint();
		startingPosition.pos = startingPos.robotAngle();
		startingPosition.vel = startingPos.rotationalVelocity();
		controller.setTarget(startingPosition, angleGoal);
	}
	
	public DrivetrainOutput update(Pose pose) {
		controller.update(pose.robotAngle(), pose.rotationalVelocity());
		double turn = controller.get();
		return new DrivetrainOutput(-turn, turn);
	}

}
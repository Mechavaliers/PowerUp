package ca.team4519.powerup.subsystems.controllers;

import com.team254.lib.trajectory.TrajectoryFollower;
import com.team254.lib.trajectory.TrajectoryFollower.TrajectorySetpoint;

import ca.team4519.lib.DrivetrainOutput;
import ca.team4519.lib.Pose;
import ca.team4519.lib.TurningPID;
import ca.team4519.powerup.Gains;
import ca.team4519.powerup.subsystems.Drivebase.Controllers;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveLineController implements Controllers{

	private TrajectoryFollowingController controller;
	private TurningPID turningPIDLoop;
	
	public DriveLineController(Pose startingPos, double goalPos, double maxVel){
		TrajectoryFollower.TrajectoryConfig configuration = new TrajectoryFollower.TrajectoryConfig();
		configuration.dt = Gains.Drive.CONTROL_LOOP_TIME;
		configuration.max_acc = Gains.Drive.ROBOT_MAX_ACCELERATION;
		configuration.max_vel = maxVel;
		
		controller = new TrajectoryFollowingController(
				Gains.Drive.Dist_P, 
				Gains.Drive.Dist_I, 
				Gains.Drive.Dist_D, 
				Gains.Drive.Dist_V, 
				Gains.Drive.Dist_A, 
				Gains.Drive.Dist_Tollerance, 
				configuration);
		
		TrajectorySetpoint startingPosition = new TrajectorySetpoint();
		startingPosition.pos = encoderDistance(startingPos);
		startingPosition.vel = encoderVelocity(startingPos);
		controller.setTarget(startingPosition, goalPos);
		
		turningPIDLoop = new TurningPID(
				Gains.Drive.DistTurn_P,
				Gains.Drive.DistTurn_I,
				Gains.Drive.DistTurn_D);
		turningPIDLoop.setSetpoint(startingPos.robotAngle());
		
	}

	public static double encoderDistance(Pose pose){
		return pose.robotDistance();
	}

	public static double encoderVelocity(Pose pose){
		return pose.robotVelocity();
	}

	public DrivetrainOutput update(Pose pose) {
		controller.update(
				pose.robotDistance(),
				pose.robotVelocity());
		double power = -controller.get();
		double turn = pose.robotAngle() * Gains.Drive.DistTurn_P;/*-turningPIDLoop.calculate(pose.robotAngle());*/
		SmartDashboard.putNumber("Turing PIDloop output", turn);	
		return new DrivetrainOutput(power+turn, power-turn);
	}
	
}
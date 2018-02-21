package ca.team4519.powerup;

public class Gains {

	public static final class Drive {
		private Drive() {}
		
		public static double CONTROL_LOOP_TIME = 0.005;	// 0.5 Millisecond
		
		public static double HANDLING_MODIFIER = 1.0;	//TODO Update this
		public static double PATH_TOLLERANCE = 0.25;	//TODO Update this
		
		//	Inches/Seconds
		public static double ROBOT_MAX_VELOCITY= 180.0;	//TODO Update this
		public static double ROBOT_MAX_ACCELERATION = 45.0;	//TODO Update this
		public static double ROBOT_MAX_ROTATIONAL_VELOCITY = 360.0;	//TODO Update this
		public static double ROBOT_MAX_ROTATIONAL_ACCELERATION = 250.0;	//TODO Update this
		public static double Wheelbase_Width = 35.25;	//TODO Update this
		public static double Wheelbase_Length = 39.25; 	//TODO Update this
		public static double EncoderTicksPerRev =(( 2 * Math.PI * 3.125 ) / 256);	
		public static double WheelSize_Inches = 6.250;	//TODO Update this
		
		
		public static double Dist_P = 0.1;	//TODO Tune this
		public static double Dist_I = 0.0;	//TODO tune this
		public static double Dist_D = 0.0;	//TODO Tune this
		public static double Dist_V = 1 / 180.0;
		public static double Dist_A = 0;	//TODO Tune this
		public static double DistTurn_P = 1.0 / 22.5;	//TODO Tune this
		public static double DistTurn_I = 0.0;	//TODO Tune this
		public static double DistTurn_D = 0.0;	//TODO Tune this
		public static double Dist_Tollerance = 0.0;	// + or - target distance

		//This works in Radians
		public static double Turn_P = 1.0 / 22.5; //0.0825	//TODO Tune this
		public static double Turn_I = 0.0;	//TODO Tune this
		public static double Turn_D = 0.00; // 0.015	//TODO Tune this
		public static double Turn_V = 1 / 360; //0.05	//TODO Tune this
		public static double Turn_A = 0.0; //0.00225	//TODO Tune this
		public static double Turn_Tollerance = 0.0225; 
	}
	
	public static final class Lift {
		private Lift() {}
		
		public static double CONTROL_LOOP_TIME = 0.005;	//200Hz
		
		public static double inchesPerTick = 2 *( ( 2 * Math.PI * Constants.ElevatorConstants.sprocketRadius)/256);
		
		public static double Lift_Tollerance = 2.0; //TODO pick a value
		
		public static double LIFT_MAX_VELOCITY = 30.0;
		public static double LIFT_MAX_ACCELERATION = 12.5;	//TODO calculate this
				
		public static double LiftAndCube_F = 0.0;	//TODO Tune this
		public static double LiftAndCubeUp_P = 0.9;	//TODO Tune this
		public static double LiftAndCubeUp_I = 0.0;	//TODO Tune this
		public static double LiftAndCubeUp_D = 0.0;	//TODO Tune this
		public static double LiftAndCubeUp_V = 1 / 25.0;	//TODO Tune this//
		public static double LiftAndCubeUp_A = 0;	//TODO Tune this
		
		public static double LiftAndCubeDown_P = 0.0;	//TODO Tune this
		public static double LiftAndCubeDown_I = 0.0;	//TODO Tune this
		public static double LiftAndCubeDown_D = 0.0;	//TODO Tune this
		public static double LiftAndCubeDown_V = 0.0;	//TODO Tune this
		public static double LiftAndCubeDown_A = 0.0;	//TODO Tune this
		
		public static double Lift_F = 0.0;	//TODO Tune this
		public static double JustLiftUp_P = 0.0;	//TODO Tune this
		public static double JustLiftUp_I = 0.0;	//TODO Tune this
		public static double JustLiftUp_D = 0.0;	//TODO Tune this
		public static double JustLiftUp_V = 0.0;	//TODO Tune this
		public static double JustLiftUp_A = 0.0;	//TODO Tune this
		
		public static double LiftjustLiftDown_P = 0.0;	//TODO Tune this
		public static double LiftjustLiftDown_I = 0.0;	//TODO Tune this
		public static double LiftjustLiftDown_D = 0.0;	//TODO Tune this
		public static double LiftjustLiftDown_V = 0.0;	//TODO Tune this
		public static double LiftjustLiftDown_A = 0.0;	//TODO Tune this
		
	}
	
}

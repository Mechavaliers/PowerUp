package ca.team4519.powerup;

public class Gains {

	public static final class Drive {
		private Drive() {}
		
		public static double CONTROL_LOOP_TIME = 0.01;	// 1 Millisecond
		
		public static double HANDLING_MODIFIER = 1.0;	//TODO Update this
		public static double PATH_TOLLERANCE = 0.25;	//TODO Update this
		
		//	Inches/Seconds
		public static double ROBOT_MAX_VELOCITY= 100.0;	//TODO Update this
		public static double ROBOT_MAX_ACCELERATION = 80.0;	//TODO Update this
		public static double ROBOT_MAX_ROTATIONAL_VELOCITY = 250.0;	//TODO Update this
		public static double ROBOT_MAX_ROTATIONAL_ACCELERATION = 250.0;	//TODO Update this
		public static double Wheelbase_Width = 35.25;	//TODO Update this
		public static double Wheelbase_Length = 39.25; 	//TODO Update this
		public static double EncoderTicksPerRev = 256.0;	
		public static double WheelSize_Inches = 4.0;	//TODO Update this
		
		
		public static double Dist_P = 0.1;	//TODO Tune this
		public static double Dist_I = 0.0;	//TODO tune this
		public static double Dist_D = 0.0;	//TODO Tune this
		public static double Dist_V = 0.01;	//TODO Tune this
		public static double Dist_A = 0.0005;	//TODO Tune this
		public static double DistTurn_P = 0.0;	//TODO Tune this
		public static double DistTurn_I = 0.0;	//TODO Tune this
		public static double DistTurn_D = 0.0;	//TODO Tune this
		public static double Dist_Tollerance = 0.75;	// + or - target distance

		//This works in Radians
		public static double Turn_P = 0.1; //0.0825	//TODO Tune this
		public static double Turn_I = 0.0;	//TODO Tune this
		public static double Turn_D = 0.00; // 0.015	//TODO Tune this
		public static double Turn_V = 0.00725; //0.05	//TODO Tune this
		public static double Turn_A = 0.001; //0.00225	//TODO Tune this
		public static double Turn_Tollerance = 0.0225; 
	}
	
	public static final class Lift {
		private Lift() {}
		
		public static double inchesPerTick = 0.0;	//TODO Figure out how the math for this
		
		public static double liftAndCube_F = 0.0;	//TODO Tune this
		public static double liftAndCubeUp_P = 0.0;	//TODO Tune this
		public static double liftAndCubeUp_I = 0.0;	//TODO Tune this
		public static double liftAndCubeUp_D = 0.0;	//TODO Tune this
		
		public static double liftAndCubeDown_P = 0.0;	//TODO Tune this
		public static double liftAndCubeDown_I = 0.0;	//TODO Tune this
		public static double liftAndCubeDown_D = 0.0;	//TODO Tune this
		
		public static double lift_F = 0.0;	//TODO Tune this
		public static double justLiftUp_P = 0.0;	//TODO Tune this
		public static double justLiftUp_I = 0.0;	//TODO Tune this
		public static double justLiftUp_D = 0.0;	//TODO Tune this
		
		public static double liftjustLiftDown_P = 0.0;	//TODO Tune this
		public static double liftjustLiftDown_I = 0.0;	//TODO Tune this
		public static double liftjustLiftDown_D = 0.0;	//TODO Tune this
		
		
	}
	
}

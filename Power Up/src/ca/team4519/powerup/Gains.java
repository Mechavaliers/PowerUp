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
		public static double EncoderDegsPerRev = 360.0;	//TODO Update this
		public static double WheelSize_Inches = 4.0;	//TODO Update this
		
		
		public static double Dist_P = 0.1;	//TODO Update this
		public static double Dist_I = 0.0;	//TODO Update this
		public static double Dist_D = 0.0;	//TODO Update this
		public static double Dist_V = 0.01;	//TODO Update this
		public static double Dist_A = 0.0005;	//TODO Update this
		public static double DistTurn_P = 0.0;	//TODO Update this
		public static double DistTurn_I = 0.0;	//TODO Update this
		public static double DistTurn_D = 0.0;	//TODO Update this
		public static double Dist_Tollerance = 0.75;	// + or - target distance

		//This works in Radians
		public static double Turn_P = 0.1; //0.0825
		public static double Turn_I = 0.0; 
		public static double Turn_D = 0.00; // 0.015
		public static double Turn_V = 0.00725; //0.05
		public static double Turn_A = 0.001; //0.00225
		public static double Turn_Tollerance = 0.0225; 
	}
	
}

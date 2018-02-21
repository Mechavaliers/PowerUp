package ca.team4519.powerup;

public class Constants {

	public static final int leftDrive = 9;
	public static final int rightDrive = 8;	
	
	public static final int leftDriveEncoderA = 6;	
	public static final int leftDriveEncoderB = 7;	
	public static final int rightDriveEncoderA  = 1;	
	public static final int rightDriveEncoderB = 0;	
	
	public static final int gyro = 1;
	
	public static final boolean isLeftDriveEncoderFlipped = false;	//TODO Update this with real value!
	public static final boolean isRightDruveEncoderFlipped = false;	//TODO Update this with real value!
	
	public static final int shifter = 0;
	
	public static final int liftMotor = 2;	
	public static final int liftEncoderA = 8;	
	public static final int liftEncoderB = 9;	
	public static final boolean isLiftEncoderFlipped = false;
	
	public static final int leftIntake = 0;
	public static final int rightIntake = 1;
	
	public static final int plowControl = 1;
	
	public static final int cubeDetector = 5;
	
	public static final class ElevatorConstants {
		private ElevatorConstants() {}
		
		public static final double sprocketRadius = 1.273 / 2;	//Nominal value in inches (pitch diameter)
		public static final double ticksToInches = (2 * (Math.PI * sprocketRadius)) / 256;	//i forget the equation 	//TODO Update this with real value!
		
		public static final double clawOffset = 0.0;	//Center of claw to the ground in inches	//TODO Update this with real value!
		
		public static final double homePos = 0.0;	//TODO Update this with real value!
		public static final double travelPos = 10.0;
		public static final double switchHeight = 25.0;	//TODO Update this with real value!
		public static final double lowScaleHeight = 40.0;	//TODO Update this with real value!
		public static final double scaleHeight = 50.0;	//TODO Update this with real value!
		public static final double HighScaleHeight = 77.0;	//TODO Update this with real value!
	}
	
	public static final class AutoDistances {
		private AutoDistances() {}
		
		//All units are in inches & Degrees
		
		public static final double autoLine = 10.0;	//Distance from diamond plate in INCHES	//TODO Update with real value!
	}
	
}

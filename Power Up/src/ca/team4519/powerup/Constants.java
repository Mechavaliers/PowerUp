package ca.team4519.powerup;

public class Constants {

	public static final int leftDrive = 9;	//TODO Update this with real value!
	public static final int rightDrive = 8;	//TODO Update this with real value!
	
	public static final int leftDriveEncoderA = 6;	//TODO Update this with real value!
	public static final int leftDriveEncoderB = 7;	//TODO Update this with real value!
	public static final int rightDriveEncoderA  = 0;	//TODO Update this with real value!
	public static final int rightDriveEncoderB = 1;	//TODO Update this with real value!
	
	public static final boolean isLeftDriveEncoderFlipped = false;	//TODO Update this with real value!
	public static final boolean isRightDruveEncoderFlipped = false;	//TODO Update this with real value!
	
	public static final int shifter = 0;
	
	public static final int liftMotor = 2;	//TODO Update this with real value!
	public static final int liftEncoderA = 8;	//TODO Update this with real value!
	public static final int liftEncoderB = 9;	//TODO Update this with real value!
	public static final boolean isLiftEncoderFlipped = false;	//TODO Update this with real value!
	
	public static final int liftSecondStageHomeSwitch = 6;	//TODO Update this with real value!
	public static final int liftThridStageHomeSwitch = 7;	//TODO Update this with real value!
	public static final int cubeDetectorSwitch = 8;	//TODO Update this with real value!
	
	public static final int plowControl = 1;	//TODO Update this with real value!
	
	public static final class ElevatorConstants {
		private ElevatorConstants() {}
		
		public static final double ticksToInches = 0.0;	//i forget the equation 	//TODO Update this with real value!
		
		public static final double clawOffset = 0.0;	//Center of claw to the ground in inches	//TODO Update this with real value!
		
		public static final double homePos = 0.0;	//TODO Update this with real value!
		public static final double switchHeight = 0.0;	//TODO Update this with real value!
		public static final double lowScaleHeight = 0.0;	//TODO Update this with real value!
		public static final double scaleHeight = 0.0;	//TODO Update this with real value!
		public static final double HighScaleHeight = 0.0;	//TODO Update this with real value!
	}
	
	public static final class AutoDistances {
		private AutoDistances() {}
		
		//All units are in inches & Degrees
		
		public static final double autoLine = 10.0;	//Distance from diamond plate in INCHES	//TODO Update with real value!
	}
	
}

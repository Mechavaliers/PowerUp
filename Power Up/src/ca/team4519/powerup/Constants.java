package ca.team4519.powerup;

public class Constants {

	public static final int leftDrive = 0;
	public static final int rightDrive = 1;
	
	public static final int leftDriveEncoderA = 0;
	public static final int leftDriveEncoderB = 1;
	public static final int rightDriveEncoderA  = 2;
	public static final int rightDriveEncoderB = 3;
	
	public static final boolean isLeftDriveEncoderFlipped = false;	//TODO Update this with real value!
	public static final boolean isRightDruveEncoderFlipped = false;	//TODO Update this with real value!
	
	public static final int shifter = 0;
	
	public static final int liftMotor = 2;	//TODO Update this with real value!
	public static final int liftEncoderA = 4;	//TODO Update this with real value!
	public static final int liftEncoderB = 5;	//TODO Update this with real value!
	public static final boolean isLiftEncoderFlipped = false;	//TODO Update this with real value!
	
	public static final class ElevatorConstants {
		private ElevatorConstants() {}
		
		public static final double ticksToInches = 0.0;	//i forget the equation 	//TODO Update this with real value!
		
		public static final double clawOffset = 0.0;	//Center of claw to the ground in inches	//TODO Update this with real value!
	}
	
	public static final class AutoDistances {
		private AutoDistances() {}
		
		//All units are in inches & Degrees
		
		public static final double autoLine = 10.0;	//TODO Update with real value!
	}
	
}

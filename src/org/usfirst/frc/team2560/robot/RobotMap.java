package org.usfirst.frc.team2560.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static final int joystick0port = 0;
//	public static final int joystick1port = 1;
	
	public static final int leftDriveMotorPWM = 0;
	public static final int rightDriveMotorPWM = 1;	
	public static final int scoopMotorPWM = 4;
	
	public static final int upLimitDIO = 2;
	public static final int downLimitDIO = 1;	
	public static final int compressorRelayDIO = 4;
	
	public static int gyroanalog = 1;
	
	public static final int upScoopButtonPos = 6;
	public static final int downScoopButtonPos = 4;
	public static int turboMode = 5;
	public static int reverseAxis = 3;
}

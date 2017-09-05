package org.usfirst.frc.team2560.robot.subsystems;

import org.usfirst.frc.team2560.robot.Robot;
import org.usfirst.frc.team2560.robot.RobotMap;
import org.usfirst.frc.team2560.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private SpeedController left, right;
	private RobotDrive driveWheels;
	private Gyro rose;
	
	public DriveTrain(){
		left = new Talon(RobotMap.leftDriveMotorPWM);
		right = new Talon(RobotMap.rightDriveMotorPWM);
		rose = new AnalogGyro(RobotMap.gyroanalog);
		
		driveWheels = new RobotDrive(left, right);
	}

	public void arcadeDrive(Joystick joy){
    	driveWheels.arcadeDrive(-joy.getY(), -joy.getTwist()/2);
    }
    
    public void arcadeDrive(double forwardAxis, double twistAxis)
    {
    	driveWheels.arcadeDrive(forwardAxis, twistAxis);
    	SmartDashboard.putString("Gyro Power Level", String.valueOf(forwardAxis) + String.valueOf(twistAxis));
    }
    
    public void tankDrive(double leftAxis, double rightAxis){
    	driveWheels.tankDrive(leftAxis, rightAxis);
    }
    
    public void stop() {
    	driveWheels.tankDrive(0, 0);
    }
    
    public void drive(double outputMagnitude, double curve){
    	driveWheels.drive(outputMagnitude, curve);
    }
    
    public double getRawAngle(){
		 return rose.getAngle(); //returns the raw angle at which the robot is facing
	}
   
   public void resetGyro(){
		rose.reset();
	}
   
   public String getValue(){
	    double heading = Robot.rose.getRawAngle();
   		double motorPower = grabValue();
   		return "heading:"+String.valueOf(heading)+"|power:"+String.valueOf(motorPower);
	}
	public double grabValue(){
		double correction = 0.03;
		double a = Robot.rose.getRawAngle()*correction;
		return a;
	}
	
	public double getSafeTan(double heading)
   {
   	double safeHeading = heading % 360;
   	double motorPower = safeHeading / 45.0;
   	if(motorPower > 1)
   	{
   		motorPower = 1;
   	}
   	if(motorPower < -1)
   	{
   		motorPower = -1;
   	}
   	return motorPower;
   }
	
	public String pullSafeTan(){
		double safeTanning = Robot.rose.getSafeTan(getRawAngle());
		return String.valueOf(safeTanning);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoysticks());
    }
}


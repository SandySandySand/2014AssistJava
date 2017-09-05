package org.usfirst.frc.team2560.robot.subsystems;

import org.usfirst.frc.team2560.robot.Robot;
import org.usfirst.frc.team2560.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * 
 */
public class GyroAnalog extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Gyro GyroAnalog;
	
	public GyroAnalog(){
		GyroAnalog = new AnalogGyro(RobotMap.gyroanalog);
	}
		
	public double getRawAngle(){
		 return GyroAnalog.getAngle(); //returns the raw angle at which the robot is facing
	}
	
	public void resetGyro(){
		GyroAnalog.reset();
	}
	
	public String getValue(){
		double heading = Robot.gyro.getRawAngle();
    	double motorPower = grabValue();
    	return "heading:"+String.valueOf(heading)+"|power:"+String.valueOf(motorPower);
	}
	public double grabValue(){
		double correction = 0.03;
		double a = Robot.gyro.getRawAngle()*correction;
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
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


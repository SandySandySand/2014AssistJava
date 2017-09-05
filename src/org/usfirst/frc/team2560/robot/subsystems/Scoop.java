package org.usfirst.frc.team2560.robot.subsystems;

import org.usfirst.frc.team2560.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Scoop extends Subsystem {
    public static final double UP = -1;
    public static final double DOWN = 1;
    public static final double STOP = 0;
    public static final double upMultiplier = 0.5;
    public static final double downMultiplier = 0.3;
    
    
    
    public SpeedController scoopController;
    public DigitalInput upLimit, downLimit;
        
    public Scoop(){
    	scoopController = new Talon(RobotMap.scoopMotorPWM);
    	upLimit = new DigitalInput(RobotMap.upLimitDIO);
    	downLimit = new DigitalInput(RobotMap.downLimitDIO);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void up()
    {
    	if(!upLimit.get())
    	{
    		scoopController.set(UP*upMultiplier);
    	} else {
    		stop();
    	}
    }
    
    public void down()
    {
    	if(!downLimit.get())
    	{
    		scoopController.set(DOWN*downMultiplier);
    	} else {
    		stop();
    	}
    }
    
    public void stop()
    {
    	scoopController.set(0.0);    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


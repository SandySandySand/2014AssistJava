package org.usfirst.frc.team2560.robot.commands;

import org.usfirst.frc.team2560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GyroPIDTest extends Command {

	//Proportional Constant
	private static final double P = 0.03;
	//Max power that we should go
	private static final double MAX_POWER = 1.0;
	//How close we should be in order to call it done
	private static final double TOLERANCE = 3.0;
	//How much we should rotate
	//private double relativeRotation;
	//the angle we want to get to
	private double targetAngle;
	private double rotationWanted;
	
	
	public GyroPIDTest(double relativeRotation) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		//this.relativeRotation = relativeRotation;
		targetAngle = 0;
		requires(Robot.driveTrain);
	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetGyro();
    	rotationWanted = Robot.gyro.getRawAngle() + targetAngle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//executes the computeError method
    	double error = computeError();
    	//gets the power that we need to supply to the motors
    	double power = error * P;
    	//sets up min and max power values
    	power = Math.max(-MAX_POWER, Math.min(+MAX_POWER, power));
    	//sends the power to the motors to move in the right direction
    	SmartDashboard.putString("Our Computed Error", String.valueOf(error) );
    	Robot.driveTrain.arcadeDrive(0.5, power);
    }
    
    public double computeError(){
    	//takes the angle in for corrections
    	double error = targetAngle - Robot.gyro.getRawAngle();
    	return error;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//executes the computeError method
    	double errorR = computeError();
		//checks to see if the we have been moved to something close to our tolerance level
    	boolean done = (Math.abs(errorR) <= TOLERANCE);
		// returns true or false depending on the outcome of the above equation
    	return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

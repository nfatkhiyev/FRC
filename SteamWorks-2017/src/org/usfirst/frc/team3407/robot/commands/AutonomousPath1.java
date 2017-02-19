package org.usfirst.frc.team3407.robot.commands;

import org.usfirst.frc.team3407.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
public class AutonomousPath1 extends Command {
double startAngle;
	
    public AutonomousPath1() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.driveSubsystem);	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {;
    System.out.println("A");
    Robot.driveSubsystem.driveForward(-0.8, 2); //speed (0-1 0 being stopped, 1 being full speed) timer(seconds)
    //Robot.driveSubsystem.turnRight(0.8, 1);
    //Robot.driveSubsystem.turnLeft(0.8, 1);
    Robot.driveSubsystem.stop(); //shouldn't be needed, but better safe than sorry
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team3407.robot.subsystems;


import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3407.robot.commands.*;;

/**
 *
 */
public class drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private RobotDrive drive;
	public drivetrain() {
		drive = new RobotDrive(0, 1);
		drive.setSafetyEnabled(false);
		drive.setSensitivity(0.75);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    public void TankDrive (double rightstick, double leftstick){
    	drive.tankDrive(leftstick, rightstick);
    }
    public void AutonomousDrive (double speedl, double time){
    	drive.drive(speedl, 0);
    	Timer.delay(time);
    	drive.drive(0, 0);
    }
    public void stop(){
    	drive.drive(0, 0);
    }
}


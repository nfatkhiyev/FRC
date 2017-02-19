package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3407.robot.OI;

import edu.wpi.first.wpilibj.SpeedController;
/**
 *
 */
public class Feeder extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static SpeedController feederMotor = new VictorSP(6);
	
	public static void start() {
    	SmartDashboard.putBoolean(OI.FEEDER_ENGAGED_KEY, true);    	 
		feederMotor.set(1);
	};

	public static void stop() {
    	SmartDashboard.putBoolean(OI.FEEDER_ENGAGED_KEY, false);    	 
		feederMotor.set(0);
	};
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


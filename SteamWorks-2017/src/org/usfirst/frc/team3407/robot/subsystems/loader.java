package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3407.robot.OI;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 */
public class loader extends Subsystem {
	public static SpeedController Agitator = new VictorSP(9); //note: wrong port
	public static SpeedController loadShooter = new VictorSP(4);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void shoot(){
    	SmartDashboard.putBoolean(OI.LOADER_ENGAGED_KEY, true);    	 
    	loadShooter.set(1);
    	Agitator.set(1);
    }
    
    public static void stopShooting(){
    	SmartDashboard.putBoolean(OI.LOADER_ENGAGED_KEY, false);    	 
        loadShooter.set(0);
        Agitator.set(0);
    }
}


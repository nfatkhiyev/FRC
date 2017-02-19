
package org.usfirst.frc.team3407.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3407.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
	private static final String ARCADE_MODE_KEY = "DB/Button 1";
	private static final String ARCADE_DISPLAY_KEY = "DB/String 2";
	
	public static final String LOADER_ENGAGED_KEY = "DB/LED 0";
	public static final String FEEDER_ENGAGED_KEY = "DB/LED 1";
	public static final String SHOOTER_ENGAGED_KEY = "DB/LED 2";
	
	public static Joystick stick = new Joystick(0);
	public static Joystick stick2 = new Joystick(1);
	
	public static JoystickButton startLoading = new JoystickButton(stick, 1);
	public static JoystickButton stopLoading = new JoystickButton(stick, 2);

	public static JoystickButton startFeeder = new JoystickButton(stick, 3);
	public static JoystickButton stopFeeder = new JoystickButton(stick, 4);

	public static JoystickButton startShooting = new JoystickButton(stick, 5);
	public static JoystickButton stopShooting = new JoystickButton(stick, 6);
	
    static {
    	startLoading.whenActive(new startLoading());
    	startShooting.whenPressed(new startShooting());
    	stopLoading.whenPressed(new stopLoading());
    	stopShooting.whenPressed(new stopShooting());
    	
    	startFeeder.whenPressed(new StartFeeder());
    	stopFeeder.whenPressed(new StopFeeder());
    	
    	toTank(); 
    }

    public static boolean isArcade() { 
    	boolean value = SmartDashboard.getBoolean(ARCADE_MODE_KEY,  true); 
    	setArcadeDisplay(value); 
    	return value; 
    } 

    public static void toArcade() { 
    	SmartDashboard.putBoolean(ARCADE_MODE_KEY,  true); 
    	setArcadeDisplay(true); 
    } 

    public static void toTank() { 
    	SmartDashboard.putBoolean(ARCADE_MODE_KEY, false); 
    	setArcadeDisplay(false); 
    } 

    private static void setArcadeDisplay(boolean mode) { 
    	SmartDashboard.putString(ARCADE_DISPLAY_KEY,  (mode ? "Arcade" : "Tank") + " Drive Mode");    	 
    } 
}
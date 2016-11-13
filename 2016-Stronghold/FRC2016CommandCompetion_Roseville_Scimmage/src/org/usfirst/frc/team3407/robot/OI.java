package org.usfirst.frc.team3407.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team3407.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	static OI instance;//1st step in creating an instance of this class
	double multiplier;
	double driveTime;
	double autoSpeedCap;
	private double autoSpeed;
	//Joystick Setup
	Joystick rightstick = new Joystick(1);
	Joystick leftstick = new Joystick(0);
	Button buttonR1 = new JoystickButton(rightstick, 1),//Setting up new buttons
		   buttonR2 = new JoystickButton(rightstick, 2),
		   buttonL3 = new JoystickButton(leftstick, 3),
		   buttonL2 = new JoystickButton (leftstick, 2),
		   buttonL1 = new JoystickButton (leftstick,1),
		   buttonR3 = new JoystickButton (rightstick, 3);
	//creating names for variables used in speed equations
	private double rightSpeedCap,leftSpeedCap;
	private double speedRight,speedLeft;
	
	public OI(){
		//Here  we assign buttons to commands based on their actions
	    //// TRIGGERING COMMANDS WITH BUTTONS
	    // Once you have a button, it's trivial to bind it to a button in one of
	    // three ways:
	    
	    // Start the command when the button is pressed and let it run the command
	    // until it is finished as determined by it's isFinished method.
	    // button.whenPressed(new ExampleCommand());
	    
	    // Run the command while the button is being held down and interrupt it once
	    // the button is released.
	    // button.whileHeld(new ExampleCommand());
	    
	    // Start the command when the button is released  and let it run the command
	    // until it is finished as determined by it's isFinished method.
	    // button.whenReleased(new ExampleCommand());
		buttonR1.whenPressed(new ShootCommand());
		buttonR2.whenPressed(new HomeCommand());
		buttonL3.whenPressed(new ArmDownCommand());
		buttonL2.whenPressed(new ArmUpCommand());
		buttonR3.whenPressed(new TrollComand());
		buttonL1.whileHeld(new BoostDriveCommand());
	}
	
	public static OI getInstance(){
		//creation of a new instance for this class used to update joystick values.
		//and link this code to the parent class Robot
		if(instance == null){
			instance = new OI();	
		}
		return instance;
	}
    

//This is an acceleration sequence for our autonomous mode.
	public double AutoFinalSpeed(double value, double acceleration, double cap){
		if(value < cap) {
			if(value + acceleration <= cap) {
				value += acceleration;
			}else if(value + acceleration > cap) { 
				value = cap;
			}
		}else if(value > cap) {
			if(value - acceleration >= cap){
				value -= acceleration;
			}else if(value - acceleration < cap) {
				value = cap;
			}
		}else {
			value = cap;
		}
		return value;
		
	}
	//This is an acceleration sequence based off of joystick values for Tele-Op.
	//It is similar to the autonomous one but it uses joysticks as cap values and more exceptions.
	public double finalSpeed(double value,double acceleration, double cap) {
		//double Ivalue = value;
		//double Iacceleration = acceleration;
		//double Icap = cap;

		if(Math.abs(cap) < 0.05){
			cap = 0;
		}
		
			if(value < cap) {
				if(value + acceleration <= cap) {
					value += acceleration;
				}else if(value + acceleration > cap) { 
					value = cap;
				}
				/*if(Ivalue < 0 && Ivalue + Iacceleration > 0) {
					value = 0;
				}*/
			}else if(value > cap) {
				if(value - acceleration >= cap){
					value -= acceleration;
				}else if(value - acceleration < cap) {
					value = cap;
				}
				/*if(Ivalue > 0 && Ivalue - Iacceleration < 0) {
					value = 0;
				}*/
			}else {
				value = cap;
			}
		return value;
	}

	public void Start() {
		//reset method for variables runs once at beginning
		speedRight = 0;
		speedLeft = 0;
		autoSpeed = 0;

		multiplier = .6;
		//autoSpeedCap =(Double.parseDouble (Robot.a));
		//driveTime = (Double.parseDouble(Robot.b));
	}
	//The update meathod is run once every frame and is called in the Robot parent class.
	public void Update() {
		rightSpeedCap = rightstick.getRawAxis(1);
        leftSpeedCap = leftstick.getRawAxis(1);

        //multiplier = leftstick.getTrigger() ? 1 : .5;
        speedRight = finalSpeed(speedRight,0.1,rightSpeedCap);
        speedLeft = finalSpeed(speedLeft,0.1,leftSpeedCap);
        
        autoSpeed = AutoFinalSpeed(autoSpeed,0.1,autoSpeedCap);
        
       // System.out.println(speedLeft * .5 + ", " + speedRight * .5 + "," + System.currentTimeMillis());
	}
//getters and setters fo multiple variables such as joysticks and speeds.
	public Joystick getRightJoystick() {
		return rightstick;
	}
	public Joystick getLeftJoystick(){
		return leftstick;
	}
	public double driveTime(){
		return driveTime;
	}
	public double getSpeedRight() {
		return speedRight;
	}
	public void setSpeedRight(double num) {
		speedRight = num;
	}
	public double getSpeedLeft() {
		return speedLeft;
	}
	public void setSpeedLeft(double num) {
		speedLeft = num;
	}
	public double getAutoSpeed(){
		return autoSpeed;
	}
	public double getDriveTime(){
		return driveTime;
	}
	public double getMultiplier() {
		return multiplier;
	}
	//DOCUMENTATION of OLD CODE
	/*public double getSpeedLeft() {
	return speedLeft;
	}*/
	/*public double rightStickCap(){
	return rightSpeedCap;
	}
	public double leftSpeedCap(){
		return leftSpeedCap;
	}
	public double getRightSpeedCap() {
		return rightSpeedCap;
	}
	public void setRightSpeedCap(double num) {
		rightSpeedCap = num;
	}
	public double getLeftSpeedCap() {
		return leftSpeedCap;
	}
	public void setLeftSpeedCap(double num) {
		leftSpeedCap = num;
	}*/
	/*public Boolean getButtonX() {
		return buttonX;
	}
	public Boolean getButtonY() {
		return buttonY;
	}
	public Boolean getButtonA() {
		return buttonA;
	}
	public Boolean geButtonB() {
		return buttonB;
	}*/

}


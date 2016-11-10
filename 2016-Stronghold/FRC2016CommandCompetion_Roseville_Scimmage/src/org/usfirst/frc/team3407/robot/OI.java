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
	static OI instance;
	double multiplier;
	double driveTime;
	double autoSpeedCap;
	private double autoSpeed;
	
	Joystick rightstick = new Joystick(1);
	Joystick leftstick = new Joystick(0);
	Button buttonR1 = new JoystickButton(rightstick, 1),
		   buttonR2 = new JoystickButton(rightstick, 2),
		   buttonL3 = new JoystickButton(leftstick, 3),
		   buttonL2 = new JoystickButton (leftstick, 2),
		   buttonL1 = new JoystickButton (leftstick,1),
		   buttonR3 = new JoystickButton (rightstick, 3);
	private double rightSpeedCap,leftSpeedCap;
	private double speedRight,speedLeft;
	
	public OI(){
		
		buttonR1.whenPressed(new ShootCommand());
		buttonR2.whenPressed(new HomeCommand());
		buttonL3.whenPressed(new ArmDownCommand());
		buttonL2.whenPressed(new ArmUpCommand());
		buttonR3.whenPressed(new TrollComand());
		buttonL1.whileHeld(new BoostDriveCommand());
	}
	
	public static OI getInstance(){
		if(instance == null){
			instance = new OI();	
		}
		return instance;
	}
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
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
		speedRight = 0;
		speedLeft = 0;
		autoSpeed = 0;

		multiplier = .6;
		//autoSpeedCap =(Double.parseDouble (Robot.a));
		//driveTime = (Double.parseDouble(Robot.b));
	}
	
	public void Update() {
		rightSpeedCap = rightstick.getRawAxis(1);
        leftSpeedCap = leftstick.getRawAxis(1);

        //multiplier = leftstick.getTrigger() ? 1 : .5;
        speedRight = finalSpeed(speedRight,0.1,rightSpeedCap);
        speedLeft = finalSpeed(speedLeft,0.1,leftSpeedCap);
        
        autoSpeed = AutoFinalSpeed(autoSpeed,0.1,autoSpeedCap);
        
       // System.out.println(speedLeft * .5 + ", " + speedRight * .5 + "," + System.currentTimeMillis());
	}

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


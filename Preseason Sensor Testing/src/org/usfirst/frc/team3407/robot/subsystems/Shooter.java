package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is the shooter that rolls the ball out of the robot.
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Victor shootmotor;
	DigitalInput homeswitch;
	DigitalInput shootswitch;
	Victor armMotL;
	Victor armMotR;
	public Shooter(){
		shootmotor = new Victor(2);
		homeswitch = new DigitalInput(0);
		shootswitch = new DigitalInput(1);
		armMotL = new Victor(3);
		armMotR = new Victor(4);
	}
	public void shoot(){
		shootmotor.set(1.0);
	}
	public void home(){
		shootmotor.set(-0.25);
	}
	public void troll(){
		shootmotor.set(-0.25);
		Timer.delay(0.4);
		shootmotor.set(0);
	}
	public void trollFromHome(){
		shootmotor.set(0.25);
		Timer.delay(0.5);
		shootmotor.set(0);
	}
	public void armUp(){
    	armMotL.set(0.9);
    	armMotR.set(-0.9);
    	Timer.delay(1);
    	armMotL.set(0.5);
    	armMotR.set(-0.5);
    	Timer.delay(0.3);
    	armMotL.set(0);
    	armMotR.set(0);
	}
	public void armDown(){
		armMotL.set(-0.7);
    	armMotR.set(0.7);
    	Timer.delay(0.5);
    	armMotL.set(0.25);
    	armMotR.set(-0.25);
    	Timer.delay(1);
    	armMotL.set(0);
    	armMotR.set(0);
	}
	public boolean isShootSwitchPressed(){
		return shootswitch.get();
	}
	public boolean isHomeSwitchPressed(){
		return homeswitch.get();
	}
	public void stop(){
		shootmotor.set(0);
	}
	public void armStop(){
    	armMotL.set(0);
    	armMotR.set(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


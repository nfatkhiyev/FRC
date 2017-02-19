package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 */
public class linearSlide extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static SpeedController linearslide = new VictorSP(7);
	
	
	public static void moveLeft(){
		if(linearslide.get() == 0){
			linearslide.set(1);
		}
		/*else {
			linearslide.set(0);
		}*/
	}
	public static void moveRight(){
		if(linearslide.get() == 0){
			linearslide.set(-1);
		}
		/*else {
			linearslide.set(0);
		}*/
	}
	public static void stop(){
		linearslide.set(0);
	}
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


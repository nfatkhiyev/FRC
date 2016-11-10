package org.usfirst.frc.team3407.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
		RobotDrive robert = new RobotDrive(0,1); 
		Joystick stick1 = new Joystick(0);
		Joystick stick2 = new Joystick(1);
		Joystick camstick = new Joystick(2);
		Timer time1 = new Timer();
		DigitalInput home = new DigitalInput(0);
		DigitalInput shoot = new DigitalInput(1);
		Victor feedMot = new Victor(2);
		Victor armMotL = new Victor(3);
		Victor armMotR = new Victor(4);
		Servo camera = new Servo(6);
		
		CameraServer server;
		
    public void robotInit() {
    	robert.setExpiration(0.1);
    	
    	server = CameraServer.getInstance();
        server.setQuality(50);
        server.startAutomaticCapture("cam0");
    }

    /**
     * This function is called periodically during autonomous
     */
    
    
    public void autonomousPeriodic() {
    	robert.setSafetyEnabled(true);
    	time1.start();
    	double tym = time1.get();
    	while(time1.get() - tym <= 8){
    		robert.tankDrive(1,1); 
    		Timer.delay(2.0); 
    	}
    		
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	robert.setSafetyEnabled(true);
    	
    	double leftSpeed = 0;
    	double rightSpeed = 0;
    	
        while (isOperatorControl() && isEnabled()) {
        	
        	boolean but1 = stick1.getRawButton(1);
        	boolean but2 = stick1.getRawButton(2);
        	boolean but5 = stick1.getRawButton(5);
        	boolean but4 = stick1.getRawButton(4);
        	boolean but3 = stick1.getRawButton(3);
        	//Camera
        	camera.setAngle(camstick.getX()*0.00588235294+85);
       //Drive
        	if (stick1.getY() >= 0){
        		leftSpeed = Math.exp(stick1.getY());
        		leftSpeed = (leftSpeed - 1)/(Math.E - 1);
        	}
        	else if (stick1.getY() < 0 ){
        		leftSpeed = Math.exp((-1*stick1.getY()));
        		leftSpeed = (-1)*(leftSpeed - 1)/(Math.E - 1);
        	}
        	
        	if (stick2.getY() >= 0){
        		rightSpeed = Math.exp(stick2.getY());
        		rightSpeed = (rightSpeed - 1)/(Math.E - 1);
        	}
        	else if (stick2.getY() < 0 ){
        		rightSpeed = Math.exp((-1*stick2.getY()));
        		rightSpeed = (-1)*(rightSpeed - 1)/(Math.E - 1);
        	}
        	
        	robert.tankDrive(leftSpeed, rightSpeed);
            Timer.delay(0.005);	
      //End Drive 
      
      //Feeder Begins here      
            while (but5 == true && shoot.get() == true){
           		feedMot.set(0.25);
           		Timer.delay(0.005);
           		feedMot.set(0);
            	but3 = stick1.getRawButton(3);
            	}
           	while (but4 == true && home.get() == true){
           		feedMot.set(-0.25);
           		Timer.delay(0.005);
           		feedMot.set(0);
           		but4 = stick1.getRawButton(4);
            }
           	
           	
           	//TROLLLL
            if (but3 == true){
            	if(shoot.get() == true){
            		while(shoot.get() == true){
            			feedMot.set(1.0);
            		}
            		feedMot.set(-0.25);
            		Timer.delay(0.3);
            	}
            	else{
            		feedMot.set(-0.25);
            		Timer.delay(0.3);
            	}
            }
            //end Troll     
           	
            
            if (but1 == true){
            	while(shoot.get() == true){
            		feedMot.set(1.0);
            	}
            }
            else if(but2 == true){
            	while(home.get() == true){
            		feedMot.set(-0.25);
            	}
            }
            else{
            	feedMot.set(0);
            }
            //End Feeder  
            
            // Arm
            
            
            if ( but2 == true){
            	armMotL.set(0.8);
            	armMotR.set(-0.8);
            	Timer.delay(0.7);
            	armMotL.set(0);
            	armMotR.set(0);
            	Timer.delay(1);
            }
            if (but3 == true){
            	armMotL.set(-0.7);
            	armMotR.set(0.7);
            	Timer.delay(0.5);
            	armMotL.set(0.1);
            	armMotR.set(-0.1);
            	Timer.delay(1);
            	armMotL.set(0);
            	armMotR.set(0);
            	Timer.delay(1);
            }
            //End Arm
            
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	while (isOperatorControl() && isEnabled()){
    		feedMot.set(1);
    	}
    }
    
}

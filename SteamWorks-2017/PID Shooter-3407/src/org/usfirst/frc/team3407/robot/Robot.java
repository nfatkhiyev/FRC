
package org.usfirst.frc.team3407.robot;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    Joystick stick;
	Victor flywheel;
	Counter RPM;
	PIDController shooter;
	Boolean rpmm = false;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        this.stick = new Joystick(0);
        flywheel = new Victor(1);
        flywheel.setInverted(true);
        RPM = new Counter(0);
        RPM.setMaxPeriod(0.01);// amount of time before it is considered not moving.
        RPM.setUpdateWhenEmpty(true);
        RPM.setDistancePerPulse(1);// 1 RPM per count
        RPM.setPIDSourceType(PIDSourceType.kRate);// Get the rate of the counter in order to set to the PID.
        //shooter = new PIDController(0,0,0,0.64,RPM,flywheel);// The first 3 numbers are P,I and D values. then the target RPM the source and the output.
        shooter = new PIDController(.25,1.5,.5,0,RPM,flywheel);
        shooter.setSetpoint(75);
        shooter.setContinuous(true); //set to true when using the potentiometer.
        shooter.startLiveWindowMode();
        shooter.enable();
        LiveWindow.addActuator("Shooter", "PID", shooter);
        }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	//flywheel.set(((-stick.getZ()+1)/2));
    	//flywheel.set(shooter.get());
    	System.out.println(RPM.getRate()*60);
    	
    	System.out.println(flywheel.get());
    	
    	System.out.println(shooter.getError());    	
    	/*double time = System.currentTimeMillis();
    	double counter = 0;
    	Double revCount = 0.0;
    	while (counter <= 100000){
    		double rpm = Encoder.getVoltage();
    	if (rpm > 4.1 && rpmm == false){
    		rpmm = true;
    		revCount ++;
    	}
    	else if (rpm < 0.1 && rpmm == true){
    		rpmm = false;
    	}
    	counter ++;
    	}
    	double endTime = System.currentTimeMillis();
    	double timeEllapsed = endTime - time;
    	double finalRPM = ((revCount/timeEllapsed)*60000);
    	System.out.println(finalRPM);*/
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
        SmartDashboard.putNumber("RPM", RPM.getRate());
        SmartDashboard.putNumber("CIM input", flywheel.get());
        SmartDashboard.putNumber("Error", shooter.getError());
    }
    
}

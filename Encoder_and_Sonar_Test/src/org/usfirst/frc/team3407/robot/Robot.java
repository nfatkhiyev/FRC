package org.usfirst.frc.team3407.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Ultrasonic;
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
	
	Ultrasonic sonar;
	Counter sonarC;
	DigitalOutput sonarO;
	
	Joystick stick;
	Victor tilter;
	AnalogInput position;
	AnalogInput sonar2;
	PIDController tilerPID;

	
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		sonar = new Ultrasonic(2,3);
		sonar.setEnabled(true);
		sonar.setAutomaticMode(true);
		sonarC = new Counter(3);
		sonarC.setSemiPeriodMode(true);
		//sonarO = new DigitalOutput(2);
		
		sonar2 = new AnalogInput(1);//change port to port map values
		
        this.stick = new Joystick(0);
        tilter = new Victor(1); // this needs to change
        tilter.setInverted(true);
        position = new AnalogInput(0);//change port
        position.setPIDSourceType(PIDSourceType.kDisplacement);// Get the rate of the counter in order to set to the PID.
        //shooter = new PIDController(0,0,0,0.64,RPM,flywheel);// The first 3 numbers are P,I and D values. then the target RPM the source and the output.
        tilerPID = new PIDController(.25,1.5,.5,position,tilter);
        tilerPID.setSetpoint(75);
        tilerPID.setInputRange(810, 950);
        tilerPID.setOutputRange(0.1, 0.15);
       // shooter.setContinuous(false);
        tilerPID.startLiveWindowMode();
        tilerPID.enable();
        LiveWindow.addActuator("Shooter", "PID", tilerPID);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//sonarO.pulse(.05);
		System.out.println(sonar.getRangeInches());
		System.out.println(sonar.isRangeValid());
		System.out.println(sonarC.getPeriod());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
        SmartDashboard.putNumber("position", position.getValue());
        SmartDashboard.putNumber("CIM input", tilter.get());
        SmartDashboard.putNumber("Error", tilerPID.getError());
	}
}


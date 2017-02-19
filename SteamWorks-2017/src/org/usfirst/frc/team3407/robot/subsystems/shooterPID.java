package org.usfirst.frc.team3407.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.livewindow.LiveWindow ;
import edu.wpi.first.wpilibj.VictorSP;

/**
 *
 */

public class shooterPID extends PIDSubsystem {
	private VictorSP shooterVictor = new VictorSP(5);
	private Encoder encoder = new Encoder(1, 2);
	
    // Initialize your subsystem here
    public shooterPID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("shooterPID", 0.25, 1.5, 0.5);
    	setSetpoint(0.65);
		
    	setAbsoluteTolerance(0.02);
    	getPIDController().setContinuous(true);
    	LiveWindow.addActuator("Shooter", "Motor", shooterVictor);
    	LiveWindow.addSensor("Shooter", "Encoder", encoder);
    	LiveWindow.addActuator("Shooter", "PID", getPIDController());
    	//PIDController.startLiveWindowMode();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return encoder.pidGet();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	shooterVictor.set(output);  	
    }
}

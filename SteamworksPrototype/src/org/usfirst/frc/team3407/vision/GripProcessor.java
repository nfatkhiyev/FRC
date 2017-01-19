package org.usfirst.frc.team3407.vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class GripProcessor {
	
	public static final int IMG_WIDTH = 400;
	public static final int IMG_HEIGHT = 200;
	
	public static GripListener setup() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
	
		GripListener listener = new GripListener(IMG_WIDTH, IMG_HEIGHT);
		VisionThread visionThread = new VisionThread(camera, new GripPipeline(), listener);
		visionThread.start();  // ??? needed ???
		
		return listener;
	}
}

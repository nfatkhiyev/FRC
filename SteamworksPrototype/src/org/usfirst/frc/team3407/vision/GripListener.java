package org.usfirst.frc.team3407.vision;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.wpilibj.vision.VisionRunner;

public class GripListener implements VisionRunner.Listener<GripPipeline> {

	private int imageWidth;
	private int imageHeight;
	
	private Rect leftTarget;
	private Rect rightTarget;
	
	public GripListener(int imageWidth, int imageHeight) {
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
	}
	
	@Override
	public void copyPipelineOutputs(GripPipeline pipeline) {
		if (!pipeline.filterContoursOutput().isEmpty()) {
            leftTarget = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
            rightTarget = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
		}	
	}	
}

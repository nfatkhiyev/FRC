package org.usfirst.frc.team3407.vision;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

public class PegPipeline extends GripPipeline {

	public PegPipeline(Mat source) {
		super(source);
	}

	@Override
	public Rect getTarget() {
		return null;
	}
	
}

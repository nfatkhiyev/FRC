package org.usfirst.frc.team3407.vision;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

/**
 * GripPipeline for Boiler images.
 *
 */
public class BoilerPipeline extends GripPipeline {

	private static final double HEIGHT_RATIO = 1.25;

	private static final int X_MATCH_THRESHOLD = 5;
	private static final int WIDTH_MATCH_THRESHOLD = 10;
	private static final double HEIGHT_RATIO_MATCH_THRESHOLD = 0.1;

	@Override
	protected double[] getHslThresholdHue() {
		return new double[] { 47.0, 98.0 };
	}

	@Override
	protected double[] getHslThresholdSaturation() {
		return new double[] { 126.0, 255.0 };
	}

	@Override
	protected double[] getHslThresholdLuminance() {
		return new double[] { 19.0, 155.0 };
	}

	@Override
	public Rect getTarget() {

		Rect target = null;

		ArrayList<MatOfPoint> contours = filterContoursOutput();
		int contourCount = contours.size();

		if (contourCount == 2) {
			Rect upper = Imgproc.boundingRect(contours.get(0));
			Rect lower = Imgproc.boundingRect(contours.get(1));
			if (upper.y > lower.y) {
				// Swap
				Rect temp = upper;
				upper = lower;
				lower = temp;
			}
			if (validateTarget(upper, lower)) {
				target = new Rect(upper.x, upper.y, upper.width, lower.height + (lower.y - upper.y));
			}
		}

		return target;
	}

	protected boolean validateTarget(Rect upper, Rect lower) {

		int xDiff = Math.abs(upper.x - lower.x);
		int widthDiff = Math.abs(upper.width - lower.width);
		double heightRatio = ((double) upper.height) / ((double) lower.height);

		double heightRatioDiff = Math.abs(HEIGHT_RATIO - heightRatio);

		System.out.println("validateTarget(): xDiff=" + xDiff + " widthDiff=" + widthDiff + " heightRatio="
				+ heightRatio + " heightRatioDiff=" + heightRatioDiff);

		boolean matches = (xDiff < X_MATCH_THRESHOLD) && (widthDiff < WIDTH_MATCH_THRESHOLD)
				&& (heightRatioDiff < HEIGHT_RATIO_MATCH_THRESHOLD);

		return matches;
	}
}

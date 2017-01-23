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

	private static final double HEIGHT_RATIO = 1.25d;

	private static final int X_MATCH_THRESHOLD = 15;
	private static final int WIDTH_MATCH_THRESHOLD = 15;
	private static final double HEIGHT_RATIO_MATCH_THRESHOLD = 0.25d;

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

		Rect upper = null;
		Rect lower = null;
		for(int i = 0;(i < (contourCount - 1)) && (upper == null);i++) {
			Rect rectI = Imgproc.boundingRect(contours.get(i));
			for(int j = (i + 1);j < contourCount;j++) {
				Rect rectJ = Imgproc.boundingRect(contours.get(j));			
				if (validateTarget(rectI, rectJ)) {
					upper = rectI;
					lower = rectJ;
					break;
				}
				else if (validateTarget(rectJ, rectI)) {
					upper = rectJ;
					lower = rectI;
					break;
				}
			}
		}
			
		if (upper != null) {
			target = new Rect(upper.x, upper.y, upper.width, lower.height + (lower.y - upper.y));
		}

		return target;
	}

	protected boolean validateTarget(Rect upper, Rect lower) {
		
		// Check height
		if(upper.y > lower.y) {
			return false;
		}

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

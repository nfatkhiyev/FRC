package org.usfirst.frc.team3407.vp;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3407.vision.GripPipeline;

public class Tester {
	public static void main(String[] args) {
			    
		String imageFileName = args[0];
		
		System.out.println("Vision Processing Tester");
		System.out.println("Processing: " + imageFileName);

		run(args[0]);
	}
		  
	public static void run(String imageFileName) {

		GripPipeline pipeline = new GripPipeline();

		Mat image = Imgcodecs.imread(imageFileName);
		System.out.println("Input image: width=" + image.width() + " height=" + image.height());
		
		pipeline.processImage(image);
		
		Imgcodecs.imwrite("C:\temp\test_hsl.jpeg", pipeline.hslThresholdOutput());
				
		ArrayList<MatOfPoint> contours = pipeline.filterContoursOutput();
		int contourCount = contours.size();
		System.out.println("Countours: count=" + contourCount);
		
		for(int i = 0;i < contourCount;i++) {
			Rect rect = Imgproc.boundingRect(contours.get(i));
			System.out.println("Bounding Rectange(" + i + ")=" + rect);
		}

	}
}

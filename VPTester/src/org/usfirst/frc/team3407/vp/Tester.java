package org.usfirst.frc.team3407.vp;

import java.io.File;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import org.usfirst.frc.team3407.vision.BoilerPipeline;
import org.usfirst.frc.team3407.vision.GripPipeline;

public class Tester {
	
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	private static final int[][] BOILER_IMAGES = {
			{ 4, 2 },
			{ 10, 4 },
			{ 12, 7 },
			{ 11, 3 }
	};
	
	public static void main(String[] args) {
			    
		String imageFileName = args[0];
		
		System.out.println("Vision Processing Tester");
		System.out.println("Processing: " + imageFileName);

		File file = new File(imageFileName);
		if(file.exists()) {
			if(file.isDirectory()) {
				int angle = (args.length > 1) ? Integer.parseInt(args[1]) : 0;
				int[] distances = BOILER_IMAGES[angle];
				runImages(args[0], angle, distances[0], distances[1]);
			}
			else {
				runImage(args[0]);
			}
		}
		else {
			System.out.println("File or directory does not exist");
		}
	}
		  
	public static void runImages(String imageFilesBaseDir, int angle, 
			int farDistance, int closeDistance) {
		
		for(int d = farDistance;d >= closeDistance;d--) {
			StringBuilder fileName = new StringBuilder();
			fileName.append(imageFilesBaseDir).append("/Angle").append(angle);
			fileName.append("/1ftH").append(d).append("ftD").append(angle);
			fileName.append("Angle0Brightness.jpg");
			
			System.out.println("\nInput: " + fileName);
			System.out.println("Distance=" + d);
			runImage(fileName.toString());
		}		
	}
	
	public static void runImage(String imageFileName) {

		Mat image = Imgcodecs.imread(imageFileName);
		System.out.println("width=" + image.width() + " height=" + image.height());

		GripPipeline pipeline = new BoilerPipeline(image);
		
		pipeline.processImage();
		
		//Imgcodecs.imwrite("C:\temp\test_hsl.jpeg", pipeline.hslThresholdOutput());
				
		ArrayList<MatOfPoint> contours = pipeline.filterContoursOutput();
		int contourCount = contours.size();
		System.out.println("Countours: count=" + contourCount);
		
		for(int i = 0;i < contourCount;i++) {
			Rect rect = Imgproc.boundingRect(contours.get(i));
			System.out.println("Bounding Rectange(" + i + ")=" + rect + " x=" + rect.x + 
					" area=" + (rect.height * rect.width));
		}

		Rect target = pipeline.getTarget();
		if(target == null) {
			System.out.println("No target identified");
		}
		else {
			System.out.println("Target Rectangle: " + target + 
					" targetArea=" + target.area() + 
					" fromCenter=" + pipeline.getTargetPointFromCenter());
		}
	}
}

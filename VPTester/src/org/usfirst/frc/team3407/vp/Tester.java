package org.usfirst.frc.team3407.vp;

import java.io.File;
import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import org.usfirst.frc.team3407.vision.BoilerPipeline;
import org.usfirst.frc.team3407.vision.GripPipeline;

public class Tester {
	public static void main(String[] args) {
			    
		String imageFileName = args[0];
		
		System.out.println("Vision Processing Tester");
		System.out.println("Processing: " + args[0]);

		File file = new File(args[0]);
		if(file.exists()) {
			if(file.isDirectory()) {
				runImages(args[0], 3, 11, 3);
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

		GripPipeline pipeline = new BoilerPipeline();

		Mat image = Imgcodecs.imread(imageFileName);
		System.out.println("width=" + image.width() + " height=" + image.height());
		
		pipeline.processImage(image);
		
		//Imgcodecs.imwrite("C:\temp\test_hsl.jpeg", pipeline.hslThresholdOutput());
				
		ArrayList<MatOfPoint> contours = pipeline.filterContoursOutput();
		int contourCount = contours.size();
		System.out.println("Countours: count=" + contourCount);
		
		for(int i = 0;i < contourCount;i++) {
			Rect rect = Imgproc.boundingRect(contours.get(i));
			System.out.println("Bounding Rectange(" + i + ")=" + rect + " x=" + rect.x + 
					" area=" + (rect.height * rect.width));
		}

		System.out.println("Target Rectange: " + pipeline.getTarget());
	}
}

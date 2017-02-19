package org.usfirst.frc.team3407.networktables;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTableConsole {

	public static void main(String[] args) {

		final String INPUT_KEY = "TestInput";
		final String TABLE_NAME = "AccelerometerTest";
		
		System.out.println("LibraryPath=" + System.getProperties().getProperty("java.library.path"));
		NetworkTable.setClientMode();
	
		// Implicilty sets IP address and 
		NetworkTable.setTeam(3407);
	
		System.out.println("Port=" + PORT);
				
		NetworkTable table = NetworkTable.getTable(TABLE_NAME);
		table.putString(INPUT_KEY, "Hello");
		
		int i = 0;
		while(true) {
			try {
				i++;
				//Object value = table.getNumber(STATUS_KEY, -1234567);
				Object xValue = table.getNumber("Xval", -1234567);
				Object yValue = table.getNumber("Yval", -1234567);
				Object zValue = table.getNumber("Zval", -1234567);
				System.out.println(" x=" + xValue+ " y=" + yValue+ " z=" + zValue);
				
				if((i % 2) == 0) {
					System.out.println("TableContents=" + table);
				}
				Thread.sleep(3000);
			}
			catch (Exception e) {
				System.err.println("ERROR: " + e.getMessage());
			}
			
		}
			
	}
}

package org.usfirst.frc.team3407.networktabbles;

import java.util.logging.Logger;
import java.util.logging.Level;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Data_Collection {
	public static void main(String[] args) {
		new Data_Collection().run();
	}
	public void run() {
		NetworkTable.setClientMode();
		NetworkTable.setTeam(3407);// sets IP address
		NetworkTable data = NetworkTable.getTable("RobotData");
		
		while(true) {
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException ex){
				Logger.getLogger(Data_Collection.class.getName()).log(Level.SEVERE,null,ex);
			}
			Object yaw = data.getNumber("NavXtest", 0.0);
			
			System.out.println("yaw is:" + yaw);
		}
	}
}

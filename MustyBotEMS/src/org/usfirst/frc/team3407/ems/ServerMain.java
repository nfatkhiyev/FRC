package org.usfirst.frc.team3407.ems;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thingworx.communications.client.ClientConfigurator;
import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class ServerMain extends ConnectedThingClient {

	private static final Logger LOG = LoggerFactory.getLogger(ServerMain.class);
	
	private static final String TABLE_NAME = "RobotData";
	
	private static String APP_KEY = "5a788559-ff94-4f2e-8818-70e5072e3107";

	public ServerMain(ClientConfigurator config) throws Exception {
		super(config);
	}

	public static void main(String[] args) {
	
		ClientConfigurator config = new ClientConfigurator();
	
		// Set the URI of the server that we are going to connect to
		config.setUri("ws://localhost:8080/Thingworx/WS");
		
		// Set the ApplicationKey. This will allow the client to authenticate with the server.
		// It will also dictate what the client is authorized to do once connected.
		config.setAppKey(APP_KEY);
		
		// This will allow us to test against a server using a self-signed certificate.
		// This should be removed for production systems.
		config.ignoreSSLErrors(true); // All self signed certs
	
		NetworkTable.setClientMode();
		
		// Implicilty sets IP address and 
		NetworkTable.setTeam(3407);

		try {
			
			// Create our client.
			ServerMain server = new ServerMain(config);
			
			// Start the client. The client will connect to the server and authenticate
			// using the ApplicationKey specified above.
			server.start();
			
			// Wait for the client to connect.
			if (server.waitForConnection(30000)) {
				
				LOG.info("The client is now connected.");
				
				//
				// Create a VirtualThing and bind it to the client
				///////////////////////////////////////////////////////////////
				
				ArrayList<PropertyAccessor> accessors = new ArrayList<PropertyAccessor>();
				accessors.add(new NumberPropertyAccessor("Yaw", 0.345));
				accessors.add(new NumberPropertyAccessor("Solenoid A Counter", -1));
				accessors.add(new NumberPropertyAccessor("Solenoid B Counter", -1));
				accessors.add(new NumberPropertyAccessor("Solenoid X Counter", -1));
				accessors.add(new NumberPropertyAccessor("Solenoid Y Counter", -1));
				NetworkTable table = NetworkTable.getTable(TABLE_NAME);
				LOG.debug("NetworkTable: connected=" + table.isConnected());
				NetworkTableThing tableThing = new NetworkTableThing(TABLE_NAME, TABLE_NAME,
						server, table.isConnected() ? table : new SimulatedTable(1234), accessors); 
				server.bindThing(tableThing);
				
				// This will prevent the main thread from exiting. It will be up to another thread
				// of execution to call client.shutdown(), allowing this main thread to exit.
				while (!server.isShutdown()) {
					
					Thread.sleep(1000);
					
					// Every 1 seconds we tell the thing to process a scan request. This is
					// an opportunity for the thing to query a data source, update property
					// values, and push new property values to the server.
					//
					// This loop demonstrates how to iterate over multiple VirtualThings
					// that have bound to a client. In this simple example the things
					// collection only contains one VirtualThing.
					for (VirtualThing vt : server.getThings().values()) {
						vt.processScanRequest();
					}
				}
				
			} else {
				// Log this as a warning. In production the application could continue
				// to execute, and the client would attempt to reconnect periodically.
				LOG.warn("Client did not connect within 30 seconds. Exiting");
			}
			
		} catch (Exception e) {
			LOG.error("An exception occured during execution.", e);
		}
		
		LOG.info("ServerMain is done. Exiting");
	}
}

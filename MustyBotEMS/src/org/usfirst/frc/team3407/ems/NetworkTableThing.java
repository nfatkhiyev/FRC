package org.usfirst.frc.team3407.ems;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;

import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTableThing extends VirtualThing {

	private static final long serialVersionUID = 1;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NetworkTableThing.class);

	private ITable table;
	private List<PropertyAccessor> accessors;

	public NetworkTableThing(String name, ConnectedThingClient client,
			List<PropertyAccessor> accessors) {
		this(name, name, client, NetworkTable.getTable(name), accessors);
	}

	public NetworkTableThing(String name, String description, ConnectedThingClient client,
			ITable table, List<PropertyAccessor> accessors) {
		super(name, description, client);
		
		this.table = table;
		this.accessors = accessors;
		
		for(Iterator<PropertyAccessor> i = accessors.iterator();i.hasNext();) {
			PropertyAccessor accessor = i.next();
			defineProperty(accessor.getDefinition());
		}

	}
	
	/**
	 * This method provides a common interface amongst VirtualThings for processing
	 * periodic requests. It is an opportunity to access data sources, update 
	 * property values, push new values to the server, and take other actions.
	 */
	@Override
	public void processScanRequest() throws Exception {
		
		for(Iterator<PropertyAccessor> i = accessors.iterator();i.hasNext();) {
			PropertyAccessor accessor = i.next();
			accessor.updateProperty(table, this);
		}
		
		LOGGER.info("processScanRequest() updating all properties");
		
		updateSubscribedProperties(3000);
	}


}

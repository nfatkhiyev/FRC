package org.usfirst.frc.team3407.ems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thingworx.communications.client.things.VirtualThing;
import com.thingworx.types.BaseTypes;
import com.thingworx.types.primitives.NumberPrimitive;

import edu.wpi.first.wpilibj.tables.ITable;

public class NumberPropertyAccessor extends PropertyAccessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(NumberPropertyAccessor.class);

	private double defaultValue;
	
	public NumberPropertyAccessor(String name, double defaultValue) {
		this(name, name, defaultValue);
	}
	
	public NumberPropertyAccessor(String name, String description,
			double defaultValue) {
		super(name, description);
		this.defaultValue = defaultValue;
	}

	@Override
	public BaseTypes getType() {
		return BaseTypes.NUMBER;
	}
	
	public void updateProperty(ITable table, VirtualThing thing) 
			throws Exception {

		final String name = getName();
		
		Double value = table.getNumber(name, defaultValue);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.debug("updateProperty() name=" + name + " value=" + value);
		}

		thing.setPropertyValue(name, new NumberPrimitive(value));		
	}
}

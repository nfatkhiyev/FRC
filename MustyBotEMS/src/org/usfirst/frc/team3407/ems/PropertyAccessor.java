package org.usfirst.frc.team3407.ems;

import com.thingworx.communications.client.things.VirtualThing;
import com.thingworx.metadata.PropertyDefinition;
import com.thingworx.types.BaseTypes;
import com.thingworx.types.collections.AspectCollection; 
import com.thingworx.types.constants.Aspects;
import com.thingworx.types.constants.DataChangeType;
import com.thingworx.types.primitives.BooleanPrimitive;
import com.thingworx.types.primitives.IntegerPrimitive;
import com.thingworx.types.primitives.NumberPrimitive;
import com.thingworx.types.primitives.StringPrimitive;

import edu.wpi.first.wpilibj.tables.ITable;

public abstract class PropertyAccessor {
	
	private String name;
	private String description;
	
	public PropertyAccessor(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public abstract BaseTypes getType();
	public abstract void updateProperty(ITable table, VirtualThing thing) 
			throws Exception;

	PropertyDefinition getDefinition() {
		PropertyDefinition propertyDef = new PropertyDefinition(getName(), 
				getDescription(), getType());
		
		//Create an aspect collection to hold all of the different aspects 
		AspectCollection aspects = new AspectCollection();
		
		//Add the dataChangeType aspect 
		aspects.put(Aspects.ASPECT_DATACHANGETYPE, new StringPrimitive(DataChangeType.ALWAYS.name()));
		
		//Add the dataChangeThreshold aspect 
		aspects.put(Aspects.ASPECT_DATACHANGETHRESHOLD, new NumberPrimitive(0.0));
		
		//Add the cacheTime aspect 
		aspects.put(Aspects.ASPECT_CACHETIME, new IntegerPrimitive(-1));
		
		//Add the isPersistent aspect
		aspects.put(Aspects.ASPECT_ISPERSISTENT, new BooleanPrimitive(false));
		
		//Add the isReadOnly aspect 
		aspects.put(Aspects.ASPECT_ISREADONLY, new BooleanPrimitive(true));
		
		//Add the pushType aspect 
		aspects.put("pushType", new StringPrimitive(DataChangeType.ALWAYS.name()));
		
		//Add the defaultValue aspect 
		//aspects.put(Aspects.ASPECT_DEFAULTVALUE, new BooleanPrimitive(true));
		
		//Set the aspects of the property definition 
		propertyDef.setAspects(aspects);
		
		return propertyDef;
	}

	
}

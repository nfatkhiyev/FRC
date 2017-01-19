package org.usfirst.frc.team3407.ems;

import java.nio.ByteBuffer;
import java.util.Random;
import java.util.Set;

import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class SimulatedTable implements ITable {

	private static final double NUMBER_BASE = -50d;
	private static final double NUMBER_RANGE = 100;
	
	private Random random;
	
	public SimulatedTable(long seed) {
		random = new Random(seed);
	}
	
	@Override
	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsSubTable(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ITable getSubTable(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getKeys(int types) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getSubTables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPersistent(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearPersistent(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPersistent(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFlags(String key, int flags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearFlags(String key, int flags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFlags(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getValue(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(String key, Object defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putValue(String key, Object value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void retrieveValue(String key, Object externalValue) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean putNumber(String key, double value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getNumber(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getNumber(String key, double defaultValue) {
		
		return (random.nextDouble() * NUMBER_RANGE) + NUMBER_BASE;
	}

	@Override
	public boolean putString(String key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getString(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String key, String defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putBoolean(String key, boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getBoolean(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getBoolean(String key, boolean defaultValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean putBooleanArray(String key, boolean[] value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean putBooleanArray(String key, Boolean[] value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] getBooleanArray(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean[] getBooleanArray(String key, boolean[] defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean[] getBooleanArray(String key, Boolean[] defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putNumberArray(String key, double[] value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean putNumberArray(String key, Double[] value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double[] getNumberArray(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getNumberArray(String key, double[] defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double[] getNumberArray(String key, Double[] defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putStringArray(String key, String[] value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getStringArray(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getStringArray(String key, String[] defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putRaw(String key, byte[] value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean putRaw(String key, ByteBuffer value, int len) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte[] getRaw(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getRaw(String key, byte[] defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTableListener(ITableListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTableListener(ITableListener listener, boolean immediateNotify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTableListenerEx(ITableListener listener, int flags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTableListener(String key, ITableListener listener, boolean immediateNotify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTableListenerEx(String key, ITableListener listener, int flags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSubTableListener(ITableListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSubTableListener(ITableListener listener, boolean localNotify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableListener(ITableListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean putInt(String key, int value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInt(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInt(String key, int defaultValue) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean putDouble(String key, double value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getDouble(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDouble(String key, double defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}

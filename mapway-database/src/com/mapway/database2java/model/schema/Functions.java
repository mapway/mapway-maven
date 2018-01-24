package com.mapway.database2java.model.schema;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Functions.
 */
public class Functions {
	
	/** The Functions. */
	ArrayList<Function> Functions;
	
	/**
	 * Instantiates a new functions.
	 */
	public Functions(){
		Functions=new ArrayList<Function>(); 
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount()
	{
		return Functions.size();
	}
	
	/**
	 * Gets the at.
	 *
	 * @param index the index
	 * @return the at
	 */
	public Function getAt(int index){
		return Functions.get(index);
	}
	
	/**
	 * Clear.
	 */
	public void Clear(){
		Functions.clear();
	}
	
	/**
	 * Adds the function.
	 *
	 * @param func the func
	 */
	public void addFunction(Function func)
	{
		Functions.add(func);
	}
}

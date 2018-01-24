package com.mapway.database2java.model.schema;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Packages.
 */
public class Packages {
	
	/** The Pack. */
	ArrayList<Pack> Pack;
	
	/**
	 * Instantiates a new packages.
	 */
	public Packages(){
		Pack=new ArrayList<Pack>(); 
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount()
	{
		return Pack.size();
	}
	
	/**
	 * Gets the at.
	 *
	 * @param index the index
	 * @return the at
	 */
	public Pack getAt(int index){
		return Pack.get(index);
	}
	
	/**
	 * Clear.
	 */
	public void Clear(){
		Pack.clear();
	}
	
	/**
	 * Adds the pack.
	 *
	 * @param pack the pack
	 */
	public void addPack(Pack pack)
	{
		Pack.add(pack);
	}
}

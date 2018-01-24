package com.mapway.database2java.model.schema;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Arguments.
 */
public class Arguments {
	
	/** The m args. */
	ArrayList<Argument> m_args;
	
	/**
	 * Instantiates a new arguments.
	 */
	public Arguments()
	{
		m_args=new ArrayList<Argument>(); 
	}
	
	/**
	 * Clear.
	 */
	public void Clear()
	{
		m_args.clear();
	}
	
	/**
	 * Adds the arguemnt.
	 *
	 * @param a the a
	 */
	public void AddArguemnt(Argument a)
	{
		m_args.add(a);
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount()
	{
		return m_args.size();
	}
	
	/**
	 * Gets the at.
	 *
	 * @param index the index
	 * @return the at
	 */
	public Argument getAt(int index)
	{
		return m_args.get(index);
	}
}

package com.mapway.database2java.model.schema;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Procedures.
 */
public class Procedures {
	
	/** The Procedures. */
	ArrayList<Procedure> Procedures;
	
	/**
	 * Instantiates a new procedures.
	 */
	public Procedures(){
		Procedures=new ArrayList<Procedure>(); 
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount()
	{
		return Procedures.size();
	}
	
	/**
	 * Gets the at.
	 *
	 * @param index the index
	 * @return the at
	 */
	public Procedure getAt(int index){
		return Procedures.get(index);
	}
	
	/**
	 * Clear.
	 */
	public void Clear(){
		Procedures.clear();
	}
	
	/**
	 * Adds the procedure.
	 *
	 * @param proc the proc
	 */
	public void addProcedure(Procedure proc)
	{
		Procedures.add(proc);
	}
}

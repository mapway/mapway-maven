package com.mapway.database2java.model.schema;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Tables.
 */
public class Tables {
	
	/** The Tables. */
	ArrayList<Table> Tables;
	
	/**
	 * Instantiates a new tables.
	 */
	public Tables(){
		Tables=new ArrayList<Table>(); 
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount()
	{
		return Tables.size();
	}
	
	/**
	 * Gets the at.
	 *
	 * @param index the index
	 * @return the at
	 */
	public ITable getAt(int index){
		return Tables.get(index);
	}
	
	/**
	 * Clear.
	 */
	public void Clear(){
		Tables.clear();
	}
	
	/**
	 * Adds the table.
	 *
	 * @param table the table
	 */
	public void addTable(Table table)
	{
		Tables.add(table);
	}
}

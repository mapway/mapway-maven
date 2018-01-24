package com.mapway.database2java.model.schema;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Columns.
 */
public class Columns {
	
	/** The Columns. */
	ArrayList<Column> Columns;
	
	/**
	 * Instantiates a new columns.
	 */
	public Columns(){
		Columns=new ArrayList<Column>(); 
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount()
	{
		return Columns.size();
	}
	
	/**
	 * Gets the at.
	 *
	 * @param index the index
	 * @return the at
	 */
	public Column getAt(int index){
		return Columns.get(index);
	}
	
	/**
	 * Clear.
	 */
	public void Clear(){
		Columns.clear();
	}
	
	/**
	 * Adds the column.
	 *
	 * @param column the column
	 */
	public void addColumn(Column column)
	{
		Columns.add(column);
	}
}

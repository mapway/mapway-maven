package com.mapway.database2java.model.schema;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Views.
 */
public class Views {
	
	/** The Views. */
	ArrayList<View> Views;
	
	/**
	 * Instantiates a new views.
	 */
	public Views(){
		Views=new ArrayList<View>(); 
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount()
	{
		return Views.size();
	}
	
	/**
	 * Gets the at.
	 *
	 * @param index the index
	 * @return the at
	 */
	public View getAt(int index){
		return Views.get(index);
	}
	
	/**
	 * Clear.
	 */
	public void Clear(){
		Views.clear();
	}
	
	/**
	 * Adds the view.
	 *
	 * @param view the view
	 */
	public void addView(View view)
	{
		Views.add(view);
	}
	
}

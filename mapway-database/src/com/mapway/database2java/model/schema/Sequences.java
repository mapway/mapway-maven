package com.mapway.database2java.model.schema;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Sequences.
 */
public class Sequences {
	
	/** The Sequences. */
	ArrayList<Sequence> Sequences;
	
	/**
	 * Instantiates a new sequences.
	 */
	public Sequences(){
		Sequences=new ArrayList<Sequence>(); 
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount()
	{
		return Sequences.size();
	}
	
	/**
	 * Gets the at.
	 *
	 * @param index the index
	 * @return the at
	 */
	public Sequence getAt(int index){
		return Sequences.get(index);
	}
	
	/**
	 * Clear.
	 */
	public void Clear(){
		Sequences.clear();
	}
	
	/**
	 * Adds the sequence.
	 *
	 * @param sequence the sequence
	 */
	public void addSequence(Sequence sequence)
	{
		Sequences.add(sequence);
	}
}

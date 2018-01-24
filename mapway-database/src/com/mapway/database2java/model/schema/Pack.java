package com.mapway.database2java.model.schema;

// TODO: Auto-generated Javadoc
/**
 * The Class Pack.
 */
public class Pack {
	
	/** The name. */
	String name;
	
	/** The procedures. */
	Procedures procedures;
	
	/**
	 * Instantiates a new pack.
	 */
	public Pack()
	{
		procedures=new Procedures();
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the procedures.
	 *
	 * @return the procedures
	 */
	public Procedures getProcedures() {
		return procedures;
	}
	
	/**
	 * Sets the procedures.
	 *
	 * @param procedures the new procedures
	 */
	public void setProcedures(Procedures procedures) {
		this.procedures = procedures;
	}
}

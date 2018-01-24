package com.mapway.database2java.model.schema;

// TODO: Auto-generated Javadoc
/**
 * The Interface ITable.
 */
public interface ITable {

	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public abstract Columns getColumns();

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public abstract String getComment();

	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public abstract void setComment(String comment);

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public abstract String getName();

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public abstract void setName(String name);

	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public abstract String getOwner();

	/**
	 * Sets the owner.
	 *
	 * @param owner the new owner
	 */
	public abstract void setOwner(String owner);

	/**
	 * Gets the java name.
	 *
	 * @return the java name
	 */
	public abstract String getJavaName();

	/**
	 * Gets the java names.
	 *
	 * @return the java names
	 */
	public abstract String getJavaNames();

	/**
	 * Gets the java access name.
	 *
	 * @return the java access name
	 */
	public abstract String getJavaAccessName();

	/**
	 * Checks for PK.
	 *
	 * @return true, if successful
	 */
	public abstract boolean hasPK();

	/**
	 * Checks for auto.
	 *
	 * @return true, if successful
	 */
	public abstract boolean hasAuto();

}
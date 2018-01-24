
package com.mapway.database2java.database;


import java.sql.Connection;


// TODO: Auto-generated Javadoc
/**
 * The Interface IConnectionPool.
 */
public interface IConnectionPool {
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection();
	
	/**
	 * Release connection.
	 *
	 * @param con the con
	 */
	public void releaseConnection(Connection con);
	
	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath();
	
	/**
	 * Gets the net path.
	 *
	 * @return the net path
	 */
	public String getNetPath();
	
	/**
	 * Gets the package.
	 *
	 * @return the package
	 */
	public String getPackage();
	
	/**
	 * Gets the gwtbase.
	 *
	 * @return the gwtbase
	 */
	public String getGwtbase();
}

package com.mapway.database2java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneratorPool.
 */
public class GeneratorPool implements IConnectionPool
{

    /**
     * Gets the single instance of GeneratorPool.
     *
     * @return single instance of GeneratorPool
     */
    public GeneratorPool getInstance()
    {
	return m_pool;
	// TODO Auto-generated constructor stub
    }

    /** The m pool. */
    private static GeneratorPool m_pool;

  
    /**
     * Instantiates a new generator pool.
     *
     * @param drivername the drivername
     * @param jdbcurl the jdbcurl
     * @param username the username
     * @param password the password
     * @param packagename the packagename
     * @param path the path
     * @param connectionSize the connection size
     */
    public GeneratorPool(String drivername, String jdbcurl, String username,
	    String password, String packagename, String path, int connectionSize)
    {
	this.driverName = drivername;
	this.jdbcURL = jdbcurl;
	this.username = username;
	this.passwd = password;
	this.pack = packagename;
	this.path = path;
	this.maxConnections = connectionSize;

	try
	{
	    Class.forName(driverName);
	} catch (ClassNotFoundException e)
	{
	    e.printStackTrace();
	}
	m_pool = this;
    }

    /** The checked out. */
    private int checkedOut;
    
    /** The free connections. */
    private Vector freeConnections = new Vector();
    
    /** The driver name. */
    private String driverName;
    
    /** The passwd. */
    private String passwd;
    
    /** The username. */
    private String username;
    
    /** The jdbc URL. */
    private String jdbcURL;
    
    /** The database. */
    private String database;
    
    /** The max connections. */
    private int maxConnections;
    
    /** The pack. */
    private String pack;
    
    /** The path. */
    private String path;

    /**
     * Log.
     *
     * @param msg the msg
     */
    public void log(String msg)
    {
	System.out.println(msg);
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.database.IConnectionPool#getConnection()
     */
    @Override
    public Connection getConnection()
    {
	Connection con = null;
	if (freeConnections.size() > 0)
	{
	   
	    con = (Connection) freeConnections.firstElement();
	    freeConnections.removeElementAt(0);
	    try
	    {
		if (con.isClosed())
		{
		   
		    con = getConnection();
		}
	    } catch (SQLException e)
	    {
		log("�����ӳ�ɾ��һ����Ч����");
		
		con = getConnection();
	    }
	} else if (this.maxConnections == 0
		|| this.checkedOut < this.maxConnections)
	{
	    con = createConnection();
	}
	if (con != null)
	{
	    checkedOut++;
	}
	return con;
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.database.IConnectionPool#releaseConnection(java.sql.Connection)
     */
    @Override
    public synchronized void releaseConnection(Connection con)
    {
	freeConnections.addElement(con);
	checkedOut--;
	notifyAll();
    }


    /**
     * Gets the connection.
     *
     * @param timeout the timeout
     * @return the connection
     */
    public synchronized Connection getConnection(long timeout)
    {
	long startTime = new java.util.Date().getTime();
	Connection con;
	while ((con = getConnection()) == null)
	{
	    try
	    {
		wait(timeout);
	    } catch (InterruptedException e)
	    {
	    }
	    if ((new java.util.Date().getTime() - startTime) >= timeout)
	    {
		// wait()���ص�ԭ���ǳ�ʱ
		return null;
	    }
	}
	return con;
    }

    /**
     * Close.
     */
    public synchronized void close()
    {
	Enumeration allConnections = freeConnections.elements();
	while (allConnections.hasMoreElements())
	{
	    Connection con = (Connection) allConnections.nextElement();
	    try
	    {
		con.close();
	    } catch (SQLException e)
	    {
		
	    }
	}
	freeConnections.removeAllElements();
    }


    /**
     * Creates the connection.
     *
     * @return the connection
     */
    private Connection createConnection()
    {
	Connection con = null;
	try
	{
	    if (this.username == null)
	    {
		con = DriverManager.getConnection(this.jdbcURL);
	    } else
	    {
		con = DriverManager.getConnection(this.jdbcURL, this.username,
			this.passwd);
	    }
	    log("" + this.jdbcURL + ":" + this.username + ":"
		    + this.passwd);
	} catch (SQLException e)
	{
	    log(" " + this.jdbcURL);
	    log("" + e.getMessage());
	    return null;
	}
	return con;
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.database.IConnectionPool#getPath()
     */
    @Override
    public String getPath()
    {
	return path;
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.database.IConnectionPool#getNetPath()
     */
    @Override
    public String getNetPath()
    {
	// TODO Auto-generated method stub
	return null;
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.database.IConnectionPool#getPackage()
     */
    @Override
    public String getPackage()
    {
	return pack;
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.database.IConnectionPool#getGwtbase()
     */
    @Override
    public String getGwtbase()
    {
	// TODO Auto-generated method stub
	return pack;
    }

}

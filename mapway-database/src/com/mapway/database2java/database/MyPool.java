package com.mapway.database2java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * 数据库线程池.
 *
 * @author zhangjianshe@gmail.com
 */
public class MyPool implements IConnectionPool
{
    
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

    /**
     * Gets the gwt base.
     *
     * @return the gwt base
     */
    public String getGwt_base()
    {
	return gwt_base;
    }

    /**
     * Sets the gwt base.
     *
     * @param gwt_base the new gwt base
     */
    public void setGwt_base(String gwt_base)
    {
	this.gwt_base = gwt_base;
    }

   
    /** The m pool oracle. */
    private static MyPool m_poolOracle;
    
    /** The m pool my SQL. */
    private static MyPool m_poolMySQL;
    
    /** The path. */
    private String path;
    
    /** The pack. */
    private String pack;
    
    /** The gwt modle. */
    private String gwt_modle;
    
    /** The gwt dao. */
    private String gwt_dao;
    
    /** The gwt base. */
    private String gwt_base;

    /**
     * Gets the single instance of MyPool.
     *
     * @param database the database
     * @return single instance of MyPool
     */
    public static synchronized MyPool getInstance(String database)
    {
	return getInstance(database, "database");
    }

    /**
     * Gets the single instance of MyPool.
     *
     * @return single instance of MyPool
     */
    public static synchronized MyPool getInstance()
    {
	return m_poolMySQL;
    }

    /**
     * Gets the single instance of MyPool.
     *
     * @param database the database
     * @param configurefile the configurefile
     * @return single instance of MyPool
     */
    public static synchronized MyPool getInstance(String database,
	    String configurefile)
    {
	if (database == null || database.equals(""))
	{
	    database = "Oracle";
	}
	if (database.equals("Oracle"))
	{
	    if (m_poolOracle == null)
	    {
		m_poolOracle = new MyPool("Oracle", configurefile);
	    }
	    return m_poolOracle;
	} else if (database.equals("MySQL"))
	{
	    if (m_poolMySQL == null)
	    {
		m_poolMySQL = new MyPool("MySQL", configurefile);
	    }
	} else if (database.equals("MySQL2"))
	{
	    if (m_poolMySQL == null)
	    {
		m_poolMySQL = new MyPool("MySQL2", configurefile);
	    }
	}
	return m_poolMySQL;

    }

    /**
     * �����̳߳�.
     *
     * @param drivername            ��ݿ������
     * @param jdbcurl            ��ݿ�����URL
     * @param username            ��ݿ��û���
     * @param password            ��ݿ�����
     * @param packagename            package���
     * @param path            ��ɴ���·��
     * @param connectionSize            ����ʱ��С
     */
    public MyPool(String drivername, String jdbcurl, String username,
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
	m_poolMySQL = this;
    }

    /** The dt mysql. */
    public final String DT_MYSQL = "MySQL";
    
    /** The dt oracle. */
    public final String DT_ORACLE = "Oracle";

    /**
     * 根据配置文件.
     *
     * @param databaseType            数据库类型，可用的参数类型有 MySQL ，Oracle
     * @param rb            数据库配置参数属性文件
     */
    public MyPool(String databaseType, ResourceBundle rb)
    {
	init(databaseType, rb);
    }

    /**
     * Instantiates a new my pool.
     *
     * @param database the database
     * @param configurefile the configurefile
     */
    public MyPool(String database, String configurefile)
    {
	if (database == null)
	{
	    database = "Oracle";
	}
	Locale locale = Locale.getDefault();
	ResourceBundle rb = ResourceBundle.getBundle(configurefile, locale);
	init(database, rb);
    }

    /**
     * Inits the.
     *
     * @param databaseType the database type
     * @param rb the rb
     */
    private void init(String databaseType, ResourceBundle rb)
    {
	this.driverName = rb.getString(database + "_driver");
	this.jdbcURL = rb.getString(database + "_dataurl");
	this.username = rb.getString(database + "_user");
	this.passwd = rb.getString(database + "_password");
	this.pack = rb.getString(database + "_package");
	this.path = rb.getString(database + "_path");
	this.database = rb.getString(database + "_database");
	this.netPath = rb.getString(database + "_netpath");

	this.gwt_modle = rb.getString("gwt_modle");
	this.gwt_dao = rb.getString("gwt_dao");
	this.gwt_base = rb.getString("gwt_base");

	this.maxConnections = Integer.valueOf(rb.getString(database
		+ "_maxconnections"));
	try
	{
	    Class.forName(driverName);
	} catch (ClassNotFoundException e)
	{
	    e.printStackTrace();
	}
    }

    /**
     * Log.
     *
     * @param msg the msg
     */
    public void log(String msg)
    {
	System.out.println(msg);
    }

    /**
     * ������ʹ�õ����ӷ��ظ����ӳ�.
     *
     * @param con            �ͻ������ͷŵ�����
     */
    @SuppressWarnings("unchecked")
    public synchronized void releaseConnection(Connection con)
    {
	// ��ָ�����Ӽ��뵽����ĩβ
	freeConnections.addElement(con);
	checkedOut--;
	notifyAll();
    }

    /**
     * �����ӳػ��һ����������.��û�п��е������ҵ�ǰ������С���������
     * ������,�򴴽�������.��ԭ���Ǽ�Ϊ���õ����Ӳ�����Ч,�������ɾ��֮,
     * Ȼ��ݹ�����Լ��Գ����µĿ�������.
     *
     * @return the connection
     */
    public synchronized Connection getConnection()
    {
	Connection con = null;
	if (freeConnections.size() > 0)
	{
	    // ��ȡ�����е�һ����������
	    con = (Connection) freeConnections.firstElement();
	    freeConnections.removeElementAt(0);
	    try
	    {
		if (con.isClosed())
		{
		    log("�����ӳ�ɾ��һ����Ч����");
		    // �ݹ�����Լ�,�����ٴλ�ȡ��������
		    con = getConnection();
		}
	    } catch (SQLException e)
	    {
		log("�����ӳ�ɾ��һ����Ч����");
		// �ݹ�����Լ�,�����ٴλ�ȡ��������
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

    /**
     * �����ӳػ�ȡ��������.����ָ���ͻ������ܹ��ȴ���ʱ�� �μ�ǰһ��getConnection()����.
     *
     * @param timeout            �Ժ���Ƶĵȴ�ʱ������
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
     * �ر���������.
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
		log("�ر����ӳ��е�һ������");
	    } catch (SQLException e)
	    {
		log("�޷��ر����ӳ��е�����");
	    }
	}
	freeConnections.removeAllElements();
    }

    /**
     * �����µ�����.
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
		con = DriverManager.getConnection(this.jdbcURL.replace(
			"$database", this.database));
	    } else
	    {
		con = DriverManager.getConnection(
			this.jdbcURL.replace("$database", this.database),
			this.username, this.passwd);
	    }
	    log("���ӳش���һ���µ�����@"
		    + this.jdbcURL.replace("$database", this.database) + ":"
		    + this.username + ":" + this.passwd);
	} catch (SQLException e)
	{
	    log("�޷���������URL������: "
		    + this.jdbcURL.replace("$database", this.database));
	    log("ԭ�� " + e.getMessage());
	    return null;
	}
	return con;
    }

    /**
     * Gets the driver name.
     *
     * @return the driverName
     */
    public String getDriverName()
    {
	return driverName;
    }

    /**
     * Sets the driver name.
     *
     * @param driverName            the driverName to set
     */
    public void setDriverName(String driverName)
    {
	this.driverName = driverName;
    }

    /**
     * Gets the jdbc URL.
     *
     * @return the jdbcURL
     */
    public String getJdbcURL()
    {
	return jdbcURL;
    }

    /**
     * Sets the jdbc URL.
     *
     * @param jdbcURL            the jdbcURL to set
     */
    public void setJdbcURL(String jdbcURL)
    {
	this.jdbcURL = jdbcURL;
    }

    /**
     * Gets the passwd.
     *
     * @return the passwd
     */
    public String getPasswd()
    {
	return passwd;
    }

    /**
     * Sets the passwd.
     *
     * @param passwd            the passwd to set
     */
    public void setPasswd(String passwd)
    {
	this.passwd = passwd;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername()
    {
	return username;
    }

    /**
     * Sets the username.
     *
     * @param username            the username to set
     */
    public void setUsername(String username)
    {
	this.username = username;
    }

    /**
     * Instantiates a new my pool.
     *
     * @param driverName the driver name
     * @param passwd the passwd
     * @param username the username
     * @param jdbcURL the jdbc URL
     */
    public MyPool(String driverName, String passwd, String username,
	    String jdbcURL)
    {
	super();
	this.driverName = driverName;
	this.passwd = passwd;
	this.username = username;
	this.jdbcURL = jdbcURL;
    }

    /**
     * Gets the max connections.
     *
     * @return the maxConnections
     */
    public int getMaxConnections()
    {
	return maxConnections;
    }

    /**
     * Sets the max connections.
     *
     * @param maxConnections            the maxConnections to set
     */
    public void setMaxConnections(int maxConnections)
    {
	this.maxConnections = maxConnections;
    }

    /**
     * Sets the package.
     *
     * @param pack the new package
     */
    public void setPackage(String pack)
    {
	this.pack = pack;
    }

    /** The net path. */
    String netPath;

    /* (non-Javadoc)
     * @see com.mapway.database2java.database.IConnectionPool#getNetPath()
     */
    public String getNetPath()
    {
	return netPath;
    }

    /**
     * Sets the net path.
     *
     * @param netPath the new net path
     */
    public void setNetPath(String netPath)
    {
	this.netPath = netPath;
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.database.IConnectionPool#getPath()
     */
    public String getPath()
    {
	return path;
    }

    /**
     * Sets the path.
     *
     * @param path the new path
     */
    public void setPath(String path)
    {
	this.path = path;
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.database.IConnectionPool#getPackage()
     */
    public String getPackage()
    {
	return this.pack;
    }

    /**
     * Gets the database.
     *
     * @return the database
     */
    public String getDatabase()
    {
	return database;
    }

    /**
     * Sets the database.
     *
     * @param database the new database
     */
    public void setDatabase(String database)
    {
	this.database = database;
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.database.IConnectionPool#getGwtbase()
     */
    public String getGwtbase()
    {
	return this.gwt_base;
    }
}

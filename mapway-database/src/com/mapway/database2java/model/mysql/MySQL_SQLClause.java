package com.mapway.database2java.model.mysql;

import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;

import com.mapway.database2java.database.AccessBase;
import com.mapway.database2java.database.MyPool;
import com.mapway.database2java.model.itf.ISQLClause;
import com.mapway.database2java.model.oracle.Oracle_SQLClause;

// TODO: Auto-generated Javadoc
/**
 * The Class MySQL_SQLClause.
 */
public class MySQL_SQLClause implements ISQLClause {

    /** The m database. */
    String m_database = "";

   
    /**
     * Instantiates a new my SQ L SQL clause.
     *
     * @param database the database
     */
    public MySQL_SQLClause(String database) {
	m_database = database;
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.model.itf.ISQLClause#getTableSQL()
     */
    public String getTableSQL()
    {

	String sql = "select b.TABLE_COMMENT COMMENTS,b.TABLE_NAME TABLE_NAME,a.COLUMN_NAME COLUMN_NAME,a.DATA_TYPE DATATYPE,a.CHARACTER_MAXIMUM_LENGTH DATA_LENGTH,a.ORDINAL_POSITION COLUMN_ID,a.IS_NULLABLE NULLABLE,a.COLUMN_COMMENT,a.EXTRA from information_schema.columns a ,\r\n"
		+ "(select * from information_schema.tables where table_schema=\'"
		+ m_database
		+ "\') b where\r\n"
		+ " a.TABLE_NAME=b.TABLE_NAME and a.table_schema='"+m_database+"' order by TABLE_NAME,COLUMN_ID;";

	return sql;
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args)
    {
	MySQL_SQLClause app = new MySQL_SQLClause("youeryuan");
	MyPool pool = MyPool.getInstance("MySQL");
	AccessBase a = new AccessBase(pool);
	try
	{
	    Result r = a.execute(app.getTableSQL());
	    a.log(a.resultToString(r));
	} catch (SQLException e)
	{
	    e.printStackTrace();
	}
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.model.itf.ISQLClause#getPKSQL()
     */
    public String getPKSQL()
    {
	String sql = "select  TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME from information_schema.KEY_COLUMN_USAGE where TABLE_SCHEMA=\'"
		+ m_database + "\'";
	return sql;
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.model.itf.ISQLClause#getProcedureSQL()
     */
    public String getProcedureSQL()
    {

	return null;
    }

    /* (non-Javadoc)
     * @see com.mapway.database2java.model.itf.ISQLClause#getSequence_SQL()
     */
    public String getSequence_SQL()
    {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * COMMENTS TABLE_NAME COLUMN_NAME DATA_TYPE DATA_LENGTH COLUMN_ID NULLABLE
     * COMMENTS.
     *
     * @return the view SQL
     */
    public String getViewSQL()
    {

	String sql = "select  'View Comments' ,b.TABLE_NAME TABLE_NAME,a.COLUMN_NAME COLUMN_NAME,a.DATA_TYPE DATATYPE,a.CHARACTER_MAXIMUM_LENGTH DATA_LENGTH,a.ORDINAL_POSITION COLUMN_ID,a.IS_NULLABLE NULLABLE,a.COLUMN_COMMENT,a.EXTRA from information_schema.columns a ,\r\n"
		+ "(select * from information_schema.views where table_schema=\'"
		+ m_database
		+ "\') b where\r\n"
		+ "a.table_schema=\'"+m_database+"\' and a.TABLE_NAME=b.TABLE_NAME order by TABLE_NAME;";

	return sql;

    }
}

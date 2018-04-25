package com.mapway.database2java.model.base;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.jsp.jstl.sql.Result;


import com.squareup.javawriter.JavaWriter;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

import com.mapway.database2java.database.AccessBase;
import com.mapway.database2java.database.IConnectionPool;
import com.mapway.database2java.model.itf.ISQLClause;
import com.mapway.database2java.model.itf.ISchema;
import com.mapway.database2java.model.schema.Column;
import com.mapway.database2java.model.schema.ITable;
import com.mapway.database2java.model.schema.Packages;
import com.mapway.database2java.model.schema.Sequences;
import com.mapway.database2java.model.schema.Tables;
import com.mapway.database2java.model.schema.View;
import com.mapway.database2java.model.schema.Views;
import org.nutz.lang.stream.StringOutputStream;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

// TODO: Auto-generated Javadoc

/**
 * The Class SchemaBase.
 */
public class SchemaBase implements ISchema {

    /**
     * The Field style camel.
     */
    public final static String FIELD_STYLE_CAMEL = "CAMEL";
    /**
     * The Field style lower case.
     */
    public final static String FIELD_STYLE_LOWER_CASE = "LOWER_CASE";
    /**
     * The Field style upper case.
     */
    public final static String FIELD_STYLE_UPPER_CASE = "UPPER_CASE";

    /**
     * The a.
     */
    public AccessBase a = null;

    /**
     * The tables.
     */
    Tables tables = null;

    /**
     * The sequences.
     */
    Sequences sequences = null;

    /**
     * The packages.
     */
    Packages packages = null;

    /**
     * The views.
     */
    Views views = null;

    /**
     * The sql clause.
     */
    ISQLClause sqlClause = null;

    /**
     * The m configure.
     */
    Configure m_configure = null;

    /**
     * The pks.
     */
    Object[][] pks = null;

    /**
     * Instantiates a new schema base.
     *
     * @param pool      the pool
     * @param configure the configure
     */
    public SchemaBase(IConnectionPool pool, Configure configure) {
        a = new AccessBase(pool);
        tables = new Tables();
        sequences = new Sequences();
        packages = new Packages();
        views = new Views();
        m_configure = configure;
        pks = new Object[0][0];
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.itf.ISchema#fetchSchema()
     */
    @Override
    public boolean fetchSchema() {
        Result r;
        try {
            r = a.execute(this.getSQLClause().getPKSQL());
            pks = r.getRowsByIndex();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * The copyright.
     */
    String copyright = "\r\n<pre>\r\n\r\n"
            + "           =============================================================\r\n"
            + "           -   ____ _  _ ____ _  _ ____  _ _ ____ _  _ ____ _  _ ____  -\r\n"
            + "           -    __] |__| |__| |\\ | | __  | | |__| |\\ | [__  |__| |___  -\r\n"
            + "           -   [___ |  | |  | | \\| |__| _| | |  | | \\| ___] |  | |___  -\r\n"
            + "           -           http://hi.baidu.com/zhangjianshe                -\r\n"
            + "           =============================================================" + "\r\n\r\n</pre>\r\n";

    /**
     * Gets the header.
     * dont include header
     *
     * @return the header
     */
    public String getHeader() {
        return "";
    }

    /**
     * 设置SQL语句提供器.
     *
     * @param c the new SQL clause
     */
    public void setSQLClause(ISQLClause c) {
        sqlClause = c;
    }

    /**
     * 获取当前配置.
     *
     * @return the configure
     */
    public Configure getConfigure() {
        return m_configure;
    }

    /**
     * 表 tn中的列cn 是否为主键.
     *
     * @param tn the tn
     * @param cn the cn
     * @return true, if is pk
     */
    public boolean isPK(String tn, String cn) {
        boolean b = false;
        for (int i = 0; i < pks.length; i++) {
            String tna = (String) pks[i][0];
            String cna = (String) pks[i][1];
            String pktype = (String) pks[i][2];
            if (tna.equals(tn) && cna.equals(cn) && (pktype.compareToIgnoreCase("PRIMARY") == 0)) {
                b = true;
                break;
            }
        }
        return b;
    }

    /**
     * 获取SCHEMA语句.
     *
     * @return the SQL clause
     */
    public ISQLClause getSQLClause() {
        return sqlClause;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.itf.ISchema#getTables()
     */
    @Override
    public Tables getTables() {
        return tables;
    }

    /**
     * Gets the sequences.
     *
     * @return the sequences
     */
    public Sequences getSequences() {
        return sequences;
    }

    /**
     * Gets the packages.
     *
     * @return the packages
     */
    public Packages getPackages() {
        return packages;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.itf.ISchema#getViews()
     */
    @Override
    public Views getViews() {
        return views;
    }

    /**
     * Gets the pks.
     *
     * @return the pks
     */
    public Object[][] getPKS() {
        return pks;
    }

    /**
     * Write to file.
     *
     * @param path the path
     * @param fn   the fn
     * @param s    the s
     */
    public void writeToFile(String path, String fn, String s) {
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(path + File.separator + fn), "UTF-8");
            out.write(s);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.itf.ISchema#exportJSONTools(com.mapway
     * .database2java.model.base.Configure)
     */
    @Override
    public String exportJSONTools(Configure conf) {
        StringBuilder sb = new StringBuilder();
        out(sb, getCopyright());
        writeJSONTools(sb, conf);
        writeToFile(conf.getFilePath(), "JSONHelper.java", sb.toString());
        return "";
    }

    /**
     * Write JSON tools.
     *
     * @param sb   the sb
     * @param conf the conf
     */
    private void writeJSONTools(StringBuilder sb, Configure conf) {
        out(sb, "package " + conf.getPackage() + ";");
        out(sb, "public class JSONHelper");
        out(sb, "{");
        out(sb, "private static final char[] QUOTE_ENCODE = \"&quot;\".toCharArray();");
        out(sb, "                                                                  ");
        out(sb, "	private static final char[] AMP_ENCODE = \"&amp;\".toCharArray(); ");
        out(sb, "                                                                  ");
        out(sb, "	private static final char[] LT_ENCODE = \"&lt;\".toCharArray();   ");
        out(sb, "                                                                  ");
        out(sb, "	private static final char[] GT_ENCODE = \"&gt;\".toCharArray();   ");
        out(sb, "                                                                  ");
        out(sb, "	public static final String escapeForXML(String string) {        ");
        out(sb, "		if (string == null) {                                         ");
        out(sb, "			return null;                                                ");
        out(sb, "		}                                                             ");
        out(sb, "		char ch;                                                      ");
        out(sb, "		int i = 0;                                                    ");
        out(sb, "		int last = 0;                                                 ");
        out(sb, "		char[] input = string.toCharArray();                          ");
        out(sb, "		int len = input.length;                                       ");
        out(sb, "		StringBuffer out = new StringBuffer((int) (len * 1.3));       ");
        out(sb, "		for (; i < len; i++) {                                        ");
        out(sb, "			ch = input[i];                                              ");
        out(sb, "			if (ch > '>') {                                             ");
        out(sb, "				continue;                                                 ");
        out(sb, "			} else if (ch == '<') {                                     ");
        out(sb, "				if (i > last) {                                           ");
        out(sb, "					out.append(input, last, i - last);                      ");
        out(sb, "				}                                                         ");
        out(sb, "				last = i + 1;                                             ");
        out(sb, "				out.append(LT_ENCODE);                                    ");
        out(sb, "			} else if (ch == '&') {                                     ");
        out(sb, "				if (i > last) {                                           ");
        out(sb, "					out.append(input, last, i - last);                      ");
        out(sb, "				}                                                         ");
        out(sb, "				last = i + 1;                                             ");
        out(sb, "				out.append(AMP_ENCODE);                                   ");
        out(sb, "			} else if (ch == '\"') {                                     ");
        out(sb, "				if (i > last) {                                           ");
        out(sb, "					out.append(input, last, i - last);                      ");
        out(sb, "				}                                                         ");
        out(sb, "				last = i + 1;                                             ");
        out(sb, "				out.append(QUOTE_ENCODE);                                 ");
        out(sb, "			}                                                           ");
        out(sb, "		}                                                             ");
        out(sb, "		if (last == 0) {                                              ");
        out(sb, "			return string;                                              ");
        out(sb, "		}                                                             ");
        out(sb, "		if (i > last) {                                               ");
        out(sb, "			out.append(input, last, i - last);                          ");
        out(sb, "		}                                                             ");
        out(sb, "		return out.toString();                                        ");
        out(sb, "	}                                                               ");

        out(sb, "/**                                         ");
        out(sb, "	 * @param s                               ");
        out(sb, "	 * @return                                ");
        out(sb, "	 */                                       ");
        out(sb, "	public static String escape(String s){    ");
        out(sb, "		if(s==null)                             ");
        out(sb, "			return null;                          ");
        out(sb, "		StringBuffer sb=new StringBuffer();     ");
        out(sb, "		for(int i=0;i<s.length();i++){          ");
        out(sb, "			char ch=s.charAt(i);                  ");
        out(sb, "			switch(ch){                           ");
        out(sb, "			case '\"':                             ");
        out(sb, "				sb.append(\"\\\\\\\"\");                  ");
        out(sb, "				break;                              ");
        out(sb, "			case '\\\\':                            ");
        out(sb, "				sb.append(\"\\\\\\\\\");                  ");
        out(sb, "				break;                              ");
        out(sb, "			case '\\b':                            ");
        out(sb, "				sb.append(\"\\\\b\");                   ");
        out(sb, "				break;                              ");
        out(sb, "			case '\\f':                            ");
        out(sb, "				sb.append(\"\\\\f\");                   ");
        out(sb, "				break;                              ");
        out(sb, "			case '\\n':                            ");
        out(sb, "				sb.append(\"\\\\n\");                   ");
        out(sb, "				break;                              ");
        out(sb, "			case '\\r':                            ");
        out(sb, "				sb.append(\"\\\\r\");                   ");
        out(sb, "				break;                              ");
        out(sb, "			case '\\t':                            ");
        out(sb, "				sb.append(\"\\\\t\");                   ");
        out(sb, "				break;                              ");
        out(sb, "			case '/':                             ");
        out(sb, "        				sb.append(\"\\\\/\");                    ");
        out(sb, "        				break;                               ");
        out(sb, "        			default:                               ");
        out(sb, "        				if(ch>='\\u0000' && ch<='\\u001F'){      ");
        out(sb, "        					String ss=Integer.toHexString(ch); ");
        out(sb, "        					sb.append(\"\\\\u\");                ");
        out(sb, "        					for(int k=0;k<4-ss.length();k++){    ");
        out(sb, "        						sb.append('0');                  ");
        out(sb, "        					}                                  ");
        out(sb, "        					sb.append(ss.toUpperCase());         ");
        out(sb, "        				}                                    ");
        out(sb, "        				else{                                ");
        out(sb, "        					sb.append(ch);                       ");
        out(sb, "        				}                                    ");
        out(sb, "        			}                                      ");
        out(sb, "        		}//for                                   ");
        out(sb, "        		return sb.toString();                    ");
        out(sb, "        	}                                         ");
        out(sb, "}");
    }

    /**
     * Out.
     *
     * @param sb the sb
     * @param s  the s
     */
    public void out(StringBuilder sb, String s) {
        sb.append(s + "\r\n");
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.itf.ISchema#exportTable(com.mapway.
     * database2java.model.schema.ITable,
     * com.mapway.database2java.model.base.Configure)
     */
    @Override
    public String exportTable(ITable table, Configure conf) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mapway.database2java.model.itf.ISchema#exportPoolInterface(com.mapway
     * .database2java.model.base.Configure)
     */
    @Override
    public void exportPoolInterface(Configure conf) {
        StringBuilder sb = new StringBuilder();
        sb.append(getCopyright());
        out(sb, "package " + conf.getPackage() + ";\r\n");
        out(sb, "import java.sql.Connection;\r\n");

        out(sb, "public interface IConnectionPool {");
        out(sb, "\tpublic Connection getConnection();");
        out(sb, "\tpublic void releaseConnection(Connection con);");
        out(sb, "}");
        writeToFile(conf.getFilePath(), "IConnectionPool.java", sb.toString());

        InputStream ins = getClass().getResourceAsStream("/template/MyPool.txt");
        String txt = readUTF8TextFile(ins);
        txt = txt.replace("${package}", conf.getPackage());
        writeToFile(conf.getFilePath(), "MyPool.java", txt);
        try {
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Read UTF 8 text file.
     *
     * @param ins the ins
     * @return the string
     */
    public static String readUTF8TextFile(InputStream ins) {

        String txt = "";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = ins.read(bytes);
            while (len > 0) {
                out.write(bytes, 0, len);
                len = ins.read(bytes);
            }
            ins.close();
            txt = out.toString("UTF-8");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return txt;
    }

    /**
     * Read UTF 8 text file.
     *
     * @param fileName the file name
     * @return the string
     */
    public static String readUTF8TextFile(String fileName) {
        byte[] bytes = ReadFromFile(fileName);
        String str = "";
        try {
            str = new String(bytes, "UTF-8");
            return str;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * Read text file.
     *
     * @param fileName the file name
     * @param encoding the encoding
     * @return the string
     */
    public static String readTextFile(String fileName, String encoding) {
        byte[] bytes = ReadFromFile(fileName);
        String str = "";
        try {
            str = new String(bytes, encoding);
            return str;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * Read from file.
     *
     * @param fileName the file name
     * @return the byte[]
     */
    public static byte[] ReadFromFile(String fileName) {

        File ff = new java.io.File(fileName);
        long filelength = ff.length();
        byte[] code = new byte[(int) filelength];

        InputStream inStream;
        try {
            inStream = new FileInputStream(fileName);
            inStream.read(code);
            inStream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return code;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mapway.database2java.model.itf.ISchema#exportExecuteResult(com.mapway
     * .database2java.model.base.Configure)
     */
    @Override
    public void exportExecuteResult(Configure conf) {
        System.out.println("Export ExecuteResult.....");
        StringBuilder sb = new StringBuilder();
        sb.append(getCopyright());
        out(sb, "package " + conf.getPackage() + ";\r\n");
        out(sb, "import java.sql.Connection;");
        out(sb, "import java.sql.ResultSet;");
        out(sb, "import java.sql.SQLException;");
        out(sb, "import java.sql.Statement;");

        out(sb, "public class ExecuteResult {");

        out(sb, "public ResultSet result;");

        out(sb, "public Statement statement;");

        out(sb, "Connection       con;");

        out(sb, "IConnectionPool  pool;");

        out(sb, "/**");
        out(sb, "* 记得用完后 调用 dispose 释放资源");
        out(sb, "* ");
        out(sb, "* @param rs");
        out(sb, "* @param stm");
        out(sb, "*/");
        out(sb, "public ExecuteResult(IConnectionPool pool, Connection con, ResultSet rs,");
        out(sb, "Statement stm) {");
        out(sb, "this.con = con;");
        out(sb, "this.pool = pool;");
        out(sb, "result = rs;");
        out(sb, "statement = stm;");
        out(sb, "}");

        out(sb, "public void dispose()");
        out(sb, "{");
        out(sb, "try");
        out(sb, "{");
        out(sb, "if (result != null )");
        out(sb, "{");
        out(sb, "result.close();");
        out(sb, "result = null;");
        out(sb, "}");
        out(sb, "if (statement != null )");
        out(sb, "{");
        out(sb, "statement.close();");
        out(sb, "statement = null;");
        out(sb, "}");
        out(sb, "} catch (SQLException e)");
        out(sb, "{");
        out(sb, "e.printStackTrace();");
        out(sb, "} finally");
        out(sb, "{");
        out(sb, "if (this.pool != null)");
        out(sb, "{");
        out(sb, "this.pool.releaseConnection(con);");
        out(sb, "}");
        out(sb, "}");
        out(sb, "}");

        out(sb, "/**");
        out(sb, "* 获取执行的结果");
        out(sb, "* ");
        out(sb, "* @return");
        out(sb, "*/");
        out(sb, "public ResultSet getResultSet()");
        out(sb, "{");
        out(sb, "return this.result;");
        out(sb, "}");
        out(sb, "}");

        writeToFile(conf.getFilePath(), "ExecuteResult.java", sb.toString());

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.itf.ISchema#exportAccessBase(com.mapway
     * .database2java.model.base.Configure)
     */
    @Override
    public void exportAccessBase(Configure conf) {
        StringBuilder sb = new StringBuilder();
        sb.append(getCopyright());
        out(sb, "package " + conf.getPackage() + ";\r\n");
        out(sb, "import java.io.IOException;");
        out(sb, "import java.io.Reader;");
        out(sb, "import java.io.InputStream;");
        out(sb, "import java.sql.Clob;");
        out(sb, "import java.sql.Blob;");
        out(sb, "import java.sql.Connection;");
        out(sb, "import java.sql.ResultSet;");
        out(sb, "import java.sql.PreparedStatement;");
        out(sb, "import java.sql.SQLException;");
        out(sb, "import java.sql.Statement;");
        out(sb, "import java.util.ArrayList;");
        out(sb, "import javax.servlet.jsp.jstl.sql.Result;");
        out(sb, "import javax.servlet.jsp.jstl.sql.ResultSupport;\r\n");
        out(sb, "public class AccessBase {\r\n");
        out(sb, "	protected IConnectionPool ConnectionPool;\r\n");
        out(sb, "	public final static String ERROR_DATABASE = \"Database Access Error\";");
        out(sb, "	/**");
        out(sb, "	 * 获取数据库连接池");
        out(sb, "	 * @return IConnectionPool");
        out(sb, "	 */");
        out(sb, "	public IConnectionPool getConnectionPool() {");
        out(sb, "		return this.ConnectionPool;");
        out(sb, "	}\r\n");
        out(sb, "	/**");
        out(sb, "	 * 设置数据库连接池");
        out(sb, "	 * @param IConnectionPool");
        out(sb, "	 */");
        out(sb, "	public void setConnectionPool(IConnectionPool pool) {");
        out(sb, "		this.ConnectionPool = pool;");
        out(sb, "	}\r\n");
        out(sb, "	public AccessBase(IConnectionPool pool) {");
        out(sb, "		this.ConnectionPool = pool;");
        out(sb, "	}\r\n");
        out(sb, "	public Result execute(String sql) throws SQLException {");
        out(sb, "		Result r = null;");
        out(sb, "		Connection con = this.ConnectionPool.getConnection();");
        out(sb, "		if (con == null)");
        out(sb, "			return null;");
        out(sb, "		Statement statement = null;");
        out(sb, "		try {");
        out(sb, "			statement = con.createStatement();");
        out(sb, "			ResultSet rs = statement.executeQuery(sql);");
        out(sb, "			r = ResultSupport.toResult(rs);");
        out(sb, "			rs.close();");
        out(sb, "			if (statement != null)");
        out(sb, "				statement.close();");
        out(sb, "		} catch (SQLException e) {");
        out(sb, "			e.printStackTrace();");
        out(sb, "			throw e;");
        out(sb, "		} finally {");
        out(sb, "			this.ConnectionPool.releaseConnection(con);");
        out(sb, "		}");
        out(sb, "		return r;");
        out(sb, "	}");

        out(sb, "	public ExecuteResult execute2Result(String sql) throws SQLException {");
        out(sb, "		Connection con = this.ConnectionPool.getConnection();");
        out(sb, "if (con == null)");
        out(sb, "return null;");
        out(sb, "	PreparedStatement statement = null;");
        out(sb, "	try {");
        out(sb, "		statement = con.prepareStatement(sql);");
        out(sb, "		ResultSet rs = statement.executeQuery();");
        out(sb, "				return new ExecuteResult(this.ConnectionPool, con, rs, statement);");
        out(sb, "	} catch (SQLException e) {");
        out(sb, "		e.printStackTrace();");
        out(sb, "		this.ConnectionPool.releaseConnection(con);");
        out(sb, "		return null;");
        out(sb, "	}");
        out(sb, "}");

        out(sb, "	public int findCount(String src, char match) {");
        out(sb, "		int count = 0;");
        out(sb, "		for (int index = 0; index < src.length(); index++) {");
        out(sb, "			char c = src.charAt(index);");
        out(sb, "			if (c == match) {");
        out(sb, "				count++;");
        out(sb, "			}");
        out(sb, "		}");
        out(sb, "		return count;");
        out(sb, "	}\r\n");
        out(sb, "	public void Log(String info) {");
        out(sb, "		System.out.println(info);");
        out(sb, "	}\r\n");
        out(sb, "	public static byte[] bytesFromBlob(Blob blob)throws SQLException	{");

        out(sb, "		if(blob==null)return new byte[0];");
        out(sb, "	      byte[] r = new byte[(int) blob.length()];");

        out(sb, "	      InputStream in=blob.getBinaryStream();");

        out(sb, "	          for (int i=0 ; i < blob.length() ; i++) {");
        out(sb, "	              try {");
        out(sb, "					r[i]=(byte)in.read();");
        out(sb, "				} catch (IOException e) {");
        out(sb, "					e.printStackTrace();");
        out(sb, "				}");
        out(sb, "		          }");
        out(sb, "		return r;");
        out(sb, "\t}\t\n");

        out(sb, "public boolean executeDDL(String ddl) throws SQLException {");
        out(sb, "	boolean b = false;");
        out(sb, "	Connection con = this.ConnectionPool.getConnection();");
        out(sb, " if (con == null)");
        out(sb, "		return b;");
        out(sb, "	Statement statement = null;");
        out(sb, "	try {");
        out(sb, "		statement = con.createStatement();");
        out(sb, "		b = statement.execute(ddl);");
        out(sb, "		if (statement != null)");
        out(sb, "			statement.close();");
        out(sb, "	} catch (SQLException e) {");
        out(sb, "			b = false;");
        out(sb, "		e.printStackTrace();");
        out(sb, "	    throw e;");
        out(sb, "	} finally {");
        out(sb, "		this.ConnectionPool.releaseConnection(con);");
        out(sb, "	}");
        out(sb, "	return b;");
        out(sb, "}");

        out(sb, "	public static String StringFromClob(Clob clob) throws SQLException {");
        out(sb, "		if (clob == null)");
        out(sb, "			return \"\";");
        out(sb, "		Reader clobStream = null; ");
        out(sb, "		try { ");
        out(sb, "			clobStream = clob.getCharacterStream();");
        out(sb, "		} catch (SQLException e) {");
        out(sb, "			e.printStackTrace();");
        out(sb, "			throw e;");
        out(sb, "		}\r\n");

        out(sb, "		StringBuilder suggestions = new StringBuilder();");
        out(sb, "		int nchars = 0; // Number of characters read  ");
        out(sb, "		char[] buffer = new char[10];                 ");
        out(sb, "		try { ");
        out(sb, "			while ((nchars = clobStream.read(buffer)) != -1)");
        out(sb, "				suggestions.append(buffer, 0, nchars);");
        out(sb, "		} catch (IOException e) { ");
        out(sb, "			e.printStackTrace();    ");
        out(sb, "			throw new SQLException();");
        out(sb, "		}");
        out(sb, "		try { ");
        out(sb, "			clobStream.close();");
        out(sb, "		} catch (IOException e){ ");
        out(sb, "			e.printStackTrace();");
        out(sb, "			throw new SQLException();");
        out(sb, "		}");
        out(sb, "		return suggestions.toString();");
        out(sb, "	}\r\n");
        out(sb, "	public ArrayList<String> m_SQLs;\r\n");
        out(sb, "	/* ");
        out(sb, "	 * @param sql");
        out(sb, "	 */");
        out(sb, "	public void AddSQL(String sql) {");
        out(sb, "		if (sql.equals(\"\"))");
        out(sb, "			return;");
        out(sb, "		if (m_SQLs == null)");
        out(sb, "			m_SQLs = new ArrayList<String>();");
        out(sb, "		m_SQLs.add(sql);");
        out(sb, "	}\r\n");
        out(sb, "	/*      ");
        out(sb, "	 *  @return int[]    ");
        out(sb, "	 */     ");
        out(sb, "	public int[] executeBatch() throws SQLException {");
        out(sb, "		int[] rs = new int[0];");
        out(sb, "		Connection conn = this.ConnectionPool.getConnection();");
        out(sb, "		if (conn == null)");
        out(sb, "			return rs;");
        out(sb, "		Statement statement = null;");
        out(sb, "		boolean defaultCommit;");
        out(sb, "		defaultCommit = conn.getAutoCommit();");
        out(sb, "		try {");
        out(sb, "			conn.setAutoCommit(false);");
        out(sb, "			statement = conn.createStatement();");
        out(sb, "			int c = 0;");
        out(sb, "			if (m_SQLs != null && m_SQLs.size() > 0) {");
        out(sb, "				c = m_SQLs.size();");
        out(sb, "			}");
        out(sb, "			if (c > 0) {");
        out(sb, "				for (int i = 0; i < c; i++) {");
        out(sb, "					statement.addBatch(m_SQLs.get(i));");
        out(sb, "				}");
        out(sb, "          ");
        out(sb, "				rs = statement.executeBatch();");
        out(sb, "				conn.commit();");
        out(sb, "			} else {");
        out(sb, "			}");
        out(sb, "		} catch (Exception e) {");
        out(sb, "			conn.rollback();");
        out(sb, "			e.printStackTrace();");
        out(sb, "          ");
        out(sb, "		} finally {");
        out(sb, "			conn.setAutoCommit(defaultCommit);");
        out(sb, "			if (statement != null) {");
        out(sb, "				statement.clearBatch();");
        out(sb, "				statement.close();");
        out(sb, "			}");
        out(sb, "			ClearSQL();");
        out(sb, "			this.ConnectionPool.releaseConnection(conn);");
        out(sb, "		}");
        out(sb, "		return rs;");
        out(sb, "	}\r\n");
        out(sb, "	/* ");
        out(sb, "	 *   Clear SQL Batch   ");
        out(sb, "	 */");
        out(sb, "	public void ClearSQL() {");
        out(sb, "		if (m_SQLs == null)");
        out(sb, "			m_SQLs = new ArrayList<String>();");
        out(sb, "		else");
        out(sb, "			m_SQLs.clear();");
        out(sb, "	}");
        out(sb, "}");
        writeToFile(conf.getFilePath(), "AccessBase.java", sb.toString());

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.itf.ISchema#getCopyright()
     */
    @Override
    public String getCopyright() {
        return "";
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.itf.ISchema#exportViews(com.mapway.
     * database2java.model.schema.View,
     * com.mapway.database2java.model.base.Configure)
     */
    @Override
    public void exportViews(View at, Configure confTable) {

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.itf.ISchema#exportProcedures(com.mapway
     * .database2java.model.base.Configure)
     */
    @Override
    public void exportProcedures(Configure confProcedure) {
        // TODO Auto-generated method stub

    }

    /**
     * Find prev path.
     *
     * @param path the path
     * @return the string
     */
    public String findPrevPath(String path) {
        int index = path.lastIndexOf('.');
        if (index >= 0) {
            return path.substring(0, index);
        }
        return "";
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.itf.ISchema#exportSequence(com.mapway.
     * database2java.model.base.Configure)
     */
    @Override
    public void exportSequence(Configure conf) {

    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.itf.ISchema#exportSpringConfigure(com.
     * mapway.database2java.model.base.Configure)
     */
    @Override
    public void exportSpringConfigure(Configure conf) {
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.itf.ISchema#exportDwrConfigure(com.mapway
     * .database2java.model.base.Configure)
     */
    @Override
    public void exportDwrConfigure(Configure conf) {
        // TODO Auto-generated method stub

    }

    /**
     * 输出GWT模块配置信息.
     *
     * @param conf the conf
     */
    @Override
    public void exportGwtModule(Configure conf) {
        // write GWT module
        InputStream ins = getClass().getResourceAsStream("/template/gwt.xml");
        String txt = readUTF8TextFile(ins);

        String[] gpaths = upPath(conf.path, conf.Package);

        Map<String, String> m2 = new HashMap<String, String>();
        m2.put("GWT_SOURCE_PATH", gpaths[1]);
        txt = replace(m2, txt);
        System.out.println("gwt module information " + gpaths[0] + " " + conf.getSchema() + "Data.gwt.xml");
        writeToFile(gpaths[0], conf.getSchema() + "Data.gwt.xml", txt);
        try {
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String[] upPath(String dataPath, String packageName) {
        String p = packageName.replace('.', '/');
        p = dataPath + "/" + p;
        int index = p.lastIndexOf('/');

        String[] r = new String[2];
        r[0] = p.substring(0, index) + "/";
        r[1] = p.substring(index + 1);
        return r;
    }

    private String replace(Map<String, String> mapper, String template) {
        for (String key : mapper.keySet()) {
            template = template.replaceAll(key, mapper.get(key));
        }
        return template;
    }


    /**
     * Esacpe quoto string.
     *
     * @param s the s
     * @return the string
     */
    public String esacpeQuoto(String s) {
        if (Strings.isBlank(s)) {
            return "";
        }
        return s.replaceAll("\"", "\\\\\"");
    }

    /**
     * Gen simple.
     *
     * @param conf  the conf
     * @param table the table
     * @param sb    the sb
     * @throws IOException the io exception
     */
    public void genSimple(Configure conf, ITable table, StringBuilder sb) throws IOException {
        boolean needDocument = "1".equals(conf.getUseDocument());
        boolean needGwt = "1".equals(conf.getUseGwt());
        boolean needNutz = "1".equals(conf.getUseNutz());
        boolean needFieldIndex = "1".equals(conf.getUseFieldIndex());

        StringOutputStream ops = Lang.ops(sb);
        JavaWriter writer = new JavaWriter(new OutputStreamWriter(ops));

        StringBuilder paras = new StringBuilder();
        out(paras, "<b>字段列表</b>");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(paras, col.getJavaType() + " " + col.getName() + " //" + col.getComment());
        }

        int count = 0;
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            if (col.isPK()) {
                count++;
            }
        }

        //Imports
        writer.emitPackage(conf.getPackage())
                .emitImports(Date.class, BigDecimal.class, Timestamp.class);


        //class document
        writer.emitEmptyLine()
                .emitJavadoc("数据库表-" + table.getComment(), paras.toString());

        // is use Nutz annotation
        if (needNutz) {
            writer.emitAnnotation(Table.class, "\"" + table.getName() + "\"");
            if (count > 1) {
                StringBuilder pk = new StringBuilder();
                pk.append("{");
                for (int i = 0; i < table.getColumns().getCount(); i++) {
                    Column col = table.getColumns().getAt(i);
                    if (col.isPK()) {
                        if (pk.length() > 1) {
                            pk.append(",");
                        }
                        pk.append("\"").append(col.getName()).append("\"");
                    }
                }
                pk.append("}");
                writer.emitAnnotation(PK.class, pk.toString());
            }
        }

        // is use mapway document
        if (needDocument) {
            writer.emitAnnotation("cn.mapway.document.annotation.Doc", "\"" + table.getName() + " " + table.getComment() + "\"");
        }

        // is use gwt integration
        if (needGwt) {
            String i1 = "com.google.gwt.user.client.rpc.IsSerializable";
            String i2 = "com.ksyzt.gwt.client.data.IFieldValue";
            writer.beginType(conf.getPackage() + "." + table.getJavaName(), "class", EnumSet.of(PUBLIC, FINAL), null, i1, i2);
        } else {
            writer.beginType(conf.getPackage() + "." + table.getJavaName(), "class", EnumSet.of(PUBLIC, FINAL));
        }

        // is use field index
        if (needFieldIndex) {
            writer.emitJavadoc("表" + table.getComment() + "代码名称");
            writer.emitField(String.class.getSimpleName(), "TBL_" + table.getName().toUpperCase(), EnumSet.of(PUBLIC, STATIC, FINAL), "\"" + table.getName() + "\"");
        }

        //构造函数
        writer.beginConstructor(EnumSet.of(PUBLIC))
                .endConstructor();

        //字段
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);


            if (needFieldIndex) {
                writer.emitJavadoc("字段索引" + col.getComment() + " " + col.getName());
                writer.
                        emitField(String.class.getSimpleName(), "FLD_" + col.getName().toUpperCase(), EnumSet.of(PUBLIC, STATIC, FINAL), "\"" + col.getName() + "\"");
            }
            writer.emitEmptyLine()
                    .emitJavadoc("字段 " + col.getName() + " " + col.getComment());
            if (needNutz) {
                if (count == 1) {
                    if (col.isPK()) {
                        if (col.getJavaType().contains("String")) {
                            writer.emitAnnotation(Name.class);
                        } else {
                            if (col.isAuto()) {
                                writer.emitAnnotation(Id.class);
                            } else {
                                HashMap<String, String> attr = new HashMap<>();
                                attr.put("auto", "false");
                                writer.emitAnnotation(Id.class, attr);
                            }
                        }
                    } else {
                        writer.emitAnnotation(org.nutz.dao.entity.annotation.Column.class, "\"" + col.getName() + "\"");
                    }
                } else {
                    writer.emitAnnotation(org.nutz.dao.entity.annotation.Column.class, "\"" + col.getName() + "\"");
                }
            }

            if (needDocument) {
                HashMap<String, String> attr = new HashMap<>();
                String v = Strings.escapeHtml(col.getComment());
                if (Strings.isEmpty(v)) {
                    v = col.getName();
                }
                attr.put("value", "\"" + v + "\"");
                attr.put("example", "\"\"");
                writer.emitAnnotation("cn.mapway.document.annotation.ApiField", attr);
            }
            writer.emitField(col.getJavaType(), col.getJavaName(), EnumSet.of(PUBLIC));


        }

        //单独处理getset 方法
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            //GET 方法
            String retString = "return " + col.getName() + "  " + col.getComment() + "  " + col.getDatabaseType();
            writer.emitJavadoc("返回字段" + col.getName() + " " + col.getComment(), retString);
            writer.beginMethod(col.getJavaType(), "get" + Strings.upperFirst(col.getJavaName()), EnumSet.of(PUBLIC));
            writer.emitStatement("return this.%s", col.getJavaName());
            writer.endMethod();

            //Set 方法
            String paraString = "@param " + col.getName() + "  " + col.getComment() + "  " + col.getDatabaseType();
            writer.emitJavadoc("设置字段" + col.getName() + " " + col.getComment(), paraString);
            List<String> paraList = new ArrayList<>(2);
            paraList.add(col.getJavaType());
            paraList.add(col.getJavaName());
            writer.beginMethod("void", "set" + Strings.upperFirst(col.getJavaName()), EnumSet.of(PUBLIC), paraList, null);
            writer.emitStatement("this.%s=%s", col.getJavaName(), col.getJavaName());
            writer.endMethod();
        }

        //GWT method
        if (needGwt) {
            //字段
            if (needFieldIndex) {
                for (int i = 0; i < table.getColumns().getCount(); i++) {
                    Column col = table.getColumns().getAt(i);

                    writer.emitJavadoc("获取字" + col.getName() + "索引")
                            .emitField(Integer.class.getSimpleName(), "IDX_" + col.getName().toUpperCase(), EnumSet.of(PUBLIC, STATIC, FINAL), i + "");
                }
            }

            //com.ksyzt.gwt.client.data.IFieldValue 接口实现
            writer.emitJavadoc("根据字段名称获取字段的.");
            writer.emitAnnotation(Override.class);

            List<String> methodParas = new ArrayList<>(4);
            methodParas.add(String.class.getSimpleName());
            methodParas.add("fieldName");
            methodParas.add(Integer.class.getSimpleName());
            methodParas.add("fieldIndex");
            writer.beginMethod(Object.class.getSimpleName(), "getFieldValue", EnumSet.of(PUBLIC), methodParas, null);

            writer.beginControlFlow("if (fieldName != null && fieldName.length() > 0)");
            for (int i = 0; i < table.getColumns().getCount(); i++) {
                Column col = table.getColumns().getAt(i);
                writer.beginControlFlow("if (FLD_%s.equals(fieldName))", col.getName().toUpperCase());
                writer.emitStatement("return this.%s", col.getJavaName());
                writer.endControlFlow();
            }
            writer.nextControlFlow("else if (fieldIndex != null && fieldIndex >= 0 && fieldIndex < %s)", table.getColumns().getCount());
            for (int i = 0; i < table.getColumns().getCount(); i++) {
                Column col = table.getColumns().getAt(i);
                writer.beginControlFlow("if (fieldIndex == %d)", i);
                writer.emitStatement("return this.%s", col.getJavaName());
                writer.endControlFlow();
            }
            writer.nextControlFlow("else");
            writer.emitStatement("return null");
            writer.endControlFlow();
            writer.emitStatement("return null");
            writer.endMethod();
        }
        writer.endType();
        writer.close();
    }

    @Override
    public void exportSimpleBean(Configure conf) throws IOException {
        String fileName = conf.getFilePath();
        StringBuilder sb;
        Tables tables = getTables();
        for (int i = 0; i < tables.getCount(); i++) {
            ITable t = tables.getAt(i);
            sb = new StringBuilder();

            genSimple(conf, t, sb);
            writeToFile(fileName, t.getJavaName() + ".java", sb.toString());
        }
        Tables views = getTables();
        for (int i = 0; i < tables.getCount(); i++) {
            ITable t = views.getAt(i);
            sb = new StringBuilder();

            genSimple(conf, t, sb);
            writeToFile(fileName, t.getJavaName() + ".java", sb.toString());
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

    }
}

package com.mapway.database2java.model.schema;

// TODO: Auto-generated Javadoc

/**
 * The Class TypeMapper.
 */
public class TypeMapper {

    /**
     * The mt 1.
     */
    ModleTypes mt1;

    /**
     * The mt 2.
     */
    ModleTypes mt2;

    /**
     * The mt 4.
     */
    ModleTypes mt4;

    /**
     * The mt net.
     */
    ModleTypes mtNet;

	/** The mt 3. */
	private ModleTypes mt3;

    /**
     * The instance.
     */
    static TypeMapper instance = null;

    /**
     * Javaԭ��������Ͷ�Ӧ��C++����.
     *
     * @param t the t
     * @return the primitive
     */
    public String getPrimitive(String t) {
		if (t == null || t.equals("")) {
			return "";
		}
		String[][] pri = { { "Boolean", "bool" }, { "String", "CString" }, { "Timestamp", "CTime" },
				{ "boolean", "bool" }, { "Byte", "byte" }, { "byte", "byte" }, { "Character", "char" },
				{ "char", "char" }, { "Short", "short" }, { "short", "short" }, { "Integer", "int" }, { "int", "int" },
				{ "Long", "long" }, { "long", "long" }, { "Float", "float" }, { "float", "float" },
				{ "Double", "double" }, { "double", "double" }, { "Void", "void" }, { "void", "void" },
				{ "Date", "CDate" } };

		for (int i = 0; i < pri.length; i++) {
			if (t.equals(pri[i][0])) {
				return pri[i][1];
			}
		}
		return "";
	}

    /**
     * Instantiates a new type mapper.
     */
    public TypeMapper() {
		instance = this;
		mt1 = new ModleTypes();
		mt1.Add("CHAR", "String");
		mt1.Add("VARCHAR", "String");
		mt1.Add("VARCHAR2", "String");
		mt1.Add("NVARCHAR2", "String");
		mt1.Add("LONGVARCHAR", "String");
		mt1.Add("NUMBER", "Integer");
		mt1.Add("NUMERIC", "Double");
		mt1.Add("DECIMAL", "Float");
		mt1.Add("TIMESTAMP", "java.sql.Timestamp");
		mt1.Add("TIMESTAMP(6)", "java.sql.Timestamp");
		mt1.Add("BIT", "Boolean");
		mt1.Add("TINYINT", "Byte");
		mt1.Add("SMALLINT", "Short");
		mt1.Add("INTEGER", "Integer");
		mt1.Add("BIGINT", "Long");
		mt1.Add("REAL", "Float");
		mt1.Add("FLOAT", "Float");
		mt1.Add("DOUBLE", "Double");
		mt1.Add("BINARY", "byte[]");
		mt1.Add("BLOB", "byte[]");
		mt1.Add("VARBINARY", "byte[]");
		mt1.Add("LONGVARBINARY", "byte[]");
		mt1.Add("DATE", "java.sql.Date");
		mt1.Add("TIME", "java.sql.Time");
		mt1.Add("LONG", "Long");
		mt1.Add("CLOB", "String");
		mt1.Add("REF CURSOR", "Result");
		mt1.Add("MDSYS.SDO_GEOMETRY", "JGeometry");
		mt1.Add("SDO_GEOMETRY", "JGeometry");

		mt1.Add("integer", "Integer");
		mt1.Add("tinyint", "Integer");
		mt1.Add("unknown", "String");
		mt1.Add("smallint", "Integer");
		mt1.Add("mediumint", "Integer");
		mt1.Add("int", "Integer");
		mt1.Add("bigint", "Long");
		mt1.Add("double", "Double");
		mt1.Add("geometry", "String");
		mt1.Add("float", "Float");
		mt1.Add("char", "String");
		mt1.Add("varchar", "String");
		mt1.Add("text", "String");
		mt1.Add("date", "java.sql.Date");

		mt1.Add("timestamp", "java.sql.Timestamp");
		mt1.Add("datetime", "java.sql.Timestamp");
		mt1.Add("tinyblob", "byte[]");
		mt1.Add("blob", "byte[]");
		mt1.Add("mediumblob", "byte[]");
		mt1.Add("decimal", "Double");
		mt1.Add("longtext", "String");
		mt1.Add("mediumtext", "String");
		mt1.Add("enum", "Integer");
		mt1.Add("set", "Integer");
		mt1.Add("longblob", "byte[]");
		mt1.Add("time", "java.sql.Time");
		mt1.Add("year", "java.sql.Time");
		mt1.Add("json", "String");

		mtNet = new ModleTypes();
		mtNet.Add("CHAR", "String");
		mtNet.Add("VARCHAR", "String");
		mtNet.Add("VARCHAR2", "String");
		mtNet.Add("NVARCHAR2", "String");
		mtNet.Add("LONGVARCHAR", "String");
		mtNet.Add("NUMBER", "Int32");
		mtNet.Add("NUMERIC", "Int32");
		mtNet.Add("DECIMAL", "Int32");
		mtNet.Add("TIMESTAMP", "DateTime");
		mtNet.Add("TIMESTAMP(6)", "DateTime");
		mtNet.Add("BIT", "Boolean");
		mtNet.Add("TINYINT", "Byte");
		mtNet.Add("SMALLINT", "Short");
		mtNet.Add("INTEGER", "Int32");
		mtNet.Add("BIGINT", "Int32");
		mtNet.Add("REAL", "Double");
		mtNet.Add("FLOAT", "Double");
		mtNet.Add("DOUBLE", "Double");
		mtNet.Add("BINARY", "Byte[]");
		mtNet.Add("BLOB", "Byte[]");
		mtNet.Add("VARBINARY", "Byte[]");
		mtNet.Add("LONGVARBINARY", "Byte[]");
		mtNet.Add("DATE", "DateTime");
		mtNet.Add("TIME", "DateTime");
		mtNet.Add("LONG", "Long");
		mtNet.Add("CLOB", "String");
		mtNet.Add("REF CURSOR", "Result");
		mtNet.Add("MDSYS.SDO_GEOMETRY", "JGeometry");
		mtNet.Add("SDO_GEOMETRY", "JGeometry");
		mtNet.Add("integer", "Int32");
		mtNet.Add("tinyint", "Int32");
		mtNet.Add("unknown", "String");
		mtNet.Add("smallint", "Int32");
		mtNet.Add("mediumint", "Int32");
		mtNet.Add("int", "Int32");
		mtNet.Add("bigint", "Int64");
		mtNet.Add("double", "Double");
		mtNet.Add("geometry", "String");
		mtNet.Add("float", "Double");
		mtNet.Add("char", "String");
		mtNet.Add("varchar", "String");
		mtNet.Add("text", "String");
		mtNet.Add("date", "DateTime");
		mtNet.Add("timestamp", "DateTime");
		mtNet.Add("datetime", "DateTime");
		mtNet.Add("tinyblob", "Byte[]");
		mtNet.Add("blob", "Byte[]");
		mtNet.Add("mediumblob", "Byte[]");
		mtNet.Add("decimal", "Int32");
		mtNet.Add("longtext", "String");
		mtNet.Add("mediumtext", "String");
		mtNet.Add("enum", "Integer");
		mtNet.Add("set", "Integer");
		mtNet.Add("longblob", "Byte[]");
		mtNet.Add("time", "DateTime");
		mtNet.Add("year", "DateTime");

		// ---------------------------------------------------------------------
		mt2 = new ModleTypes();
		mt2.Add("TIMESTAMP(6)", "Timestamp");
		mt2.Add("TIMESTAMP", "Timestamp");
		mt2.Add("CHAR", "String");
		mt2.Add("VARCHAR", "String");
		mt2.Add("VARCHAR2", "String");
		mt2.Add("NVARCHAR2", "String");
		mt2.Add("LONGVARCHAR", "String");
		mt2.Add("NUMBER", "Object");
		mt2.Add("NUMERIC", "BigDecimal");
		mt2.Add("DECIMAL", "Int");
		mt2.Add("BIT", "Boolean");
		mt2.Add("TINYINT", "Byte");
		mt2.Add("SMALLINT", "Object");
		mt2.Add("INTEGER", "Object");
		mt2.Add("BIGINT", "Object");
		mt2.Add("REAL", "Float");
		mt2.Add("FLOAT", "Object");
		mt2.Add("DOUBLE", "Object");
		mt2.Add("BINARY", "byte[]");
		mt2.Add("VARBINARY", "byte[]");
		mt2.Add("LONGVARBINARY", "byte[]");
		mt2.Add("DATE", "Date");
		mt2.Add("TIME", "Time");
		mt2.Add("BLOB", "byte[]");
		mt2.Add("LONG", "Long");
		mt2.Add("CLOB", "String");
		mt2.Add("REF CURSOR", "Result");
		mt2.Add("MDSYS.SDO_GEOMETRY", "Object");
		mt2.Add("SDO_GEOMETRY", "Object");

		mt2.Add("integer", "Object");
		mt2.Add("tinyint", "Object");
		mt2.Add("unknown", "String");
		mt2.Add("smallint", "Object");
		mt2.Add("mediumint", "Object");
		mt2.Add("int", "Object");
		mt2.Add("bigint", "Object");
		mt2.Add("double", "Object");
		mt2.Add("float", "Float");
		mt2.Add("char", "String");
		mt2.Add("geometry", "String");
		mt2.Add("varchar", "String");
		mt2.Add("text", "String");
		mt2.Add("date", "Date");

		mt2.Add("timestamp", "Timestamp");
		mt2.Add("datetime", "Timestamp");
		mt2.Add("tinyblob", "byte[]");
		mt2.Add("blob", "byte[]");
		mt2.Add("mediumblob", "byte[]");
		mt2.Add("decimal", "Int");
		mt2.Add("longtext", "String");

		mt2.Add("mediumtext", "String");
		mt2.Add("enum", "Object");
		mt2.Add("set", "Object");
		mt2.Add("longblob", "byte[]");
		mt2.Add("time", "Time");
		mt2.Add("year", "Time");

		// ---------------------------------------------------------------------
		mt4 = new ModleTypes();
		mt4.Add("TIMESTAMP(6)", "Timestamp");
		mt4.Add("TIMESTAMP", "Timestamp");
		mt4.Add("CHAR", "String");
		mt4.Add("VARCHAR", "String");
		mt4.Add("VARCHAR2", "String");
		mt4.Add("NVARCHAR2", "String");
		mt4.Add("LONGVARCHAR", "String");
		mt4.Add("NUMBER", "Int");
		mt4.Add("NUMERIC", "BigDecimal");
		mt4.Add("DECIMAL", "Int");
		mt4.Add("BIT", "Boolean");
		mt4.Add("TINYINT", "Byte");
		mt4.Add("SMALLINT", "Int");
		mt4.Add("INTEGER", "Int");
		mt4.Add("BIGINT", "Long");
		mt4.Add("REAL", "Float");
		mt4.Add("FLOAT", "Float");
		mt4.Add("DOUBLE", "Double");
		mt4.Add("BINARY", "byte[]");
		mt4.Add("VARBINARY", "byte[]");
		mt4.Add("LONGVARBINARY", "byte[]");
		mt4.Add("DATE", "Date");
		mt4.Add("TIME", "Time");
		mt4.Add("BLOB", "byte[]");
		mt4.Add("LONG", "Long");
		mt4.Add("CLOB", "String");
		mt4.Add("REF CURSOR", "Result");
		mt4.Add("MDSYS.SDO_GEOMETRY", "Object");
		mt4.Add("SDO_GEOMETRY", "Object");

		mt4.Add("integer", "Int");
		mt4.Add("tinyint", "Int");
		mt4.Add("unknown", "String");
		mt4.Add("smallint", "Int");
		mt4.Add("mediumint", "Int");
		mt4.Add("int", "Int");
		mt4.Add("bigint", "Long");
		mt4.Add("double", "Double");
		mt4.Add("float", "Float");
		mt4.Add("char", "String");
		mt4.Add("geometry", "String");
		mt4.Add("varchar", "String");
		mt4.Add("text", "String");
		mt4.Add("date", "Date");

		mt4.Add("timestamp", "Timestamp");
		mt4.Add("datetime", "Timestamp");
		mt4.Add("tinyblob", "byte[]");
		mt4.Add("blob", "byte[]");
		mt4.Add("mediumblob", "byte[]");
		mt4.Add("decimal", "Int");
		mt4.Add("longtext", "String");

		mt4.Add("mediumtext", "String");
		mt4.Add("enum", "Int");
		mt4.Add("set", "Int");
		mt4.Add("longblob", "byte[]");
		mt4.Add("time", "Time");
		mt4.Add("year", "Time");
		// ----------------------------------------------------------------------
		/*
		 * BIT "; TINYINT "; SMALLINT "; INTEGER "; BIGINT "; FLOAT "; REAL "; DOUBLE ";
		 * NUMERIC "; DECIMAL "; CHAR "; VARCHAR "; LONGVARCHAR "; DATE "; TIME ";
		 * TIMESTAMP "; TIMESTAMPNS "; TIMESTAMPTZ "; TIMESTAMPLTZ "; INTERVALYM
		 * "; INTERVALDS "; BINARY "; VARBINARY "; LONGVARBINARY "; ROWID "; CURSOR
		 * "; BLOB "; CLOB "; BFILE "; STRUCT "; ARRAY "; REF "; OPAQUE "; JAVA_STRUCT
		 * "; JAVA_OBJECT "; PLSQL_INDEX_TABLE "; BINARY_FLOAT "; BINARY_DOUBLE
		 * "; NULL "; NUMBER "; RAW "; OTHER "; FIXED_CHAR "; DATALINK "; BOOLEAN ";
		 */
		mt3 = new ModleTypes();
		mt3.Add("LONGVARBINARY", "OracleTypes.LONGVARBINARY");
		mt3.Add("LONGVARCHAR", "OracleTypes.LONGVARCHAR");
		mt3.Add("VARBINARY", "OracleTypes.VARBINARY");
		mt3.Add("TIMESTAMP", "OracleTypes.TIMESTAMP");
		mt3.Add("TIMESTAMP(6)", "OracleTypes.TIMESTAMP");
		mt3.Add("BIT", "OracleTypes.BIT");
		mt3.Add("REF CURSOR", "OracleTypes.CURSOR");
		mt3.Add("DATALINK", "OracleTypes.DATALINK");
		mt3.Add("DISTINCT", "OracleTypes.DISTINCT");
		mt3.Add("SMALLINT", "OracleTypes.SMALLINT");
		mt3.Add("VARCHAR2", "OracleTypes.VARCHAR");
		mt3.Add("NVARCHAR2", "OracleTypes.VARCHAR");
		mt3.Add("VARCHAR", "OracleTypes.VARCHAR");
		mt3.Add("INTEGER", "OracleTypes.INTEGER");
		mt3.Add("TINYINT", "OracleTypes.TINYINT");
		mt3.Add("NUMERIC", "OracleTypes.NUMERIC");
		mt3.Add("NUMBER", "OracleTypes.NUMBER");
		mt3.Add("DECIMAL", "OracleTypes.NUMERIC");
		mt3.Add("BOOLEAN", "OracleTypes.BOOLEAN");
		mt3.Add("DECIMAL", "OracleTypes.DECIMAL");
		mt3.Add("DOUBLE", "OracleTypes.DOUBLE");
		mt3.Add("BIGINT", "OracleTypes.BIGINT");
		mt3.Add("BINARY", "OracleTypes.BINARY");
		mt3.Add("STRUCT", "OracleTypes.STRUCT");
		mt3.Add("FLOAT", "OracleTypes.FLOAT");
		mt3.Add("OTHER", "OracleTypes.OTHER");
		mt3.Add("OTHER", "OracleTypes.JAVA_OBJECT");
		mt3.Add("ARRAY", "OracleTypes.ARRAY");
		mt3.Add("REAL", "OracleTypes.REAL");
		mt3.Add("CHAR", "OracleTypes.CHAR");
		mt3.Add("DATE", "OracleTypes.DATE");
		mt3.Add("TIME", "OracleTypes.TIME");
		mt3.Add("NULL", "OracleTypes.NULL");
		mt3.Add("BLOB", "OracleTypes.BLOB");
		mt3.Add("CLOB", "OracleTypes.CLOB");
	}

    /**
     * Gets the oracle 2 JDBC.
     *
     * @return the oracle 2 JDBC
     */
    public ModleTypes getOracle2JDBC() {
		return mt1;
	}

    /**
     * Gets the single instance of TypeMapper.
     *
     * @return single instance of TypeMapper
     */
    public static synchronized TypeMapper getInstance() {
		if (instance == null) {
			instance = new TypeMapper();
		}
		return instance;
	}

    /**
     * Gets the oracle 2 JDBC 2.
     *
     * @return the oracle 2 JDBC 2
     */
    public ModleTypes getOracle2JDBC2() {
		return mt2;
	}

    /**
     * Gets the oracle 2 JDBC 3.
     *
     * @return the oracle 2 JDBC 3
     */
    public ModleTypes getOracle2JDBC3() {
		return mt3;
	}

    /**
     * Gets the oracle 2 JDBC 4.
     *
     * @return the oracle 2 JDBC 4
     */
    public ModleTypes getOracle2JDBC4() {
		return mt4;
	}

    /**
     * Gets the oracle 2 net.
     *
     * @return the oracle 2 net
     */
    public ModleTypes getOracle2Net() {
		return mtNet;
	}
}

package com.mapway.database2java.model.mysql;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.jstl.sql.Result;

import com.mapway.database2java.model.base.naming.CamelConvert;
import com.mapway.database2java.model.base.naming.LowerCaseNameConvert;
import com.mapway.database2java.model.base.naming.UpperCaseNameConvert;
import org.nutz.lang.Lang;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Schema;

import com.bethecoder.ascii_table.ASCIITable;
import com.mapway.database2java.database.GeneratorPool;
import com.mapway.database2java.database.IConnectionPool;
import com.mapway.database2java.model.base.Configure;
import com.mapway.database2java.model.base.SchemaBase;
import com.mapway.database2java.model.schema.Argument;
import com.mapway.database2java.model.schema.Arguments;
import com.mapway.database2java.model.schema.Column;
import com.mapway.database2java.model.schema.Columns;
import com.mapway.database2java.model.schema.ITable;
import com.mapway.database2java.model.schema.Pack;
import com.mapway.database2java.model.schema.Procedure;
import com.mapway.database2java.model.schema.Procedures;
import com.mapway.database2java.model.schema.Table;
import com.mapway.database2java.model.schema.Tables;
import com.mapway.database2java.model.schema.View;


/**
 * The Class MySQL_Schema2_gwt.
 */
public class MySQL_Schema2_gwt extends SchemaBase {

    /**
     * 文件头的版权说明.
     *
     * @return the copyright
     */
    @Override
    public String getCopyright() {
        java.util.Date d = new java.util.Date();
        String s = "/*******************************************************************************\r\n"
                + " 数据库Bean\r\n" + " 创建程序 Program 版本(3.0)\r\n"
                + " 程序设计 张建设 EMAIL:zhangjianshe@gmail.com PHONE:15910868680\r\n";
        s += super.getHeader();
        s += "/******************************************************************************/\r\n";
        return s;
    }

    /**
     * 构造函数 创建MySQL的元数据模型.
     *
     * @param pool      the pool
     * @param configure the configure
     */
    public MySQL_Schema2_gwt(IConnectionPool pool, Configure configure) {
        super(pool, configure);
        this.setSQLClause(new MySQL_SQLClause(configure.getSchema()));
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.base.SchemaBase#fetchSchema()
     */
    @Override
    public boolean fetchSchema() {


        super.fetchSchema();
        Result rs;
        try {
            rs = a.execute(this.getSQLClause().getTableSQL());
            System.out.println(a.resultToString(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        Object[][] b = rs.getRowsByIndex();
        String old = "";
        Table tab = null;
        Columns cols = null;
        for (int i = 0; i < rs.getRowCount(); i++) {
            Object[] c = b[i];
            String tn = (String) c[1];
            String tc = (String) c[0];
            String cn = (String) c[2];
            String ct = (String) c[3];
            String cc = (String) c[7];
            String extra = (String) c[8];
            if (!tn.equals(old)) {
                old = tn;
                tab = new Table();
                tab.setName(tn);
                tab.setComment(compass(tc));
                cols = tab.getColumns();
                getTables().addTable(tab);
                System.out.println("table " + tn);
            }
            Column col;
            if (FIELD_STYLE_LOWER_CASE.equalsIgnoreCase(getConfigure().getUseFieldStyle())) {
                col = new Column(new LowerCaseNameConvert());
            } else if (FIELD_STYLE_UPPER_CASE.equalsIgnoreCase(getConfigure().getUseFieldStyle())) {
                col = new Column(new UpperCaseNameConvert());
            } else {
                col = new Column(new CamelConvert());
            }
            col.setName(cn);
            col.setDatabaseType(ct);
            col.setComment(compass(cc));

            int l = 0;
            if (c[4] != null) {

                if (c[4].getClass().getName().indexOf("Long") >= 0) {
                    Long big = (Long) c[4];
                    l = big.intValue();
                }
            }

            col.setLength(l);

            if (extra != null && extra.indexOf("auto_increment") >= 0) {
                col.setAuto(true);
            } else {
                col.setAuto(false);
            }
            col.setPK(isPK(tn, cn));
            cols.addColumn(col);
        }

        try {
            rs = a.execute(this.getSQLClause().getViewSQL());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        b = rs.getRowsByIndex();
        old = "";
        View view = null;
        cols = null;
        for (int i = 0; i < rs.getRowCount(); i++) {
            Object[] c = b[i];

            String tn = (String) c[1];
            String tc = (String) c[0];
            String cn = (String) c[2];
            String ct = (String) c[3];
            String cc = (String) c[7];

            if (!tn.equals(old)) {
                old = tn;

                view = new View();
                view.setName(tn);
                view.setComment(compass(tc));
                cols = view.getColumns();
                this.getViews().addView(view);

            }

            Column col = new Column();
            col.setName(cn);
            col.setDatabaseType(ct);
            col.setComment(compass(cc));
            int l = 0;
            if (c[4] != null) {

                if (c[4].getClass().getName().indexOf("Long") >= 0) {
                    Long big = (Long) c[4];
                    l = big.intValue();
                }
            }

            col.setLength(l);

            cols.addColumn(col);

        }

        // SchemaCrawlerOptions options = new SchemaCrawlerOptions();
        //
        // options.setSchemaInfoLevel(SchemaInfoLevelBuilder.standard());
        // options.setRoutineInclusionRule(new ExcludeAll());
        // System.out.println(Json.toJson(this.getConfigure()));
        // options.setSchemaInclusionRule(new
        // RegularExpressionInclusionRule(this.getConfigure()
        // .getSchema()));
        //
        //
        // Catalog catalog = null;
        // try {
        // catalog =
        // SchemaCrawlerUtility.getCatalog(a.getConnectionPool().getConnection(),
        // options);
        // } catch (SchemaCrawlerException e) {
        // e.printStackTrace();
        // }
        //
        // for (schemacrawler.schema.Schema schema : catalog.getSchemas()) {
        // System.out.println(schema.getFullName() + "===>" +
        // getConfigure().getSchema());
        // if (schema.getFullName().equals(getConfigure().getSchema())) {
        // processSchema(catalog, schema);
        // }
        // }
        return true;
    }


    private void processSchema(Catalog catalog, Schema schema) {
        System.out.println("go schema " + schema.getFullName());
        for (schemacrawler.schema.Table table : catalog.getTables(schema)) {
            System.out.println(table.getFullName());
            String[] headers = Lang.array("模式", "表名", "列名", "是否主键", "是否递增", "是否为空", "数据类型");

            Table t = new Table();
            getTables().addTable(t);
            t.setName(trip(table.getName()));
            t.setComment(compass(table.getRemarks()));
            String[][] data;
            data = new String[table.getColumns().size()][];

            int i = 0;
            for (schemacrawler.schema.Column column : table.getColumns()) {
                System.out.println(column.getFullName());
                Column c = new Column();
                c.setAuto(column.isAutoIncremented());
                c.setPK(column.isPartOfPrimaryKey());
                c.setName(trip(column.getName()));
                c.setComment(compass(column.getRemarks()));
                c.setDatabaseType(column.getColumnDataType().getJavaSqlType().getJavaSqlTypeName());
                t.getColumns().addColumn(c);

                String[] row = Lang.array(trip(schema.getName()), trip(table.getName()), trip(column.getName()),
                        column.isPartOfPrimaryKey() + "", column.isAutoIncremented() + "", column.isNullable() + "",
                        column.getColumnDataType().getJavaSqlType().getJavaSqlTypeName(), trip(c.getComment()));
                data[i++] = row;
            }
            ASCIITable.getInstance().printTable(headers, data);
        }
    }

    private String compass(String remarks) {

        if (remarks == null) {
            return "";
        }
        String dest = "";

        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(remarks);
        dest = m.replaceAll("");
        return dest;
    }

    private static String trip(String name) {
        if (name == null) {
            return "";
        }
        return name.replaceAll("\\\"", "");
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.base.SchemaBase#exportTable(com.mapway.
     * database2java.model.schema .ITable,
     * com.mapway.database2java.model.base.Configure)
     */
    @Override
    public String exportTable(ITable table, Configure conf) {
        StringBuilder sb = new StringBuilder();
        out(sb, getCopyright());

        Configure cf = conf.copy();
        // cf.setPackage(conf.getPackage() + ".shared.modle"); //shared

        writeJavaBean(table, sb, cf);

        writeToFile(cf.getFilePath() + "/shared/modle", table.getName() + "Obj.java", sb.toString());

        sb = new StringBuilder();
        out(sb, getCopyright());
        writeJavaObjs(table, sb, cf);
        writeToFile(conf.getFilePath() + "/shared/modle", table.getName() + "Objs.java", sb.toString());

        cf = conf.copy();
        // cf.setPackage(conf.getPackage() + ".server.database.dao");
        sb = new StringBuilder();
        out(sb, getCopyright());
        writeJavaInterface(table, sb, cf);
        writeToFile(cf.getFilePath() + "/server/database/dao/", "I" + table.getName() + "DAO.java", sb.toString());

        cf = conf.copy();
        // cf.setPackage(conf.getPackage() + ".server.database.dao." +
        // cf.getDatabase() + ".impl");
        sb = new StringBuilder();
        out(sb, getCopyright());
        writeJavaAccess(table, sb, cf);
        writeToFile(cf.getFilePath() + "/server/database/dao/impl/", table.getName() + "DAOImpl.java", sb.toString());

        return "";
    }

    /**
     * Write java interface.
     *
     * @param table the table
     * @param sb    the sb
     * @param conf  the conf
     */
    private void writeJavaInterface(ITable table, StringBuilder sb, Configure conf) {
        out(sb, "package " + conf.getPackage() + ".server.database.dao" + ";\r\n");

        String pac = conf.getPackage();
        int pos = pac.lastIndexOf('.');

        String p = conf.getPackage() + ".server.database.dao";

        out(sb, "import java.sql.ResultSet;");
        out(sb, "import java.sql.SQLException;");
        out(sb, "import java.math.BigDecimal;");
        out(sb, "import javax.servlet.jsp.jstl.sql.Result;");
        out(sb, "import javax.servlet.jsp.jstl.sql.Result;");

        out(sb, "import " + conf.getPackage() + ".server.database.base.ExecuteResult;");
        out(sb, "import " + conf.getPackage() + ".shared.modle." + table.getJavaName() + ";");
        out(sb, "import " + conf.getPackage() + ".shared.modle." + table.getJavaNames() + ";");
        out(sb, "/**");
        out(sb, " * @author ZhangJianshe@gmail.com");
        out(sb, " * " + table.getComment() + "  访问数据库接口类");
        out(sb, " */");
        out(sb, "public interface I" + table.getName() + "DAO" + " {\r\n");

        out(sb, "\tpublic Result execute(String sql) throws SQLException;");
        out(sb, "\tpublic ExecuteResult execute2Result(String sql) throws SQLException;");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\r\n" + table.getJavaName() + " findBy" + col.getName() + "(" + col.getJavaType() + " "
                    + col.getName().toLowerCase() + ")throws SQLException;");
        }

        out(sb, "\t public void setMapByIndex(boolean b);");

        out(sb, "\t/**");
        out(sb, "\t * 重置是否搜索字段标志");
        out(sb, "\t * @param b");
        out(sb, "\t */");
        out(sb, "\tpublic void resetSearched(boolean b);");
        out(sb, "\t/**");
        out(sb, "\t * 获取表中所有记录");
        out(sb, "\t * @return Result");
        out(sb, "\t * @throws SQLException");
        out(sb, "\t */");
        out(sb, "\tpublic Result selectAll2Result() throws SQLException;");

        out(sb, "\t/**");
        out(sb, "\t * 获取表中所有记录");
        out(sb, "\t * @return " + table.getJavaNames());
        out(sb, "\t * @throws SQLException");
        out(sb, "\t */");
        out(sb, "\tpublic " + table.getJavaNames() + " selectAll() throws SQLException;");
        out(sb, "\t/**");
        out(sb, "\t* 将ResultSet中的一条记录转变为对象");
        out(sb, "\t* @Param ResultSet rs");
        out(sb, "\t* @return " + table.getJavaName());
        out(sb, "\t* @throws SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaName() + " mapRecord2Obj(ResultSet rs) throws SQLException;");

        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t/**");
            out(sb, "\t* 设置字段 " + col.getComment() + " [" + col.getName() + "] 是否查询");
            out(sb, "\t* @Param boolean");
            out(sb, "\t*/");
            out(sb, "\tpublic void setSearched" + col.getName() + "( boolean b );");
        }

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaNames()
                + " selectByWhereTemplate  (String whereTemplate,Object...values) throws SQLException;");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率 返回的结果<p>");
        out(sb, "\t* ExecuteResult  对于这个返回值切记要进行返回值检查，并释放资源");
        out(sb, "\t* <code>if(rs!=null){.... rs.dispose();}</code>");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @see ExecuteResult");
        out(sb, "\t*/");
        out(sb, "\tpublic ExecuteResult selectByWhereTemplate2ExetureResult  (String whereTemplate,Object...values);");

        out(sb, "public ExecuteResult getSpecificRecordByWhereTemplate2ExetureResult  (String whereTemplate,int start,int end,Object...values);");

        out(sb, "\t/**");
        out(sb, "\t* 在where条件取出的记录集中返回 start < X < end 的记录");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @param start");
        out(sb, "\t* @param end");
        out(sb, "\t* @return " + table.getJavaNames());
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaNames()
                + " getSpecificRecordByWhereTemplate  (String whereTemplate,int start, int end, Object...values) throws SQLException;");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录个数");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @return long");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic long getCountByWhereTemplate  (String whereTemplate,Object...values) throws SQLException;");
        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板删除记录 可以提高查询效率");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param boolean");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic boolean deleteByWhereTemplate  (String whereTemplate,Object...values) throws SQLException;");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param Result");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic Result selectByWhereTemplate2  (String whereTemplate,Object...values) throws SQLException;");
        out(sb, "\t/**");
        out(sb, "\t* 根据主键获取" + table.getComment() + "[" + table.getName() + "]中的单条记录");
        StringBuilder param = new StringBuilder();
        StringBuilder param2 = new StringBuilder();
        StringBuilder param3 = new StringBuilder();
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            if (col.isPK() == true) {
                out(sb, "\t* @Param " + col.getName().toLowerCase());
                param.append(col.getJavaType() + " " + col.getName().toLowerCase() + ",");
                param2.append("`" + col.getName() + "`=? AND ");
                param3.append(col.getName().toLowerCase() + ",");
            }
        }
        if (param.length() > 1) {
            param.deleteCharAt(param.length() - 1);
        }
        if (param2.length() > 4) {
            param2.setLength(param2.length() - 4);
        }
        if (param3.length() > 1) {
            param3.deleteCharAt(param3.length() - 1);
        }
        if (table.hasPK() == true) {
            out(sb, "\t/**");
            out(sb, "\t* @return " + table.getJavaName());
            out(sb, "\t*/");
            out(sb, "public " + table.getJavaName() + " selectByPK  (" + param.toString() + ") throws SQLException;");

            out(sb, "\t/**");
            out(sb, "\t* 根据ORACLE 主键删除" + table.getComment() + "[" + table.getName() + "]中的单条记录");
            out(sb, "\t* @param " + param.toString());
            out(sb, "\t* @return boolean");
            out(sb, "\t*/");
            out(sb, "\tpublic boolean deleteByPK(" + param.toString() + " ) throws SQLException;");

        }

        out(sb, "\t/** ");
        out(sb, "\t* 向数据库中插入一个对象");
        out(sb, "\t* @param " + table.getName());
        out(sb, "\t*/");
        out(sb, "\tpublic boolean insertObject  (" + table.getJavaName() + " " + table.getJavaName().toLowerCase()
                + " ) throws SQLException;");
        out(sb, "\t/**");
        out(sb, "\t* 向数据库中插入多个对象");
        out(sb, "\t* @param " + table.getJavaNames().toLowerCase());
        out(sb, "\t* @return boolean");
        out(sb, "\t*/");
        out(sb, "\tpublic boolean insertObjects  (" + table.getJavaNames() + " " + table.getJavaNames().toLowerCase()
                + " ) throws SQLException;");
        out(sb, "\t/**");
        out(sb, "\t* 向数据库中更新多个对象");
        out(sb, "\t* @param " + table.getJavaNames().toLowerCase());
        out(sb, "\t* @return boolean");
        out(sb, "\t*/");
        out(sb, "\tpublic boolean updateObjectsByPK  (" + table.getJavaNames() + " "
                + table.getJavaNames().toLowerCase() + " ) throws SQLException;");
        out(sb, "\t/**");
        out(sb, "\t* 向数据库中更新一个对象 ");
        out(sb, "\t* @param " + table.getName());
        out(sb, "\t*/  ");
        out(sb, "\tpublic boolean updateObjectByPK  (" + table.getJavaName() + " " + table.getJavaName().toLowerCase()
                + " ) throws SQLException;");

        out(sb, "\t/**");
        out(sb, "\t* 根据模板SQL语句更新数据库的记录  ");
        out(sb, "\t* <code>sqlTemplate=\"FIELDA =? ,FIELDB=? where FIELDC=?\";</code> ");
        out(sb, "\t* @param sqlTemplate ");
        out(sb, "\t* @param values  ");
        out(sb, "\t* @return boolean ");
        out(sb, "\t*/ ");
        out(sb, "\tpublic boolean updateBySQLTemplate  (String sqlTemplate,Object...values)  throws SQLException;");

        out(sb, "}");
    }

    /**
     * Write java bean.
     *
     * @param table the table
     * @param sb    the sb
     * @param conf  the conf
     */
    private void writeJavaBean(ITable table, StringBuilder sb, Configure conf) {

        out(sb, "package " + conf.getPackage() + ".shared.modle;\r\n");
        out(sb, "import " + conf.getPackage() + ".shared.modle.JSONHelper;");
        out(sb, "import java.util.Date;\r\n");

        out(sb, "/**");
        out(sb, " * 数据库表 " + table.getComment() + "<br/>");
        out(sb, " * @author database2java@polatu.cn\r\n");
        out(sb, " * <b>字段列表</b><br/>\r\n");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, " * " + col.getName() + "   " + col.getComment() + " " + col.getJavaType() + "<br/>");
        }
        out(sb, " */\r\n");

        out(sb, "public class " + table.getJavaName() + " implements java.io.Serializable{");
        out(sb, "\tprivate boolean[] m_changed;\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 构造函数");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaName() + "(){");
        out(sb, "\t\tm_changed = new boolean[" + table.getColumns().getCount() + "];");
        out(sb, "\t\tresetChanged(false);");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 全部重置字段是否被更改过");
        out(sb, "\t* @param b 布尔值 true 字段值已被更改 false 字段值未被更改,用户修改数据时参考");
        out(sb, "\t*/");
        out(sb, "\tpublic void resetChanged(boolean b){");
        out(sb, "\t\tfor(int i=0;i<" + table.getColumns().getCount() + ";i++){");
        out(sb, "\t\t\tm_changed[i]=b;");
        out(sb, "\t\t}");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 设置字段是否被更改过");
        out(sb, "\t* @param index 字段索引");
        out(sb, "\t* @param b 布尔值 true 字段值已被更改 false 字段值未被更改,用户修改数据时参考");
        out(sb, "\t*/");
        out(sb, "\tpublic void setChanged(int index,boolean b){");
        out(sb, "\t\tm_changed[index]=b;");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 获取字段是否被更改过");
        out(sb, "\t* @param index 字段索引");
        out(sb, "\t* @return");
        out(sb, "\t*/");
        out(sb, "\tpublic boolean getChanged(int index){");
        out(sb, "\t\treturn m_changed[index];");
        out(sb, "\t}\r\n");

        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column c = table.getColumns().getAt(i);
            out(sb, "\t/**");
            out(sb, "\t* 字段" + c.getName() + "(" + c.getComment() + ")的索引");
            out(sb, "\t*/");
            out(sb, "\t public final static int INDEX_" + c.getName().toUpperCase() + "=" + i + ";\r\n");
        }

        out(sb, "\t/**");
        out(sb, "\t*序列化uid");
        out(sb, "\t*/");
        out(sb, "\tprivate static final long serialVersionUID = 1L;");
        out(sb, "\t/**");
        out(sb, "\t*返回序列化uid");
        out(sb, "\t*/");
        out(sb, "\tstatic long getSerialVersionUID() {");
        out(sb, "\treturn serialVersionUID;");
        out(sb, "\t}");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\tprivate " + col.getJavaType() + " " + col.getName() + ";\r\n");
            out(sb, "\t/**");
            out(sb, "\t * @return " + col.getName() + "  " + col.getComment() + "  " + col.getDatabaseType());
            out(sb, "\t */");
            out(sb, "\tpublic " + col.getJavaType() + " get" + col.getName() + "(){");
            out(sb, "\t\treturn " + col.getName() + ";");
            out(sb, "\t\t}\r\n");
            out(sb, "\t/**");
            out(sb, "\t * @param " + col.getName() + "  " + col.getComment() + "  " + col.getDatabaseType());
            out(sb, "\t */");
            out(sb, "\tpublic void set" + col.getName() + "(" + col.getJavaType() + " " + col.getName() + "){");
            out(sb, "\t\tthis." + col.getName() + "=" + col.getName() + ";");
            out(sb, "\t\tsetChanged( " + i + " ,true);");
            out(sb, "\t\t}\r\n");
        }
        out(sb, "\t/**");
        out(sb, "\t * 将本对象的字段值拷贝到其他的对象中");
        out(sb, "\t*/");
        out(sb, "\tpublic void copyTo(" + table.getJavaName() + " " + table.getJavaName().toLowerCase() + "){");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t\t" + table.getJavaName().toLowerCase() + "." + col.getName() + " = " + "this." + col.getName()
                    + ";");
        }
        out(sb, "\t}");
        out(sb, "\t/**");
        out(sb, "\t * 将本对象已经的更改的字段值拷贝到其他的对象中");
        out(sb, "\t*/");
        out(sb, "\tpublic void mergeTo(" + table.getJavaName() + " " + table.getJavaName().toLowerCase() + "){");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t\tif(this.getChanged(" + i + ")==true){" + table.getJavaName().toLowerCase() + "."
                    + col.getName() + " = " + "this." + col.getName() + ";}");
        }
        out(sb, "\t}");

        out(sb, "\t/**");
        out(sb, "\t * 返回本对象的文本描述");
        out(sb, "\t*/");
        out(sb, "\tpublic String toString(){");
        out(sb, "\t\tStringBuilder sb=new StringBuilder();");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"----\":this.get" + col.getName()
                    + "().toString());");
            out(sb, "\t\tsb.append(\" | \");");
        }
        out(sb, "\treturn sb.toString();");
        out(sb, "\t}\r\n");

        out(sb, "		/**                                     ");
        out(sb, "		 * 输出对象为XML属性                  ");
        out(sb, "		 * @return                              ");
        out(sb, "		 */                                     ");
        out(sb, "		public String toXMLProperty()   ");
        out(sb, "		{                                       ");
        out(sb, "			StringBuilder sb=new StringBuilder(); ");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t\tsb.append(\"" + col.getName() + "=\\\"\");");
            if (col.getJavaType().equals("String")) {

                out(sb, "\t\tsb.append(JSONHelper.escapeForXML(this.get" + col.getName() + "()==null?\"\":this.get"
                        + col.getName() + "().toString()));");

            } else if (col.getJavaType().equals("java.math.BigDecimal")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Boolean")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Byte")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Short")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Integer")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Float")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Double")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("byte[]")) {
                out(sb, "\t\tsb.append(JSONHelper.escapeForXMLthis.get" + col.getName() + "()==null?\"\":this.get"
                        + col.getName() + "().toString()));");
            } else if (col.getJavaType().equals("java.sql.Date")) {

                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");

            } else if (col.getJavaType().equals("java.sql.Time")) {

                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");

            } else if (col.getJavaType().equals("Long")) {
                out(sb, "\t\tsb.append(JSONHelper.escapeForXML(this.get" + col.getName() + "()==null?\"\":this.get"
                        + col.getName() + "().toString()));");
            } else if (col.getJavaType().equals("java.sql.Timestamp")) {

                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString().substring(0,19));");

            }
            out(sb, "\t\tsb.append(\"\\\" \");");
        }
        out(sb, "		return sb.toString();                   ");
        out(sb, "		}                                       ");

        out(sb, "		/**                                     ");
        out(sb, "		 * 输出对象为XML对象                    ");
        out(sb, "		 * @param tag                           ");
        out(sb, "		 * @return                              ");
        out(sb, "		 */                                     ");
        out(sb, "		public String toXMLString(String tag)   ");
        out(sb, "		{                                       ");
        out(sb, "			StringBuilder sb=new StringBuilder(); ");
        out(sb, "			if(tag==null || tag.equals(\"\"))       ");
        out(sb, "			{                                     ");
        out(sb, "				sb.append(\"<" + table.getName().toUpperCase() + " \");         ");
        out(sb, "			}                                     ");
        out(sb, "			else                                  ");
        out(sb, "			{                                     ");
        out(sb, "				sb.append(\"<\");                     ");
        out(sb, "				sb.append(tag);                     ");
        out(sb, "				sb.append(\" \");                     ");
        out(sb, "			}                                     ");
        out(sb, "			                                      ");
        out(sb, "			sb.append(toXMLProperty());                                      ");
        out(sb, "			                                      ");
        out(sb, "			sb.append(\"/>\\r\\n\");                     ");
        out(sb, "			return sb.toString();                 ");
        out(sb, "		}  ");

        out(sb, "\t/**");
        out(sb, "\t * 将本对象的JSON格式文本");
        out(sb, "\t*/");
        out(sb, "\tpublic String toJSONString(){");
        out(sb, "\t\tStringBuilder sb=new StringBuilder();");
        out(sb, "\t\tsb.append(\"{\");");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t\tsb.append(\"" + col.getName() + ":\");");
            if (col.getJavaType().equals("String")) {
                out(sb, "\t\tsb.append(\"\\\"\");");
                out(sb, "\t\tsb.append(JSONHelper.escape(this.get" + col.getName() + "()==null?\"\":this.get"
                        + col.getName() + "().toString()));");
                out(sb, "\t\tsb.append(\"\\\"\");");
            } else if (col.getJavaType().equals("java.math.BigDecimal")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Boolean")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Byte")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Short")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Integer")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Float")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Double")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("byte[]")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("java.sql.Date")) {
                out(sb, "sb.append(\"\\\"\");");
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
                out(sb, "sb.append(\"\\\"\");");
            } else if (col.getJavaType().equals("java.sql.Time")) {
                out(sb, "sb.append(\"\\\"\");");
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
                out(sb, "sb.append(\"\\\"\");");
            } else if (col.getJavaType().equals("Long")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("java.sql.Timestamp")) {
                out(sb, "sb.append(\"\\\"\");");
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString().substring(0,19));");
                out(sb, "sb.append(\"\\\"\");");
            }
            out(sb, "\t\tsb.append(\",\");");
        }
        out(sb, "\t\tsb.deleteCharAt(sb.length()-1);");
        out(sb, "\t\tsb.append(\"}\");");
        out(sb, "\treturn sb.toString();");
        out(sb, "\t}\r\n");
        out(sb, "}");
    }

    /**
     * Write java objs.
     *
     * @param table the table
     * @param sb    the sb
     * @param conf  the conf
     */
    private void writeJavaObjs(ITable table, StringBuilder sb, Configure conf) {
        out(sb, "package " + conf.getPackage() + ".shared.modle;\r\n");
        out(sb, "import java.util.ArrayList;");

        out(sb, "import java.util.Date;");
        out(sb, "import java.util.Iterator;\r\n");

        out(sb, "/**");
        out(sb, " * @author ZhangJianshe@gmail.com");
        out(sb, " * " + table.getComment());
        out(sb, " */");
        out(sb, "public class " + table.getJavaNames() + " extends ArrayList<" + table.getJavaName()
                + "> implements java.io.Serializable{\r\n\r\n");

        out(sb, "\t/**");
        out(sb, "\t * 构造函数");
        out(sb, "\t */");
        out(sb, "\tpublic " + table.getJavaNames() + "()");
        out(sb, "\t{");
        out(sb, "\t\tsuper();");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t * 输出对象的文本");
        out(sb, "\t * @return String");
        out(sb, "\t */");
        out(sb, "\tpublic String toString(){");
        out(sb, "\t\tStringBuilder sb=new StringBuilder();");
        out(sb, "\t\tfor(int i=0;i<this.size();i++){");
        out(sb, "\t\t\tsb.append(this.get(i).toString());");
        out(sb, "\t\t\tsb.append(\"\\r\\n\");");
        out(sb, "\t\t}");
        out(sb, "\t\treturn sb.toString();");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t * 输出对象的JSON文本");
        out(sb, "\t * @return String");
        out(sb, "\t */");
        out(sb, "\tpublic String toJSONString(){");
        out(sb, "\t\tif(this.size()>0)");
        out(sb, "\t\t{");
        out(sb, "\t\tStringBuilder sb=new StringBuilder();");
        out(sb, "\t\t\tsb.append(\"[\");");
        out(sb, "\t\tfor(int i=0;i<this.size();i++){");
        out(sb, "\t\t\tsb.append(this.get(i).toJSONString());");
        out(sb, "\t\t\tsb.append(\",\");");
        out(sb, "\t\t}");
        out(sb, "\t\t\tsb.deleteCharAt(sb.length()-1);");
        out(sb, "\t\t\tsb.append(\"]\");");
        out(sb, "\t\treturn sb.toString();");
        out(sb, "\t}\r\n");
        out(sb, "\t\telse");
        out(sb, "\t\t{");
        out(sb, "\t\t	return \"[]\";");
        out(sb, "\t\t}");
        out(sb, "\t}\r\n");

        out(sb, "	/**                                               ");
        out(sb, "	 * 输出XML节点字符串                              ");
        out(sb, "	 * @return                                        ");
        out(sb, "	 */                                               ");
        out(sb, "	public String toXMLString(String tag,String tag1) ");
        out(sb, "	{                                                 ");
        out(sb, "		StringBuilder sb=new StringBuilder();           ");
        out(sb, "		if(tag==null || tag.equals(\"\"))                 ");
        out(sb, "		{                                               ");
        out(sb, "			sb.append(\"<" + table.getName() + "S \");                           ");
        out(sb, "		}                                               ");
        out(sb, "		else                                            ");
        out(sb, "		{                                               ");
        out(sb, "			sb.append(\"<\");                               ");
        out(sb, "			sb.append(tag);                               ");
        out(sb, "			sb.append(\" \");                               ");
        out(sb, "		}                                               ");
        out(sb, "		sb.append(\"size=\\\"\");                           ");
        out(sb, "		sb.append(this.size());                         ");
        out(sb, "		sb.append(\"\\\" >\\r\\n\");                          ");
        out(sb, "		                                                ");
        out(sb, "		Iterator<" + table.getJavaName() + "> it=this.iterator();          ");
        out(sb, "		while(it.hasNext())                             ");
        out(sb, "		{                                               ");
        out(sb, "			" + table.getJavaName() + " obj=it.next();                       ");
        out(sb, "			sb.append(\"\\t\");                              ");
        out(sb, "			sb.append(obj.toXMLString(tag1));             ");
        out(sb, "		}                                               ");
        out(sb, "		if(tag==null || tag.equals(\"\"))                 ");
        out(sb, "		{                                               ");
        out(sb, "			sb.append(\"</" + table.getName() + "S>\");                          ");
        out(sb, "		}                                               ");
        out(sb, "		else                                            ");
        out(sb, "		{                                               ");
        out(sb, "			sb.append(\"</\");                              ");
        out(sb, "			sb.append(tag);                               ");

        out(sb, "			sb.append(\">\");                               ");
        out(sb, "		}                                               ");
        out(sb, "		sb.append(\"\\r\\n\");                           ");
        out(sb, "		return sb.toString();                           ");
        out(sb, "	}                                                 ");
        out(sb, "}");
    }

    /**
     * Write java access.
     *
     * @param table the table
     * @param sb    the sb
     * @param conf  the conf
     */
    private void writeJavaAccess(ITable table, StringBuilder sb, Configure conf) {
        out(sb, "package " + conf.getPackage() + ".server.database.dao.impl;\r\n");

        for (int i = 0; i < table.getColumns().getCount(); i++) {
            if (table.getColumns().getAt(i).getJavaType().equals("byte[]")) {
                out(sb, "import java.sql.Blob;");
                break;
            }
        }

        out(sb, "import java.sql.Connection;");
        out(sb, "import java.sql.ResultSet;");
        out(sb, "import java.sql.SQLException;");
        out(sb, "import java.sql.Statement;");
        out(sb, "import java.sql.PreparedStatement;");
        out(sb, "import java.math.BigDecimal;");
        out(sb, "import javax.servlet.jsp.jstl.sql.Result;");
        out(sb, "import javax.servlet.jsp.jstl.sql.ResultSupport;\r\n");

        out(sb, "import " + conf.getPackage() + ".server.database.base.AccessBase;");
        out(sb, "import " + conf.getPackage() + ".server.database.base.ExecuteResult;");
        out(sb, "import " + conf.getPackage() + ".server.database.base.IConnectionPool;\r\n");
        out(sb, "import java.util.ArrayList;");
        out(sb, "import " + conf.getPackage() + ".server.database.dao.I" + table.getName() + "DAO;\r\n");

        out(sb, "import " + conf.getPackage() + ".shared.modle." + table.getJavaName() + ";\r\n");
        out(sb, "import " + conf.getPackage() + ".shared.modle." + table.getJavaNames() + ";\r\n");

        out(sb, "/**");
        out(sb, " * @author ZhangJianshe@gmail.com");
        out(sb, " * " + table.getComment() + "  访问数据库类");
        out(sb, " */");
        out(sb, "public class " + table.getName() + "DAOImpl" + " extends AccessBase implements I" + table.getName()
                + "DAO{\r\n");
        out(sb, "\tpublic " + table.getName() + "DAOImpl" + "(IConnectionPool pool){");
        out(sb, "\t\tsuper(pool);");
        out(sb, "\t\tm_searched=new boolean[" + table.getColumns().getCount() + "];");

        out(sb, "\t\tresetSearched(true);");
        out(sb, "\t}\r\n");

        out(sb, "\tprivate boolean bMapByIndex=true;\r\n");
        out(sb, "\tpublic void setMapByIndex(boolean b)");
        out(sb, "{");
        out(sb, "	bMapByIndex=b;");
        out(sb, "}");

        out(sb, "\tprivate boolean[] m_searched;\r\n");

        out(sb, "\t/**");
        out(sb, "\t * 重置是否搜索字段标志");
        out(sb, "\t * @param b");
        out(sb, "\t */");
        out(sb, "\tpublic void resetSearched(boolean b){");
        out(sb, "\t\tfor(int i=0;i<" + table.getColumns().getCount() + ";i++){");
        out(sb, "\t\t\tm_searched[i]=b;");
        out(sb, "\t\t}");
        out(sb, "\t}");
        out(sb, "\t/**");
        out(sb, "\t * 获取表中所有记录");
        out(sb, "\t * @return Result");
        out(sb, "\t * @throws SQLException");
        out(sb, "\t */");
        out(sb, "\tpublic Result selectAll2Result() throws SQLException{");
        out(sb, "\t\treturn selectByWhereTemplate2(\"\");");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t * 获取表中所有记录");
        out(sb, "\t * @return " + table.getJavaNames());
        out(sb, "\t * @throws SQLException");
        out(sb, "\t */");
        out(sb, "\tpublic " + table.getJavaNames() + " selectAll() throws SQLException{");
        out(sb, "\t\treturn selectByWhereTemplate(\"\");");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 将ResultSet中的一条记录转变为对象");
        out(sb, "\t* @Param ResultSet rs");
        out(sb, "\t* @return " + table.getJavaName());
        out(sb, "\t* @throws SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaName() + " mapRecord2Obj(ResultSet rs) throws SQLException");
        out(sb, "\t{");

        out(sb, "\t\tint i=1;");
        out(sb, "\t	" + table.getJavaName() + " o=new " + table.getJavaName() + "();");
        out(sb, "\t if(true==bMapByIndex){");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            if (col.getJavaType().equals("byte[]")) {
                out(sb, "\t\tif(true ==m_searched[" + i + "]) o.set" + col.getName()
                        + "(bytesFromBlob(rs.getBlob(i++)));");
            } else if (col.getDatabaseType().equals("NUMBER") || col.getDatabaseType().equals("NUMERIC")
                    || col.getDatabaseType().equals("SMALLINT") || col.getDatabaseType().equals("INTEGER")
                    || col.getDatabaseType().equals("BIGINT") || col.getDatabaseType().equals("REAL")
                    || col.getDatabaseType().equals("FLOAT") || col.getDatabaseType().equals("DOUBLE")
                    || col.getDatabaseType().equals("integer") || col.getDatabaseType().equals("smallint")
                    || col.getDatabaseType().equals("mediumint") || col.getDatabaseType().equals("int")
                    || col.getDatabaseType().equals("bigint") || col.getDatabaseType().equals("double")
                    || col.getDatabaseType().equals("float") || col.getDatabaseType().equals("enum")
                    || col.getDatabaseType().equals("set")) {
                out(sb, "\t\tif(true ==m_searched[" + i + "])" + "{ \r\n\t\t\trs.get" + col.getJavaType4() + "(i);"

                        + "o.set" + col.getName() + "(true==rs.wasNull()?null:rs.get" + col.getJavaType4()
                        + "(i));i++;\r\n\t\t}");
            } else {
                out(sb, "\t\tif(true ==m_searched[" + i + "]) o.set" + col.getName() + "(rs.get" + col.getJavaType4()
                        + "(i++));");
            }
        }
        out(sb, "\t }else {");

        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            if (col.getJavaType().equals("byte[]")) {
                out(sb, "\t\tif(true ==m_searched[" + i + "]) o.set" + col.getName() + "(bytesFromBlob(rs.getBlob(\""
                        + col.getName() + "\")));");
            } else if (col.getDatabaseType().equals("NUMBER") || col.getDatabaseType().equals("NUMERIC")
                    || col.getDatabaseType().equals("SMALLINT") || col.getDatabaseType().equals("INTEGER")
                    || col.getDatabaseType().equals("BIGINT") || col.getDatabaseType().equals("REAL")
                    || col.getDatabaseType().equals("FLOAT") || col.getDatabaseType().equals("DOUBLE")
                    || col.getDatabaseType().equals("integer") || col.getDatabaseType().equals("smallint")
                    || col.getDatabaseType().equals("mediumint") || col.getDatabaseType().equals("int")
                    || col.getDatabaseType().equals("bigint") || col.getDatabaseType().equals("double")
                    || col.getDatabaseType().equals("float") || col.getDatabaseType().equals("enum")
                    || col.getDatabaseType().equals("set")) {
                out(sb, "\t\tif(true ==m_searched[" + i + "])" + "{ \r\n\t\t\trs.get" + col.getJavaType4() + "(\""
                        + col.getName() + "\");"

                        + "o.set" + col.getName() + "(true==rs.wasNull()?null:rs.get" + col.getJavaType4() + "(\""
                        + col.getName() + "\"));\r\n\t\t}");
            } else {
                out(sb, "\t\tif(true ==m_searched[" + i + "]) o.set" + col.getName() + "(rs.get" + col.getJavaType4()
                        + "(\"" + col.getName() + "\"));");
            }
        }
        out(sb, "\t }");

        out(sb, "\t\to.resetChanged(false);");
        out(sb, "\t\treturn o;");
        out(sb, "\t}\r\n");

        // 接口
        // 接口
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t/**");
            out(sb, "\t* 根据字段 " + col.getComment() + "查询");
            out(sb, "\t* @Param boolean");
            out(sb, "\t*/");
            out(sb, "public " + table.getJavaName() + " findBy" + col.getName() + "  (" + col.getJavaType() + " "
                    + col.getName().toLowerCase() + ") throws SQLException{");
            out(sb, "\t\t" + table.getJavaNames() + " objs=null;");
            out(sb, "\t\tString where=\"where " + col.getName() + "=? \";");
            out(sb, "\t\tobjs=this.selectByWhereTemplate(where," + col.getName().toLowerCase() + ");");

            out(sb, "\t\tif(objs.size()>0){ ");
            out(sb, "\t\treturn objs.get(0);");
            out(sb, "\t\t} ");
            out(sb, "\t\treturn null; ");
            out(sb, "\t} ");

        }

        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t/**");
            out(sb, "\t* 设置字段 " + col.getComment() + " [" + col.getName() + "] 是否查询");
            out(sb, "\t* @Param boolean");
            out(sb, "\t*/");
            out(sb, "\tpublic void setSearched" + col.getName() + "( boolean b ){");
            out(sb, "\t\tm_searched[ " + i + " ] = b;");
            out(sb, "\t}\r\n");
        }

        out(sb, "\t/**");
        out(sb, "\t* 构造搜索字段");
        out(sb, "\t* @return String");
        out(sb, "\t*/");
        out(sb, "\tprivate String searchField(){");
        out(sb, "\t\tStringBuilder sb=new StringBuilder();");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            if (col.getDatabaseType().equals("geometry")) {
                out(sb, "\t\tif( true == m_searched[" + i + "]){sb.append(\"astext(" + col.getName() + ") "
                        + col.getName() + ",\");}");
            } else {
                out(sb, "\t\tif( true == m_searched[" + i + "]){sb.append(\"`" + col.getName() + "`,\");}");
            }
        }
        out(sb, "\t\tsb.deleteCharAt(sb.length()-1);");
        out(sb, "\t\treturn sb.toString();");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaNames()
                + " selectByWhereTemplate  (String whereTemplate,Object...values) throws SQLException{");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");
        out(sb, "\t\tsql.append(\"SELECT \");");
        out(sb, "\t\tsql.append(searchField());");
        out(sb, "\t\tsql.append(\" FROM `" + table.getName() + "` \");");
        out(sb, "\t\tsql.append(whereTemplate);");
        out(sb, "\t\t" + table.getJavaNames() + " os=new " + table.getJavaNames() + "();");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return null;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tResultSet rs=statement.executeQuery();");
        out(sb, "\t\t\twhile(rs.next()){");
        out(sb, "\t\t\t\t" + table.getJavaName() + " o=this.mapRecord2Obj(rs);");
        out(sb, "\t\t\t\tos.add(o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\trs.close();");
        out(sb, "\t\t\tif(statement!=null) statement.close();");
        out(sb, "\t\t}");
        out(sb, "\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthrow e;");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn os;");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率 返回的结果<p>");
        out(sb, "\t* ExecuteResult  对于这个返回值切记要进行返回值检查，并释放资源");
        out(sb, "\t* <code>if(rs!=null){.... rs.dispose();}</code>");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @see ExecuteResult");
        out(sb, "\t*/");
        out(sb, "\tpublic ExecuteResult selectByWhereTemplate2ExetureResult  (String whereTemplate,Object...values) {");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");
        out(sb, "\t\tsql.append(\"SELECT \");");
        out(sb, "\t\tsql.append(searchField());");
        out(sb, "\t\tsql.append(\" FROM `" + table.getName() + "` \");");
        out(sb, "\t\tsql.append(whereTemplate);");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return null;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tResultSet rs=statement.executeQuery();");

        out(sb, "\t\t\treturn new ExecuteResult(this.ConnectionPool,con,rs,statement);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t\treturn null;");
        out(sb, "\t\t\t}");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率 返回的结果<p>");
        out(sb, "\t* ExecuteResult  对于这个返回值切记要进行返回值检查，并释放资源");
        out(sb, "\t* <code>if(rs!=null){.... rs.dispose();}</code>");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @see ExecuteResult");
        out(sb, "\t*/");
        out(sb, "\tpublic ExecuteResult getSpecificRecordByWhereTemplate2ExetureResult  (String whereTemplate,int start,int end,Object...values) {");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");

        out(sb, "\t\tif(whereTemplate==null )whereTemplate=\"\";");

        out(sb, "\t\t    sql.append(\"select \");");
        out(sb, "\t\t    sql.append(searchField());");
        out(sb, "\t\t    sql.append(\" from `\");");
        out(sb, "\t\t    sql.append(\"" + table.getName() + "` \"); ");
        out(sb, "\t\t    sql.append(whereTemplate);");
        out(sb, "\t\t    sql.append(\" limit \");");
        out(sb, "\t\t    sql.append(start);");
        out(sb, "\t\t    sql.append(\",\");");
        out(sb, "\t\t    sql.append(end-start);");

        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return null;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tResultSet rs=statement.executeQuery();");

        out(sb, "\t\t\treturn new ExecuteResult(this.ConnectionPool,con,rs,statement);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t\treturn null;");
        out(sb, "\t\t\t}");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 在where条件取出的记录集中返回 start < X < end 的记录");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @param start");
        out(sb, "\t* @param end");
        out(sb, "\t* @return " + table.getJavaNames());
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaNames()
                + " getSpecificRecordByWhereTemplate  (String whereTemplate,int start, int end, Object...values) throws SQLException{");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");

        out(sb, "\t\tif(whereTemplate==null )whereTemplate=\"\";");

        out(sb, "\t\t    sql.append(\"select \");");
        out(sb, "\t\t    sql.append(searchField());");
        out(sb, "\t\t    sql.append(\" from `\");");
        out(sb, "\t\t    sql.append(\"" + table.getName() + "` \"); ");
        out(sb, "\t\t    sql.append(whereTemplate);");
        out(sb, "\t\t    sql.append(\" limit \");");
        out(sb, "\t\t    sql.append(start);");
        out(sb, "\t\t    sql.append(\",\");");
        out(sb, "\t\t    sql.append(end-start);");

        out(sb, "\t\t" + table.getJavaNames() + " os=new " + table.getJavaNames() + "();");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return null;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tResultSet rs=statement.executeQuery();");
        out(sb, "\t\t\twhile(rs.next()){");
        out(sb, "\t\t\t\t" + table.getJavaName() + " o=this.mapRecord2Obj(rs);");
        out(sb, "\t\t\t\tos.add(o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\trs.close();");
        out(sb, "\t\t\tif(statement!=null) statement.close();");
        out(sb, "\t\t}");
        out(sb, "\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthrow e;");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn os;");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录个数");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @return long");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic long getCountByWhereTemplate  (String whereTemplate,Object...values) throws SQLException{");
        out(sb, "\t\tlong count=0;");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");
        out(sb, "\t\tsql.append(\"SELECT \");");
        if (table.hasPK()) {
            String key = "";
            for (int i = 0; i < table.getColumns().getCount(); i++) {
                Column col = table.getColumns().getAt(i);
                if (col.isPK()) {
                    key = col.getName();
                    break;
                }
            }
            out(sb, "\t\tsql.append(\"count(" + key + ")\");");
        } else {
            out(sb, "\t\tsql.append(\"count(" + table.getColumns().getAt(0).getName() + ")\");");
        }
        out(sb, "\t\tsql.append(\" FROM `" + table.getName() + "` \");");
        out(sb, "\t\tsql.append(whereTemplate);");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return 0;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count1=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count1;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tResultSet rs=statement.executeQuery();");
        out(sb, "\t\t\tif(rs.next()){");
        out(sb, "\t\t\t\tcount=rs.getLong(1);;");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\trs.close();");
        out(sb, "\t\t\tif(statement!=null) statement.close();");
        out(sb, "\t\t}");
        out(sb, "\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthrow e;");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn count;");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板删除记录 可以提高查询效率");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param boolean");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic boolean deleteByWhereTemplate  (String whereTemplate,Object...values) throws SQLException{");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");
        out(sb, "\t\tsql.append(\"DELETE FROM `" + table.getName() + "` \");");
        out(sb, "\t\tsql.append(whereTemplate);");
        out(sb, "\t\tboolean b=false;");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return false;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tb=statement.execute();");
        out(sb, "\t\t\tif(statement!=null) statement.close();");
        out(sb, "\t\t}");
        out(sb, "\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthrow e;");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn b;");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param Result");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic Result selectByWhereTemplate2  (String whereTemplate,Object...values) throws SQLException{");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");
        out(sb, "\t\tResult result=null;");
        out(sb, "\t\tsql.append(\"SELECT \");");
        out(sb, "\t\tsql.append(searchField());");
        out(sb, "\t\tsql.append(\" FROM `" + table.getName() + "` \");");
        out(sb, "\t\tsql.append(whereTemplate);");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return null;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tResultSet rs=statement.executeQuery();");
        out(sb, "\t\t\tresult=ResultSupport.toResult(rs);");
        out(sb, "\t\t\trs.close();");
        out(sb, "\t\t\tif(statement!=null) statement.close();");
        out(sb, "\t\t}");
        out(sb, "\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthrow e;");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn result;");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 根据主键获取" + table.getComment() + "[" + table.getName() + "]中的单条记录");
        StringBuilder param = new StringBuilder();
        StringBuilder param2 = new StringBuilder();
        StringBuilder param3 = new StringBuilder();
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            if (col.isPK() == true) {
                out(sb, "\t* @Param " + col.getName().toLowerCase());
                param.append(col.getJavaType() + " " + col.getName().toLowerCase() + ",");
                param2.append("`" + col.getName() + "`=? AND ");
                param3.append(col.getName().toLowerCase() + ",");
            }
        }
        if (param.length() > 1) {
            param.deleteCharAt(param.length() - 1);
        }
        if (param2.length() > 4) {
            param2.setLength(param2.length() - 4);
        }
        if (param3.length() > 1) {
            param3.deleteCharAt(param3.length() - 1);
        }
        if (table.hasPK() == true) {
            out(sb, "\t* @return " + table.getJavaName());
            out(sb, "\t*/");
            out(sb, "public " + table.getJavaName() + " selectByPK  (" + param.toString() + ") throws SQLException{");
            out(sb, "\t\tStringBuilder sql = new StringBuilder();");
            out(sb, "\t\tsql.append(\"SELECT \");");
            out(sb, "\t\tsql.append(searchField());");
            out(sb, "\t\tsql.append(\" FROM `" + table.getName() + "` WHERE \"); ");
            out(sb, "\t\tsql.append(\"" + param2.toString() + "\" );");
            out(sb, "\t\t" + table.getJavaName() + " o = null;");
            out(sb, "\t\tConnection con = this.ConnectionPool.getConnection();");
            out(sb, "\t\tPreparedStatement statement=null;");
            out(sb, "\t\ttry{");
            out(sb, "\t\t\tstatement = con.prepareStatement(sql.toString());");
            int index = 1;
            for (int i = 0; i < table.getColumns().getCount(); i++) {
                Column col = table.getColumns().getAt(i);
                if (col.isPK() == true) {
                    out(sb, "\t\t\tstatement.set" + col.getJavaType2() + "(" + index + "," + col.getName().toLowerCase()
                            + ");");
                    index++;
                }
            }
            out(sb, "\t\t\tResultSet rs = statement.executeQuery();");
            out(sb, "\t\t\tif (rs.next()){");
            out(sb, "\t\t\t\to = this.mapRecord2Obj(rs);");
            out(sb, "\t\t\t}");
            out(sb, "\t\t\trs.close();");
            out(sb, "\t\t\tif(statement!=null) statement.close();");
            out(sb, "\t\t\t}");
            out(sb, "\t\tcatch (SQLException e){");
            out(sb, "\t\t\te.printStackTrace();");
            out(sb, "\t\t\tthrow e;");
            out(sb, "\t\t}");
            out(sb, "\t\tfinally{");
            out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
            out(sb, "\t\t}");
            out(sb, "\t\treturn o;");
            out(sb, "\t}\r\n");

            out(sb, "\t/**");
            out(sb, "\t* 根据ORACLE 主键删除" + table.getComment() + "[" + table.getName() + "]中的单条记录");
            out(sb, "\t* @param " + param.toString());
            out(sb, "\t* @return boolean");
            out(sb, "\t*/");
            out(sb, "\tpublic boolean deleteByPK(" + param.toString() + " ) throws SQLException");
            out(sb, "\t{");
            out(sb, "\tString sql=\" WHERE " + param2.toString() + "\";");
            out(sb, "\tboolean b=deleteByWhereTemplate(sql," + param3.toString() + " );");
            out(sb, "\treturn b;");
            out(sb, "\t}\r\n");
        }

        out(sb, "\t/** ");
        out(sb, "\t* 向数据库中插入一个对象");
        out(sb, "\t* @param " + table.getName());
        out(sb, "\t*/");
        out(sb, "\tpublic boolean insertObject  (" + table.getJavaName() + " " + table.getJavaName().toLowerCase()
                + " ) throws SQLException{");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");
        out(sb, "\t\tsql.append(\"INSERT INTO `" + table.getName() + "` (\");");
        out(sb, "\t\tStringBuilder s1=new StringBuilder();");
        out(sb, "\t\tStringBuilder s2=new StringBuilder();");
        out(sb, "\t\tint index=1;");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);

            out(sb, "\t\tif(" + table.getJavaName().toLowerCase() + ".getChanged(" + i + ")==true){");

            out(sb, "\t\t\ts1.append(\"`" + col.getName() + "`,\");");

            if (col.getDatabaseType().equals("geometry")) {
                out(sb, "\t\t\ts2.append(\" geomfromtext(?) ,\");");
            } else {
                out(sb, "\t\t\ts2.append(\" ? ,\");");
            }

            out(sb, "\t\t}");
        }
        out(sb, "\t\ts1.deleteCharAt(s1.length()-1);");
        out(sb, "\t\ts2.deleteCharAt(s2.length()-1);");
        out(sb, "\t\ts2.append(\")\");");
        out(sb, "\t\tsql.append(s1.toString());");
        out(sb, "\t\tsql.append(\" ) VALUES ( \");");
        out(sb, "\t\tsql.append(s2.toString());");
        out(sb, "\t\tboolean b=false; ");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return false;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry{");
        out(sb, "\t\t\tstatement = con.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);");
        int cur = 0;
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            if (col.getJavaType().equals("byte[]")) {
                out(sb, "\t\t\tBlob blob" + String.valueOf(cur)
                        + "=oracle.sql.BLOB.createTemporary(con, true, oracle.sql.BLOB.DURATION_SESSION);");
                out(sb, "\t\t\tblob" + String.valueOf(cur) + ".setBytes(1, " + table.getJavaName().toLowerCase()
                        + ".get" + col.getName() + "());");
                out(sb, "\t\t\tif(" + table.getJavaName().toLowerCase() + ".getChanged( " + i
                        + " ) ==true ){ statement.setBlob(index, blob" + String.valueOf(cur++) + ");index++;}");
            } else {
                out(sb, "\t\t\tif(" + table.getJavaName().toLowerCase() + ".getChanged( " + i
                        + " ) ==true ){ statement.set" + col.getJavaType2() + "(index, "
                        + table.getJavaName().toLowerCase() + ".get" + col.getName() + "());index++;}");
            }
        }
        out(sb, "\t\t\tb = statement.execute();");

        if (table.hasAuto()) {
            out(sb, "\t\t\tResultSet rs=statement.getGeneratedKeys();");
            out(sb, "\t\t\twhile(rs.next())");
            out(sb, "\t\t\t{");
            out(sb, "\t\t\t int index1=1;");
            for (int i = 0; i < table.getColumns().getCount(); i++) {
                Column col = table.getColumns().getAt(i);
                if (col.isAuto()) {
                    out(sb, "\t\t\tif(" + table.getJavaName().toLowerCase() + ".getChanged( " + i + " ) ==false ){ "
                            + table.getJavaName().toLowerCase() + ".set" + col.getName() + "(rs.get"
                            + col.getJavaType4() + "(index1++));}");

                }
            }
            out(sb, "\t\t\t}");
            out(sb, "\t\t\trs=null;");
        }
        out(sb, "\t\t\tif(statement!=null);");
        out(sb, "    {");
        out(sb, "    	statement.close();");
        out(sb, "    	statement=null;");
        out(sb, "    }");

        out(sb, "\t\t}");
        out(sb, "\t\tcatch (SQLException e){");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthrow e;");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn b;");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 向数据库中插入多个对象");
        out(sb, "\t* @param " + table.getJavaNames().toLowerCase());
        out(sb, "\t* @return boolean");
        out(sb, "\t*/");
        out(sb, "\tpublic boolean insertObjects  (" + table.getJavaNames() + " " + table.getJavaNames().toLowerCase()
                + " ) throws SQLException{");
        out(sb, "\t\tboolean b = false;");
        out(sb, "\t\tfor(int i=0;i<" + table.getJavaNames().toLowerCase() + ".size();i++){");
        out(sb, "\t\t\tthis.insertObject(" + table.getJavaNames().toLowerCase() + ".get(i));");
        out(sb, "\t\t}");
        out(sb, "\t\tb=true;");
        out(sb, "\t\treturn b;");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 向数据库中更新多个对象");
        out(sb, "\t* @param " + table.getJavaNames().toLowerCase());
        out(sb, "\t* @return boolean");
        out(sb, "\t*/");
        out(sb, "\tpublic boolean updateObjectsByPK  (" + table.getJavaNames() + " "
                + table.getJavaNames().toLowerCase() + " ) throws SQLException{");
        out(sb, "\t\tboolean b = false;");
        out(sb, "\t\tfor(int i=0;i<" + table.getJavaNames().toLowerCase() + ".size();i++){");
        out(sb, "\t\t\tthis.updateObjectByPK(" + table.getJavaNames().toLowerCase() + ".get(i));");
        out(sb, "\t\t}");
        out(sb, "\t\tb=true;");
        out(sb, "\t\treturn b;");
        out(sb, "\t}\r\n");
        out(sb, "\t/** ");
        out(sb, "\t* 向数据库中更新一个对象 ");
        out(sb, "\t* @param " + table.getName());
        out(sb, "\t*/  ");
        out(sb, "\tpublic boolean updateObjectByPK  (" + table.getJavaName() + " " + table.getJavaName().toLowerCase()
                + " ) throws SQLException{");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");
        out(sb, "\t\tsql.append(\"UPDATE `" + table.getName() + "` SET \");");
        out(sb, "\t\tint index=1;");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            if (!col.isPK()) {
                System.out.println("\tCOLUMN " + i + ":" + col.isPK() + ":" + col.getName());

                if (col.getDatabaseType().equals("geometry")) {
                    out(sb, "\t\t\tif(" + table.getJavaName().toLowerCase() + ".getChanged( " + i
                            + " ) ==true ) sql.append(\"" + col.getName() + " =geomfromtext(?),\");");

                } else {
                    out(sb, "\t\t\tif(" + table.getJavaName().toLowerCase() + ".getChanged( " + i
                            + " ) ==true ) sql.append(\"" + col.getName() + " =?,\");");
                }

            }
            System.out.println("COLUMN " + i + ":" + col.isPK() + ":" + col.getName());
        }
        out(sb, "\t\t\tsql.deleteCharAt(sql.length()-1);");
        out(sb, "\t\t\tsql.append(\"  WHERE " + param2.toString() + "\");");
        out(sb, "\t\tboolean b=false; ");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return false;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry{");
        out(sb, "\t\t\tstatement = con.prepareStatement(sql.toString());");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            if (!col.isPK()) {
                if (col.getJavaType().equals("byte[]")) {
                    out(sb, "\t\t\tBlob blob" + String.valueOf(cur)
                            + "=oracle.sql.BLOB.createTemporary(con, true, oracle.sql.BLOB.DURATION_SESSION);");
                    out(sb, "\t\t\tblob" + String.valueOf(cur) + ".setBytes(1, " + table.getJavaName().toLowerCase()
                            + ".get" + col.getName() + "());");
                    out(sb, "\t\t\tif(" + table.getJavaName().toLowerCase() + ".getChanged( " + i
                            + " ) ==true ){ statement.setBlob(index, blob" + String.valueOf(cur++) + ");index++;}");
                } else {
                    out(sb, "\t\t\tif(" + table.getJavaName().toLowerCase() + ".getChanged( " + i
                            + " ) ==true ){ statement.set" + col.getJavaType2() + "(index, "
                            + table.getJavaName().toLowerCase() + ".get" + col.getName() + "());index++;}");
                }
            }
        }
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            if (col.isPK())
                out(sb, "\t\t\tstatement.set" + col.getJavaType2() + "(index, " + table.getJavaName().toLowerCase()
                        + ".get" + col.getName() + "());index++;");
        }
        out(sb, "\t\t\tb = statement.execute();");
        out(sb, "\t\t\tif(statement!=null) statement.close();");
        out(sb, "\t\t}");
        out(sb, "\t\tcatch (SQLException e){");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthrow e;");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn b;");
        out(sb, "\t}\r\n");
        out(sb, "\t/** ");
        out(sb, "\t* 根据模板SQL语句更新数据库的记录  ");
        out(sb, "\t* <code>sqlTemplate=\"FIELDA =? ,FIELDB=? where FIELDC=?\";</code> ");
        out(sb, "\t* @param sqlTemplate ");
        out(sb, "\t* @param values  ");
        out(sb, "\t* @return boolean ");
        out(sb, "\t*/ ");
        out(sb, "\tpublic boolean updateBySQLTemplate  (String sqlTemplate,Object...values)  throws SQLException{");
        out(sb, "\t\tboolean b=false; ");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return false;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry{");
        out(sb, "\t\t\tstatement = con.prepareStatement(\"UPDATE `" + table.getName() + "` SET \"+sqlTemplate);");
        out(sb, "\t\t\tint count=findCount(sqlTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tb=statement.execute();");
        out(sb, "\t\t\tif(statement!=null) statement.close();");
        out(sb, "\t\t}");
        out(sb, "\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthrow e;");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn b;");
        out(sb, "\t}\r\n");
        out(sb, "}");
    }

    /**
     * ExportView View has only the following functions selectAll
     * selectByWhereTemplate selectByWhereTemplate2.
     *
     * @param table the table
     * @param conf  the conf
     */
    @Override
    public void exportViews(View table, Configure conf) {
        StringBuilder sb = new StringBuilder();
        out(sb, getCopyright());
        Configure cf = conf.copy();
        // cf.setPackage(conf.getPackage() + "shared.modle");

        WriteViewBean(table, sb, cf);
        writeToFile(conf.getFilePath() + "/shared/modle", table.getName() + "Obj.java", sb.toString());
        sb = new StringBuilder();
        out(sb, getCopyright());
        WriteViewObjs(table, sb, cf);
        writeToFile(conf.getFilePath() + "/shared/modle", table.getName() + "Objs.java", sb.toString());

        cf = conf.copy();
        // cf.setPackage(conf.getPackage() + ".dao");
        sb = new StringBuilder();
        out(sb, getCopyright());
        WriteViewInterface(table, sb, cf);
        writeToFile(cf.getFilePath() + "/server/database/dao", "I" + table.getName() + "DAO.java", sb.toString());

        cf = conf.copy();
        // cf.setPackage(conf.getPackage() + ".dao." + cf.getDatabase() +
        // ".impl");
        sb = new StringBuilder();
        out(sb, getCopyright());
        WriteViewAccess(table, sb, cf);
        writeToFile(cf.getFilePath() + "/server/database/dao/impl", table.getName() + "DAOImpl.java", sb.toString());

    }

    /**
     * Write view interface.
     *
     * @param table the table
     * @param sb    the sb
     * @param conf  the conf
     */
    private void WriteViewInterface(View table, StringBuilder sb, Configure conf) {
        out(sb, "package " + conf.getPackage() + ".server.database.dao;\r\n");

        String pac = conf.getPackage();
        int pos = pac.lastIndexOf('.');

        String p = pac.substring(0, pos);

        out(sb, "import java.sql.ResultSet;");
        out(sb, "import java.sql.SQLException;");
        out(sb, "import java.math.BigDecimal;");
        out(sb, "import javax.servlet.jsp.jstl.sql.Result;");
        out(sb, "import javax.servlet.jsp.jstl.sql.Result;");

        out(sb, "import " + conf.getPackage() + ".server.database.base.ExecuteResult;");
        out(sb, "import " + conf.getPackage() + ".shared.modle." + table.getJavaName() + ";");
        out(sb, "import " + conf.getPackage() + ".shared.modle." + table.getJavaNames() + ";");

        out(sb, "/**");
        out(sb, " * @author ZhangJianshe@gmail.com");
        out(sb, " * " + table.getComment() + "  访问数据库接口类");
        out(sb, " */");
        out(sb, "public interface I" + table.getName() + "DAO" + " {\r\n");

        out(sb, "\tpublic Result execute(String sql) throws SQLException;");
        out(sb, "\tpublic ExecuteResult execute2Result(String sql) throws SQLException;");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\r\n" + table.getJavaName() + " findBy" + col.getName() + "(" + col.getJavaType() + " "
                    + col.getName().toLowerCase() + ")throws SQLException;;");
        }

        out(sb, "\t public void setMapByIndex(boolean b);");
        out(sb, "\t/**");
        out(sb, "\t * 重置是否搜索字段标志");
        out(sb, "\t * @param b");
        out(sb, "\t */");
        out(sb, "\tpublic void resetSearched(boolean b);");
        out(sb, "\t/**");
        out(sb, "\t * 获取表中所有记录");
        out(sb, "\t * @return Result");
        out(sb, "\t * @throws SQLException");
        out(sb, "\t */");
        out(sb, "\tpublic Result selectAll2Result() throws SQLException;");

        out(sb, "\t/**");
        out(sb, "\t * 获取表中所有记录");
        out(sb, "\t * @return " + table.getJavaNames());
        out(sb, "\t * @throws SQLException");
        out(sb, "\t */");
        out(sb, "\tpublic " + table.getJavaNames() + " selectAll() throws SQLException;");
        out(sb, "\t/**");
        out(sb, "\t* 将ResultSet中的一条记录转变为对象");
        out(sb, "\t* @Param ResultSet rs");
        out(sb, "\t* @return " + table.getJavaName());
        out(sb, "\t* @throws SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaName() + " mapRecord2Obj(ResultSet rs) throws SQLException;");

        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t/**");
            out(sb, "\t* 设置字段 " + col.getComment() + " [" + col.getName() + "] 是否查询");
            out(sb, "\t* @Param boolean");
            out(sb, "\t*/");
            out(sb, "\tpublic void setSearched" + col.getName() + "( boolean b );");
        }

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaNames()
                + " selectByWhereTemplate  (String whereTemplate,Object...values) throws SQLException;");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率 返回的结果<p>");
        out(sb, "\t* ExecuteResult  对于这个返回值切记要进行返回值检查，并释放资源");
        out(sb, "\t* <code>if(rs!=null){.... rs.dispose();}</code>");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @see ExecuteResult");
        out(sb, "\t*/");
        out(sb, "\tpublic ExecuteResult selectByWhereTemplate2ExetureResult  (String whereTemplate,Object...values);");

        out(sb, "\t/**");
        out(sb, "\t* 在where条件取出的记录集中返回 start < X < end 的记录");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @param start");
        out(sb, "\t* @param end");
        out(sb, "\t* @return " + table.getJavaNames());
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaNames()
                + " getSpecificRecordByWhereTemplate  (String whereTemplate,int start, int end, Object...values) throws SQLException;");
        out(sb, "public ExecuteResult getSpecificRecordByWhereTemplate2ExetureResult  (String whereTemplate,int start,int end,Object...values);");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录个数");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @return long");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic long getCountByWhereTemplate  (String whereTemplate,Object...values) throws SQLException;");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param Result");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic Result selectByWhereTemplate2  (String whereTemplate,Object...values) throws SQLException;");
        out(sb, "}");

    }

    /**
     * Write view access.
     *
     * @param table the table
     * @param sb    the sb
     * @param conf  the conf
     */
    private void WriteViewAccess(View table, StringBuilder sb, Configure conf) {
        out(sb, "package " + conf.getPackage() + ".server.database.dao.impl;\r\n");
        out(sb, "import java.sql.Connection;");
        out(sb, "import java.sql.ResultSet;");
        out(sb, "import java.sql.SQLException;");
        out(sb, "import java.sql.Statement;");
        out(sb, "import java.sql.PreparedStatement;");
        out(sb, "import java.math.BigDecimal;");
        out(sb, "import javax.servlet.jsp.jstl.sql.Result;");
        out(sb, "import javax.servlet.jsp.jstl.sql.ResultSupport;\r\n");

        out(sb, "import " + conf.getPackage() + ".server.database.base.AccessBase;");
        out(sb, "import " + conf.getPackage() + ".server.database.base.ExecuteResult;");
        out(sb, "import " + conf.getPackage() + ".server.database.base.IConnectionPool;\r\n");
        out(sb, "import " + conf.getPackage() + ".server.database.dao.I" + table.getName() + "DAO;\r\n");

        out(sb, "import " + conf.getPackage() + ".shared.modle." + table.getJavaName() + ";\r\n");
        out(sb, "import " + conf.getPackage() + ".shared.modle." + table.getJavaNames() + ";\r\n");

        out(sb, "/**");
        out(sb, " * @author ZhangJianshe@gmail.com");
        out(sb, " * " + table.getComment() + "  访问数据库类");
        out(sb, " */");
        out(sb, "public class " + table.getName() + "DAOImpl" + " extends AccessBase implements I" + table.getName()
                + "DAO{\r\n");
        out(sb, "\tpublic " + table.getName() + "DAOImpl" + "(IConnectionPool pool){");
        out(sb, "\t\tsuper(pool);");
        out(sb, "\t\tm_searched=new boolean[" + table.getColumns().getCount() + "];");

        out(sb, "\t\tresetSearched(true);");
        out(sb, "\t}\r\n");
        out(sb, "\tprivate boolean bMapByIndex=true;\r\n");
        out(sb, "\tpublic void setMapByIndex(boolean b)");
        out(sb, "{");
        out(sb, "	bMapByIndex=b;");
        out(sb, "}");
        out(sb, "\tprivate boolean[] m_searched;\r\n");

        // 接口
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t/**");
            out(sb, "\t* 根据字段 " + col.getComment() + "查询");
            out(sb, "\t* @Param boolean");
            out(sb, "\t*/");
            out(sb, "public " + table.getJavaName() + " findBy" + col.getName() + "(" + col.getJavaType() + " "
                    + col.getName().toLowerCase() + ") throws SQLException{");
            out(sb, "\t\t" + table.getJavaNames() + " objs=null;");
            out(sb, "\t\tString where=\"where " + col.getName() + "=? \";");
            out(sb, "\t\tobjs=this.selectByWhereTemplate(where," + col.getName().toLowerCase() + ");");

            out(sb, "\t\tif(objs.size()>0){ ");
            out(sb, "\t\treturn objs.get(0);");
            out(sb, "\t\t} ");
            out(sb, "\t\treturn null; ");
            out(sb, "\t} ");

        }

        out(sb, "\t/**");
        out(sb, "\t * 重置是否搜索字段标志");
        out(sb, "\t * @param b");
        out(sb, "\t */");
        out(sb, "\tpublic void resetSearched(boolean b){");
        out(sb, "\t\tfor(int i=0;i<" + table.getColumns().getCount() + ";i++){");
        out(sb, "\t\t\tm_searched[i]=b;");
        out(sb, "\t\t}");
        out(sb, "\t}");
        out(sb, "\t/**");
        out(sb, "\t * 获取表中所有记录");
        out(sb, "\t * @return Result");
        out(sb, "\t * @throws SQLException");
        out(sb, "\t */");
        out(sb, "\tpublic Result selectAll2Result() throws SQLException{");
        out(sb, "\t\treturn selectByWhereTemplate2(\"\");");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t * 获取表中所有记录");
        out(sb, "\t * @return " + table.getJavaNames());
        out(sb, "\t * @throws SQLException");
        out(sb, "\t */");
        out(sb, "\tpublic " + table.getJavaNames() + " selectAll() throws SQLException{");
        out(sb, "\t\treturn selectByWhereTemplate(\"\");");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 将ResultSet中的一条记录转变为对象");
        out(sb, "\t* @Param ResultSet rs");
        out(sb, "\t* @return " + table.getJavaName());
        out(sb, "\t* @throws SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaName() + " mapRecord2Obj(ResultSet rs) throws SQLException");
        out(sb, "\t{");

        out(sb, "\t\tint i=1;");
        out(sb, "\t	" + table.getJavaName() + " o=new " + table.getJavaName() + "();");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            if (col.getJavaType().equals("byte[]")) {
                out(sb, "\t\tif(true ==m_searched[" + i + "]) o.set" + col.getName()
                        + "(bytesFromBlob(rs.getBlob(i++)));");
            } else if (col.getDatabaseType().equals("NUMBER") || col.getDatabaseType().equals("NUMERIC")
                    || col.getDatabaseType().equals("SMALLINT") || col.getDatabaseType().equals("INTEGER")
                    || col.getDatabaseType().equals("BIGINT") || col.getDatabaseType().equals("REAL")
                    || col.getDatabaseType().equals("FLOAT") || col.getDatabaseType().equals("DOUBLE")
                    || col.getDatabaseType().equals("integer") || col.getDatabaseType().equals("smallint")
                    || col.getDatabaseType().equals("mediumint") || col.getDatabaseType().equals("int")
                    || col.getDatabaseType().equals("bigint") || col.getDatabaseType().equals("double")
                    || col.getDatabaseType().equals("float") || col.getDatabaseType().equals("enum")
                    || col.getDatabaseType().equals("set")) {
                out(sb, "\t\tif(true ==m_searched[" + i + "])" + "{ \r\n\t\t\trs.get" + col.getJavaType4() + "(\""
                        + col.getName() + "\");"

                        + "o.set" + col.getName() + "(true==rs.wasNull()?null:rs.get" + col.getJavaType4() + "(\""
                        + col.getName() + "\"));\r\n\t\t}");
            } else {
                out(sb, "\t\tif(true ==m_searched[" + i + "]) o.set" + col.getName() + "(rs.get" + col.getJavaType4()
                        + "(\"" + col.getName() + "\"));");
            }
        }
        out(sb, "\t\to.resetChanged(false);");
        out(sb, "\t\treturn o;");
        out(sb, "\t}\r\n");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t/**");
            out(sb, "\t* 设置字段 " + col.getComment() + " [" + col.getName() + "] 是否查询");
            out(sb, "\t* @Param boolean");
            out(sb, "\t*/");
            out(sb, "\tpublic void setSearched" + col.getName() + "( boolean b ){");
            out(sb, "\t\tm_searched[ " + i + " ] = b;");
            out(sb, "\t}\r\n");
        }

        out(sb, "\t/**");
        out(sb, "\t* 构造搜索字段");
        out(sb, "\t* @return String");
        out(sb, "\t*/");
        out(sb, "\tprivate String searchField(){");
        out(sb, "\t\tStringBuilder sb=new StringBuilder();");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            if (col.getDatabaseType().equals("geometry")) {
                out(sb, "\t\tif( true == m_searched[" + i + "]){sb.append(\"astext(" + col.getName() + ") "
                        + col.getName() + ",\");}");
            } else {
                out(sb, "\t\tif( true == m_searched[" + i + "]){sb.append(\"`" + col.getName() + "`,\");}");
            }
        }
        out(sb, "\t\tsb.deleteCharAt(sb.length()-1);");
        out(sb, "\t\treturn sb.toString();");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaNames()
                + " selectByWhereTemplate  (String whereTemplate,Object...values) throws SQLException{");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");
        out(sb, "\t\tsql.append(\"SELECT \");");
        out(sb, "\t\tsql.append(searchField());");
        out(sb, "\t\tsql.append(\" FROM `" + table.getName() + "` \");");
        out(sb, "\t\tsql.append(whereTemplate);");
        out(sb, "\t\t" + table.getJavaNames() + " os=new " + table.getJavaNames() + "();");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return null;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tResultSet rs=statement.executeQuery();");
        out(sb, "\t\t\twhile(rs.next()){");
        out(sb, "\t\t\t\t" + table.getJavaName() + " o=this.mapRecord2Obj(rs);");
        out(sb, "\t\t\t\tos.add(o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\trs.close();");
        out(sb, "\t\t\tif(statement!=null) statement.close();");
        out(sb, "\t\t}");
        out(sb, "\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthrow e;");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn os;");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率 返回的结果<p>");
        out(sb, "\t* ExecuteResult  对于这个返回值切记要进行返回值检查，并释放资源");
        out(sb, "\t* <code>if(rs!=null){.... rs.dispose();}</code>");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @see ExecuteResult");
        out(sb, "\t*/");
        out(sb, "\tpublic ExecuteResult selectByWhereTemplate2ExetureResult  (String whereTemplate,Object...values) {");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");
        out(sb, "\t\tsql.append(\"SELECT \");");
        out(sb, "\t\tsql.append(searchField());");
        out(sb, "\t\tsql.append(\" FROM `" + table.getName() + "` \");");
        out(sb, "\t\tsql.append(whereTemplate);");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return null;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tResultSet rs=statement.executeQuery();");

        out(sb, "\t\t\treturn new ExecuteResult(this.ConnectionPool,con,rs,statement);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t\treturn null;");
        out(sb, "\t\t\t}");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率 返回的结果<p>");
        out(sb, "\t* ExecuteResult  对于这个返回值切记要进行返回值检查，并释放资源");
        out(sb, "\t* <code>if(rs!=null){.... rs.dispose();}</code>");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @see ExecuteResult");
        out(sb, "\t*/");
        out(sb, "\tpublic ExecuteResult getSpecificRecordByWhereTemplate2ExetureResult  (String whereTemplate,int start,int end,Object...values) {");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");

        out(sb, "\t\tif(whereTemplate==null )whereTemplate=\"\";");

        out(sb, "\t\t    sql.append(\"select \");");
        out(sb, "\t\t    sql.append(searchField());");
        out(sb, "\t\t    sql.append(\" from `\");");
        out(sb, "\t\t    sql.append(\"" + table.getName() + "` \"); ");
        out(sb, "\t\t    sql.append(whereTemplate);");
        out(sb, "\t\t    sql.append(\" limit \");");
        out(sb, "\t\t    sql.append(start);");
        out(sb, "\t\t    sql.append(\",\");");
        out(sb, "\t\t    sql.append(end-start);");

        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return null;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tResultSet rs=statement.executeQuery();");

        out(sb, "\t\t\treturn new ExecuteResult(this.ConnectionPool,con,rs,statement);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t\treturn null;");
        out(sb, "\t\t\t}");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 在where条件取出的记录集中返回 start < X < end 的记录");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @param start");
        out(sb, "\t* @param end");
        out(sb, "\t* @return " + table.getJavaNames());
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaNames()
                + " getSpecificRecordByWhereTemplate  (String whereTemplate,int start, int end, Object...values) throws SQLException{");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");

        out(sb, "\t\tif(whereTemplate==null )whereTemplate=\"\";");

        out(sb, "\t\t    sql.append(\"select \");");
        out(sb, "\t\t    sql.append(searchField());");
        out(sb, "\t\t    sql.append(\" from `\");");
        out(sb, "\t\t    sql.append(\"" + table.getName() + "` \"); ");
        out(sb, "\t\t    sql.append(whereTemplate);");
        out(sb, "\t\t    sql.append(\" limit \");");
        out(sb, "\t\t    sql.append(start);");
        out(sb, "\t\t    sql.append(\",\");");
        out(sb, "\t\t    sql.append(end-start);");

        out(sb, "\t\t" + table.getJavaNames() + " os=new " + table.getJavaNames() + "();");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return null;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tResultSet rs=statement.executeQuery();");
        out(sb, "\t\t\twhile(rs.next()){");
        out(sb, "\t\t\t\t" + table.getJavaName() + " o=this.mapRecord2Obj(rs);");
        out(sb, "\t\t\t\tos.add(o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\trs.close();");
        out(sb, "\t\t\tif(statement!=null) statement.close();");
        out(sb, "\t\t}");
        out(sb, "\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthrow e;");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn os;");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录个数");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param values");
        out(sb, "\t* @return long");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic long getCountByWhereTemplate  (String whereTemplate,Object...values) throws SQLException{");
        out(sb, "\t\tlong count=0;");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");
        out(sb, "\t\tsql.append(\"SELECT \");");
        if (table.hasPK()) {
            String key = "";
            for (int i = 0; i < table.getColumns().getCount(); i++) {
                Column col = table.getColumns().getAt(i);
                if (col.isPK()) {
                    key = col.getName();
                    break;
                }
            }
            out(sb, "\t\tsql.append(\"count(" + key + ")\");");
        } else {
            out(sb, "\t\tsql.append(\"count(*)\");");
        }
        out(sb, "\t\tsql.append(\" FROM `" + table.getName() + "` \");");
        out(sb, "\t\tsql.append(whereTemplate);");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return 0;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count1=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count1;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tResultSet rs=statement.executeQuery();");
        out(sb, "\t\t\tif(rs.next()){");
        out(sb, "\t\t\t\tcount=rs.getLong(1);;");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\trs.close();");
        out(sb, "\t\t\tif(statement!=null) statement.close();");
        out(sb, "\t\t}");
        out(sb, "\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthrow e;");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn count;");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 根据Where条件模板查询记录 可以提高查询效率");
        out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
        out(sb, "\t* @param whereTemplate");
        out(sb, "\t* @param Result");
        out(sb, "\t* @throw SQLException");
        out(sb, "\t*/");
        out(sb, "\tpublic Result selectByWhereTemplate2  (String whereTemplate,Object...values) throws SQLException{");
        out(sb, "\t\tStringBuilder sql=new StringBuilder();");
        out(sb, "\t\tResult result=null;");
        out(sb, "\t\tsql.append(\"SELECT \");");
        out(sb, "\t\tsql.append(searchField());");
        out(sb, "\t\tsql.append(\" FROM `" + table.getName() + "` \");");
        out(sb, "\t\tsql.append(whereTemplate);");
        out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
        out(sb, "\t\tif( con==null)return null;");
        out(sb, "\t\tPreparedStatement statement=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tString s=sql.toString();");
        // out(sb, "\t\t\tLog(s);");
        out(sb, "\t\t\tstatement = con.prepareStatement(s);");
        out(sb, "\t\t\tint count=findCount(whereTemplate,'?');");
        out(sb, "\t\t\tfor(int i=0;i<count;i++){");
        out(sb, "\t\t\t\tObject o=values[i];");
        out(sb, "\t\t\t\tstatement.setObject(i+1, o);");
        out(sb, "\t\t\t}");
        out(sb, "\t\t\tResultSet rs=statement.executeQuery();");
        out(sb, "\t\t\tresult=ResultSupport.toResult(rs);");
        out(sb, "\t\t\trs.close();");
        out(sb, "\t\t\tif(statement!=null) statement.close();");
        out(sb, "\t\t}");
        out(sb, "\t\tcatch (SQLException e) {");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t\tthrow e;");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn result;");
        out(sb, "\t}\r\n");

        out(sb, "}");

    }

    /**
     * Write view objs.
     *
     * @param table the table
     * @param sb    the sb
     * @param conf  the conf
     */
    private void WriteViewObjs(View table, StringBuilder sb, Configure conf) {
        out(sb, "package " + conf.getPackage() + ".shared.modle;\r\n");
        out(sb, "import java.util.ArrayList;");
        out(sb, "import java.util.Iterator;\r\n");
        out(sb, "import java.util.Date;");

        out(sb, "/**");
        out(sb, " * @author ZhangJianshe@gmail.com");
        out(sb, " * " + table.getComment());
        out(sb, " */");
        out(sb, "public class " + table.getJavaNames() + " extends ArrayList<" + table.getJavaName()
                + "> implements java.io.Serializable{\r\n\r\n");

        out(sb, "\t/**");
        out(sb, "\t * 构造函数");
        out(sb, "\t */");
        out(sb, "\tpublic " + table.getJavaNames() + "()");
        out(sb, "\t{");
        out(sb, "\t\tsuper();");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t * 输出对象的文本");
        out(sb, "\t * @return String");
        out(sb, "\t */");
        out(sb, "\tpublic String toString(){");
        out(sb, "\t\tStringBuilder sb=new StringBuilder();");
        out(sb, "\t\tfor(int i=0;i<this.size();i++){");
        out(sb, "\t\t\tsb.append(this.get(i).toString());");
        out(sb, "\t\t\tsb.append(\"\\r\\n\");");
        out(sb, "\t\t}");
        out(sb, "\t\treturn sb.toString();");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t * 输出对象的JSON文本");
        out(sb, "\t * @return String");
        out(sb, "\t */");
        out(sb, "\tpublic String toJSONString(){");
        out(sb, "\t\tif(this.size()>0)");
        out(sb, "\t\t{");
        out(sb, "\t\tStringBuilder sb=new StringBuilder();");
        out(sb, "\t\t\tsb.append(\"[\");");
        out(sb, "\t\tfor(int i=0;i<this.size();i++){");
        out(sb, "\t\t\tsb.append(this.get(i).toJSONString());");
        out(sb, "\t\t\tsb.append(\",\");");
        out(sb, "\t\t}");
        out(sb, "\t\t\tsb.deleteCharAt(sb.length()-1);");
        out(sb, "\t\t\tsb.append(\"]\");");
        out(sb, "\t\treturn sb.toString();");
        out(sb, "\t}\r\n");
        out(sb, "\t\telse");
        out(sb, "\t\t{");
        out(sb, "\t\t	return \"[]\";");
        out(sb, "\t\t}");
        out(sb, "\t}\r\n");
        out(sb, "	/**                                               ");
        out(sb, "	 * 输出XML节点字符串                              ");
        out(sb, "	 * @return                                        ");
        out(sb, "	 */                                               ");
        out(sb, "	public String toXMLString(String tag,String tag1) ");
        out(sb, "	{                                                 ");
        out(sb, "		StringBuilder sb=new StringBuilder();           ");
        out(sb, "		if(tag==null || tag.equals(\"\"))                 ");
        out(sb, "		{                                               ");
        out(sb, "			sb.append(\"<" + table.getName() + "S \");                           ");
        out(sb, "		}                                               ");
        out(sb, "		else                                            ");
        out(sb, "		{                                               ");
        out(sb, "			sb.append(\"<\");                               ");
        out(sb, "			sb.append(tag);                               ");
        out(sb, "			sb.append(\" \");                               ");
        out(sb, "		}                                               ");
        out(sb, "		sb.append(\"size=\\\"\");                           ");
        out(sb, "		sb.append(this.size());                         ");
        out(sb, "		sb.append(\"\\\" >\\r\\n\");                          ");
        out(sb, "		                                                ");
        out(sb, "		Iterator<" + table.getJavaName() + "> it=this.iterator();          ");
        out(sb, "		while(it.hasNext())                             ");
        out(sb, "		{                                               ");
        out(sb, "			" + table.getJavaName() + " obj=it.next();                       ");
        out(sb, "			sb.append(\"\\t\");                              ");
        out(sb, "			sb.append(obj.toXMLString(tag1));             ");
        out(sb, "		}                                               ");
        out(sb, "		if(tag==null || tag.equals(\"\"))                 ");
        out(sb, "		{                                               ");
        out(sb, "			sb.append(\"</" + table.getName() + "S>\");                          ");
        out(sb, "		}                                               ");
        out(sb, "		else                                            ");
        out(sb, "		{                                               ");
        out(sb, "			sb.append(\"</\");                              ");
        out(sb, "			sb.append(tag);                               ");

        out(sb, "			sb.append(\">\");                               ");
        out(sb, "		}                                               ");
        out(sb, "		sb.append(\"\\r\\n\");                           ");
        out(sb, "		return sb.toString();                           ");
        out(sb, "	}                                                 ");
        out(sb, "}");

    }

    /**
     * Write view bean.
     *
     * @param table the table
     * @param sb    the sb
     * @param conf  the conf
     */
    private void WriteViewBean(View table, StringBuilder sb, Configure conf) {
        out(sb, "package " + conf.getPackage() + ".shared.modle;\r\n");

        out(sb, "import " + conf.getPackage() + ".shared.modle.JSONHelper;");

        out(sb, "import java.util.Date;");

        out(sb, "/**");
        out(sb, " * @author ZhangJianshe@gmail.com");
        out(sb, " *  " + table.getComment());
        out(sb, " */");

        out(sb, "/**\r\n");
        out(sb, " * 字段列表");
        out(sb, " * @author zhangjianshe <br/>");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, " * " + col.getName() + "   " + col.getComment() + " " + col.getJavaType() + "<br/>");
        }
        out(sb, " */\r\n");

        out(sb, "public class " + table.getJavaName() + " implements java.io.Serializable{");

        out(sb, "\tprivate boolean[] m_changed;\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 构造函数");
        out(sb, "\t*/");
        out(sb, "\tpublic " + table.getJavaName() + "(){");
        out(sb, "\t\tm_changed = new boolean[" + table.getColumns().getCount() + "];");
        out(sb, "\t\tresetChanged(false);");
        out(sb, "\t}\r\n");

        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column c = table.getColumns().getAt(i);
            out(sb, "\t/**");
            out(sb, "\t* 字段" + c.getName() + "(" + c.getComment() + ")的索引");
            out(sb, "\t*/");
            out(sb, "\t public final static int INDEX_" + c.getName().toUpperCase() + "=" + i + ";\r\n");
        }

        out(sb, "\t/**");
        out(sb, "\t* 全部重置字段是否被更改过");
        out(sb, "\t* @param b");
        out(sb, "\t*/");
        out(sb, "\tpublic void resetChanged(boolean b){");
        out(sb, "\t\tfor(int i=0;i<" + table.getColumns().getCount() + ";i++){");
        out(sb, "\t\t\tm_changed[i]=b;");
        out(sb, "\t\t}");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 设置字段是否被更改过");
        out(sb, "\t* @param index");
        out(sb, "\t* @param b Boolean");
        out(sb, "\t*/");
        out(sb, "\tpublic void setChanged(int index,boolean b){");
        out(sb, "\t\tm_changed[index]=b;");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 获取字段是否被更改过");
        out(sb, "\t* @param index");
        out(sb, "\t* @return");
        out(sb, "\t*/");
        out(sb, "\tpublic boolean getChanged(int index){");
        out(sb, "\t\treturn m_changed[index];");
        out(sb, "\t}\r\n");

        out(sb, "\t/**");
        out(sb, "\t* 获取记录ROWID");
        out(sb, "\t* @return rowid");
        out(sb, "\t*/");
        out(sb, "\tpublic String getROWID(){");
        out(sb, "\t   return this.ROWID;");
        out(sb, "\t}\r\n");
        out(sb, "\t/**");
        out(sb, "\t* 设置记录ROWID");
        out(sb, "\t* @param rowid");
        out(sb, "\t*/");
        out(sb, "\tpublic void setROWID(String rowid){");
        out(sb, "\t\tthis.ROWID=rowid;");
        out(sb, "\t}\r\n");

        out(sb, "\tprivate String	ROWID;");

        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\tprivate " + col.getJavaType() + " " + col.getName() + ";\r\n");

            out(sb, "\t/**");
            out(sb, "\t * @return " + col.getName() + "  " + col.getComment());
            out(sb, "\t */");
            out(sb, "\tpublic " + col.getJavaType() + " get" + col.getName() + "(){");
            out(sb, "\t\treturn " + col.getName() + ";");
            out(sb, "\t\t}\r\n");

            out(sb, "\t/**");
            out(sb, "\t * @param " + col.getName() + "  " + col.getComment());
            out(sb, "\t */");
            out(sb, "\tpublic void set" + col.getName() + "(" + col.getJavaType() + " " + col.getName() + "){");
            out(sb, "\t\tthis." + col.getName() + "=" + col.getName() + ";");
            out(sb, "\t\tsetChanged( " + i + " ,true);");
            out(sb, "\t\t}\r\n");
        }
        out(sb, "\tpublic String toString(){");
        out(sb, "\t\tStringBuilder sb=new StringBuilder();");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"----\":this.get" + col.getName()
                    + "().toString());");
            out(sb, "\t\tsb.append(\" | \");");
        }
        out(sb, "\treturn sb.toString();");
        out(sb, "\t}\r\n");

        out(sb, "\tpublic void copyTo(" + table.getJavaName() + " " + table.getJavaName().toLowerCase() + "){");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t\t" + table.getJavaName().toLowerCase() + "." + col.getName() + " = " + "this." + col.getName()
                    + ";");
        }
        out(sb, "\t}");

        out(sb, "\tpublic void mergeTo(" + table.getJavaName() + " " + table.getJavaName().toLowerCase() + "){");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t\tif(this.getChanged(" + i + ")==true){" + table.getJavaName().toLowerCase() + "."
                    + col.getName() + " = " + "this." + col.getName() + ";}");
        }
        out(sb, "\t}");

        out(sb, "\tpublic String toJSONString(){");
        out(sb, "\t\tStringBuilder sb=new StringBuilder();");
        out(sb, "\t\tsb.append(\"{\");");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t\tsb.append(\"" + col.getName() + ":\");");
            if (col.getJavaType().equals("String")) {
                out(sb, "\t\tsb.append(\"\\\"\");");
                out(sb, "\t\tsb.append(JSONHelper.escape(this.get" + col.getName() + "()==null?\"\":this.get"
                        + col.getName() + "().toString()));");
                out(sb, "\t\tsb.append(\"\\\"\");");
            } else if (col.getJavaType().equals("java.math.BigDecimal")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Boolean")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Byte")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Short")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Integer")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Float")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Double")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("byte[]")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("java.sql.Date")) {
                out(sb, "sb.append(\"\\\"\");");
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
                out(sb, "sb.append(\"\\\"\");");
            } else if (col.getJavaType().equals("java.sql.Time")) {
                out(sb, "sb.append(\"\\\"\");");
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
                out(sb, "sb.append(\"\\\"\");");
            } else if (col.getJavaType().equals("Long")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("java.sql.Timestamp")) {
                out(sb, "sb.append(\"\\\"\");");
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString().substring(0,19));");
                out(sb, "sb.append(\"\\\"\");");
            }
            out(sb, "\t\tsb.append(\",\");");
        }
        out(sb, "\t\tsb.deleteCharAt(sb.length()-1);");
        out(sb, "\t\tsb.append(\"}\");");
        out(sb, "\treturn sb.toString();");
        out(sb, "\t}\r\n");

        out(sb, "		/**                                     ");
        out(sb, "		 * 输出对象为XML属性                  ");
        out(sb, "		 * @return                              ");
        out(sb, "		 */                                     ");
        out(sb, "		public String toXMLProperty()   ");
        out(sb, "		{                                       ");
        out(sb, "			StringBuilder sb=new StringBuilder(); ");
        for (int i = 0; i < table.getColumns().getCount(); i++) {
            Column col = table.getColumns().getAt(i);
            out(sb, "\t\tsb.append(\"" + col.getName() + "=\\\"\");");
            if (col.getJavaType().equals("String")) {

                out(sb, "\t\tsb.append(JSONHelper.escapeForXML(this.get" + col.getName() + "()==null?\"\":this.get"
                        + col.getName() + "().toString()));");

            } else if (col.getJavaType().equals("java.math.BigDecimal")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Boolean")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Byte")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Short")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Integer")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Float")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("Double")) {
                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");
            } else if (col.getJavaType().equals("byte[]")) {
                out(sb, "\t\tsb.append(JSONHelper.escapeForXMLthis.get" + col.getName() + "()==null?\"\":this.get"
                        + col.getName() + "().toString()));");
            } else if (col.getJavaType().equals("java.sql.Date")) {

                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");

            } else if (col.getJavaType().equals("java.sql.Time")) {

                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString());");

            } else if (col.getJavaType().equals("Long")) {
                out(sb, "\t\tsb.append(JSONHelper.escapeForXML(this.get" + col.getName() + "()==null?\"\":this.get"
                        + col.getName() + "().toString()));");
            } else if (col.getJavaType().equals("java.sql.Timestamp")) {

                out(sb, "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                        + "().toString().substring(0,19));");

            }
            out(sb, "\t\tsb.append(\"\\\" \");");
        }
        out(sb, "		return sb.toString();                   ");
        out(sb, "		}                                       ");

        out(sb, "		/**                                     ");
        out(sb, "		 * 输出对象为XML对象                    ");
        out(sb, "		 * @param tag                           ");
        out(sb, "		 * @return                              ");
        out(sb, "		 */                                     ");
        out(sb, "		public String toXMLString(String tag)   ");
        out(sb, "		{                                       ");
        out(sb, "			StringBuilder sb=new StringBuilder(); ");
        out(sb, "			if(tag==null || tag.equals(\"\"))       ");
        out(sb, "			{                                     ");
        out(sb, "				sb.append(\"<" + table.getName().toUpperCase() + " \");         ");
        out(sb, "			}                                     ");
        out(sb, "			else                                  ");
        out(sb, "			{                                     ");
        out(sb, "				sb.append(\"<\");                     ");
        out(sb, "				sb.append(tag);                     ");
        out(sb, "				sb.append(\" \");                     ");
        out(sb, "			}                                     ");
        out(sb, "			sb.append(toXMLProperty());                                      ");

        out(sb, "			sb.append(\"/>\\r\\n\");                     ");
        out(sb, "			return sb.toString();                 ");
        out(sb, "		}  ");

        out(sb, "}");

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mapway.database2java.model.base.SchemaBase#exportProcedures(com.mapway.
     * database2java.model .base.Configure)
     */
    @Override
    public void exportProcedures(Configure confProcedure) {

        StringBuilder sb1 = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        out(sb, "package " + confProcedure.getPackage() + ".server.database.dao." + "impl;\r\n");
        out(sb, "import java.sql.CallableStatement;");
        out(sb, "import java.sql.Connection;");
        out(sb, "import java.sql.ResultSet;");
        out(sb, "import java.sql.SQLException;\r\n");
        out(sb, "import javax.servlet.jsp.jstl.sql.Result;");
        out(sb, "import javax.servlet.jsp.jstl.sql.ResultSupport;\r\n");
        out(sb, "import " + confProcedure.getPackage() + ".server.database.dao.IProcedures;\r\n");

        out(sb, "import " + confProcedure.getPackage() + ".server.database.base.AccessBase;");
        out(sb, "import " + confProcedure.getPackage() + ".server.database.base.ExecuteResult;");
        out(sb, "import " + confProcedure.getPackage() + ".server.database.base.IConnectionPool;\r\n");

        StringBuilder imports = new StringBuilder();
        StringBuilder t1 = new StringBuilder();
        StringBuilder t2 = new StringBuilder();

        for (int i = 0; i < this.getPackages().getCount(); i++) {
            Pack pack = getPackages().getAt(i);

            Procedures procs = pack.getProcedures();
            for (int j = 0; j < procs.getCount(); j++) {

                Procedure proc = procs.getAt(j);
                imports.append(
                        "\r\nimport " + confProcedure.getPackage() + ".shared.modle.Arg_" + proc.getName() + ";");

                exportProcedureArgument(proc, confProcedure);
                exportProcedure(proc, confProcedure, t1);
                exportProcedureInterface(proc, confProcedure, t2);
                System.out.println("Export Database PL/SQL Package -Procedure " + proc.getName());
            }

        }
        out(sb, imports.toString());
        out(sb, "public class  ProceduresImpl" + " extends AccessBase implements IProcedures{\r\n");

        out(sb, "\tpublic ProceduresImpl" + "(IConnectionPool pool){");
        out(sb, "\t\tsuper(pool);");
        out(sb, "\t}\r\n");

        out(sb, "\tString errMessage=\"\";");
        out(sb, "\tpublic String getErrMessage() {");
        out(sb, "\t\treturn errMessage;");
        out(sb, "\t}\r\n");
        out(sb, "\tpublic void setErrMessage(String errMessage) {");
        out(sb, "\t\tthis.errMessage = errMessage;");
        out(sb, "\t}\r\n");

        out(sb, t1.toString());
        out(sb, "}");

        out(sb1, "package " + confProcedure.getPackage() + ".server.database.dao;\r\n");
        out(sb1, imports.toString());
        out(sb1, "public interface  IProcedures{\r\n");
        out(sb1, t2.toString());
        out(sb1, "}");
        writeToFile(confProcedure.getFilePath() + "/server/database/dao/" + "/impl", "ProceduresImpl" + ".java",
                sb.toString());
        writeToFile(confProcedure.getFilePath() + "/server/database/dao", "IProcedures" + ".java", sb1.toString());
    }

    /**
     * Export procedure argument.
     *
     * @param func the func
     * @param conf the conf
     */
    private void exportProcedureArgument(Procedure func, Configure conf) {
        StringBuilder sb = new StringBuilder();
        out(sb, "package " + conf.getPackage() + ".shared.modle;");
        out(sb, "\r\n");
        out(sb, findImport(func.getArguments()));
        out(sb, "public class Arg_" + func.getName() + "{");

        for (int i = 0; i < func.getArguments().getCount(); i++) {
            Argument arg = func.getArguments().getAt(i);
            out(sb, "\tprivate " + arg.getJavaType() + " " + arg.getName() + ";\r\n");
            out(sb, "\t/**");
            out(sb, "\t * @return " + arg.getName() + " " + arg.getJavaType());
            if (arg.getProperty().equals("IN")) {
                out(sb, "\t * 此参数是传入参数");
            } else if (arg.getProperty().equals("OUT")) {
                out(sb, "\t * 此参数是传出参数 不需要附值");
            } else if (arg.getProperty().equals("IN/OUT")) {
                out(sb, "\t * 此参数是传入 传出 参数");
            }
            out(sb, "\t */");
            out(sb, "\tpublic " + arg.getJavaType() + " get" + arg.getName() + "(){");
            out(sb, "\t\treturn " + arg.getName() + ";");
            out(sb, "\t\t}\r\n");

            out(sb, "\t/**");
            out(sb, "\t * @param " + arg.getName() + " " + arg.getJavaType());
            if (arg.getProperty().equals("IN")) {
                out(sb, "\t * 此参数是传入参数");
            } else if (arg.getProperty().equals("OUT")) {
                out(sb, "\t * 此参数是传出参数 不需要附值");
            } else if (arg.getProperty().equals("IN/OUT")) {
                out(sb, "\t * 此参数是传入 传出 参数");
            }
            out(sb, "\t */");
            out(sb, "\tpublic void set" + arg.getName() + "(" + arg.getJavaType() + " " + arg.getName() + "){");
            out(sb, "\t\tthis." + arg.getName() + "=" + arg.getName() + ";");
            out(sb, "\t\t}\r\n");

            if (arg.getJavaType().equals("MDSYS.SDO_GEOMETRY") || arg.getJavaType().equals("SDO_GEOMETRY")) {
                out(sb, "\tpublic String get" + arg.getName() + "AsWKT(){");
                out(sb, "\tWKT wkt=new WKT();");
                out(sb, "\treturn String(wkt.fromJGeometry(g));");
                out(sb, "}");

                out(sb, "\tpublic void set" + arg.getName() + "AsWKT(String wktString){");
                out(sb, "\tWKT wkt=new WKT();");
                out(sb, "\tthis." + arg.getName() + "= wkt.toJGeometry(wktString.getBytes()));");
                out(sb, "\t\tsetChanged( " + i + " ,true);");
                out(sb, "}");

            }
        }
        out(sb, "}");
        writeToFile(conf.getFilePath() + "/shared/modle", "Arg_" + func.getName() + ".java", sb.toString());
    }

    /**
     * Export procedure interface.
     *
     * @param func the func
     * @param conf the conf
     * @param body the body
     */
    private void exportProcedureInterface(Procedure func, Configure conf, StringBuilder body) {
        StringBuilder sb = new StringBuilder();
        sb.append("\tpublic   boolean " + func.getName() + "(Arg_" + func.getName() + " arg);");
        body.append(sb.toString());
    }

    /**
     * Export procedure.
     *
     * @param func the func
     * @param conf the conf
     * @param body the body
     */
    private void exportProcedure(Procedure func, Configure conf, StringBuilder body) {

        // 构造过程函数
        StringBuilder sb = new StringBuilder();
        sb.append("\tpublic final  boolean " + func.getName() + "(Arg_" + func.getName() + " arg)");
        out(sb, "\t{");
        out(sb, "\t\tboolean b=false;");
        String head = "";
        String que = "";
        for (int i = 0; i < func.getArguments().getCount(); i++) {
            Argument arg = func.getArguments().getAt(i);

            if (arg.getPosition() == 0) {
                head = " ?:= ";
            } else {
                que = que + "?,";
            }
        }

        if (que.length() > 1) {
            que = que.substring(0, que.length() - 1);
        }
        String sche = "";
        if (func.getPackageName().equals("")) {
            sche = "";
        } else {
            sche = func.getPackageName() + ".";
        }
        String sql = "{call " + head + " " + sche + func.getName() + "(" + que + ")}";
        out(sb, "\t\tString sql=\"" + sql + "\";");
        out(sb, "\t\tConnection con= this.ConnectionPool.getConnection();");
        out(sb, "\t\tCallableStatement cstm=null;");
        out(sb, "\t\ttry {");
        out(sb, "\t\t\tcstm= con.prepareCall(sql);");
        for (int i = 0; i < func.getArguments().getCount(); i++) {
            Argument arg = func.getArguments().getAt(i);

            if (arg.getProperty().equals("IN")) {
                out(sb, "\t\t\tcstm.set" + arg.getJavaType2() + "(" + (i + 1) + ",arg.get" + arg.getName() + "());");
            } else if (arg.getProperty().equals("IN/OUT")) {
                out(sb, "\t\t\tcstm.set" + arg.getJavaType2() + "(" + (i + 1) + ",arg.get" + arg.getName() + "());");
                out(sb, "\t\t\tcstm.registerOutParameter(" + (i + 1) + ", " + arg.getJavaType3() + ");");
            } else if (arg.getProperty().equals("OUT")) {
                out(sb, "\t\t\tcstm.registerOutParameter(" + (i + 1) + ", " + arg.getJavaType3() + ");");
            }
        }
        out(sb, "\t\t\tb=cstm.execute();");

        boolean hasREF = false;
        for (int i = 0; i < func.getArguments().getCount(); i++) {
            Argument arg = func.getArguments().getAt(i);

            if (arg.getProperty().equals("IN/OUT")) {
                out(sb, "\t\t\targ.set" + arg.getName() + "(cstm.get" + arg.getJavaType4() + "(" + (i + 1) + "));");

            } else if (arg.getProperty().equals("OUT")) {
                System.out.println("argument  " + arg.getName() + " " + arg.getType());
                if (arg.getType().equals("REF CURSOR")) {
                    if (hasREF == false) {
                        out(sb, "\t\t\tResultSet rs=null;");
                        out(sb, "\t\t\tResult r=null;");
                        hasREF = true;
                    }

                    out(sb, "\t\t\trs=(ResultSet)cstm.getObject(" + (i + 1) + ");");
                    out(sb, "\t\t\tr=ResultSupport.toResult(rs);");
                    out(sb, "\t\t\trs.close();");
                    out(sb, "\t\t\targ.set" + arg.getName() + "(r);");
                } else {
                    out(sb, "\t\t\targ.set" + arg.getName() + "(cstm.get" + arg.getJavaType4() + "(" + (i + 1) + "));");
                }
            }
        }

        out(sb, "\t\t\tcstm.close();");
        out(sb, "\t\t\tb=true;");
        out(sb, "\t\t} catch (SQLException e) {");
        out(sb, "\t\t\tsetErrMessage(e.getMessage());");
        out(sb, "\t\t\te.printStackTrace();");
        out(sb, "\t\t}");
        out(sb, "\t\tfinally{");
        out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
        out(sb, "\t\t}");
        out(sb, "\t\treturn b;");
        out(sb, "\t}\r\n");
        body.append(sb.toString());

    }

    /**
     * Find import.
     *
     * @param arguments the arguments
     * @return the string
     */
    private String findImport(Arguments arguments) {
        StringBuilder imports = new StringBuilder();
        for (int i = 0; i < arguments.getCount(); i++) {
            Argument arg = arguments.getAt(i);
            if (arg.getJavaType().equals("Result")) {
                imports.append("import javax.servlet.jsp.jstl.sql.Result;\r\n"
                        + "import javax.servlet.jsp.jstl.sql.ResultSupport;\r\n");
            } else if (arg.getJavaType().equals("JGeometry")) {

                imports.append("import oracle.spatial.geometry.JGeometry;\r\n");
                imports.append("import oracle.spatial.util.GeometryExceptionWithContext;\r\n");
                imports.append("import oracle.spatial.util.WKT;\r\n");
                imports.append("import oracle.sql.STRUCT;\r\n");
            }
        }

        return imports.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mapway.database2java.model.base.SchemaBase#exportSpringConfigure(com.
     * mapway.database2java .model.base.Configure)
     */
    @Override
    public void exportSpringConfigure(Configure conf) {
        StringBuilder sb = new StringBuilder();
        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
                + "<beans xmlns=\"http://www.springframework.org/schema/beans\"\r\n"
                + "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n"
                + "	xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd\">\r\n"
                + "	<bean id=\"MyPool\" class=\"" + conf.getGwtbase() + ".server.database.base.MyPool\"\r\n"
                + "	factory-method=\"getInstance\">\r\n" + "	<constructor-arg>\r\n"
                + "		<value>MySQL</value>\r\n" + "	</constructor-arg>\r\n" + "	</bean>\r\n";
        sb.append(header);

        Tables ts = this.getTables();

        for (int i = 0; i < ts.getCount(); i++) {
            ITable t = ts.getAt(i);
            sb.append("\t<bean id=\"" + t.getName() + "DAO\" scope=\"prototype\"" + "\tclass=\"" + conf.getPackage()
                    + ".server.database.dao.impl." + t.getJavaAccessName() + "\">\r\n"
                    + "\t\t<constructor-arg ref=\"MyPool\"></constructor-arg>\r\n" + "\t</bean>\r\n");
        }

        sb.append("</beans>");

        writeToFile(conf.getFilePath(), "bean_dao_mysql.xml", sb.toString());

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mapway.database2java.model.base.SchemaBase#exportDwrConfigure(com.mapway.
     * database2java. model.base.Configure)
     */
    @Override
    public void exportDwrConfigure(Configure conf) {
        StringBuilder sb = new StringBuilder();
        String header = "<!DOCTYPE dwr PUBLIC \"-//GetAhead Limited//DTD Direct Web Remoting 3.0//EN\" \"http://directwebremoting.org/schema/dwr30.dtd\">"
                + "<dwr >" + "\t<allow>";

        sb.append(header);

        Tables ts = this.getTables();

        for (int i = 0; i < ts.getCount(); i++) {
            ITable t = ts.getAt(i);
            sb.append("\t\t<convert converter=\"bean\" " + "match=\"" + conf.getPackage() + ".shared.modle."
                    + t.getJavaName() + "\"/>\r\n");
        }

        sb.append("\t</allow>\r\n</dwr>");

        writeToFile(conf.getFilePath(), "dwr_objs.xml", sb.toString());

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        String driverName = "com.mysql.jdbc.Driver";
        String jdbcurl = "jdbc:mysql://10.39.0.150/mapway-wiki";
        String username = "mapway-wiki";
        String pwd = "mapway-wiki";
        String schema0 = "mapway-wiki";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        IConnectionPool pool = new GeneratorPool(driverName, jdbcurl, username, pwd, "", "", 100);
        Configure configure = new Configure();
        configure.setSchema(trans(schema0));
        MySQL_Schema2_gwt schema = new MySQL_Schema2_gwt(pool, configure);
        schema.fetchSchema();
    }

    /**
     * Trans string.
     *
     * @param schema the schema
     * @return the string
     */
    static String trans(String schema) {
        if (schema.contains("-")) {
            return "`" + schema + "`";
        }
        return schema;
    }
}

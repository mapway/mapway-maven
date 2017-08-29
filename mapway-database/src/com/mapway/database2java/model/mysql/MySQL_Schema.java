package com.mapway.database2java.model.mysql;

import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;

import com.mapway.database2java.database.IConnectionPool;
import com.mapway.database2java.model.base.Configure;
import com.mapway.database2java.model.base.SchemaBase;
import com.mapway.database2java.model.schema.Column;
import com.mapway.database2java.model.schema.Columns;
import com.mapway.database2java.model.schema.ITable;
import com.mapway.database2java.model.schema.Table;
import com.mapway.database2java.model.schema.View;

// TODO: Auto-generated Javadoc
/*
 * ============================================================ # ____ _ _ ____ _ _ ____ _ _ ____ _
 * _ ____ _ _ ____ # # __] |__| |__| |\ | | __ | | |__| |\ | [__ |__| |___ # # [___ | | | | | \|
 * |__| _| | | | | \| ___] | | |___ # # http://hi.baidu.com/zhangjianshe #
 * ============================================================
 */

/**
 * The Class MySQL_Schema.
 */
public class MySQL_Schema extends SchemaBase {

  /**
   * �ļ�ͷ�İ�Ȩ˵��.
   *
   * @return the copyright
   */
  @Override
  public String getCopyright() {
    java.util.Date d = new java.util.Date();
    String s =
        "/*******************************************************************************\r\n"
            + "(For MySQL5.1 Java)\r\n" + "\r\n" + " (3.0)\r\n"
            + " EMAIL:zhangjianshe@gmail.com PHONE:15910868680\r\n";
    s += super.getHeader();
    s += "/******************************************************************************/\r\n";
    return s;
  }


  /**
   * Instantiates a new my SQ L schema.
   *
   * @param pool the pool
   * @param configure the configure
   */
  public MySQL_Schema(IConnectionPool pool, Configure configure) {
    super(pool, configure);
    this.setSQLClause(new MySQL_SQLClause(configure.getDatabase()));
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
        tab.setName(tn.toUpperCase());
        tab.setComment(tc);
        cols = tab.getColumns();
        getTables().addTable(tab);
      }
      Column col = new Column();
      col.setName(cn.toUpperCase());
      col.setDatabaseType(ct);
      col.setComment(cc);

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
        view.setName(tn.toUpperCase());
        view.setComment(tc);
        cols = view.getColumns();
        this.getViews().addView(view);

      }

      Column col = new Column();
      col.setName(cn.toUpperCase());
      col.setDatabaseType(ct);
      col.setComment(cc);
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

    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.mapway.database2java.model.base.SchemaBase#exportTable(com.mapway.database2java.model.schema
   * .ITable, com.mapway.database2java.model.base.Configure)
   */
  @Override
  public String exportTable(ITable table, Configure conf) {
    StringBuilder sb = new StringBuilder();
    out(sb, getCopyright());
    writeJavaBean(table, sb, conf);
    writeToFile(conf.getFilePath(), table.getName() + "Obj.java", sb.toString());
    sb = new StringBuilder();
    out(sb, getCopyright());
    writeJavaObjs(table, sb, conf);
    writeToFile(conf.getFilePath(), table.getName() + "Objs.java", sb.toString());
    sb = new StringBuilder();
    out(sb, getCopyright());
    writeJavaAccess(table, sb, conf);
    writeToFile(conf.getFilePath(), table.getName() + "Access.java", sb.toString());
    return "";
  }

  /**
   * Write java bean.
   *
   * @param table the table
   * @param sb the sb
   * @param conf the conf
   */
  private void writeJavaBean(ITable table, StringBuilder sb, Configure conf) {
    out(sb, "/**");
    out(sb, " * @author ZhangJianshe@gmail.com");
    out(sb, " *  " + table.getComment());
    out(sb, " */");
    out(sb, "package " + conf.getPackage() + ";\r\n");
    String s = conf.getPackage();
    int index = s.lastIndexOf(".");
    s = s.substring(0, index) + ".json";
    out(sb, "import " + s + ".JSONHelper;");
    out(sb, "public class " + table.getJavaName() + " implements java.io.Serializable{");
    out(sb, "\tprivate boolean[] m_changed;\r\n");
    out(sb, "\t/**");
    out(sb, "\t* ���캯��");
    out(sb, "\t*/");
    out(sb, "\tpublic " + table.getJavaName() + "(){");
    out(sb, "\t\tm_changed = new boolean[" + table.getColumns().getCount() + "];");
    out(sb, "\t\tresetChanged(false);");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t* ȫ�������ֶ��Ƿ񱻸�Ĺ�");
    out(sb, "\t* @param b");
    out(sb, "\t*/");
    out(sb, "\tpublic void resetChanged(boolean b){");
    out(sb, "\t\tfor(int i=0;i<" + table.getColumns().getCount() + ";i++){");
    out(sb, "\t\t\tm_changed[i]=b;");
    out(sb, "\t\t}");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t* �����ֶ��Ƿ񱻸�Ĺ�");
    out(sb, "\t* @param index");
    out(sb, "\t* @param b Boolean");
    out(sb, "\t*/");
    out(sb, "\tpublic void setChanged(int index,boolean b){");
    out(sb, "\t\tm_changed[index]=b;");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t* ��ȡ�ֶ��Ƿ񱻸�Ĺ�");
    out(sb, "\t* @param index");
    out(sb, "\t* @return");
    out(sb, "\t*/");
    out(sb, "\tpublic boolean getChanged(int index){");
    out(sb, "\t\treturn m_changed[index];");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t*���л�uid");
    out(sb, "\t*/");
    out(sb, "\tprivate static final long serialVersionUID = 1L;");
    out(sb, "\t/**");
    out(sb, "\t*�������л�uid");
    out(sb, "\t*/");
    out(sb, "\tstatic long getSerialVersionUID() {");
    out(sb, "\treturn serialVersionUID;");
    out(sb, "\t}");
    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      out(sb, "\tprivate " + col.getJavaType() + " " + col.getName() + ";\r\n");
      out(sb, "\t/**");
      out(sb,
          "\t * @return " + col.getName() + "  " + col.getComment() + "  " + col.getDatabaseType());
      out(sb, "\t */");
      out(sb, "\tpublic " + col.getJavaType() + " get" + col.getName() + "(){");
      out(sb, "\t\treturn " + col.getName() + ";");
      out(sb, "\t\t}\r\n");
      out(sb, "\t/**");
      out(sb,
          "\t * @param " + col.getName() + "  " + col.getComment() + "  " + col.getDatabaseType());
      out(sb, "\t */");
      out(sb, "\tpublic void set" + col.getName() + "(" + col.getJavaType() + " " + col.getName()
          + "){");
      out(sb, "\t\tthis." + col.getName() + "=" + col.getName() + ";");
      out(sb, "\t\tsetChanged( " + i + " ,true);");
      out(sb, "\t\t}\r\n");
    }

    out(sb, "\tpublic void copyTo(" + table.getJavaName() + " " + table.getJavaName().toLowerCase()
        + "){");
    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      out(sb, "\t\t" + table.getJavaName().toLowerCase() + "." + col.getName() + " = " + "this."
          + col.getName() + ";");
    }
    out(sb, "\t}");

    out(sb, "\tpublic void mergeTo(" + table.getJavaName() + " "
        + table.getJavaName().toLowerCase() + "){");
    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      out(sb, "\t\tif(this.getChanged(" + i + ")==true){" + table.getJavaName().toLowerCase() + "."
          + col.getName() + " = " + "this." + col.getName() + ";}");
    }
    out(sb, "\t}");

    out(sb, "\tpublic String toString(){");
    out(sb, "\t\tStringBuilder sb=new StringBuilder();");
    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      out(sb,
          "\t\tsb.append(this.get" + col.getName() + "()==null?\"----\":this.get" + col.getName()
              + "().toString());");
      out(sb, "\t\tsb.append(\" | \");");
    }
    out(sb, "\treturn sb.toString();");
    out(sb, "\t}\r\n");
    out(sb, "\tpublic String toJSONString(){");
    out(sb, "\t\tStringBuilder sb=new StringBuilder();");
    out(sb, "\t\tsb.append(\"{\");");
    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      out(sb, "\t\tsb.append(\"" + col.getName() + ":\");");
      if (col.getJavaType().equals("String")) {
        out(sb, "\t\tsb.append(\"\\\"\");");
        out(sb, "\t\tsb.append(JSONHelper.escape(this.get" + col.getName()
            + "()==null?\"null\":this.get" + col.getName() + "().toString()));");
        out(sb, "\t\tsb.append(\"\\\"\");");
      } else if (col.getJavaType().equals("java.math.BigDecimal")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("Boolean")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("Byte")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("Short")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("Integer")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("Float")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("Double")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("byte[]")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("java.sql.Date")) {
        out(sb, "sb.append(\"\\\"\");");
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
        out(sb, "sb.append(\"\\\"\");");
      } else if (col.getJavaType().equals("java.sql.Time")) {
        out(sb, "sb.append(\"\\\"\");");
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
        out(sb, "sb.append(\"\\\"\");");
      } else if (col.getJavaType().equals("Long")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("java.sql.Timestamp")) {
        out(sb, "sb.append(\"\\\"\");");
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
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
   * @param sb the sb
   * @param conf the conf
   */
  private void writeJavaObjs(ITable table, StringBuilder sb, Configure conf) {
    out(sb, "package " + conf.getPackage() + ";\r\n");
    out(sb, "import java.util.ArrayList;\r\n");
    out(sb, "/**");
    out(sb, " * @author ZhangJianshe@gmail.com");
    out(sb, " * " + table.getComment());
    out(sb, " */");
    out(sb, "public class " + table.getJavaNames() + "{\r\n\r\n");
    out(sb, "\tArrayList<" + table.getJavaName() + "> ObjList;\r\n");
    out(sb, "\t/**");
    out(sb, "\t * ���캯��");
    out(sb, "\t */");
    out(sb, "\tpublic " + table.getJavaNames() + "()");
    out(sb, "\t{");
    out(sb, "\t\tObjList=new ArrayList<" + table.getJavaName() + ">();");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t * @return ArrayList<" + table.getJavaName() + ">");
    out(sb, "\t */");
    out(sb, "\tpublic ArrayList<" + table.getJavaName() + "> getObjList()");
    out(sb, "\t{");
    out(sb, "\t\treturn this.ObjList;");
    out(sb, "\t}");
    out(sb, "\t/**");
    out(sb, "\t * @return int");
    out(sb, "\t * ��ȡ�б����ݼ�¼��");
    out(sb, "\t */");
    out(sb, "\tpublic int getCount()");
    out(sb, "\t{");
    out(sb, "\t\treturn this.ObjList.size();");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t * @param index");
    out(sb, "\t * @return " + table.getJavaName());
    out(sb, "\t * ��ȡ�б������ index ��¼");
    out(sb, "\t */");
    out(sb, "\tpublic " + table.getJavaName() + " getAt(int index)");
    out(sb, "\t{");
    out(sb, "\t\treturn this.ObjList.get(index);");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t * ��ռ�¼");
    out(sb, "\t */");
    out(sb, "\tpublic void clear()");
    out(sb, "\t{");
    out(sb, "\t\t this.ObjList.clear();");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t * @param " + table.getJavaName() + " " + table.getJavaName().toLowerCase());
    out(sb, "\t * ��Ӽ�¼");
    out(sb, "\t */");
    out(sb, "\tpublic void add(" + table.getJavaName() + " " + table.getJavaName().toLowerCase()
        + ")");
    out(sb, "\t{");
    out(sb, "\t\t this.ObjList.add(" + table.getJavaName().toLowerCase() + ");");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t * ���������ı�");
    out(sb, "\t * @return String");
    out(sb, "\t */");
    out(sb, "\tpublic String toString(){");
    out(sb, "\t\tStringBuilder sb=new StringBuilder();");
    out(sb, "\t\tfor(int i=0;i<this.ObjList.size();i++){");
    out(sb, "\t\t\tsb.append(this.ObjList.get(i).toString());");
    out(sb, "\t\t\tsb.append(\"\\r\\n\");");
    out(sb, "\t\t}");
    out(sb, "\t\treturn sb.toString();");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t * ��������JSON�ı�");
    out(sb, "\t * @return String");
    out(sb, "\t */");
    out(sb, "\tpublic String toJSONString(){");
    out(sb, "\t\tif(this.ObjList.size()>0)");
    out(sb, "\t\t{");
    out(sb, "\t\tStringBuilder sb=new StringBuilder();");
    out(sb, "\t\t\tsb.append(\"[\");");
    out(sb, "\t\tfor(int i=0;i<this.ObjList.size();i++){");
    out(sb, "\t\t\tsb.append(this.ObjList.get(i).toJSONString());");
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
    out(sb, "}");
  }

  /**
   * Write java access.
   *
   * @param table the table
   * @param sb the sb
   * @param conf the conf
   */
  private void writeJavaAccess(ITable table, StringBuilder sb, Configure conf) {
    out(sb, "package " + conf.getPackage() + ";\r\n");
    for (int i = 0; i < table.getColumns().getCount(); i++) {
      if (table.getColumns().getAt(i).getJavaType().equals("byte[]")) {
        out(sb, "import java.sql.Blob;");
        break;
      }
    }
    out(sb, "import java.sql.Connection;");
    out(sb, "import java.sql.ResultSet;");
    out(sb, "import java.sql.SQLException;");
    out(sb, "import java.sql.PreparedStatement;");
    out(sb, "import java.math.BigDecimal;");
    out(sb, "import javax.servlet.jsp.jstl.sql.Result;");
    out(sb, "import javax.servlet.jsp.jstl.sql.ResultSupport;\r\n");
    String pac = conf.getPackage();
    int pos = pac.lastIndexOf('.');

    String p = pac.substring(0, pos);
    pos = p.lastIndexOf('.');
    p = p.substring(0, pos);
    out(sb, "import " + p + ".base.AccessBase;");
    out(sb, "import " + p + ".base.ExecuteResult;");
    out(sb, "import " + p + ".base.IConnectionPool;\r\n");
    out(sb, "/**");
    out(sb, " * @author ZhangJianshe@gmail.com");
    out(sb, " * " + table.getComment() + "  ������ݿ���");
    out(sb, " */");
    out(sb, "public class " + table.getJavaAccessName() + " extends AccessBase {\r\n");
    out(sb, "\tpublic " + table.getJavaAccessName() + "(IConnectionPool pool){");
    out(sb, "\t\tsuper(pool);");
    out(sb, "\t\tm_searched=new boolean[" + table.getColumns().getCount() + "];");

    out(sb, "\t\tresetSearched(true);");
    out(sb, "\t}\r\n");
    out(sb, "\tprivate boolean[] m_searched;\r\n");

    out(sb, "\t/**");
    out(sb, "\t * �����Ƿ������ֶα�־");
    out(sb, "\t * @param b");
    out(sb, "\t */");
    out(sb, "\tpublic void resetSearched(boolean b){");
    out(sb, "\t\tfor(int i=0;i<" + table.getColumns().getCount() + ";i++){");
    out(sb, "\t\t\tm_searched[i]=b;");
    out(sb, "\t\t}");
    out(sb, "\t}");
    out(sb, "\t/**");
    out(sb, "\t * ��ȡ�������м�¼");
    out(sb, "\t * @return Result");
    out(sb, "\t * @throws SQLException");
    out(sb, "\t */");
    out(sb, "\tpublic Result selectAll2Result() throws SQLException{");
    out(sb, "\t\treturn selectByWhereTemplate2(\"\");");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t * ��ȡ�������м�¼");
    out(sb, "\t * @return " + table.getJavaNames());
    out(sb, "\t * @throws SQLException");
    out(sb, "\t */");
    out(sb, "\tpublic " + table.getJavaNames() + " selectAll() throws SQLException{");
    out(sb, "\t\treturn selectByWhereTemplate(\"\");");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t* ��ResultSet�е�һ����¼ת��Ϊ����");
    out(sb, "\t* @Param ResultSet rs");
    out(sb, "\t* @return " + table.getJavaName());
    out(sb, "\t* @throws SQLException");
    out(sb, "\t*/");
    out(sb, "\tpublic " + table.getJavaName() + " mapRecord2Obj(ResultSet rs) throws SQLException");
    out(sb, "\t{");
    out(sb, "\t	" + table.getJavaName() + " o=new " + table.getJavaName() + "();");
    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      if (col.getJavaType().equals("byte[]")) {
        out(sb, "\t\tif(true ==m_searched[" + i + "]) o.set" + col.getName()
            + "(bytesFromBlob(rs.getBlob(\"" + col.getName() + "\")));");
      } else {
        out(sb,
            "\t\tif(true ==m_searched[" + i + "]) o.set" + col.getName() + "(rs.get"
                + col.getJavaType2() + "(\"" + col.getName() + "\"));");
      }
    }
    out(sb, "\t\to.resetChanged(false);");
    out(sb, "\t\treturn o;");
    out(sb, "\t}\r\n");
    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      out(sb, "\t/**");
      out(sb, "\t* �����ֶ� " + col.getComment() + " [" + col.getName() + "] �Ƿ��ѯ");
      out(sb, "\t* @Param boolean");
      out(sb, "\t*/");
      out(sb, "\tpublic void setSearched" + col.getName() + "( boolean b ){");
      out(sb, "\t\tm_searched[ " + i + " ] = b;");
      out(sb, "\t}\r\n");
    }

    out(sb, "\t/**");
    out(sb, "\t* ���������ֶ�");
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
    out(sb, "\t* ���Where����ģ���ѯ��¼ ������߲�ѯЧ��");
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
    out(sb, "\t* ���Where����ģ���ѯ��¼ ������߲�ѯЧ�� ���صĽ��<p>");
    out(sb, "\t* ExecuteResult  �����������ֵ�м�Ҫ���з���ֵ��飬���ͷ���Դ");
    out(sb, "\t* <code>if(rs!=null){.... rs.dispose();}</code>");
    out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
    out(sb, "\t* @param whereTemplate");
    out(sb, "\t* @param values");
    out(sb, "\t* @see ExecuteResult");
    out(sb, "\t*/");
    out(sb,
        "\tpublic ExecuteResult selectByWhereTemplate2ExetureResult  (String whereTemplate,Object...values) {");
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
    out(sb, "\t* ��where����ȡ���ļ�¼���з��� start < X < end �ļ�¼");
    out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
    out(sb, "\t* @param whereTemplate");
    out(sb, "\t* @param values");
    out(sb, "\t* @param start");
    out(sb, "\t* @param end");
    out(sb, "\t* @return " + table.getJavaNames());
    out(sb, "\t* @throw SQLException");
    out(sb, "\t*/");
    out(sb,
        "\tpublic "
            + table.getJavaNames()
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
    out(sb, "\t\t    sql.append(end);");

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
    out(sb, "\t* ���Where����ģ���ѯ��¼����");
    out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
    out(sb, "\t* @param whereTemplate");
    out(sb, "\t* @param values");
    out(sb, "\t* @return long");
    out(sb, "\t* @throw SQLException");
    out(sb, "\t*/");
    out(sb,
        "\tpublic long getCountByWhereTemplate  (String whereTemplate,Object...values) throws SQLException{");
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
    out(sb, "\t* ���Where����ģ��ɾ���¼ ������߲�ѯЧ��");
    out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
    out(sb, "\t* @param whereTemplate");
    out(sb, "\t* @param boolean");
    out(sb, "\t* @throw SQLException");
    out(sb, "\t*/");
    out(sb,
        "\tpublic boolean deleteByWhereTemplate  (String whereTemplate,Object...values) throws SQLException{");
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
    out(sb, "\t* ���Where����ģ���ѯ��¼ ������߲�ѯЧ��");
    out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
    out(sb, "\t* @param whereTemplate");
    out(sb, "\t* @param Result");
    out(sb, "\t* @throw SQLException");
    out(sb, "\t*/");
    out(sb,
        "\tpublic Result selectByWhereTemplate2  (String whereTemplate,Object...values) throws SQLException{");
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
    out(sb, "\t* ��������ȡ" + table.getComment() + "[" + table.getName() + "]�еĵ�����¼");
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
      out(sb, "public " + table.getJavaName() + " selectByPK  (" + param.toString()
          + ") throws SQLException{");
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
          out(sb, "\t\t\tstatement.set" + col.getJavaType2() + "(" + index + ","
              + col.getName().toLowerCase() + ");");
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
      out(sb, "\t* ���ORACLE ����ɾ��" + table.getComment() + "[" + table.getName() + "]�еĵ�����¼");
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
    out(sb, "\t* ����ݿ��в���һ������");
    out(sb, "\t* @param " + table.getName());
    out(sb, "\t*/");
    out(sb, "\tpublic boolean insertObject  (" + table.getJavaName() + " "
        + table.getJavaName().toLowerCase() + " ) throws SQLException{");
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
    out(sb, "\t\t\tstatement = con.prepareStatement(sql.toString());");
    int cur = 0;
    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      if (col.getJavaType().equals("byte[]")) {
        out(sb, "\t\t\tBlob blob" + String.valueOf(cur)
            + "=oracle.sql.BLOB.createTemporary(con, true, oracle.sql.BLOB.DURATION_SESSION);");
        out(sb, "\t\t\tblob" + String.valueOf(cur) + ".setBytes(1, "
            + table.getJavaName().toLowerCase() + ".get" + col.getName() + "());");
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
          out(sb, "\t\t\tif(" + table.getJavaName().toLowerCase() + ".getChanged( " + i
              + " ) ==false ){ " + table.getJavaName().toLowerCase() + ".set" + col.getName()
              + "(rs.get" + col.getJavaType2() + "(index1++));}");

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
    out(sb, "\t* ����ݿ��в���������");
    out(sb, "\t* @param " + table.getJavaNames().toLowerCase());
    out(sb, "\t* @return boolean");
    out(sb, "\t*/");
    out(sb, "\tpublic boolean insertObjects  (" + table.getJavaNames() + " "
        + table.getJavaNames().toLowerCase() + " ) throws SQLException{");
    out(sb, "\t\tboolean b = false;");
    out(sb, "\t\tfor(int i=0;i<" + table.getJavaNames().toLowerCase() + ".getCount();i++){");
    out(sb, "\t\t\tthis.insertObject(" + table.getJavaNames().toLowerCase() + ".getAt(i));");
    out(sb, "\t\t}");
    out(sb, "\t\tb=true;");
    out(sb, "\t\treturn b;");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t* ����ݿ��и��¶������");
    out(sb, "\t* @param " + table.getJavaNames().toLowerCase());
    out(sb, "\t* @return boolean");
    out(sb, "\t*/");
    out(sb, "\tpublic boolean updateObjectsByPK  (" + table.getJavaNames() + " "
        + table.getJavaNames().toLowerCase() + " ) throws SQLException{");
    out(sb, "\t\tboolean b = false;");
    out(sb, "\t\tfor(int i=0;i<" + table.getJavaNames().toLowerCase() + ".getCount();i++){");
    out(sb, "\t\t\tthis.updateObjectByPK(" + table.getJavaNames().toLowerCase() + ".getAt(i));");
    out(sb, "\t\t}");
    out(sb, "\t\tb=true;");
    out(sb, "\t\treturn b;");
    out(sb, "\t}\r\n");
    out(sb, "\t/** ");
    out(sb, "\t* ����ݿ��и���һ������ ");
    out(sb, "\t* @param " + table.getName());
    out(sb, "\t*/  ");
    out(sb, "\tpublic boolean updateObjectByPK  (" + table.getJavaName() + " "
        + table.getJavaName().toLowerCase() + " ) throws SQLException{");
    out(sb, "\t\tStringBuilder sql=new StringBuilder();");
    out(sb, "\t\tsql.append(\"UPDATE `" + table.getName() + "` SET \");");
    out(sb, "\t\tint index=1;");
    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      if (!col.isPK()) {
        System.out.println("\tCOLUMN " + i + ":" + col.isPK() + ":" + col.getName());
        out(sb, "\t\t\tif(" + table.getJavaName().toLowerCase() + ".getChanged( " + i
            + " ) ==true ) sql.append(\"" + col.getName() + " =?,\");");
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
          out(sb, "\t\t\tblob" + String.valueOf(cur) + ".setBytes(1, "
              + table.getJavaName().toLowerCase() + ".get" + col.getName() + "());");
          out(sb, "\t\t\tif(" + table.getJavaName().toLowerCase() + ".getChanged( " + i
              + " ) ==true ){ statement.setBlob(index, blob" + String.valueOf(cur++)
              + ");index++;}");
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
        out(sb, "\t\t\tstatement.set" + col.getJavaType2() + "(index, "
            + table.getJavaName().toLowerCase() + ".get" + col.getName() + "());index++;");
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
    out(sb, "\t* ���ģ��SQL��������ݿ�ļ�¼  ");
    out(sb, "\t* <code>sqlTemplate=\"FIELDA =? ,FIELDB=? where FIELDC=?\";</code> ");
    out(sb, "\t* @param sqlTemplate ");
    out(sb, "\t* @param values  ");
    out(sb, "\t* @return boolean ");
    out(sb, "\t*/ ");
    out(sb,
        "\tpublic boolean updateBySQLTemplate  (String sqlTemplate,Object...values)  throws SQLException{");
    out(sb, "\t\tboolean b=false; ");
    out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
    out(sb, "\t\tif( con==null)return false;");
    out(sb, "\t\tPreparedStatement statement=null;");
    out(sb, "\t\ttry{");
    out(sb, "\t\t\tstatement = con.prepareStatement(\"UPDATE `" + table.getName()
        + "` SET \"+sqlTemplate);");
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
   * ExportView View has only the following functions selectAll selectByWhereTemplate
   * selectByWhereTemplate2.
   *
   * @param at the at
   * @param confView the conf view
   */
  @Override
  public void exportViews(View at, Configure confView) {
    StringBuilder sb = new StringBuilder();
    out(sb, getCopyright());
    WriteViewBean(at, sb, confView);
    writeToFile(confView.getFilePath(), at.getName() + "Obj.java", sb.toString());
    sb = new StringBuilder();
    out(sb, getCopyright());
    WriteViewObjs(at, sb, confView);
    writeToFile(confView.getFilePath(), at.getName() + "Objs.java", sb.toString());
    sb = new StringBuilder();
    out(sb, getCopyright());
    WriteViewAccess(at, sb, confView);
    writeToFile(confView.getFilePath(), at.getName() + "Access.java", sb.toString());

  }

  /**
   * Write view access.
   *
   * @param table the table
   * @param sb the sb
   * @param conf the conf
   */
  private void WriteViewAccess(View table, StringBuilder sb, Configure conf) {
    out(sb, "package " + conf.getPackage() + ";\r\n");
    out(sb, "import java.sql.Connection;");
    out(sb, "import java.sql.ResultSet;");
    out(sb, "import java.sql.SQLException;");
    out(sb, "import java.sql.PreparedStatement;");
    out(sb, "import java.math.BigDecimal;");
    out(sb, "import javax.servlet.jsp.jstl.sql.Result;");
    out(sb, "import javax.servlet.jsp.jstl.sql.ResultSupport;\r\n");
    String pac = conf.getPackage();
    int pos = pac.lastIndexOf('.');
    String p = pac.substring(0, pos);

    pos = p.lastIndexOf('.');
    p = p.substring(0, pos);

    out(sb, "import " + p + ".base.AccessBase;");
    out(sb, "import " + p + ".base.ExecuteResult;");
    out(sb, "import " + p + ".base.IConnectionPool;\r\n");

    out(sb, "/**");
    out(sb, " * @author ZhangJianshe@gmail.com");
    out(sb, " * " + table.getComment() + "  ������ݿ���");
    out(sb, " */");
    out(sb, "public class " + table.getJavaAccessName() + " extends AccessBase {\r\n");
    out(sb, "\tpublic " + table.getJavaAccessName() + "(IConnectionPool pool){");
    out(sb, "\t\tsuper(pool);");
    out(sb, "\t\tm_searched=new boolean[" + table.getColumns().getCount() + "];");
    out(sb, "\t\tm_SearchedROWID=true;");
    out(sb, "\t\tresetSearched(true);");
    out(sb, "\t}\r\n");
    out(sb, "\tprivate boolean[] m_searched;\r\n");
    out(sb, "\tprivate boolean m_SearchedROWID;\r\n");

    out(sb, "\t/**");
    out(sb, "\t * �����Ƿ������ֶα�־");
    out(sb, "\t * @param b");
    out(sb, "\t */");
    out(sb, "\tpublic void resetSearched(boolean b){");
    out(sb, "\t\tfor(int i=0;i<" + table.getColumns().getCount() + ";i++){");
    out(sb, "\t\t\tm_searched[i]=b;");
    out(sb, "\t\t}");
    out(sb, "\t}");

    out(sb, "\t/**");
    out(sb, "\t * ��ȡ�������м�¼");
    out(sb, "\t * @return Result");
    out(sb, "\t * @throws SQLException");
    out(sb, "\t */");
    out(sb, "\tpublic Result selectAll2Result() throws SQLException{");
    out(sb, "\t\treturn selectByWhereTemplate2(\"\");");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t * ��ȡ�������м�¼");
    out(sb, "\t * @return " + table.getJavaNames());
    out(sb, "\t * @throws SQLException");
    out(sb, "\t */");
    out(sb, "\tpublic " + table.getJavaNames() + " selectAll() throws SQLException{");
    out(sb, "\t\treturn selectByWhereTemplate(\"\");");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t* ��ResultSet�е�һ����¼ת��Ϊ����");
    out(sb, "\t* @Param ResultSet rs");
    out(sb, "\t* @return " + table.getJavaName());
    out(sb, "\t* @throws SQLException");
    out(sb, "\t*/");
    out(sb, "\tprivate " + table.getJavaName() + " mapRecord2Obj(ResultSet rs) throws SQLException");
    out(sb, "\t{");
    out(sb, "\t	" + table.getJavaName() + " o=new " + table.getJavaName() + "();");

    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      out(sb,
          "\t\tif(true ==m_searched[" + i + "]) o.set" + col.getName() + "(rs.get"
              + col.getJavaType2() + "(\"" + col.getName() + "\"));");
    }
    out(sb, "\t\tif(true ==m_SearchedROWID) o.setROWID(rs.getString(\"ROWID\"));");
    out(sb, "\t\to.resetChanged(false);");
    out(sb, "\t\treturn o;");
    out(sb, "\t}\r\n");

    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);

      out(sb, "\t/**");
      out(sb, "\t* �����ֶ� " + col.getComment() + " [" + col.getName() + "] �Ƿ��ѯ");
      out(sb, "\t* @Param boolean");
      out(sb, "\t*/");
      out(sb, "\tpublic void setSearched" + col.getName() + "( boolean b ){");
      out(sb, "\t\tm_searched[ " + i + " ] = b;");
      out(sb, "\t}\r\n");
    }

    out(sb, "\t/**");
    out(sb, "\t* ���������ֶ�");
    out(sb, "\t* @return String");
    out(sb, "\t*/");
    out(sb, "\tprivate String searchField(){");
    out(sb, "\t\tStringBuilder sb=new StringBuilder();");

    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      out(sb, "\t\tif( true == m_searched[" + i + "]){sb.append(\"`" + col.getName() + "` ,\");}");
    }
    out(sb, "\t\tsb.deleteCharAt(sb.length()-1);");
    out(sb, "\t\treturn sb.toString();");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t* ���Where����ģ���ѯ��¼ ������߲�ѯЧ�� ���صĽ��<p>");
    out(sb, "\t* ExecuteResult  �����������ֵ�м�Ҫ���з���ֵ��飬���ͷ���Դ");
    out(sb, "\t* <code>if(rs!=null){.... rs.dispose();}</code>");
    out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
    out(sb, "\t* @param whereTemplate");
    out(sb, "\t* @param values");
    out(sb, "\t* @see ExecuteResult");
    out(sb, "\t*/");
    out(sb,
        "\tpublic ExecuteResult selectByWhereTemplate2ExetureResult  (String whereTemplate,Object...values) {");
    out(sb, "\t\tStringBuilder sql=new StringBuilder();");
    out(sb, "\t\tsql.append(\"SELECT \");");
    out(sb, "\t\tsql.append(searchField());");
    out(sb, "\t\tsql.append(\" FROM \\\"" + table.getName() + "\\\" \");");
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

    out(sb, "\t\t\treturn new ExecuteResult(this.ConnectionPool,con,rs,statement);");
    out(sb, "\t\t\t}");
    out(sb, "\t\t\tcatch (SQLException e) {");
    out(sb, "\t\t\te.printStackTrace();");
    out(sb, "\t\t\tthis.ConnectionPool.releaseConnection(con);");
    out(sb, "\t\t\treturn null;");
    out(sb, "\t\t\t}");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t* ���Where����ģ���ѯ��¼ ������߲�ѯЧ��");
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
    out(sb, "\t* ��where����ȡ���ļ�¼���з��� start < X < end �ļ�¼");
    out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
    out(sb, "\t* @param whereTemplate");
    out(sb, "\t* @param values");
    out(sb, "\t* @param start");
    out(sb, "\t* @param end");
    out(sb, "\t* @return " + table.getJavaNames());
    out(sb, "\t* @throw SQLException");
    out(sb, "\t*/");
    out(sb,
        "\tpublic "
            + table.getJavaNames()
            + " getSpecificRecordByWhereTemplate  (String whereTemplate,int start, int end, Object...values) throws SQLException{");
    out(sb, "\t\tStringBuilder sql=new StringBuilder();");

    out(sb, "\t\t    sql.append(\"select \");");
    out(sb, "\t\t    sql.append(searchField());");
    out(sb, "\t\t    sql.append(\" from `\");");
    out(sb, "\t\t    sql.append(\"" + table.getName() + "` \"); ");
    out(sb, "\t\t    sql.append(whereTemplate);");
    out(sb, "\t\t    sql.append(\" limit \");");
    out(sb, "\t\t    sql.append(start);");
    out(sb, "\t\t    sql.append(\",\");");
    out(sb, "\t\t    sql.append(end);");

    out(sb, "\t\t" + table.getJavaNames() + " os=new " + table.getJavaNames() + "();");
    out(sb, "\t\tConnection con=this.ConnectionPool.getConnection();");
    out(sb, "\t\tif( con==null)return null;");
    out(sb, "\t\tPreparedStatement statement=null;");
    out(sb, "\t\ttry {");
    out(sb, "\t\t\tString s=sql.toString();");
    // (sb, "\t\t\tLog(s);");
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
    out(sb, "\t* ���Where����ģ���ѯ��¼����");
    out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
    out(sb, "\t* @param whereTemplate");
    out(sb, "\t* @param values");
    out(sb, "\t* @return long");
    out(sb, "\t* @throw SQLException");
    out(sb, "\t*/");
    out(sb,
        "\tpublic long getCountByWhereTemplate  (String whereTemplate,Object...values) throws SQLException{");
    out(sb, "\t\tlong count=0;");
    out(sb, "\t\tStringBuilder sql=new StringBuilder();");
    out(sb, "\t\tsql.append(\"SELECT \");");

    out(sb, "\t\tsql.append(\"count(" + table.getColumns().getAt(0).getName() + ")\");");

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
    out(sb, "\t* ���Where����ģ���ѯ��¼ ������߲�ѯЧ��");
    out(sb, "\t* <code>whereTemplate=\"where a=? or b=?\";</code>");
    out(sb, "\t* @param whereTemplate");
    out(sb, "\t* @param Result");
    out(sb, "\t* @throw SQLException");
    out(sb, "\t*/");
    out(sb,
        "\tpublic Result selectByWhereTemplate2  (String whereTemplate,Object...values) throws SQLException{");
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
   * @param sb the sb
   * @param conf the conf
   */
  private void WriteViewObjs(View table, StringBuilder sb, Configure conf) {
    out(sb, "package " + conf.getPackage() + ";\r\n");
    out(sb, "import java.util.ArrayList;\r\n");

    out(sb, "/**");
    out(sb, " * @author ZhangJianshe@gmail.com");
    out(sb, " * " + table.getComment());
    out(sb, " */");
    out(sb, "public class " + table.getJavaNames() + "{\r\n\r\n");
    out(sb, "\tArrayList<" + table.getJavaName() + "> ObjList;\r\n");

    out(sb, "\t/**");
    out(sb, "\t * ���캯��");
    out(sb, "\t */");
    out(sb, "\tpublic " + table.getJavaNames() + "()");
    out(sb, "\t{");
    out(sb, "\t\tObjList=new ArrayList<" + table.getJavaName() + ">();");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t * @return ArrayList<" + table.getJavaName() + ">");
    out(sb, "\t */");
    out(sb, "\tpublic ArrayList<" + table.getJavaName() + "> getObjList()");
    out(sb, "\t{");
    out(sb, "\t\treturn this.ObjList;");
    out(sb, "\t}");

    out(sb, "\t/**");
    out(sb, "\t * @return int");
    out(sb, "\t * ��ȡ�б����ݼ�¼��");
    out(sb, "\t */");
    out(sb, "\tpublic int getCount()");
    out(sb, "\t{");
    out(sb, "\t\treturn this.ObjList.size();");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t * @param index");
    out(sb, "\t * @return " + table.getJavaName());
    out(sb, "\t * ��ȡ�б������ index ��¼");
    out(sb, "\t */");
    out(sb, "\tpublic " + table.getJavaName() + " getAt(int index)");
    out(sb, "\t{");
    out(sb, "\t\treturn this.ObjList.get(index);");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t * ��ռ�¼");
    out(sb, "\t */");
    out(sb, "\tpublic void clear()");
    out(sb, "\t{");
    out(sb, "\t\t this.ObjList.clear();");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t * @param " + table.getJavaName() + " " + table.getJavaName().toLowerCase());
    out(sb, "\t * ��Ӽ�¼");
    out(sb, "\t */");
    out(sb, "\tpublic void add(" + table.getJavaName() + " " + table.getJavaName().toLowerCase()
        + ")");
    out(sb, "\t{");
    out(sb, "\t\t this.ObjList.add(" + table.getJavaName().toLowerCase() + ");");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t * ���������ı�");
    out(sb, "\t * @return String");
    out(sb, "\t */");
    out(sb, "\tpublic String toString(){");
    out(sb, "\t\tStringBuilder sb=new StringBuilder();");
    out(sb, "\t\tfor(int i=0;i<this.ObjList.size();i++){");
    out(sb, "\t\t\tsb.append(this.ObjList.get(i).toString());");
    out(sb, "\t\t\tsb.append(\"\\r\\n\");");
    out(sb, "\t\t}");
    out(sb, "\t\treturn sb.toString();");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t * ��������JSON�ı�");
    out(sb, "\t * @return String");
    out(sb, "\t */");
    out(sb, "\tpublic String toJSONString(){");
    out(sb, "\t\tif(this.ObjList.size()>0)");
    out(sb, "\t\t{");
    out(sb, "\t\tStringBuilder sb=new StringBuilder();");
    out(sb, "\t\t\tsb.append(\"[\");");
    out(sb, "\t\tfor(int i=0;i<this.ObjList.size();i++){");
    out(sb, "\t\t\tsb.append(this.ObjList.get(i).toJSONString());");
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
    out(sb, "}");

  }

  /**
   * Write view bean.
   *
   * @param table the table
   * @param sb the sb
   * @param conf the conf
   */
  private void WriteViewBean(View table, StringBuilder sb, Configure conf) {
    out(sb, "package " + conf.getPackage() + ";\r\n");
    String s = conf.getPackage();
    int index = s.lastIndexOf(".");
    s = s.substring(0, index) + ".json";
    out(sb, "import " + s + ".JSONHelper;");
    out(sb, "/**");
    out(sb, " * @author ZhangJianshe@gmail.com");
    out(sb, " *  " + table.getComment());
    out(sb, " */");
    out(sb, "public class " + table.getJavaName() + "{");
    out(sb, "\tprivate boolean[] m_changed;\r\n");

    out(sb, "\t/**");
    out(sb, "\t* ���캯��");
    out(sb, "\t*/");
    out(sb, "\tpublic " + table.getJavaName() + "(){");
    out(sb, "\t\tm_changed = new boolean[" + table.getColumns().getCount() + "];");
    out(sb, "\t\tresetChanged(false);");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t* ȫ�������ֶ��Ƿ񱻸�Ĺ�");
    out(sb, "\t* @param b");
    out(sb, "\t*/");
    out(sb, "\tpublic void resetChanged(boolean b){");
    out(sb, "\t\tfor(int i=0;i<" + table.getColumns().getCount() + ";i++){");
    out(sb, "\t\t\tm_changed[i]=b;");
    out(sb, "\t\t}");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t* �����ֶ��Ƿ񱻸�Ĺ�");
    out(sb, "\t* @param index");
    out(sb, "\t* @param b Boolean");
    out(sb, "\t*/");
    out(sb, "\tpublic void setChanged(int index,boolean b){");
    out(sb, "\t\tm_changed[index]=b;");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t* ��ȡ�ֶ��Ƿ񱻸�Ĺ�");
    out(sb, "\t* @param index");
    out(sb, "\t* @return");
    out(sb, "\t*/");
    out(sb, "\tpublic boolean getChanged(int index){");
    out(sb, "\t\treturn m_changed[index];");
    out(sb, "\t}\r\n");

    out(sb, "\t/**");
    out(sb, "\t* ��ȡ��¼ROWID");
    out(sb, "\t* @return rowid");
    out(sb, "\t*/");
    out(sb, "\tpublic String getROWID(){");
    out(sb, "\t   return this.ROWID;");
    out(sb, "\t}\r\n");
    out(sb, "\t/**");
    out(sb, "\t* ���ü�¼ROWID");
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
      out(sb, "\tpublic void set" + col.getName() + "(" + col.getJavaType() + " " + col.getName()
          + "){");
      out(sb, "\t\tthis." + col.getName() + "=" + col.getName() + ";");
      out(sb, "\t\tsetChanged( " + i + " ,true);");
      out(sb, "\t\t}\r\n");
    }
    out(sb, "\tpublic String toString(){");
    out(sb, "\t\tStringBuilder sb=new StringBuilder();");
    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      out(sb,
          "\t\tsb.append(this.get" + col.getName() + "()==null?\"----\":this.get" + col.getName()
              + "().toString());");
      out(sb, "\t\tsb.append(\" | \");");
    }
    out(sb, "\treturn sb.toString();");
    out(sb, "\t}\r\n");

    out(sb, "\tpublic void copyTo(" + table.getJavaName() + " " + table.getJavaName().toLowerCase()
        + "){");
    for (int i = 0; i < table.getColumns().getCount(); i++) {
      Column col = table.getColumns().getAt(i);
      out(sb, "\t\t" + table.getJavaName().toLowerCase() + "." + col.getName() + " = " + "this."
          + col.getName() + ";");
    }
    out(sb, "\t}");

    out(sb, "\tpublic void mergeTo(" + table.getJavaName() + " "
        + table.getJavaName().toLowerCase() + "){");
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
        out(sb, "\t\tsb.append(JSONHelper.escape(this.get" + col.getName()
            + "()==null?\"null\":this.get" + col.getName() + "().toString()));");
        out(sb, "\t\tsb.append(\"\\\"\");");
      } else if (col.getJavaType().equals("java.math.BigDecimal")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("Boolean")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("Byte")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("Short")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("Integer")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("Float")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("Double")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("byte[]")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("java.sql.Date")) {
        out(sb, "sb.append(\"\\\"\");");
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
        out(sb, "sb.append(\"\\\"\");");
      } else if (col.getJavaType().equals("java.sql.Time")) {
        out(sb, "sb.append(\"\\\"\");");
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
        out(sb, "sb.append(\"\\\"\");");
      } else if (col.getJavaType().equals("Long")) {
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
                + "().toString());");
      } else if (col.getJavaType().equals("java.sql.Timestamp")) {
        out(sb, "sb.append(\"\\\"\");");
        out(sb,
            "\t\tsb.append(this.get" + col.getName() + "()==null?\"null\":this.get" + col.getName()
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
}

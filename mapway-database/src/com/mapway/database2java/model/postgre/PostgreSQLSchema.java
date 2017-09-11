package com.mapway.database2java.model.postgre;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Schema;
import schemacrawler.schemacrawler.ExcludeAll;
import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.utility.SchemaCrawlerUtility;

import com.mapway.TableBuilder;
import com.mapway.database2java.database.GeneratorPool;
import com.mapway.database2java.database.IConnectionPool;
import com.mapway.database2java.model.base.Configure;
import com.mapway.database2java.model.base.SchemaBase;
import com.mapway.database2java.model.schema.Column;
import com.mapway.database2java.model.schema.Table;

public class PostgreSQLSchema extends SchemaBase {

  public PostgreSQLSchema(IConnectionPool pool, Configure configure) {
    super(pool, configure);
    setSQLClause(new Postgre_SQLClause(configure.getDatabase()));
  }



  /*
   * 利用 SchemaCrawerl 获取元数据信息 (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.base.SchemaBase#fetchSchema()
   */
  @Override
  public boolean fetchSchema() {
    fetchPostgreSQLMetaData();
    return true;
  }

  private void fetchPostgreSQLMetaData() {
    SchemaCrawlerOptions options = new SchemaCrawlerOptions();
    // Set what details are required in the schema - this affects the
    // time taken to crawl the schema
    options.setSchemaInfoLevel(SchemaInfoLevelBuilder.standard());
    options.setRoutineInclusionRule(new ExcludeAll());


    Catalog catalog = null;
    try {
      catalog = SchemaCrawlerUtility.getCatalog(a.getConnectionPool().getConnection(), options);
    } catch (SchemaCrawlerException e) {
      e.printStackTrace();
    }


    for (schemacrawler.schema.Schema schema : catalog.getSchemas()) {
      System.out.println(schema.getName() + "===>" + getConfigure().getSchema());
      if (schema.getName().equals(getConfigure().getSchema())) {
        processSchema(catalog, schema);
      }
    }
  }

  private void processSchema(Catalog catalog, Schema schema) {
    System.out.println("go schema " + schema.getName());
    for (schemacrawler.schema.Table table : catalog.getTables(schema)) {
      TableBuilder tb = new TableBuilder();
      tb.addRow("模式", "表名", "列名", "是否主键", "是否递增", "是否为空", "数据类型");
      Table t = new Table();
      getTables().addTable(t);
      t.setName(trip(table.getName()));
      t.setComment(table.getRemarks());

      for (schemacrawler.schema.Column column : table.getColumns()) {
        tb.addRow(schema.getName(), table.getName(), column.getName(), column.isPartOfPrimaryKey()
            + "", column.isAutoIncremented() + "", column.isNullable() + "", column
            .getColumnDataType().getJavaSqlType().getJavaSqlTypeName());
        Column c = new Column();
        c.setAuto(column.isAutoIncremented());
        c.setPK(column.isPartOfPrimaryKey());
        c.setName(column.getName());
        c.setComment(column.getRemarks());
        c.setDatabaseType(column.getColumnDataType().getJavaSqlType().getJavaSqlTypeName());
        t.getColumns().addColumn(c);

      }
      System.out.println(tb.toString());
    }
  }

  private String trip(String name) {
    return name.replaceAll("\\\"", "");
  }

  public static void main(String[] args) {

    String driverName = "org.postgresql.Driver";
    String jdbcurl = "jdbc:postgresql://localhost/hangye";
    String username = "hangye";
    String pwd = "hangye";
    try {
      Class.forName(driverName);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    IConnectionPool pool = new GeneratorPool(driverName, jdbcurl, username, pwd, "", "", 100);
    Configure configure = new Configure();
    PostgreSQLSchema schema = new PostgreSQLSchema(pool, configure);
    schema.fetchSchema();
  }


}

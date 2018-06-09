package com.mapway.database2java.model.postgre;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mapway.database2java.model.base.naming.CamelConvert;
import com.mapway.database2java.model.base.naming.LowerCaseNameConvert;
import com.mapway.database2java.model.base.naming.UpperCaseNameConvert;
import org.nutz.lang.Lang;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Schema;
import schemacrawler.schemacrawler.ExcludeAll;
import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.utility.SchemaCrawlerUtility;

import com.bethecoder.ascii_table.ASCIITable;
import com.mapway.database2java.database.GeneratorPool;
import com.mapway.database2java.database.IConnectionPool;
import com.mapway.database2java.model.base.Configure;
import com.mapway.database2java.model.base.SchemaBase;
import com.mapway.database2java.model.schema.Column;
import com.mapway.database2java.model.schema.Table;

/**
 * The type Postgre sql schema.
 */
public class PostgreSQLSchema extends SchemaBase {

    /**
     * Instantiates a new Postgre sql schema.
     *
     * @param pool      the pool
     * @param configure the configure
     */
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

            String[] headers = Lang.array("模式", "表名", "列名", "是否主键", "是否递增", "是否为空", "数据类型");

            Table t = new Table();
            getTables().addTable(t);
            t.setName(trip(table.getName()));
            t.setComment(compass(table.getRemarks()));
            String[][] data;
            data = new String[table.getColumns().size()][];

            int i = 0;
            for (schemacrawler.schema.Column column : table.getColumns()) {
                Column c;
                if (FIELD_STYLE_LOWER_CASE.equalsIgnoreCase(getConfigure().getUseFieldStyle())) {
                    c = new Column(new LowerCaseNameConvert());
                } else if (FIELD_STYLE_UPPER_CASE.equalsIgnoreCase(getConfigure().getUseFieldStyle())) {
                    c = new Column(new UpperCaseNameConvert());
                } else {
                    c = new Column(new CamelConvert());
                }

                c.setAuto(column.isAutoIncremented());
                c.setPK(column.isPartOfPrimaryKey());
                c.setName(trip(column.getName()));
                c.setComment(compass(column.getRemarks()));
                c.setDatabaseType(column.getColumnDataType().getJavaSqlType().getJavaSqlTypeName());
                t.getColumns().addColumn(c);

                String[] row =
                        Lang.array(schema.getName(), table.getName(), trip(column.getName()),
                                column.isPartOfPrimaryKey() + "", column.isAutoIncremented() + "",
                                column.isNullable() + "", column.getColumnDataType().getJavaSqlType()
                                        .getJavaSqlTypeName(), c.getComment());
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
        return name.replaceAll("\\\"", "");
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {


        String driverName = "org.postgresql.Driver";
        String jdbcurl = "jdbc:postgresql://pg.ennwifi.cn/bigdata";
        String username = "bigdata";
        String pwd = "bigdata";
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

package com.mapway.database2java;

import org.apache.commons.cli.ParseException;

import com.mapway.database2java.database.GeneratorPool;
import com.mapway.database2java.database.IConnectionPool;
import com.mapway.database2java.model.base.Configure;
import com.mapway.database2java.model.itf.ISchema;
import com.mapway.database2java.model.mysql.MySQL_Schema2_gwt;
import com.mapway.database2java.model.schema.Tables;
import com.mapway.database2java.model.schema.Views;

// TODO: Auto-generated Javadoc
/**
 * 输出应用程序.
 *
 * @author zhangjianshe@gmail.com
 */
public class MainApplication {

	/**
	 * 
	 * @param configure
	 */
	public void execute(IConfigure configure) {
		gen(configure);
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		Config config = new Config();
		config.help();
		try {
			config.parse(args);
		} catch (ParseException e) {
			config.help();
			System.exit(0);
		}

		System.out.println(config.getDriver());
		System.out.println(config.getJDBCURL());
		System.out.println(config.getMaxConnections());
		System.out.println(config.getPackage());
		System.out.println(config.getPath());
		System.out.println(config.getSchema());
		System.out.println(config.getUser());
		System.out.println(config.getPassword());

		MainApplication app = new MainApplication();
		app.gen(config);

	}

	private void gen(IConfigure config) {
		IConnectionPool pool = new GeneratorPool(config.getDriver(),
				config.getJDBCURL(), config.getUser(), config.getPassword(),
				config.getPackage(), config.getPath(),
				config.getMaxConnections());
		ISchema schema = null;

		Configure configure = new Configure();

		configure.setSchema(config.getSchema());
		System.out.println(" out " + pool.getPath());
		configure.setPath(pool.getPath());
		configure.setPackage(pool.getPackage());
		configure.setGwtbase(pool.getGwtbase());
		if (config.getDriver().contains("mysql")) {
			configure.setDatabase("mysql");
			schema = new MySQL_Schema2_gwt(pool, configure);
		} else {
			// schema = new Oracle_Schema_gwt(pool, configure);
		}

		boolean b = schema.fetchSchema();
		if (b) {
			System.out.println("Database Connected and fetch successful");
			System.out.println("Export Path:" + configure.getPath());

			if (config.getNutz().equals("1")) {
				Configure conf = configure.copy();
				schema.exportJavaBean(conf);
				System.out.println("gen NUTZ OK!");
				return;
			}
			Configure conf = configure.copy();
			conf.setPackage(conf.getGwtbase());
			schema.exportProcedures(conf);

			Configure confTable = configure.copy();
			// confTable.setPackage(confTable.getPackage() );

			Tables tables = schema.getTables();
			System.out.println("Export Database tables ...");
			for (int j = 0; j < tables.getCount(); j++) {
				schema.exportTable(tables.getAt(j), confTable);
				System.out.println("Export Database tables "
						+ tables.getAt(j).getName());
			}

			confTable = configure.copy();
			confTable.setPackage(configure.getPackage());

			Views views = schema.getViews();
			System.out.println("Export Database views ...");
			for (int j = 0; j < views.getCount(); j++) {
				schema.exportViews(views.getAt(j), confTable);
				System.out.println("Export Database view"
						+ tables.getAt(j).getName());
			}

			Configure confjson = configure.copy();
			confjson.setPackage(confjson.getPackage() + ".shared.modle");
			System.out.println("Export JSON Helper");
			schema.exportJSONTools(confjson);

			Configure confbase = configure.copy();
			schema.exportGwtModule(confbase);
			confbase.setPackage(confbase.getPackage() + ".server.database.base");
			schema.exportPoolInterface(confbase);

			schema.exportAccessBase(confbase);

			schema.exportExecuteResult(confbase);

			Configure confSpring = configure.copy();

			schema.exportSpringConfigure(confSpring);

			schema.exportDwrConfigure(confSpring);

		} else {
			System.out.println("Error connect to the database");
		}
	}

}

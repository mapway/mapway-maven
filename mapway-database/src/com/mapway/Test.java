package com.mapway;

import com.mapway.database2java.IConfigure;
import com.mapway.database2java.MainApplication;

public class Test {

  public static void main(String[] args) {


    String USER = "mapway-wiki";
    String SCHEMA = "mapway-wiki";
    String PWD = "mapway-wiki";
    String DRIVER = "org.mysql.jdbc.Driver";
    String JDBCURL = "jdbc:mysql://10.39.0.150/mapway-wiki";

    // args[index++] = "driver=org.postgresql.Driver";
    // args[index++] = "jdbcurl=jdbc:postgresql://localhost/hangye";
    // args[index++] = "schema=public";
    // args[index++] = "user=hangye";
    // args[index++] = "pasword=hangye";
    // args[index++] = "path=D:\\enn\\ExportCoder\\src\\main\\java";
    // args[index++] = "schema=public";
    // args[index++] = "package=cn.ennwifi.test.module";
    // args[index++] = "maxConnections=10";
    // args[index++] = "nutz=1";

    IConfigure config = new IConfigure() {

      @Override
      public String getUser() {
        return USER;
      }

      @Override
      public String getSchema() {
        return SCHEMA;
      }

      @Override
      public String getPath() {
        return "D:\\\\enn\\\\ExportCoder\\\\src\\\\main\\\\java";
      }

      @Override
      public String getPassword() {

        return PWD;
      }

      @Override
      public String getPackage() {
        return "cn.ennwifi.test.module";
      }

      @Override
      public String getNutz() {
        return "1";
      }

      @Override
      public int getMaxConnections() {
        return 10;
      }

      @Override
      public String getJDBCURL() {
        return JDBCURL;
      }

      @Override
      public String getDriver() {
        return DRIVER;
      }

      @Override
      public String getNormalNutz() {
        return "1";
      }

      @Override
      public String getSimple() {
        return "1";
      }
    };


    System.out.println(config.getDriver());
    System.out.println(config.getJDBCURL());
    System.out.println(config.getMaxConnections());
    System.out.println(config.getPackage());
    System.out.println(config.getPath());
    System.out.println(config.getSchema());
    System.out.println(config.getUser());
    System.out.println(config.getPassword());
    System.out.println(config.getNormalNutz());


    MainApplication app = new MainApplication();
    app.gen(config);

  }
}

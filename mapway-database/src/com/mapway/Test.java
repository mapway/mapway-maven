package com.mapway;

import com.mapway.database2java.IConfigure;
import com.mapway.database2java.MainApplication;

public class Test {

  public static void main(String[] args) {



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
        return "biguser";
      }

      @Override
      public String getSchema() {
        return "public";
      }

      @Override
      public String getPath() {
        return "D:\\\\enn\\\\ExportCoder\\\\src\\\\main\\\\java";
      }

      @Override
      public String getPassword() {

        return "biguser";
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
        return "jdbc:postgresql://pg.ennwifi.cn/bigdata";
      }

      @Override
      public String getDriver() {
        return "org.postgresql.Driver";
      }

      @Override
      public String getNormalNutz() {
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

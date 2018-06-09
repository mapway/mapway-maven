package com.mapway;

import com.mapway.database2java.IConfigure;
import com.mapway.database2java.MainApplication;

/**
 * The type Test.
 */
public class Test {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        String USER = "mapway-wiki";
        String SCHEMA = "mapway-wiki";
        String PWD = "mapway-wiki";
        String DRIVER = "org.mysql.jdbc.Driver";
        String JDBCURL = "jdbc:mysql://10.39.0.150/mapway-wiki";


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
            public String getMapper() {
                return "";
            }

            @Override
            public String getUseFieldStyle() {
                return "CAMEL";
            }

            @Override
            public String getUseGwt() {
                return "0";
            }

            @Override
            public String getUseDocument() {
                return "1";
            }

            @Override
            public String getUseNutz() {
                return "1";
            }

            @Override
            public String getUseFieldIndex() {
                return "0";
            }
        };


        MainApplication app = new MainApplication();
        app.gen(config);

    }
}

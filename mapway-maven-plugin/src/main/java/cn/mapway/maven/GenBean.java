package cn.mapway.maven;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import cn.mapway.tools.TableBuilder;

import com.mapway.database2java.IConfigure;
import com.mapway.database2java.MainApplication;

/**
 * Goal which touches a timestamp file.
 *
 */
@Mojo(name = "genbean")
public class GenBean extends AbstractMojo {

  /** The path. */
  @Parameter(defaultValue = "${project.basedir}/src/main/java", property = "path", required = true)
  private String path;

  /** The driver. */
  @Parameter(defaultValue = "com.mysql.jdbc.Driver", property = "driver", required = true)
  private String driver;

  /** The jdbcurl. */
  @Parameter(defaultValue = "", property = "jdbcurl", required = true)
  private String jdbcurl;

  /** The package name. */
  @Parameter(defaultValue = "test", property = "packageName", required = true)
  private String packageName;

  /** The user. */
  @Parameter(defaultValue = "root", property = "user", required = true)
  private String user;

  /** The pwd. */
  @Parameter(defaultValue = "pwd", property = "pwd", required = true)
  private String pwd;

  /** The schema. */
  @Parameter(defaultValue = "schem", property = "schema", required = true)
  private String schema;

  /** The nutz. */
  @Parameter(defaultValue = "1", property = "nutz", required = true)
  private String nutz;

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.maven.plugin.Mojo#execute()
   */
  public void execute() throws MojoExecutionException {

    IConfigure configure = new IConfigure() {

      public String getUser() {
        return user;
      }

      public String getSchema() {
        return schema;
      }

      public String getPath() {
        return path;
      }

      public String getPassword() {
        return pwd;
      }

      public String getPackage() {
        return packageName;
      }

      public String getNutz() {
        return nutz;
      }

      public int getMaxConnections() {
        return 10;
      }

      public String getJDBCURL() {
        return jdbcurl;
      }

      public String getDriver() {
        return driver;
      }
    };

    TableBuilder tb = new TableBuilder();
    tb.addRow("parameter", "value");
    tb.addRow("driver", configure.getDriver());
    tb.addRow("url", configure.getJDBCURL());
    tb.addRow("user", configure.getUser());
    tb.addRow("password", configure.getPassword());
    tb.addRow("path", configure.getPath());
    tb.addRow("pacakgeName", configure.getPackage());


    System.out.println(tb.toString());

    MainApplication app = new MainApplication();
    app.execute(configure);
  }
}

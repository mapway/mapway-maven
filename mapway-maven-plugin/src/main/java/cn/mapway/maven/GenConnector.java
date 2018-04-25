package cn.mapway.maven;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.nutz.lang.Files;
import org.nutz.lang.Strings;

import cn.mapway.document.helper.GwtConnextorExport;
import cn.mapway.document.helper.JavaClassContent;
import cn.mapway.document.helper.JavaConnextorExport;
import cn.mapway.document.module.ApiDoc;

/**
 * 生成连接器的GOal.
 */
@Mojo(name = "genconnector", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GenConnector extends AbstractMojo {



  /** The path. */
  @Parameter(defaultValue = "${project.basedir}/src/main/java", property = "path", required = true)
  private String path;

  /** The api url. */
  @Parameter(defaultValue = "", property = "apiUrl", required = true)
  private String apiUrl;

  /** The package name. */
  @Parameter(defaultValue = "", property = "packageName", required = true)
  private String packageName;

  /** The class name. */
  @Parameter(defaultValue = "Connector", property = "className", required = true)
  private String className;

  /** The class name. */
  @Parameter(defaultValue = "gwt", property = "contype", required = true)
  private String contype;


  /**
   * 执行goal 调用 mapway-doc-ui 生成源代码.
   *
   * @throws MojoExecutionException the mojo execution exception
   */
  public void execute() throws MojoExecutionException {
    Log logger = getLog();
    if (contype.compareTo("gwt") == 0) {
      exportGwtConnector(logger);
    } else if (contype.compareToIgnoreCase("java") == 0) {
      exportJavaConnector(logger);
    } else {
      logger.error("unsupport connection type " + contype + " ,current support gwt java");
    }

  }

  private void exportJavaConnector(Log logger) {
    JavaConnextorExport exportJava = new JavaConnextorExport();

    if (Strings.isBlank(apiUrl)) {
      logger.info("apiurl 配置为空字符串,取消本次访问代码生成");
      return;
    }

    // 获取 ApiDoc
    Response res = Http.get(apiUrl);
    if (!res.isOK()) {
      logger.info("获取API文档信息" + apiUrl + "错误:" + res.getStatus());
      return;
    }
    ApiDoc doc = Json.fromJson(ApiDoc.class, res.getReader());
    if (doc == null) {
      logger.info("获取API文档信息" + apiUrl + "错误:" + res.getContent());
      return;
    }

    List<JavaClassContent> codes = exportJava.export2(doc, packageName, className);

    for (JavaClassContent jcc : codes) {
      String fileName = makePathFile(path, jcc.packageName, jcc.className);
      logger.info("生成JavaAPI的代理接口" + className + "-->" + fileName);
      Files.write(fileName, jcc.body);
    }
  }


  /**
   * 
   */
  private void exportGwtConnector(Log logger) {
    GwtConnextorExport exportJava = new GwtConnextorExport();

    if (Strings.isBlank(apiUrl)) {
      logger.info("apiurl 配置为空字符串,取消本次访问代码生成");
      return;
    }

    // 获取 ApiDoc
    Response res = Http.get(apiUrl);
    if (!res.isOK()) {
      logger.info("获取API文档信息" + apiUrl + "错误:" + res.getStatus());
      return;
    }
    ApiDoc doc = Json.fromJson(ApiDoc.class, res.getReader());
    if (doc == null) {
      logger.info("获取API文档信息" + apiUrl + "错误:" + res.getContent());
      return;
    }

    List<JavaClassContent> codes = exportJava.export2(doc, packageName, className);

    for (JavaClassContent jcc : codes) {
      String fileName = makePathFile(path, jcc.packageName, jcc.className);
      logger.info("生成API的代理接口" + className + "-->" + fileName);
      Files.write(fileName, jcc.body);
    }
  }

    /**
     * 构造文件路径.
     *
     * @param javaConnectorSourcePath the java connector source path
     * @param packageName             the package name
     * @param className               the class name
     * @return the string
     */
    public static String makePathFile(String javaConnectorSourcePath, String packageName,
      String className) {
    String p = packageName.replace(".", File.separator);

    return javaConnectorSourcePath + File.separator + p + File.separator + className + ".java";
  }
}

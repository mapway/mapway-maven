package cn.mapway.ui.client.mvc;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.repo.Base64;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;


/**
 * 管理系统模块类，自动搜索IModule类,生成一个代理类,使用此代理类实例化模块,模块的生成是单例模式 +.
 *
 * @author zhangjianshe
 */
public class ModuleFactoryGenerator extends Generator {

  private static final String DEFAULT_ICON =
      "data:image/png;base64,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";

  /*
   * 11 (non-Javadoc)
   * 
   * @see com.google.gwt.core.ext.Generator#generate(com.google.gwt.core.ext.TreeLogger,
   * com.google.gwt.core.ext.GeneratorContext, java.lang.String)
   */
  @Override
  public String generate(TreeLogger logger, GeneratorContext context, String typeName)
      throws UnableToCompleteException {

    TypeOracle oracle = context.getTypeOracle();

    // 需要查找的管理模块
    JClassType instantiableType = oracle.findType(IModule.class.getName());

    // 需要管理的模块集合
    List<JClassType> clazzes = new ArrayList<JClassType>();


    // 查找所有的类，并统计出需要管理的类
    for (JClassType classType : oracle.getTypes()) {
      if (!classType.equals(instantiableType) && classType.isAssignableTo(instantiableType)) {
        clazzes.add(classType);
      }
    }

    // 生成代理类的package
    final String genPackageName = "cn.mapway.ui.client.mvc";

    // 代理类名称
    final String genClassName = "ModuleFactoryImpl";

    // 代码生成器工厂类
    ClassSourceFileComposerFactory composer =
        new ClassSourceFileComposerFactory(genPackageName, genClassName);

    // 代理类继承需要代理的接口
    composer.addImplementedInterface(ModuleFactory.class.getCanonicalName());
    // 代理类要引用的类包
    composer.addImport("cn.mapway.ui.client.mvc.*");

    // 创建一个源代码生成器对象
    PrintWriter printWriter = context.tryCreate(logger, genPackageName, genClassName);

    if (printWriter != null) {
      // 源代码生成器
      SourceWriter sourceWriter = composer.createSourceWriter(context, printWriter);
      // 生成一个无参数构造函数
      sourceWriter.println("ModuleFactoryImpl() {");
      sourceWriter.println("}");

      sourceWriter.println("\r\n   private static String DEFAULT_ICON=\"" + DEFAULT_ICON + "\";");

      // 输出代码方法
      printFactoryMethod(clazzes, sourceWriter);

      // 写入磁盘
      sourceWriter.commit(logger);
    }

    // 返回生成的代理对象类名称
    return composer.getCreatedClassName();
  }

  /**
   * Prints the factory method.
   *
   * @param clazzes 需要代理的模块集合
   * @param sourceWriter the source writer
   */
  private void printFactoryMethod(List<JClassType> clazzes, SourceWriter sourceWriter) {
    sourceWriter.println();

    StringBuilder sb = new StringBuilder();

    StringBuilder sbModules = new StringBuilder();
    sbModules.append(" private final static ModuleInfo[] gModules={");

    int count = 0;
    for (JClassType classType : clazzes) {
      if (classType.isAbstract()) {
        continue;
      }
      ModuleInfo item = findModuleName(classType);
      if (item == null) {
        continue;
      }
      if (count > 0) {
        sbModules.append(",");
      }

      if (item.icon.equals(DEFAULT_ICON)) {
        sbModules.append("new ModuleInfo(\"" + item.name + "\",\"" + item.code + "\",\""
            + item.summary + "\"," + (item.isPublic ? "true" : "false") + ",DEFAULT_ICON,\""
            + item.hash + "\"," + item.isVisible + ")\r\n");
      } else {
        sbModules.append("new ModuleInfo(\"" + item.name + "\",\"" + item.code + "\",\""
            + item.summary + "\"," + (item.isPublic ? "true" : "false") + ",\"" + item.icon
            + "\",\"" + item.hash + "\"," + item.isVisible + ")\r\n");
      }

      sb.append("\r\n\t private static IModule " + item.code + "=null;\r\n");

      count++;

    }
    sbModules.append("};");
    sourceWriter.println(sbModules.toString());

    sourceWriter.println(sb.toString());


    // 输出 接口 getModules();

    sourceWriter.println("public ModuleInfo[] getModules(){");
    sourceWriter.println("return gModules;");
    sourceWriter.println("}");

    // 输出 public boolean isModulePublic(String moduleCode);
    sourceWriter.println("\r\npublic boolean isModulePublic(String moduleCode){");
    sourceWriter.println("\r\n    if(moduleCode==null || moduleCode.length()==0) {return false;}");
    sourceWriter.println("\r\n    for(int i=0;i<gModules.length;i++) {\r\n");
    sourceWriter.println("\r\n      ModuleInfo item=gModules[i];");
    sourceWriter.println("\r\n      if ( moduleCode.equals(item.code)) {\r\n");
    sourceWriter.println("\r\n          return item.isPublic;\r\n");
    sourceWriter.println("\r\n      }");
    sourceWriter.println("\r\n    }");
    sourceWriter.println("\r\n    return false;\r\n");
    sourceWriter.println("\r\n}");

    // 输出 模块信息

    sourceWriter.println("\r\n public ModuleInfo findModuleInfoByHash(String hash){");
    sourceWriter.println("\r\n    if(hash==null || hash.length()==0) {return null;}");
    sourceWriter.println("\r\n    for(int i=0;i<gModules.length;i++) {\r\n");
    sourceWriter.println("\r\n      ModuleInfo item=gModules[i];");
    sourceWriter.println("\r\n      if ( hash.equals(item.hash)) {\r\n");
    sourceWriter.println("\r\n          return item;\r\n");
    sourceWriter.println("\r\n      }");
    sourceWriter.println("\r\n    }");
    sourceWriter.println("\r\n    return null;\r\n");
    sourceWriter.println("\r\n}");

    sourceWriter.println("\r\n public ModuleInfo findModuleInfo(String moduleCode){");
    sourceWriter.println("\r\n    if(moduleCode==null || moduleCode.length()==0) {return null;}");
    sourceWriter.println("\r\n    for(int i=0;i<gModules.length;i++) {\r\n");
    sourceWriter.println("\r\n      ModuleInfo item=gModules[i];");
    sourceWriter.println("\r\n      if ( moduleCode.equals(item.code)) {\r\n");
    sourceWriter.println("\r\n          return item;\r\n");
    sourceWriter.println("\r\n      }");
    sourceWriter.println("\r\n    }");
    sourceWriter.println("\r\n    return null;\r\n");
    sourceWriter.println("\r\n}");

    // 实例化签名
    sourceWriter.println("public  IModule createModule( String code ,boolean single) {");

    sourceWriter.println();
    sourceWriter.indent();
    for (JClassType classType : clazzes) {
      if (classType.isAbstract()) {
        continue;
      }
      ModuleInfo item = findModuleName(classType);
      if (item == null) {
        continue;

      }


      sourceWriter.println("if(\"" + item.code + "\".equals(code)) {\r\n");
      sourceWriter.println("if(single==false) {\r\n");
      sourceWriter.println("      return new " + classType.getQualifiedSourceName() + "();\r\n");
      sourceWriter.println("}\r\n");

      sourceWriter.println("if (" + item.code + " == null) {\r\n");
      sourceWriter.println("     " + item.code + "=new " + classType.getQualifiedSourceName()
          + "();\r\n");
      sourceWriter.println("}\r\n");
      sourceWriter.println("return " + item.code + ";\r\n");
      sourceWriter.println("}\r\n");
    }
    sourceWriter.outdent();
    sourceWriter.println();

    sourceWriter.indent();
    sourceWriter.println("return  null;");
    sourceWriter.outdent();
    sourceWriter.println();
    sourceWriter.println("}");
    sourceWriter.outdent();
    sourceWriter.println();
  }

  /**
   * Find module name.
   *
   * @param classType the class type
   * @return the module item
   */
  private ModuleInfo findModuleName(JClassType classType) {
    ModuleMarker marker = classType.getAnnotation(ModuleMarker.class);
    if (marker == null) {
      return null;
    }
    String modulecode = null;
    String modulename = null;
    String summary = "";
    boolean isPublic = false;
    boolean isVisible = true;
    if (marker != null) {
      modulecode = marker.value();
      modulename = marker.name();
      isPublic = marker.isPublic();
      summary = marker.summary();
      isVisible = marker.visible();
    }
    if (modulecode == null || modulecode.length() == 0) {
      modulecode = classType.getSimpleSourceName();
    }
    if (modulename == null || modulename.length() == 0) {
      modulename = modulecode;
    }
    String icondata = "";
    String iconname = marker.icon();

    icondata = loadIconData(classType, iconname);

    ModuleInfo item = new ModuleInfo("", "", "", false, icondata, "", false);

    item.code = modulecode;
    item.name = modulename;
    item.isPublic = isPublic;
    item.summary = summary;
    String md5 = Lang.md5(item.code);
    item.hash = md5.substring(md5.length() - 6);
    item.isVisible = isVisible;
    return item;
  }

  /**
   * @param classType
   * @param iconname
   * @return
   */
  private String loadIconData(JClassType classType, String iconname) {

    if (iconname == null || "icon.png".equals(iconname)) {
      return DEFAULT_ICON;
    }
    String packageName = classType.getPackage().getName().replaceAll("\\.", "/");
    String filename = packageName + "/" + iconname;
    String type = Files.getSuffix(filename).substring(1);
    File f = Files.findFile(filename);
    if (f != null) {
      return "data:image/" + type + ";base64," + Base64.encodeToString(Files.readBytes(f), false);
    } else {
      System.out.println("Xcan find " + filename);
      return DEFAULT_ICON;
    }
  }
}

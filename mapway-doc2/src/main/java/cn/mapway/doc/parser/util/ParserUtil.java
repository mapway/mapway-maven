package cn.mapway.doc.parser.util;

import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.types.ResolvedType;
import org.nutz.lang.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析辅助工具
 */
public class ParserUtil {
    /**
     * 取最后的信息  RequestMethod.POST  ===>  POST
     *
     * @param txt
     * @param sep
     * @return
     */
    public static String last(String txt, String sep) {
        if (Strings.isBlank(txt)) {
            return "";
        } else {
            int pos = txt.lastIndexOf(sep);
            if (pos >= 0) {
                return txt.substring(pos + sep.length());
            }
            return txt;
        }
    }

    /**
     * 取最后的信息  RequestMethod.POST  ===>  POST
     *
     * @param txt
     * @return
     */
    public static String removeSlash(String txt) {
        if (Strings.isBlank(txt)) {
            return "";
        } else {
            txt = txt.trim();
            if (txt.endsWith("/")) {
                return txt.substring(0, txt.length() - 1);
            } else {
                return txt;
            }
        }
    }

    /**
     * 将 cn.mapway.compare ===> cn/mapway/compare
     *
     * @param packageName
     * @return
     */
    public static String packageToPath(String packageName) {
        if (!Strings.isBlank(packageName)) {
            return packageName.replaceAll("\\.", "/");
        }
        return "";
    }

    public static Double parseDouble(String data, double defaultValue) {
        if (Strings.isBlank(data)) {
            return defaultValue;
        }
        double d = defaultValue;
        try {
            d = Double.parseDouble(data);
        } catch (Exception e) {
            d = defaultValue;
        }
        return d;
    }

    public static boolean isPrimitive(Type t) {
        String type = t.asString();

        String[] ps = {"String", "Integer", "int", "double", "Double", "float", "Float", "Byte", "byte", "Char", "char", "bool", "Boolean", "Short", "short"};
        return Strings.isin(ps, type);

    }

    public static boolean isArray(String typeName) {
        if (Strings.isBlank(typeName)) {
            return false;
        }
        Pattern pattern = Pattern.compile(".*\\[[(\\s)|\\d]*\\].*");
        Matcher matcher = pattern.matcher(typeName);
        return matcher.matches();
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(".*\\[[(\\s)|\\d]*\\].*");

        Matcher matcher = pattern.matcher("public String [12]stringArray;");

        System.out.println(matcher.matches());
    }

    /**
     * 判断是否是数组或者 List
     *
     * @param elementType
     * @return
     */
    public static boolean isList(Type elementType) {


        String t = elementType.asString();
        if (elementType.isClassOrInterfaceType()) {
            String name = elementType.asClassOrInterfaceType().asString();
            return name.startsWith("java.util.List") || name.startsWith("List<");
        } else {
            ResolvedType resolve = elementType.resolve();
            if (resolve != null) {
                if (resolve.isPrimitive()) {
                    return false;
                } else if (resolve.isReferenceType()) {
                    String typeName = resolve.asReferenceType().getQualifiedName();
                    return typeName.startsWith("java.util.List");
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isPrimitive(String className) {
        String[] ps = {"String", "Integer", "Double", "Float", "Byte", "Boolean",
                "int", "double", "float", "byte", "boolean"};
        className = last(className, ".");
        return Strings.isin(ps, className);
    }
}

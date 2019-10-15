package cn.mapway.doc.parser;

import cn.mapway.doc.parser.exception.NotFoundException;
import cn.mapway.doc.parser.module.*;
import cn.mapway.doc.parser.util.AnnotationData;
import cn.mapway.doc.parser.util.CompiledUnits;
import cn.mapway.doc.parser.util.ParserUtil;
import cn.mapway.doc.parser.util.RecursionData;
import cn.mapway.doc.test.TestController;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.resolution.declarations.*;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.resolution.types.ResolvedTypeVariable;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;
import com.github.javaparser.symbolsolver.model.typesystem.NullType;
import com.github.javaparser.symbolsolver.reflectionmodel.ReflectionClassDeclaration;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.springframework.boot.system.ApplicationPid;

import java.util.*;
import java.util.function.Consumer;

import static cn.mapway.doc.parser.module.ApiPara.FLD_ARRAY;
import static cn.mapway.doc.parser.module.ApiPara.FLD_BASIC;
import static cn.mapway.doc.parser.module.ApiPara.FLD_LIST;


/**
 * Java源代码解析器，解析API文档
 *
 * @author zhangjianshe@gmail.com
 */
public class SourceParser {

    CompiledUnits units;

    public SourceParser(String root) {
        units = new CompiledUnits(root);
    }

    /**
     * 解析控制器类
     *
     * @param typeName
     */
    public void parseController(String typeName) throws NotFoundException {

        ResolvedReferenceTypeDeclaration typeDeclartion = units.findTypeDeclartion(typeName);
        if (typeDeclartion instanceof JavaParserClassDeclaration) {
            JavaParserClassDeclaration jpcd = (JavaParserClassDeclaration) typeDeclartion;
            ClassOrInterfaceDeclaration wrappedNode = jpcd.getWrappedNode();
            parseClassDeclaration(wrappedNode);
        }

    }

    /**
     * 解析控制器类 ,此类一般具有源代码
     *
     * @param wrappedNode
     */
    private void parseClassDeclaration(ClassOrInterfaceDeclaration wrappedNode) {
        //只处理 Controller RestController
        NodeList<AnnotationExpr> annotations = wrappedNode.getAnnotations();

        if (isSpringController(annotations)) {
            handleSpringController(wrappedNode, annotations);
        }
    }

    /**
     * 判断是否是SpringController
     *
     * @param annotations
     * @return
     */
    boolean isSpringController(NodeList<AnnotationExpr> annotations) {
        String[] springAnnotations = {"org.springframework.stereotype.Controller", "org.springframework.stereotype.RestController"};

        for (int i = 0; i < annotations.size(); i++) {
            AnnotationExpr annotationExpr = annotations.get(i).asAnnotationExpr();
            if (annotationExpr.isAnnotationExpr()) {
                if (annotationExpr instanceof NormalAnnotationExpr) {
                    NormalAnnotationExpr exp = (NormalAnnotationExpr) annotationExpr;
                    ResolvedAnnotationDeclaration resolve = units.resolveAnnotation(exp);
                    String name = resolve.getQualifiedName();
                    if (Strings.isin(springAnnotations, name)) {
                        return true;
                    }
                } else if (annotationExpr instanceof MarkerAnnotationExpr) {
                    MarkerAnnotationExpr exp = (MarkerAnnotationExpr) annotationExpr;
                    ResolvedAnnotationDeclaration resolve = units.resolveAnnotation(exp);
                    String name = resolve.getQualifiedName();
                    if (Strings.isin(springAnnotations, name)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 处理 Spring控制器信息
     *
     * @param t
     * @param annotations
     */
    private void handleSpringController(TypeDeclaration t, NodeList<AnnotationExpr> annotations) {
        ApiController ci = parseControllerInformation(t, annotations);
        List<MethodDeclaration> methods = t.getMethods();

        ApiGroup g = ApiGroup.findGroup(ci.group);
        g.comments.addAll(ci.comments);
        for (MethodDeclaration md : methods) {
            ApiEntry entry = new ApiEntry();
            entry.controller = ci.clzName;
            entry.methodName = md.getNameAsString();

            processEntry(ci, entry, md);

            updateEntryFromControllerInformation(ci, entry);

            ApiGroup group = ApiGroup.findGroup(entry.group);
            group.addEntry(entry);
        }

    }

    /**
     * 处理接口的JAVADOC
     *
     * @param controllerInformation
     * @param entry
     * @param md
     */
    private void processEntry(ApiController controllerInformation, ApiEntry entry, MethodDeclaration md) {

        //处理注解
        final Optional<AnnotationData> data = findAnnotation(md.getAnnotations(), "RequestMapping");
        if (data.isPresent()) {
            data.get().data("value").ifPresent(new Consumer<Expression>() {
                @Override
                public void accept(Expression expression) {
                    entry.url = controllerInformation.url + ParserUtil.removeSlash(expression.asLiteralStringValueExpr().getValue());
                }
            });


            Optional<Expression> methodAnnotation = data.get().data("method");
            if (methodAnnotation.isPresent()) {
                if (methodAnnotation.get().isFieldAccessExpr()) {
                    FieldAccessExpr fieldAccessExpr = methodAnnotation.get().asFieldAccessExpr();
                    entry.method = ParserUtil.last(fieldAccessExpr.getName().asString(), ".");

                } else if (methodAnnotation.get().isArrayInitializerExpr()) {
                    ArrayInitializerExpr array = methodAnnotation.get().asArrayInitializerExpr();
                    array.getValues().forEach(new Consumer<Expression>() {
                        @Override
                        public void accept(Expression expression) {
                            entry.method += " " + ParserUtil.last(expression.toString(), ".");
                        }
                    });
                }

            } else {
                entry.method = "GET";
            }
        }

        Optional<Javadoc> javadoc = md.getJavadoc();
        javadoc.ifPresent(new Consumer<Javadoc>() {
            @Override
            public void accept(Javadoc javadoc) {
                processJavaComment(entry, javadoc, md.getNameAsString());

            }
        });

        for (Comment c : md.getOrphanComments()) {
            entry.comments.add(c.getContent());
        }

        //处理过程中 要注意处理 循环引用的问题,如果发现了所有祖先类中含有当前类型信息，就停止引用解析
        //处理输入参数
        NodeList<Parameter> parameters = md.getParameters();
        for (Parameter p : parameters) {
            processParameter(entry, javadoc, p);
        }
        //处理输出参数
        processReturnType(entry, javadoc, md);
    }

    /**
     * 处理返回值
     *
     * @param entry
     * @param javadoc
     * @param md
     */
    private void processReturnType(ApiEntry entry, Optional<Javadoc> javadoc, MethodDeclaration md) {


        Type retType = md.getType();
        if (ParserUtil.isPrimitive(retType)) {
            ApiPara para = new ApiPara();
            para.type = retType.asString();
            //从注解中
            String desc = findTagData(javadoc.get(), "return");
            para.description = desc;
            entry.output = para;
        } else {
            entry.output = parseType(retType, new RecursionData());
        }
    }


    public static String[] STRINGS_INT = {"int", "Integer", "short", "Short", "Byte", "byte"};
    public static String[] STRINGS_DOUBLE = {"double", "Double", "Float", "float"};
    public static String[] STRINGS_BOOLEAN = {"boolean", "Boolean"};
    public static String[] STRINGS_CHAR = {"char", "Char"};
    public static String[] STRINGS_STRING = {"String"};
    public static String[] STRINGS_OBJECT = {"Object"};

    /**
     * 处理基本的数据类型 例子
     *
     * @param para
     */
    private void processPrimitiveDatatypeExample(ApiPara para) {

        boolean hasExample = !Strings.isBlank(para.example);

        if (Strings.isin(STRINGS_INT, para.type)) {
            if (!hasExample) para.example = "123";
        }

        if (Strings.isin(STRINGS_DOUBLE, para.type)) {
            if (!hasExample) para.example = "36.785";
        }

        if (Strings.isin(STRINGS_BOOLEAN, para.type)) {
            if (!hasExample) para.example = "true";
        }

        if (Strings.isin(STRINGS_BOOLEAN, para.type)) {
            if (!hasExample) para.example = "true";
        }


        if (Strings.isin(STRINGS_CHAR, para.type)) {
            if (!hasExample) para.example = "A";
        }

        if (Strings.isin(STRINGS_STRING, para.type)) {
            if (!hasExample) para.example = "请填写一个字符串";
        }
        if (Strings.isin(STRINGS_OBJECT, para.type)) {
            if (!hasExample) para.example = "{}";
        }
    }


    /**
     * 处理参数
     *
     * @param entry
     * @param javadoc
     * @param p
     */
    private void processParameter(ApiEntry entry, Optional<Javadoc> javadoc, Parameter p) {

        //定义的名称
        String delareName = p.getNameAsString();

        ApiPara para = new ApiPara();
        para.type = p.getType().asString();

        extractParameterDoclet(javadoc, delareName, para);

        //处理PathVariable参数
        NodeList<AnnotationExpr> annotations = p.getAnnotations();

        Optional<AnnotationData> data = findAnnotation(annotations, "PathVariable");
        if (data.isPresent()) {
            //处理URL参数信息
            data.get().data("value").ifPresent(new Consumer<Expression>() {
                @Override
                public void accept(Expression expression) {
                    String value = expression.asLiteralStringValueExpr().getValue();
                    if (Strings.isBlank(value)) {
                        para.name = p.getNameAsString();
                    } else {
                        para.name = value;
                    }
                }
            });
            entry.urlParameters.add(para);
        }
        Optional<AnnotationData> isRequestBody = findAnnotation(annotations, "RequestBody");
        if (isRequestBody.isPresent()) {
            //处理POST方法的请求体
            ApiPara apiPara = parseType(p.getType(), new RecursionData());
            para.name = p.getName().getIdentifier();
            extractParameterDoclet(javadoc, p.getNameAsString(), apiPara);
            entry.inputs.add(apiPara);
        }
    }


    private ApiPara parseType(Type pType, final RecursionData data) {
        final ApiPara[] p = {new ApiPara()};
        if (ParserUtil.isPrimitive(pType)) {

            p[0].type = pType.asString();
            p[0].fieldType = FLD_BASIC;

            return p[0];

        }
        if (data.hasType(pType.asString())) {
            return null;
        }

        data.add(pType.asString());

        pType.ifClassOrInterfaceType(new Consumer<ClassOrInterfaceType>() {
            @Override
            public void accept(ClassOrInterfaceType classOrInterfaceType) {

                if (ParserUtil.isList(pType)) {
                    //是List类型  我们取出其中的对象类型
                    Type templateType = extractListTemplateType(pType);
                    p[0] = new ApiPara();
                    p[0].fieldType = FLD_LIST;
                    p[0].type = templateType.asString();

                    ApiPara para = parseType(templateType, data);
                    if (para != null) {
                        p[0].addField(para);
                    }

                } else {
                    ClassOrInterfaceType cif = pType.asClassOrInterfaceType();
                    p[0] = parseClassOrInterfaceType(cif, data);
                }

            }
        });

        pType.ifArrayType(new Consumer<ArrayType>() {
            @Override
            public void accept(ArrayType arrayType) {
                ArrayType at = pType.asArrayType();
                p[0].fieldType = FLD_ARRAY;
                p[0].type = at.getComponentType().asString();
                ApiPara para = parseType(at.getComponentType(), data);
                if (para != null) {
                    p[0].addField(para);
                }
            }
        });


        return p[0];
    }


    /**
     * 处理类 with template
     * 我们处理这个类的字段信息 所有字段
     * ClassOrInterfaceType
     * \
     * ClassOrInterfaceDeclaration
     *
     * @param cif
     * @param data
     * @return
     */
    private ApiPara parseClassOrInterfaceType(ClassOrInterfaceType cif, final RecursionData data) {

        Optional<NodeList<Type>> typeArguments = cif.getTypeArguments();
        ResolvedReferenceTypeDeclaration typeDeclartion = units.findTypeDeclartion(cif.getElementType());
        if (typeDeclartion instanceof ReflectionClassDeclaration) {
            ReflectionClassDeclaration rcd = (ReflectionClassDeclaration) typeDeclartion;
            return handleReflectionClassDeclaration(rcd, data);
        }
        if (typeDeclartion instanceof JavaParserClassDeclaration) {
            JavaParserClassDeclaration jpcl = (JavaParserClassDeclaration) typeDeclartion;
            return handleJavaParserClassDeclaration(jpcl, typeArguments, data);
        }
        return null;
    }

    /**
     * 没有源代码的类
     *
     * @param rcd
     * @param parent
     * @return
     */
    private ApiPara handleReflectionClassDeclaration(ReflectionClassDeclaration rcd, RecursionData parent) {
        ApiPara para = new ApiPara();
        if (ParserUtil.isPrimitive(rcd.getClassName())) {
            para.fieldType = FLD_BASIC;
            para.type = rcd.getClassName();
            processPrimitiveDatatypeExample(para);
        }
        return para;
    }

    /**
     * 有原代码的  类
     *
     * @param jpcl
     * @param typeArguments
     * @param data
     * @return
     */
    private ApiPara handleJavaParserClassDeclaration(JavaParserClassDeclaration jpcl, Optional<NodeList<Type>> typeArguments, final RecursionData data) {
        ApiPara para = new ApiPara();
        ClassOrInterfaceDeclaration wrappedNode = jpcl.getWrappedNode();
        NodeList<TypeParameter> typeParameters = wrappedNode.getTypeParameters();

        Map<String, Type> typeMapper = new HashMap<>();
        Type t = new VoidType();
        for (int i = 0; i < typeParameters.size(); i++) {
            t = new VoidType();
            TypeParameter tp = typeParameters.get(i);
            if (typeArguments.isPresent()) {
                if (typeArguments.get().size() >= i) {
                    t = typeArguments.get().get(i);
                }
            }
            typeMapper.put(tp.getNameAsString(), t);
        }
        // we have mappered the class Temp<A,B>  ==> (A->String,B->Integer)

        extractFieldDoclet(wrappedNode.getJavadoc(), "", para);
        para.fieldType = FLD_BASIC;
        para.type = jpcl.getClassName();
        para.name = jpcl.getName();

        if (ParserUtil.isPrimitive(jpcl.getClassName())) {
            processPrimitiveDatatypeExample(para);
            return para;
        }

        List<ResolvedFieldDeclaration> allFields = jpcl.getAllFields();
        for (ResolvedFieldDeclaration fd : allFields) {
            if (fd instanceof JavaParserFieldDeclaration) {
                JavaParserFieldDeclaration jpfd = (JavaParserFieldDeclaration) fd;

                Type type = typeMapper.get(jpfd.getWrappedNode().getElementType().asString());
                if (type == null) {
                    type = jpfd.getWrappedNode().getElementType();
                }
                if (type.isVoidType()) {
                    ApiPara p = new ApiPara();
                    p.type = "Object";
                    p.fieldType = FLD_BASIC;
                    p.example = "{}";
                    p.name = fd.getName();
                    extractFieldDoclet(jpfd.getWrappedNode().getJavadoc(), "", p);
                    para.addField(p);
                } else {
                    ApiPara p = parseType(type, data);
                    if (p != null) {
                        extractFieldDoclet(jpfd.getWrappedNode().getJavadoc(), "", p);
                        p.name = fd.getName();
                        para.addField(p);
                    }
                }

            } else {
                ResolvedType type = fd.getType();
                if (type.isTypeVariable()) {
                    ResolvedTypeVariable rtv = (ResolvedTypeVariable) type;
                    ResolvedTypeParameterDeclaration resolvedTypeParameterDeclaration = rtv.asTypeParameter();
                    Type realType = typeMapper.get(resolvedTypeParameterDeclaration.getName());


                    if (realType == null) {
                        ApiPara p = new ApiPara();
                        p.type = "Object";
                        p.fieldType = FLD_BASIC;
                        p.example = "{}";

                        para.addField(p);

                    } else {
                        ApiPara subPara = parseType(realType, data);
                        if (subPara != null) {
                            subPara.name = fd.getName();
                            para.addField(subPara);
                        }
                    }
                }
            }
        }
        return para;
    }

    /**
     * 去除LIST的实际类型
     *
     * @param pType
     * @return
     */
    private Type extractListTemplateType(Type pType) {
        final Type[] type = {pType};
        pType.ifClassOrInterfaceType(new Consumer<ClassOrInterfaceType>() {
            @Override
            public void accept(ClassOrInterfaceType classOrInterfaceType) {
                Optional<NodeList<Type>> typeArguments = classOrInterfaceType.getTypeArguments();
                if (typeArguments.isPresent()) {
                    if (typeArguments.get().size() > 0)
                        type[0] = typeArguments.get().get(0);
                }

            }
        });

        return type[0];
    }


    public void log(String s) {
        System.out.println(s);
    }

    /**
     * 解析 javadoc中的参数信息
     *
     * @param javadoc
     * @param defaultName
     * @param para
     */
    private void extractFieldDoclet(Optional<Javadoc> javadoc, String defaultName, ApiPara para) {
        if (!javadoc.isPresent()) {
            return;
        }

        if (javadoc.get().getDescription().isEmpty()) {
            //没有描述信息,就取类名称
            para.name = defaultName;
        } else {
            // 处理整个描述信息，第一行描述信息作为控制器的标题
            String description = javadoc.get().getDescription().toText();
            description = Strings.trim(description);
            String[] lines = Strings.split(description, false, false, '\r', '\n');

            boolean first = true;
            for (String line : lines) {
                if (first) {
                    if (!Strings.isBlank(line)) {
                        para.name = defaultName;
                        para.description = line;
                        first = false;
                        continue;
                    }
                }
                para.addComment(line);
            }
            if (first == true) {
                //没有找到描述信息
                para.name = defaultName;
            }
        }

        String author = findTagData(javadoc.get(), "author");
        para.setAuthor(author);
        String date = findTagData(javadoc.get(), "date");
        para.setDate(date);

        String data;
        data = findTagData(javadoc.get(), DOC_MIN);
        para.min = ParserUtil.parseDouble(data, 0);
        data = findTagData(javadoc.get(), DOC_MAX);
        para.max = ParserUtil.parseDouble(data, 0);
        data = findTagData(javadoc.get(), DOC_LENGTH);
        para.len = ParserUtil.parseDouble(data, 0).intValue();
        data = findTagData(javadoc.get(), DOC_MANDATORY);
        para.mandatory = Lang.parseBoolean(data);
        data = findTagData(javadoc.get(), DOC_EXAMPLE);
        para.example = data;
        data = findTagData(javadoc.get(), DOC_DEFAULT);
        para.defaultValue = data;

    }

    /**
     * 解析 javadoc中的参数信息
     *
     * @param javadoc
     * @param delareName
     * @param para
     */
    private void extractParameterDoclet(Optional<Javadoc> javadoc, String delareName, ApiPara para) {
        if (!javadoc.isPresent() || Strings.isBlank(delareName)) {
            return;
        }

        List<JavadocBlockTag> blockTags = javadoc.get().getBlockTags();
        for (JavadocBlockTag tag : blockTags) {
            if (tag.getType().equals(JavadocBlockTag.Type.PARAM)) {
                if (tag.getName().isPresent()) {
                    String name = tag.getName().get();
                    if (name.trim().equals(delareName)) {

                        //找到了参数的文档定义
                        String content = tag.getContent().toText();
                        String[] ds = Strings.split(content, true, false, ';', '\r', '\n');
                        parseParameterProperty(ds, para);
                        return;
                    }
                }
            }
        }
    }

    public final static String DOC_MIN = "doc.min";
    public final static String DOC_MAX = "doc.max";
    public final static String DOC_EXAMPLE = "doc.example";
    public final static String DOC_DEFAULT = "doc.default";
    public final static String DOC_MANDATORY = "doc.mandatory";
    public final static String DOC_LENGTH = "doc.length";

    /**
     * 解析参数的 Javadoc文档
     *
     * @param ds
     * @param para
     */
    private void parseParameterProperty(String[] ds, ApiPara para) {
        if (ds != null && ds.length > 0) {
            para.description = ds[0];
        }

        for (int i = 1; i < ds.length; i++) {
            String[] ts = ds[i].trim().split(":");
            if (ts.length == 2) {
                String k = ts[0];
                if (k.equals(DOC_MIN)) {
                    para.min = ParserUtil.parseDouble(ts[1], 0);
                }
                if (k.equals(DOC_MAX)) {
                    para.max = ParserUtil.parseDouble(ts[1], 0);
                }
                if (k.equals(DOC_EXAMPLE)) {
                    para.example = ts[1];
                }
                if (k.equals(DOC_LENGTH)) {
                    para.len = ParserUtil.parseDouble(ts[1], 0).intValue();
                }
                if (k.equals(DOC_DEFAULT)) {
                    para.defaultValue = ts[1];
                }
                if (k.equals(DOC_MANDATORY)) {
                    para.mandatory = Lang.parseBoolean(ts[1]);
                }
            } else {
                para.comments.add(ds[i].trim());
            }
        }

    }


    /**
     * 查找方法的注解
     *
     * @param annotationExprs
     * @param typeName
     */
    Optional<AnnotationData> findAnnotation(NodeList<AnnotationExpr> annotationExprs, String typeName) {

        Optional<AnnotationExpr> name = annotationExprs.stream().filter(a -> a.getName().getIdentifier().equals(typeName)).findFirst();

        if (!name.isPresent()) {
            return Optional.empty();
        }

        AnnotationData data = new AnnotationData();
        name.ifPresent(new Consumer<AnnotationExpr>() {
            @Override
            public void accept(AnnotationExpr annotationExpr) {

                if (annotationExpr instanceof SingleMemberAnnotationExpr) {
                    SingleMemberAnnotationExpr e = annotationExpr.asSingleMemberAnnotationExpr();
                    data.put("value", e.getMemberValue());
                } else if (annotationExpr instanceof NormalAnnotationExpr) {
                    NormalAnnotationExpr e = annotationExpr.asNormalAnnotationExpr();
                    NodeList<MemberValuePair> pairs = e.getPairs();
                    for (MemberValuePair p : pairs) {
                        data.put(p.getNameAsString(), p.getValue());
                    }
                } else if (annotationExpr instanceof MarkerAnnotationExpr) {
                    MarkerAnnotationExpr e = annotationExpr.asMarkerAnnotationExpr();
                    data.put("value", new LiteralStringValueExpr("") {
                        @Override
                        public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
                            return null;
                        }

                        @Override
                        public <A> void accept(VoidVisitor<A> v, A arg) {

                        }
                    });
                }
            }
        });

        return Optional.of(data);

    }

    /**
     * 用controller中的信息 填充ENTRY
     *
     * @param controllerInformation
     * @param entry
     */
    private void updateEntryFromControllerInformation(ApiController controllerInformation, ApiEntry entry) {

        if (Strings.isBlank(entry.author)) {
            entry.author = controllerInformation.author;
        }

        if (!Strings.startsWithChar(entry.group, '/')) {
            entry.setGroup(controllerInformation.group + "/" + entry.group);
        }

    }

    /**
     * 解析控制器的基本信息
     *
     * @param t
     * @param annotations
     */
    private ApiController parseControllerInformation(TypeDeclaration t, NodeList<AnnotationExpr> annotations) {

        //控制器头信息
        ApiController ci = new ApiController();
        ci.clzName = t.getName().asString();

        Optional<Javadoc> javadoc = t.getJavadoc();

        javadoc.ifPresent(new Consumer<Javadoc>() {
            @Override
            public void accept(Javadoc javadoc) {
                //处理Javadoc
                processJavaComment(ci, javadoc, ci.clzName);
            }
        });

        final Optional<AnnotationData> data = findAnnotation(t.getAnnotations(), "RequestMapping");
        if (data.isPresent()) {
            data.get().data("value").ifPresent(new Consumer<Expression>() {
                @Override
                public void accept(Expression expression) {
                    ci.url = ParserUtil.removeSlash(expression.asLiteralStringValueExpr().getValue());
                }
            });
        }
        return ci;
    }

    public final static String DOC_TAG_GROUP = "doc.group";
    public final static String DOC_TAG_REFS = "doc.refs";
    public final static String DOC_TAG_TAGS = "doc.tags";
    public final static String DOC_TAG_STYLES = "doc.styles";

    /**
     * 从文档中
     *
     * @param javadoc
     * @return
     */
    public String findTagData(Javadoc javadoc, String tag) {

        if (Strings.isBlank(tag)) {
            return "";
        }

        List<JavadocBlockTag> blockTags = javadoc.getBlockTags();
        for (JavadocBlockTag t : blockTags) {
            if (tag.equals(t.getTagName())) {
                return Strings.trim(t.getContent().toText());
            }
        }
        return "";
    }

    /**
     * 处理JAVADOC  到 IDocklet对象中
     *
     * @param ci
     * @param javadoc
     */
    private void processJavaComment(IDoclet ci, Javadoc javadoc, String defaultName) {
        if (javadoc.getDescription().isEmpty()) {
            //没有描述信息,就取类名称
            ci.setName(defaultName);
        } else {
            // 处理整个描述信息，第一行描述信息作为控制器的标题
            String description = javadoc.getDescription().toText();
            Strings.trim(description);
            String[] lines = Strings.split(description, false, true, '\r', '\n');

            boolean first = true;
            for (String line : lines) {
                if (first) {
                    if (!Strings.isBlank(line)) {
                        ci.setName(line);
                        first = false;
                        continue;
                    }
                }
                ci.addComment(line);
            }
            if (first == true) {
                //没有找到描述信息
                ci.setName(defaultName);
            }
        }

        String author = findTagData(javadoc, "author");
        ci.setAuthor(author);

        String group = findTagData(javadoc, DOC_TAG_GROUP);
        if (group.endsWith("/")) {
            group = group.substring(0, group.length() - 1);
        }
        ci.setGroup(group);


        String tags = findTagData(javadoc, DOC_TAG_TAGS);
        String[] ts = Strings.split(tags, false, false, ' ', ',', ';');
        for (String t : ts) {
            ci.addTag(t);
        }

        String refs = findTagData(javadoc, DOC_TAG_REFS);
        String[] rs = Strings.split(refs, false, false, ' ', ',', ';');
        for (String t : rs) {
            ci.addRef(t);
        }

        String styles = findTagData(javadoc, DOC_TAG_STYLES);
        rs = Strings.split(styles, false, false, ' ', ',', ';');
        for (String t : rs) {
            ci.addStyle(t);
        }
    }
}



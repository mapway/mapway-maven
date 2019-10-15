package cn.mapway.doc.parser.util;

import cn.mapway.doc.parser.SourceParser;
import cn.mapway.doc.parser.exception.NotFoundException;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.declarations.ResolvedAnnotationDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.resolution.typesolvers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理编译好的单元信息
 */
public class CompiledUnits {
    private Map<String, CompilationUnit> units;
    private String parseRoot = "";

    public static CombinedTypeSolver combinedTypeSolver;

    public CompiledUnits(String parseRoot) {
        this.parseRoot = parseRoot;
        units = new HashMap<>();

        combinedTypeSolver = new CombinedTypeSolver();
        combinedTypeSolver.add(new JavaParserTypeSolver(new File(parseRoot)));
        combinedTypeSolver.add(new ReflectionTypeSolver());

        combinedTypeSolver.add(new ClassLoaderTypeSolver(SourceParser.class.getClassLoader()));

        // Configure JavaParser to use type resolution
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedTypeSolver);
        JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);

    }


    /**
     * 找到编译单元,如果没有缓存就从源文件中解析，如果找不到源文件 就抛出异常
     *
     * @param type
     * @return
     * @throws NotFoundException
     */
    public CompilationUnit findUnit(String type) throws NotFoundException {
        CompilationUnit compilationUnit = units.get(type);
        if (compilationUnit == null) {
            String path = ParserUtil.packageToPath(type) + ".java";
            path = parseRoot + "/" + path;
            File f = new File(path);
            try {
                compilationUnit = JavaParser.parse(f);
                units.put(type, compilationUnit);
            } catch (FileNotFoundException e) {
                throw new NotFoundException(type);
            }
        }
        return compilationUnit;
    }

    /**
     * 根据 类型的全名称 获取关于类的定义信息
     *
     * @param typeName
     * @return
     */
    public ResolvedReferenceTypeDeclaration findTypeDeclartion(String typeName) {
        ResolvedReferenceTypeDeclaration resolvedReferenceTypeDeclaration = combinedTypeSolver.solveType(typeName);
        return resolvedReferenceTypeDeclaration;
    }

    /**
     * 根据 类型的全名称 获取关于类的定义信息
     *
     * @param typeName
     * @return
     */
    public ResolvedReferenceTypeDeclaration findTypeDeclartion(Type type) {
        ResolvedType resolvedType = JavaParserFacade.get(combinedTypeSolver).convertToUsage(type);
        ResolvedReferenceTypeDeclaration resolvedReferenceTypeDeclaration = resolvedType.asReferenceType().getTypeDeclaration();
        return resolvedReferenceTypeDeclaration;
    }

    /**
     * @param exp
     * @return
     */
    public ResolvedAnnotationDeclaration resolveAnnotation(AnnotationExpr exp) {
        SymbolReference<ResolvedAnnotationDeclaration> solve = JavaParserFacade.get(combinedTypeSolver).solve(exp);

        ResolvedAnnotationDeclaration correspondingDeclaration = solve.getCorrespondingDeclaration();
        return correspondingDeclaration;

    }
}

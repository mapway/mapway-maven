package cn.mapway.doc.parser.util;

import com.github.javaparser.ast.expr.Expression;
import org.nutz.lang.Strings;

import java.util.HashMap;
import java.util.Optional;

/**
 * 注解信息
 */
public class AnnotationData extends HashMap<String, Expression> {
    public AnnotationData() {
    }

    /**
     * 获取data
     *
     * @param key
     * @return
     */
    public Optional<Expression> data(String key) {
        if (Strings.isBlank(key)) {
            return Optional.empty();
        }
        Expression expression = this.get(key);
        return Optional.ofNullable(expression);
    }
}

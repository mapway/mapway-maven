package cn.mapway.document.parser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.nutz.lang.Lang;

/**
 * 泛型类型定义操作
 *
 * @author zhangjianshe @gmail.com
 */
public class Types {

    /**
     * The Type.
     */
    Type type;
    /**
     * The Type mapper.
     */
    Map<String, Type> typeMapper;

    /**
     * Instantiates a new Types.
     *
     * @param t the t
     */
    public Types(Type t) {
        typeMapper = new HashMap<String, Type>();
        this.type = t;

        // 参数化类型
        if (type instanceof ParameterizedType) {
        }
    }

    /**
     * 返回参数的实例类型
     *
     * @param name the name
     * @return class
     */
    public Class<?> findClassByTypeName(String name) {
        Type t = typeMapper.get(name);
        if (t instanceof Class) {
            return (Class) t;
        }
        return Object.class;
    }

    /**
     * 获取类型定义的名称
     *
     * @return string [ ]
     */
    public String[] getTypeNames() {
        return Lang.collection2array(typeMapper.keySet());
    }
}

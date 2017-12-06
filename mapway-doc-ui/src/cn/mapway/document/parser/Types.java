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
 * @author zhangjianshe@gmail.com
 * @creation 2017-11-28 11:20:37
 */
public class Types {

	Type type;
	Map<String, Type> typeMapper;

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
	 * @return
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
	 * @return
	 */
	public String[] getTypeNames() {
		return Lang.collection2array(typeMapper.keySet());
	}
}

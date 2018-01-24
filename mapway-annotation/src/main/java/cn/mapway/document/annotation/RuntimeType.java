package cn.mapway.document.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解一个字段标明其类型在运行时动态调整，可以为所列举的类型其一
 * 
 * @author zhangjianshe@gmail.com
 * @creation 2017-11-21 23:28:01
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface RuntimeType {
	/**
	 * 支持的类
	 * @return
	 */
	Class<?>[] value() default {};
}

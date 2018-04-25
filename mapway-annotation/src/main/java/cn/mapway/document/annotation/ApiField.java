/******************************************************************************
 * <pre>
 * 
 *            =============================================================
 *            -   ____ _  _ ____ _  _ ____  _ _ ____ _  _ ____ _  _ ____  -
 *            -    __] |__| |__| |\ | | __  | | |__| |\ | [__  |__| |___  -
 *            -   [___ |  | |  | | \| |__| _| | |  | | \| ___] |  | |___  -
 *            -           http://hi.baidu.com/zhangjianshe                -
 *            =============================================================
 * </pre>
 *******************************************************************************/
package cn.mapway.document.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * API 字段注解.
 *
 * @author zhangjianshe @navinfo.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
public @interface ApiField {

    /**
     * 描述.
     *
     * @return the string
     */
    String value();

    /**
     * 长度.
     *
     * @return the int
     */
    int length() default 0;

    /**
     * 给出一个字段值得例子.
     *
     * @return the string
     */
    String example() default "";

    /**
     * 是否是强制类型.
     *
     * @return true, if successful
     */
    boolean mandidate() default true;

    /**
     * 字段的排序.
     *
     * @return the int
     */
    int order() default 0;

    /**
     * 引用的HTML文件.
     *
     * @return 引用的文件列表 string [ ]
     */
    String[] refs() default {};

}

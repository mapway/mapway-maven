package cn.mapway.ui.client.mvc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 如果一个类实现了 IFrameModule ,必须通过此注解为该模块命名.
 *
 * @author zhangjianshe
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ModuleMarker {

    /**
     * 模块代码.
     *
     * @return the string
     */
    public String value() default "";

    /**
     * 模块名称.
     *
     * @return the string
     */
    public String name() default "";

    /**
     * 是否是公共模块，无需认证
     *
     * @return boolean
     */
    public boolean isPublic() default false;

    /**
     * 模块图表
     *
     * @return string
     */
    public String icon() default "icon.png";

    /**
     * 说明.
     *
     * @return string
     */
    public String summary() default "";


    /**
     * 是否可以显示在界面上.
     *
     * @return boolean
     */
    public boolean visible() default true;

    /**
     * 模块分组信息.
     *
     * @return string
     */
    public String group() default "/";

}

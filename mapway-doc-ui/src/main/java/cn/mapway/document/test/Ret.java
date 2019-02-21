package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Code;
import cn.mapway.document.annotation.Codes;
import cn.mapway.document.annotation.Doc;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class Ret.
 */
@Doc(value = "RET对象定义", desc = "RET对象描述")
public class Ret<T> {

    /**
     * 参数的实际类型取决于实际的模板参数
     * <p>
     * Temp is a good boy!
     *
     * @author Testin@gmail.com
     * @date 2019-03-04
     */
    public T data;


    /**
     * 模板数据.
     */
    public List<String> templateData;
    /**
     * sads.
     */
    @ApiField(value = "double value", example = "35.4")
    @Codes({@Code(value = "0", desc = "调用成功"),
            @Code(value = "123", desc = "调用成功"),
            @Code(value = "1200", desc = "参数校验失败")})
    public Double d;
}

package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;


/**
 * Created by chengzhiguang on 2018/6/13.
 */
@Doc("操作信息出参")

public class ElementOper {

    @ApiField(value = "元素code",example = "light_on_index")
    public String elementCode;

    @ApiField(value = "元素name",example = "智能灯开")
    public String elementName;

    @ApiField(value = "元素类型，1开，2关",example = "1")
    public Integer elementType;

    @ApiField(value = "操作标识",example = "1232323223232")
    public String operCode;

    @ApiField(value = "操作名称",example = "1232323223232")
    public String operName;

    @ApiField(value = "1具体数值类2范围类3多操作",example = "1")
    public Integer homeOperType;

    @ApiField(value = "参数的值,如果是多操作 是operList",example = "[k:bright v:100]")
    public String paramValue;

    @ApiField(value = "操作参数规则 {1},{2},{3}, {1}为最小值 {2}为最大值 {3}为最小单位",example = "2700,7000")
    public String paramUnitRule;
}

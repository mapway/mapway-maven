package cn.mapway.document.test;

import java.util.List;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

/**
 * The Class TestObj.
 * 1.场景ID 统一为 Long.2.定义场景结构由原来的一个场景下可以有多个执行单元 改为一个场景下只允许存在一个执行单元，这样结构简化为如下的形式统一为 Long.2.定义场景结构由原来的一个场景下可以有多个执行单元 改为一个场景下只允许存在一个执行单元，这样结构简化为如下的形式统一为 Long.2.定义场景结构由原来的一个场景下可以有多个执行单元 改为一个场景下只允许存在一个执行单元，这样结构简化为如下的形式统一为 Long.2.定义场景结构由原来的一个场景下可以有多个执行单元 改为一个场景下只允许存在一个执行单元，这样结构简化为如下的形式
 *
 * @author 章子怡
 * @date  2019-10-03
 */
@Doc(value = "TestObject in TestObject", desc = "1.场景ID 统一为 Long.2.定义场景结构由原来的一个场景下可以有多个执行单元 改为一个场景下只允许存在一个执行单元，这样结构简化为如下的形式：")
public class TestObj {

    /**
     * The a.
     */
    @ApiField("eq")
    public int a;

    /**
     * The b.
     */
    @ApiField("eq")
    public Integer b;

    /**
     * This is an array for template field
     */
    @ApiField("eq")
    public List<String> listStrings;



}

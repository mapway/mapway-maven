package cn.mapway.doc.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

import java.util.List;

/**
 * The Class TestObj.
 * 1.场景ID 统一为 Long.2.定义场景结构由原来的一个场景下可以有多个执行单元 改为一个场景下只允许存在一个执行单元，这样结构简化为如下的形式：
 */
public class TestObj {

    /**
     * The a.
     * wenming is a culture .
     * @doc.example 999
     * @author  tomcat
     *
     */
    public int a;

    /**
     * 循环引用
     */
    public TestObj recursly;



}

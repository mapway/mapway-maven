package cn.mapway.doc.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

import java.util.List;


/**
 * The Class Req.Spring is here to generator a document for use in api.
 * </p>safsad.
 *
 * @author zhangjianshe@163.com
 * @doc.refs /isadsad
 */
@Doc(value = "触发条件", desc = "<p>触发条件分为两类:设备属性条件 和定时器触发条件，"
        + "<p>定时器(虚拟设备,deviceId=0)有两个属性 1000为定时属性 1001为倒计时属性，"
        + "<p>定时属性 value值为一个具体的时间格式为2016-05-18 13:24:03,"
        + "<p>倒计时属性 value值为一个为一个用/隔开的字符串 其格式为 [时间/间隔值/循环的单位 ] ,"
        + "<p>举一个例子 [ 2016-05-18 13:24:03/3/D] 表示 从 2016-05-18 13:24:03开始 每隔3天 的 13:24:03 秒执行动作,"
        + "<p>再一个例子 [ 2016-05-18 13:24:03/2/H] 表示 从 2016-05-18 13:24:03开始 每隔2小时 的 24:03 秒执行动作,"
        + "<p>再一个例子 [ 2016-05-18 13:24:03/30/M] 表示从 2016-05-18 13:24:03开始 每隔30分钟   03 秒执行动作."
        + "<p>格式中的取值范围[A/B/C] A 表示一个个发的时间点其格式2016-05-18 13:24:03 C取值有三种 D天 H小时 M分钟,"
        + "<p>当C为 D为天的时候 B取值为一个正整数  当C为 H时 B取值为[1-24],"
        + "<p>当C为 M为天的时候 [1-59]."

)
public class Req {


    /**
     * custom Special name for init field
     *
     * @doc.example 34.5534
     */
    @ApiField("int field")
    public int f1;

    /**
     * INteger field.
     */
    @ApiField("Integer field")
    public Integer f2;

    /**
     * The boolea field.
     */
    @ApiField("booelan field")
    public Boolean booleaField;


    /**
     * 字符串数组
     */
    public String[] stringArray;

    /**
     * 模板类 温度数据信息.
     *
     * @author zhangkaiwen.
     */
    public Temp<Float,Integer> templateField;


    /**
     * 模板类数组 温度数据信息.
     *
     * @author xiaodamo
     */
    public Temp<Float,String>[] tempeture;
    /**
     * The names.
     * @doc.example abd,hello
     */
    @ApiField("List test")
    public List<String> names;




}

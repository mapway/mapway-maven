package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengzhiguang on 2018/6/13.
 */

@Doc("预约组")
public class AppointmentGroupInfo {

    /**
     * 动作标识
     */
    @ApiField(value = "组标识",example = "set_on_off")
    public String groupCode;
    /**
     * 动作名称
     */
    @ApiField(value = "组名称",example = "设置开关")
    public String groupName;

    @ApiField(value = "组类型1.点击按钮 2.滑动按钮",example = "1")
    public String groupType;

    /**
     * 当前对应操作
     */
    @ApiField(value = "test")
    public String currentValue;
    /**
     * 动作对应操作列表
     */
    @ApiField(value = "动作对应操作列表")
    public List<ElementOper> operList;

    public AppointmentGroupInfo()
    {
        operList=new ArrayList<>();
    }
}

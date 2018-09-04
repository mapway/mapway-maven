package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengzhiguang on 2018/6/13.
 */
@Doc("根据产品查询操作信息接口出参")

public class GetOperInfoByProdTypeIdResp {
    /**
     * 预约唯一标识
     */
    @ApiField(value = "预约唯一标识", example = "1232323223232")
    public String appointmentCode;
    /**
     * 预约名称
     */
    @ApiField(value = "预约名称", example = "空调-开-自动-26℃")
    public String appointmentName;
    /**
     * 执行周期
     */
    @ApiField(value = "执行周期", example = "1,2,3,4,5,6,7")
    public String timecycle;
    /**
     * 1执行一次2每天3工作日4周末5自定义
     */
    @ApiField(value = "执行周期", example = "2")
    public String timecycleName;
    /**
     * 执行时间 HH:mm
     */
    @ApiField(value = "执行时间 HH:mm", example = "10:00")
    public String time;
//    /**
//     * 执行时间 HH:mm
//     */
//    @ApiField(value = "动作信息列表",example = "")
//    public List<CommandInfo> commandInfoList;

    @ApiField(value = "动作信息列表")
    public List<AppointmentGroupInfo> commandInfoList;

    public GetOperInfoByProdTypeIdResp() {
        commandInfoList = new ArrayList<>();
    }


}

package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

import java.util.ArrayList;
import java.util.List;

;

/**
 * Created by chengzhiguang on 2018/6/13.
 */
@Doc("动作信息")

public class CommandInfo {
    /**
     * 动作标识
     */
    @ApiField(value = "动作标识", example = "1232323223232")
    public String commandCode;
    /**
     * 动作名称
     */
    @ApiField(value = "动作名称", example = "1232323223232")
    public String commandName;
    /**
     * 当前对应操作
     */
    public String currentValue;
   
}

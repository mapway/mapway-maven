package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

@Doc("SubComplexBean")
public class SubComplexBean {
    @ApiField(value = "名称", example = "yuling")
    public String name;


    @ApiField(value = "年龄", example = "15")
    public Integer age;


    @ApiField(value = "性别", example = "female")
    public String gender;
}

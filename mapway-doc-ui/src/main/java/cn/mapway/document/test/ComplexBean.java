package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

import java.util.ArrayList;
import java.util.List;

@Doc("复杂的Bean结构")
public class ComplexBean {

    @ApiField(value = "名称", example = "zhangjianshe")
    public String name;

    @ApiField(value = "复杂的Bean结构")
    public List<SubComplexBean> complexBean;

    public ComplexBean() {
        complexBean = new ArrayList<>();
    }
}

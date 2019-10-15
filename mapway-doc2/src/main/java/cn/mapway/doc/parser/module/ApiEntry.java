package cn.mapway.doc.parser.module;


import java.util.ArrayList;
import java.util.List;

/**
 * 接口的定义信息
 */
public class ApiEntry extends ApiData {


    public String methodName;


    /**
     * 控制器名称
     */
    public String controller;


    /**
     * 访问的URL
     */
    public String url;


    /**
     * 访问的请求METHOD
     */
    public String method;

    /**
     * URL参数
     */
    public List<ApiPara> urlParameters;

    /**
     * 输入参数
     */
    public List<ApiPara> inputs;

    /**
     * 输出参数
     */
    public ApiPara output;

    public ApiEntry() {
        url = "";
        output = new ApiPara();
        inputs = new ArrayList<>();
        urlParameters = new ArrayList<>();
        method = "";
        controller = "";
        methodName = "";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addComment(String comment) {
        comments.add(comment);
    }

    @Override
    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }
}

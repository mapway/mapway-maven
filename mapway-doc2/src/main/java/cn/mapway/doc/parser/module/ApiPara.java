package cn.mapway.doc.parser.module;

import java.util.ArrayList;
import java.util.List;

/**
 * API参数
 */
public class ApiPara {

    ApiPara parent = null;

    public final static Integer FLD_BASIC = 0;
    public final static Integer FLD_ARRAY = 1;
    public final static Integer FLD_LIST = 2;


    public Integer fieldType;
    public String name;
    public String description;
    public String type;
    public String example;
    public int len;
    public double min;
    public double max;
    public String defaultValue;
    public boolean mandatory;
    public List<String> comments;
    private List<ApiPara> fields;
    public String author;
    public String date;

    public ApiPara() {
        fieldType = FLD_BASIC;
        author = "";
        name = "";
        type = "";
        example = "";
        len = 0;
        min = 0;
        max = 0;
        defaultValue = "";
        mandatory = true;
        comments = new ArrayList<>();
        fields = new ArrayList<>();
        description = "";
        date = "";
    }

    /**
     * 添加子节点
     *
     * @param para
     */
    public void addField(ApiPara para) {
        fields.add(para);
    }

    public void addComment(String line) {
        comments.add(line);
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }


    /**
     * 祖先类节点是否含有type类型
     *
     * @param type
     * @return
     */
    public boolean hasParent(String type) {
        ApiPara p = this;
        while (p != null) {
            if (p.type.equals(type)) {
                return true;
            }
            p = p.parent;
        }
        return false;
    }
}

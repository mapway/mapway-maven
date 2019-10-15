package cn.mapway.doc.parser.module;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API的基本信息
 */
public class ApiData implements IDoclet {

    public String name;
    public String group;
    public String author;
    public String date;
    public List<String> comments;
    public List<String> tags;
    public List<String> styles;
    /**
     * 引用信息
     */
    public Map<String, String> refs;

    public ApiData() {
        date = "";
        name = "";
        group = "";
        author = "";
        comments = new ArrayList<>();
        tags = new ArrayList<>();
        refs = new HashMap<>();
        styles = new ArrayList<>();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addComment(String comment) {
        this.comments.add(comment);
    }

    @Override
    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void addTag(String tag) {
        tags.add(tag);
    }

    @Override
    public void addRef(String t) {
        refs.put(t, "");
    }

    @Override
    public void addStyle(String t) {
        styles.add(t);
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }


}

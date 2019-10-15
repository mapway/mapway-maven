package cn.mapway.doc.parser.module;

import java.util.ArrayList;
import java.util.List;

/**
 * API 分组信息
 */
public class ApiGroup {
    /**
     * API 子分组信息
     */
    public List<ApiGroup> children;
    /**
     * API分组描述信息
     */
    public List<String> comments;

    /**
     * 该分组下的所有API接口定义信息
     */
    public List<ApiEntry> entries;
    /**
     * 父分组信息
     */
    public ApiGroup parent;
    /**
     * 分组名称
     */
    public String name;

    public ApiGroup() {
        this("/");
    }

    public ApiGroup(String name) {
        children = new ArrayList<ApiGroup>();
        comments = new ArrayList<>();
        entries = new ArrayList<>();
        parent = null;
        this.name = name;
    }

    public ApiGroup addComment(String comment) {
        comments.add(comment);
        return this;
    }

    /**
     * 分组下添加一个接口信息
     *
     * @param entry
     * @return
     */
    public ApiGroup addEntry(ApiEntry entry) {
        entries.add(entry);
        return this;
    }

    /**
     * 添加子节点 并返回子节点信息
     *
     * @param group
     * @return
     */
    public ApiGroup appendTo(ApiGroup group) {
        children.add(group);
        group.setParent(this);
        return group;
    }

    /**
     * 返回父节点信息
     *
     * @return
     */
    public ApiGroup getParent() {
        return parent;
    }


    /**
     * 设置父节点
     *
     * @param group
     * @return
     */
    private ApiGroup setParent(ApiGroup group) {
        this.parent = group;
        return this;
    }

    /**
     * 添加子节点 并返回本节点信息
     *
     * @param group
     * @return
     */
    public ApiGroup append(ApiGroup group) {
        children.add(group);
        group.setParent(this);
        return this;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(getGroupPath()).append("\r\n");
        for (String c : comments) {
            sb.append(c).append("\r\n");
        }
        return sb.toString();
    }

    public String getGroupPath() {
        StringBuilder sb = new StringBuilder();
        if (parent != null) {
            sb.append(parent.getGroupPath());
            sb.append("/").append(name);
        } else {
            sb.append("");
        }
        return sb.toString();
    }

    /**
     * 根节点
     */
    private static ApiGroup root;

    static {
        root = new ApiGroup();
    }

    /**
     * 从全局Group目录中找到符合 group的 ApiGroup对象
     *
     * @param group /Path/to/group
     * @return
     */
    public static ApiGroup findGroup(String group) {
        if (group == null || group.length() == 0) {
            return root;
        }
        group = group.trim();
        if (group.length() == 0) {
            return root;
        }

        String[] segs = group.split("\\/");

        //去掉空格 和 根/符号
        List<String> ss = new ArrayList<>();
        for (String s : segs) {
            s = s.trim();
            if (s.length() > 0) {
                ss.add(s);
            }
        }

        ApiGroup temp = root;

        for (String s : ss) {
            boolean find = false;
            for (ApiGroup g : temp.children) {
                if (g.name.equals(s)) {
                    find = true;
                    temp = g;
                    break;
                }
            }
            if (find == false) {
                ApiGroup newGroup = new ApiGroup(s);
                temp.append(newGroup);
                temp = newGroup;
            }
        }
        return temp;
    }

    /**
     * 打印整个Group
     *
     * @return
     */
    public String print() {
        StringBuilder sb = new StringBuilder();

        sb.append("---------------" + getGroupPath() + "   " + entries.size() + "----------------\r\n");

        for (ApiEntry e : entries) {
            sb.append(e.toString());
        }

        for (ApiGroup g : children) {
            sb.append(g.print());
        }
        return sb.toString();
    }
}

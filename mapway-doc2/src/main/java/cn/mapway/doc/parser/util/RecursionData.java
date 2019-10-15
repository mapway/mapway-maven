package cn.mapway.doc.parser.util;

import java.util.ArrayList;

/**
 * 处理递归数据
 */
public class RecursionData {
    private ArrayList<String> types;

    public RecursionData() {
        types = new ArrayList<>();
    }

    /**
     * 清空类型信息
     */
    public void reset() {
        types.clear();
    }

    /**
     * 是否处理了type类型
     *
     * @param type
     * @return
     */
    public boolean hasType(String type) {
        for (String s : types) {
            if (s.equals(type)) {
                return true;
            }
        }
        return false;
    }

    public void add(String s) {
        types.add(s);
    }
}

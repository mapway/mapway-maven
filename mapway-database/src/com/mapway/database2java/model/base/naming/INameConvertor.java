package com.mapway.database2java.model.base.naming;

import java.util.StringTokenizer;

/**
 * 名字转换策略
 */
public interface INameConvertor {
    /**
     * 转换名字.
     *
     * @param name the name
     * @return string
     */
    String convert(String name);
}

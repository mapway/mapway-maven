package com.mapway.database2java.model.base.naming;

/**
 * The type Upper case name convert.
 */
public class UpperCaseNameConvert implements INameConvertor {
    @Override
    public String convert(String name) {
        return name.toUpperCase();
    }
}

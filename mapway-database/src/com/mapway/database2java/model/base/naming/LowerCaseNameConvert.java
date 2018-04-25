package com.mapway.database2java.model.base.naming;

/**
 * The type Lower case name convert.
 */
public class LowerCaseNameConvert implements INameConvertor {
    @Override
    public String convert(String name) {
        return name.toLowerCase();
    }
}

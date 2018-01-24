package com.mapway.database2java.model.base.naming;

public class UpperCaseNameConvert implements INameConvertor {
    @Override
    public String convert(String name) {
        return name.toUpperCase();
    }
}

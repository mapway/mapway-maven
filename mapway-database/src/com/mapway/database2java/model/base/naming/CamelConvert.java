package com.mapway.database2java.model.base.naming;

import org.nutz.lang.Strings;

public class CamelConvert implements INameConvertor {
    @Override
    public String convert(String name) {
        return convertToCamelCase(name);
    }

    /**
     * Accepts any String & converts in into Camel Case String
     *
     * @param text
     * @return
     */
    public String convertToCamelCase(String text) {
        String[] segs;
        if (text.contains("_")) {
            segs = text.split("_");
        } else if (text.contains("-")) {
            segs = text.split("-");
        } else {
            segs = new String[1];
            segs[0] = text;
        }

        for (int i = 0; i < segs.length; i++) {
            String seg = segs[i];
            seg = seg.toLowerCase();
            seg = Strings.upperFirst(seg);
            segs[i] = seg;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < segs.length; i++) {
            if (i == 0) {
                sb.append(segs[i].toLowerCase());
            } else {
                sb.append(segs[i]);
            }
        }

        return sb.toString();

    }
}

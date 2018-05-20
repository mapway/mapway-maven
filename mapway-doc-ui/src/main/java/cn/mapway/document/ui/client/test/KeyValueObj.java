package cn.mapway.document.ui.client.test;

import com.google.gwt.core.client.JavaScriptObject;

public class KeyValueObj extends JavaScriptObject {

    protected KeyValueObj() {
    }

    public final static native KeyValueObj create(String key, String v)/*-{
        return {"k": key, "v": v};
    }-*/;

    public final native void set(String k, String v)/*-{
        this.k = k;
        this.v = v;
    }-*/;

    public final native String getKey()/*-{
        return this.k;
    }-*/;


    public final native String getValue()/*-{
        return this.v;
    }-*/;

    public final native void setValue(String v)/*-{
        this.v = v;
    }-*/;
}

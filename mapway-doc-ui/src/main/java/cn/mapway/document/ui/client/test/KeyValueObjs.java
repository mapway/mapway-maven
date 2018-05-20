package cn.mapway.document.ui.client.test;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;

public class KeyValueObjs extends JsArray<KeyValueObj> {

    protected KeyValueObjs() {
    }

    public static final KeyValueObjs parse(String jsonString) {
        if (jsonString == null || jsonString.length() == 0) {
            jsonString = "[]";
        }
        return JsonUtils.safeEval(jsonString);
    }

    public final void append(String key, String v) {
        boolean find = false;
        for (int i = 0; i < this.length(); i++) {
            KeyValueObj obj = this.get(i);
            if (obj.getKey().equals(key)) {
                obj.setValue(v);
                find = true;
                break;
            }
        }
        if (find == false) {
            KeyValueObj value = KeyValueObj.create(key, v);
            this.push(value);
        }
    }

    public final void remove(String key) {

        for (int i = 0; i < this.length(); i++) {
            KeyValueObj obj = this.get(i);
            if (obj.getKey().equals(key)) {
                splice(this.cast(), i, 1);
                break;
            }
        }
    }

    static native <T extends JavaScriptObject> T splice(JavaScriptObject array, int index, int count) /*-{
        return array.splice(index, count);
    }-*/;

    public final String toJSON() {
        return JsonUtils.stringify(this);
    }
}

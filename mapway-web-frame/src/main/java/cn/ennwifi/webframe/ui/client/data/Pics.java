package cn.ennwifi.webframe.ui.client.data;

import cn.mapway.ui.client.json.JSON;

import com.google.gwt.core.client.JsArray;

/**
 * 图片列表
 *
 * @author zhangjianshe
 */
public class Pics extends JsArray<PicData> {

    /**
     * Instantiates a new Pics.
     */
    protected Pics() {
    }

    /**
     * Create pics.
     *
     * @return the pics
     */
    public final native static Pics create()/*-{
		return [];
  }-*/;


    /**
     * To json string.
     *
     * @return the string
     */
    public final String toJson() {
        return JSON.stringify(this);
    }

    /**
     * From json pics.
     *
     * @param json the json
     * @return the pics
     */
    public final static Pics fromJson(String json) {
        if (json == null || json.length() == 0) {
            json = "[]";
        }
        return (Pics) JSON.parse(json);
    }

}

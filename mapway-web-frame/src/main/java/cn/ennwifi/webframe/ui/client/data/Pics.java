package cn.ennwifi.webframe.ui.client.data;

import cn.mapway.ui.client.json.JSON;

import com.google.gwt.core.client.JsArray;

/**
 * 图片列表
 * @author zhangjianshe
 *
 */
public class Pics extends JsArray<PicData> {

  protected Pics() {}

  public final native static Pics create()/*-{
		return [];
  }-*/;


  public final String toJson() {
    return JSON.stringify(this);
  }

  public final static Pics fromJson(String json) {
    if (json == null || json.length() == 0) {
      json = "[]";
    }
    return (Pics) JSON.parse(json);
  }

}

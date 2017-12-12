package cn.ennwifi.webframe.ui.client.data;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * 图片数据
 * @author zhangjianshe
 *
 */
public class PicData extends JavaScriptObject {

  protected PicData() {

  }

  /**
   * 构造客户端的JAVASCRIPT 对象
   * @return
   */
  public native final static PicData create()/*-{
		return {
			"title" : "",
			"url" : ""
		};
  }-*/;

  public native final String title()/*-{
		return this.title;
  }-*/;

  public native final String url()/*-{
		return this.url;
  }-*/;

  public native final PicData title(String title)/*-{
		this.title = title;
		return this;
  }-*/;

  public native final PicData url(String url)/*-{
		this.url = url;
		return this;
  }-*/;
}

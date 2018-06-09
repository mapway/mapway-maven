package cn.ennwifi.webframe.ui.client.data;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * 图片数据
 *
 * @author zhangjianshe
 */
public class PicData extends JavaScriptObject {

    /**
     * Instantiates a new Pic data.
     */
    protected PicData() {

    }

    /**
     * 构造客户端的JAVASCRIPT 对象
     *
     * @return pic data
     */
    public native final static PicData create()/*-{
		return {
			"title" : "",
			"url" : ""
		};
  }-*/;

    /**
     * Title string.
     *
     * @return the string
     */
    public native final String title()/*-{
		return this.title;
  }-*/;

    /**
     * Url string.
     *
     * @return the string
     */
    public native final String url()/*-{
		return this.url;
  }-*/;

    /**
     * Title pic data.
     *
     * @param title the title
     * @return the pic data
     */
    public native final PicData title(String title)/*-{
		this.title = title;
		return this;
  }-*/;

    /**
     * Url pic data.
     *
     * @param url the url
     * @return the pic data
     */
    public native final PicData url(String url)/*-{
		this.url = url;
		return this;
  }-*/;
}

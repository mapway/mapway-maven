package com.ksyzt.gwt.client.ui.richeditor;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

// TODO: Auto-generated Javadoc

/**
 * The Class RichBox.
 */
public class RichBox extends JavaScriptObject {

    /**
     * Instantiates a new rich box.
     */
    protected RichBox() {

    }

    /**
     * 初始化文本编辑器.
     *
     * @param id      textarea 控件ID
     * @param options the options
     * @return the rich box
     */
    public final static native RichBox initialize(String id, EditOption options)/*-{
		var t = $("#" + id).xheditor(options);
		return t;
	}-*/;

    /**
     * 初始化文本编辑器.
     *
     * @param e       the e
     * @param options the options
     * @return the rich box
     */
    public final static native RichBox initialize(Element e, EditOption options)/*-{
		var w=$wnd.$;
		var t = w(e).xheditor(options);
		return t;
	}-*/;

    /**
     * Un load.
     */
    public final native void unLoad()/*-{
		this.xheditor(false);
	}-*/;

    /**
     * Focus.
     */
    public final native void focus()/*-{
		this.focus();
	}-*/;

    /**
     * Html.
     *
     * @return the string
     */
    public final native String html()/*-{
		return this.getSource();
	}-*/;

    /**
     * Html.
     *
     * @param html the html
     */
    public final native void html(String html)/*-{
		this.setSource(html);
	}-*/;

}

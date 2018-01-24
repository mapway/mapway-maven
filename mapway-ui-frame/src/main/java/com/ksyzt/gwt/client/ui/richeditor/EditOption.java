package com.ksyzt.gwt.client.ui.richeditor;

import com.google.gwt.core.client.JavaScriptObject;

// TODO: Auto-generated Javadoc
/**
 * 文本编辑器初始化参数.
 *
 * @author Administrator
 */
public class EditOption extends JavaScriptObject {

	/**
	 * Gets the simple option.
	 *
	 * @return the simple option
	 */
	public final static EditOption getSimpleOption() {
		EditOption option = EditOption.create();
		option.tools(TS_SIMPLE);
		return option;
	}

	/** The Constant TS_FULL. */
	public final static String TS_FULL = "full";
	
	/** The Constant TS_MFULL. */
	public final static String TS_MFULL = "mfull";
	
	/** The Constant TS_SIMPLE. */
	public final static String TS_SIMPLE = "simple";
	
	/** The Constant TS_MINI. */
	public final static String TS_MINI = "mini";

	/** The Constant SKIN_O2007_SILVER. */
	public final static String SKIN_O2007_SILVER = "o2007silver";
	
	/** The Constant SKIN_VISTA. */
	public final static String SKIN_VISTA = "vista";
	
	/** The Constant SKIN_O2007_BLUE. */
	public final static String SKIN_O2007_BLUE = "o2007blue";
	
	/** The Constant SKIN_NOSTYLE. */
	public final static String SKIN_NOSTYLE = "o2007blue";

	/** The Constant LINK_EXT. */
	public final static String LINK_EXT = "txt,rar,zip,jpg,jpeg,gif,png,swf,wmv,avi,wma,mp3,mid,pdf,doc,docx";

	/**
	 * Instantiates a new edits the option.
	 */
	protected EditOption() {
		
	}

	/**
	 * Creates the.
	 *
	 * @return the edits the option
	 */
	public static native EditOption create()/*-{
		return new Object();
	}-*/;

	/**
	 * Width.
	 *
	 * @return the string
	 */
	public final native String width()/*-{
		return (this.width === undefined) ? "0" : this.width + "";
	}-*/;

	/**
	 * Width.
	 *
	 * @param width the width
	 */
	public final native void width(String width)/*-{
		this.width = width;
	}-*/;

	/**
	 * Height.
	 *
	 * @return the string
	 */
	public final native String height()/*-{
		return (this.height === undefined) ? "0" : this.height + "";
	}-*/;

	/**
	 * Height.
	 *
	 * @param height the height
	 */
	public final native void height(String height)/*-{
		this.height = height;
	}-*/;

	/**
	 * Tools.
	 *
	 * @return the string
	 */
	public final native String tools()/*-{
		return this.tools;
	}-*/;

	/**
	 * Tools.
	 *
	 * @param toolsStyle the tools style
	 */
	public final native void tools(String toolsStyle)/*-{
		this.tools = toolsStyle;
	}-*/;

	/**
	 * Sets the skin.
	 *
	 * @param skinname the new skin
	 */
	public final native void setSkin(String skinname)/*-{
		this.skin = skinname;
	}-*/;

	/**
	 * Sets the upload image path.
	 *
	 * @param path the new upload image path
	 */
	public final native void setUploadImagePath(String path)/*-{
		this.upImgUrl = path;
	}-*/;

	/**
	 * Sets the link ext.
	 *
	 * @param ext the new link ext
	 */
	public final native void setLinkExt(String ext)/*-{
		this.upLinkExt = ext;
	}-*/;

	/**
	 * Sets the upload link ext.
	 *
	 * @param path the new upload link ext
	 */
	public final native void setUploadLinkExt(String path)/*-{
		this.upLinkUrl = path;
	}-*/;

	/**
	 * Sets the editor root.
	 *
	 * @param path the new editor root
	 */
	public final native void setEditorRoot(String path)/*-{
		this.editorRoot = path;
	}-*/;

	/**
	 * Sets the url base.
	 *
	 * @param base the new url base
	 */
	public final native void setUrlBase(String base)/*-{
		this.urlBase = base;
	}-*/;

	/**
	 * Enable html 5 upload.
	 *
	 * @param b the b
	 */
	public final native void enableHtml5Upload(boolean b)/*-{
		this.html5Upload = false;
	}-*/;
}

package cn.mapway.document.ui.client.main;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The type Field code.
 */
public class FieldCode extends JavaScriptObject {

    /**
     * Instantiates a new Field code.
     */
    protected FieldCode() {
	}

    /**
     * Value string.
     *
     * @return the string
     */
    public final native String value()/*-{
		return this.value;
	}-*/;

    /**
     * Desc string.
     *
     * @return the string
     */
    public final native String desc()/*-{
		return this.desc;
	}-*/;
}

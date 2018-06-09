package cn.mapway.document.ui.client.component.ace;

import com.google.gwt.core.client.JavaScriptObject;

import java.util.Map;

/**
 * Ace command's argument could be either string or string-to-string map.
 */
public class AceCommandArgs {
	private final Object value;
	
	/**
	 * Create map argument. In case <code>data</code> is null map will be empty.
	 */
	public AceCommandArgs(Map<String, String> data) {
		value = JavaScriptObject.createObject();
		if (data != null)
			for (Map.Entry<String, String> entry : data.entrySet())
				with(entry.getKey(), entry.getValue());
	}
	
	/**
	 * Create text argument.
	 */
	public AceCommandArgs(String value) {
		this.value = value;
	}
	
	/**
	 * Add key-value pair to map.
	 * @param argKey
	 * @param argValue
	 */
	public native AceCommandArgs with(String argKey, String argValue) /*-{
		this.@cn.mapway.document.ui.client.component.ace.AceCommandArgs::value[argKey] = argValue;
		return this;
	}-*/;
	
	/**
	 * Give inner value.
	 * @return string or map depending on used constructor
	 */
	public Object getValue() {
		return value;
	}
}

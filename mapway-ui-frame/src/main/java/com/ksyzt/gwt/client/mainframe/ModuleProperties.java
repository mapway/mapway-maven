package com.ksyzt.gwt.client.mainframe;

import java.util.HashMap;

// TODO: Auto-generated Javadoc

/**
 * 模块初始化参数.
 *
 * @author Administrator
 */
public class ModuleProperties extends HashMap<String, Object> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /**
     * Gets the int.
     *
     * @param key          the key
     * @param defaultvalue the defaultvalue
     * @return the int
     */
    public int getInt(String key, int defaultvalue) {
		if (this.containsKey(key)) {
			Integer i = (Integer) this.get(key);
			return i;
		} else {
			return defaultvalue;
		}
	}

    /**
     * Gets the boolean.
     *
     * @param key          the key
     * @param defaultvalue the defaultvalue
     * @return the boolean
     */
    public Boolean getBoolean(String key, Boolean defaultvalue) {
		if (this.containsKey(key)) {
			Boolean i = (Boolean) this.get(key);
			return i;
		} else {
			return defaultvalue;
		}

	}

    /**
     * Gets the object.
     *
     * @param key          the key
     * @param defaultvalue the defaultvalue
     * @return the object
     */
    public Object getObject(String key, Object defaultvalue) {
		if (this.containsKey(key)) {
			return this.get(key);
		} else {
			return defaultvalue;
		}

	}

    /**
     * Gets the string.
     *
     * @param key          the key
     * @param defaultvalue the defaultvalue
     * @return the string
     */
    public String getString(String key, String defaultvalue) {
		if (this.containsKey(key)) {
			return (String) this.get(key);
		} else {
			return defaultvalue;
		}

	}

}

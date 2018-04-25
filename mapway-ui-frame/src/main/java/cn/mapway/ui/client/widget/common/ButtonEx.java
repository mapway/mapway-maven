package cn.mapway.ui.client.widget.common;

import com.google.gwt.user.client.ui.Button;

/**
 * The type Button ex.
 */
public class ButtonEx extends Button{

    /**
     * Instantiates a new Button ex.
     */
    public ButtonEx() {
		setStyleName("gwtEx-Button");
	}
	
	private Object data;

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData() {
		return data;
	}

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Object data) {
		this.data = data;
	}
	
}

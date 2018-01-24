package cn.mapway.ui.client.widget.common;

import com.google.gwt.user.client.ui.Button;

public class ButtonEx extends Button{
	
	public ButtonEx() {
		setStyleName("gwtEx-Button");
	}
	
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}

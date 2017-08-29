package cn.mapway.ui.client.widget.common;

import com.google.gwt.user.client.ui.TextBox;

public class TextBoxEx extends TextBox {

	public TextBoxEx() {
		setStyleName("gwtEx-TextBox");
	}

	public void setPlaceHolder(String placeholder) {
		this.getElement().setAttribute("placeholder", placeholder);
	}

}

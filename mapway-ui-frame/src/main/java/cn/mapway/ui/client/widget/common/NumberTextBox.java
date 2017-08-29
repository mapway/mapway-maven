package cn.mapway.ui.client.widget.common;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;

public class NumberTextBox extends TextBoxEx implements KeyUpHandler{
	
	public NumberTextBox() {
		setStyleName("gwtEx-TextBox");
		init();
	}
	
	private void init() { 
		this.addKeyUpHandler(this);
	}
	
	
	@Override
	public void onKeyUp(KeyUpEvent event) {
		String value = this.getValue();
		value = value.replaceAll("[^\\d\\.\\-]","");
		this.setValue(value);
	}
	
	public boolean checkNum(){
		String value = this.getValue();
		String reg = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
		return value.matches(reg);
	}

}

package cn.mapway.ui.client.widget.common;

import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.RadioButton;

public class RadioButtonEx extends RadioButton{
	
	@UiConstructor
	public RadioButtonEx(){
		super("");
		setStyleName("gwtEx-RadioButton");
	}
	public RadioButtonEx( String name ){
		super(name);
	}
	
	public RadioButtonEx( String name , SafeHtml label ){
		super(name , label );
	}
	
	public RadioButtonEx(String name, SafeHtml label, DirectionEstimator directionEstimator) {
		super(name, label, directionEstimator);
	}

}

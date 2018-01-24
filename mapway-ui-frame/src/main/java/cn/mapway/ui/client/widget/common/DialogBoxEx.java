package cn.mapway.ui.client.widget.common;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.DialogBox;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

public class DialogBoxEx extends DialogBox implements HasMessageHandlers{
	public DialogBoxEx() {
		super(new CloseCaption());
		setStyleName("gwtEx-DialogBox");
	}
	
	@Override
	  public HandlerRegistration addMessageHandler(MessageHandler handler) {
	    return addHandler(handler, MessageEvent.TYPE);
	  }

}

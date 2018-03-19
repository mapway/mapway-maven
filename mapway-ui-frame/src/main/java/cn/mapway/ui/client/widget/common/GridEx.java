package cn.mapway.ui.client.widget.common;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Grid;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * The type Grid ex.
 */
public class GridEx extends Grid implements HasMessageHandlers {
    /**
     * Instantiates a new Grid ex.
     */
    public GridEx() {
		init();
	}

	private void init() {
		this.setStyleName("gwtEx-Table");
		Element e = getElement();
		e.setAttribute("borderCollapse", "collapse");
		e.setAttribute("cellPadding", "10px");
		e.setAttribute("cellSpacing", "1px");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ksyzt.gwt.client.event.HasMessageHandlers#addMessageHandler(com.ksyzt
	 * .gwt.client.event. MessageHandler)
	 */
	@Override
	public HandlerRegistration addMessageHandler(MessageHandler handler) {
		return addHandler(handler, MessageEvent.TYPE);
	}

    /**
     * 报告消息.
     *
     * @param msg the msg
     */
    public void message(String msg) {
		MessageEvent ev = new MessageEvent(MessageEvent.MESSAGE, msg);
		fireEvent(ev);
	}

}

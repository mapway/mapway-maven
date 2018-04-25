package com.ksyzt.gwt.client.ui.dialog;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc

/**
 * The Class Alert.
 */
public class Alert extends DialogBox {

    /**
     * Instantiates a new alert.
     */
    public Alert() {

	}

    /**
     * Instantiates a new alert.
     *
     * @param caption the caption
     * @param msg     the msg
     */
    public Alert(String caption, String msg) {
		this.setHTML(caption);

		VerticalPanel p = new VerticalPanel();
		HorizontalPanel btns = new HorizontalPanel();
		btns.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		btns.setWidth("100%");
		Style style=btns.getElement().getStyle();
		style.setMarginTop(30, Unit.PX);
		style.setBackgroundColor("#f0f0f0");
		btns.setSpacing(5);
		p.add(new HTMLPanel(msg));
		p.add(btns);
		Button btn = new Button("确定");
		
		btn.addClickHandler(btn_ok_handler);
		btns.add(btn);
		this.add(p);
		this.setModal(true);
		this.setGlassEnabled(true);
	}

    /**
     * The btn ok handler.
     */
    ClickHandler btn_ok_handler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			Alert.this.hide(true);
		}
	};

}

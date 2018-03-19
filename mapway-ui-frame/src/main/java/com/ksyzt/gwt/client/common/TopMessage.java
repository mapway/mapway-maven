package com.ksyzt.gwt.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

// TODO: Auto-generated Javadoc

/**
 * The Class TopMessage.
 */
public class TopMessage extends PopupPanel {

	/** The ui binder. */
	private static TopMessageUiBinder uiBinder = GWT
			.create(TopMessageUiBinder.class);

    /**
     * The Interface TopMessageUiBinder.
     */
    interface TopMessageUiBinder extends UiBinder<Widget, TopMessage> {
	}

    /**
     * Instantiates a new top message.
     */
    public TopMessage() {
		setWidget(uiBinder.createAndBindUi(this));
		this.setAnimationEnabled(true);
		this.setAutoHideEnabled(false);
		this.setGlassEnabled(false);
		this.setModal(false);
		this.setStyleName("");
	}

    /**
     * The lb message.
     */
    @UiField
	Label lbMessage;
	
	/** The m callback. */
	private PositionCallback m_callback = new PositionCallback() {

		public void setPosition(int offsetWidth, int offsetHeight) {
			int width = Window.getClientWidth();
			int left = (width - offsetWidth) / 2;
			int top = 0;
			TopMessage.this.setPopupPosition(left, top);
		}

	};

    /**
     * Show mesasge.
     *
     * @param text the text
     */
    public void showMesasge(String text) {
		if (text == null || text.length() == 0) {
			this.hide();
		} else {
			lbMessage.setText(text);
			if (!this.isShowing()) {
				this.setPopupPositionAndShow(m_callback);
			}
			new Timer() {

				@Override
				public void run() {
					TopMessage.this.hide(true);
				}
			}.schedule(5 * 1000);
		}
	}

}

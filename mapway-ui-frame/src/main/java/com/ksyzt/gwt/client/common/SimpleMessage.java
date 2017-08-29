package com.ksyzt.gwt.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleMessage.
 */
public class SimpleMessage extends Composite {

	/**
	 * The Interface SimpleMessageUiBinder.
	 */
	interface SimpleMessageUiBinder extends UiBinder<Widget, SimpleMessage> {
	}

	/** The ui binder. */
	private static SimpleMessageUiBinder uiBinder = GWT
			.create(SimpleMessageUiBinder.class);

	/**
	 * Sets the message.
	 *
	 * @param t the t
	 * @param msg the msg
	 */
	public void setMessage(String t, String msg) {
		title.setText(t);
		html.setHTML(msg);
	}

	/**
	 * Instantiates a new simple message.
	 */
	public SimpleMessage() {
		initWidget(uiBinder.createAndBindUi(this));
		title.setText("");
		html.setHTML("");
	}

	/**
	 * Instantiates a new simple message.
	 *
	 * @param text the text
	 */
	public SimpleMessage(String text) {
		initWidget(uiBinder.createAndBindUi(this));
		title.setText("");
		html.setHTML(text);
	}

	/**
	 * Sets the message.
	 *
	 * @param text the new message
	 */
	public void setMessage(String text) {
		title.setText("");
		html.setHTML(text);
	}

	/** The html. */
	@UiField
	HTML html;
	
	/** The title. */
	@UiField
	Label title;
}

package com.ksyzt.gwt.client.common;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Image;

// TODO: Auto-generated Javadoc
/**
 * The Class DeleteButton.
 */
public class DeleteButton extends Image {

	/**
	 * Instantiates a new delete button.
	 *
	 * @param value the value
	 */
	public DeleteButton(int value) {
		super(SysResource.INSTANCE.delete());
		DOM.setElementPropertyInt(this.getElement(), "v", value);
		DOM.setStyleAttribute(this.getElement(), "cursor", "hand");
	}

	/**
	 * Instantiates a new delete button.
	 */
	@UiConstructor
	public DeleteButton() {
		super(SysResource.INSTANCE.delete());
		DOM.setElementPropertyInt(this.getElement(), "v", 0);
		DOM.setStyleAttribute(this.getElement(), "cursor", "hand");
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int getValue() {
		return DOM.getElementPropertyInt(this.getElement(), "v");
	}
}

package com.ksyzt.gwt.client.common;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Image;

// TODO: Auto-generated Javadoc

/**
 * The Class EditButton.
 */
public class EditButton extends Image {

    /**
     * Instantiates a new edits the button.
     */
    @UiConstructor
	public EditButton() {
		super(SysResource.INSTANCE.edit());
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

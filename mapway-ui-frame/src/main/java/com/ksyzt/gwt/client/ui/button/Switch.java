package com.ksyzt.gwt.client.ui.button;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Image;
import com.ksyzt.gwt.client.common.SysResource;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class Switch.
 */
public class Switch extends Image implements HasMessageHandlers {

	/** The b. */
	boolean b = true;
	
	/** The m click handler. */
	private ClickHandler m_click_handler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			toggle();
		}
	};

	/**
	 * Instantiates a new switch.
	 */
	@UiConstructor
	public Switch() {
		super();
		on();
		this.addClickHandler(m_click_handler);
	}

	/**
	 * On.
	 */
	public void on() {
		b = true;
		this.setResource(SysResource.INSTANCE.switchon());
	}

	/**
	 * Off.
	 */
	public void off() {
		b = false;
		this.setResource(SysResource.INSTANCE.switchoff());
	}

	/**
	 * Toggle.
	 *
	 * @return true, if successful
	 */
	public boolean toggle() {
		b = !b;
		setValue(b,true);
		return b;
	}

	/**
	 * Sets the value.
	 *
	 * @param b the new value
	 */
	public void setValue(boolean b) {
		setValue(b,false);
	}
	
	/**
	 * Sets the value.
	 *
	 * @param b the b
	 * @param fire the fire
	 */
	public void setValue(boolean b,boolean fire) {
		this.b = b;
		if (b == true) {
			this.setResource(SysResource.INSTANCE.switchon());
		} else {
			this.setResource(SysResource.INSTANCE.switchoff());
		}
		if(fire==true)
		{
			MessageEvent e=new MessageEvent(MessageEvent.VALUECHANGE, b);
			fireEvent(e);
		}
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public boolean getValue() {
		return b;
	}

	/* (non-Javadoc)
	 * @see com.ksyzt.gwt.client.event.HasMessageHandlers#addMessageHandler(com.ksyzt.gwt.client.event.MessageHandler)
	 */
	@Override
	public HandlerRegistration addMessageHandler(MessageHandler handler) {
		return addHandler(handler, MessageEvent.TYPE);
	}
}

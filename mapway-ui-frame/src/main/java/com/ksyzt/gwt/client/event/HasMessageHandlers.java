package com.ksyzt.gwt.client.event;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

// TODO: Auto-generated Javadoc
/**
 * The Interface HasMessageHandlers.
 */
public interface HasMessageHandlers extends HasHandlers {
	
	/**
	 * Adds the message handler.
	 *
	 * @param handler the handler
	 * @return the handler registration
	 */
	HandlerRegistration addMessageHandler(MessageHandler handler);
}

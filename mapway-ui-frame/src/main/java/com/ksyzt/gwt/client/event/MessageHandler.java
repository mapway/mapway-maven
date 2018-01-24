package com.ksyzt.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;

// TODO: Auto-generated Javadoc
/**
 * The Interface MessageHandler.
 */
public interface MessageHandler extends EventHandler {
	
	/**
	 * On message.
	 *
	 * @param sender the sender
	 * @param message the message
	 * @param value the value
	 */
	void onMessage(Object sender,Integer message, Object value);
}

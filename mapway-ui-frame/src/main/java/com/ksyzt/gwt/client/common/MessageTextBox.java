package com.ksyzt.gwt.client.common;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.TextBox;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

// TODO: Auto-generated Javadoc

/**
 * The Class MessageTextBox.
 */
public class MessageTextBox extends TextBox implements HasMessageHandlers {

    /* (non-Javadoc)
     * @see com.ksyzt.gwt.client.event.HasMessageHandlers#addMessageHandler(com.ksyzt.gwt.client.event.MessageHandler)
     */
    public HandlerRegistration addMessageHandler(MessageHandler handler) {
        if (m_register != null) {
            m_register.removeHandler();
        }
        m_register = addHandler(handler, MessageEvent.TYPE);
        return m_register;
    }

    /**
     * The m register.
     */
    HandlerRegistration m_register = null;

    /**
     * The m key down handler.
     */
    private KeyDownHandler m_key_down_handler = new KeyDownHandler() {


        public void onKeyDown(KeyDownEvent event) {
            if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                MessageEvent ev = new MessageEvent(MessageEvent.RETURN, 0);
                fireEvent(ev);
            }

        }
    };

    /**
     * The m focus handler.
     */
    private FocusHandler m_focus_handler = new FocusHandler() {


        public void onFocus(FocusEvent event) {


        }
    };

    /**
     * The m blur handler.
     */
    private BlurHandler m_blur_handler = new BlurHandler() {


        public void onBlur(BlurEvent event) {
            MessageEvent ev = new MessageEvent(MessageEvent.CANCEL, 0);
            fireEvent(ev);

        }
    };

    /**
     * Instantiates a new message text box.
     */
    @UiConstructor
    public MessageTextBox() {
        super();
        this.addKeyDownHandler(m_key_down_handler);
        this.addFocusHandler(m_focus_handler);
        this.addBlurHandler(m_blur_handler);
    }

}

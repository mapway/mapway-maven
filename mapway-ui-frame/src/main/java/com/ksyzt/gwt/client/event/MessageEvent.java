package com.ksyzt.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;

// TODO: Auto-generated Javadoc

/**
 * The Class MessageEvent.
 */
public class MessageEvent extends GwtEvent<MessageHandler> {

    /**
     * The type.
     */
    public static Type<MessageHandler> TYPE = new Type<MessageHandler>();

    /* (non-Javadoc)
     * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
     */
    @Override
    protected void dispatch(MessageHandler handler) {
        handler.onMessage(this.getSource(), message, value);
    }

    /* (non-Javadoc)
     * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
     */
    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<MessageHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * The message.
     */
    Integer message;

    /**
     * The value.
     */
    Object value;

    /**
     * Gets the value.
     *
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public Integer getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(Integer message) {
        this.message = message;
    }

    /**
     * Instantiates a new message event.
     *
     * @param message the message
     * @param value   the value
     */
    public MessageEvent(Integer message, Object value) {
        this.message = message;
        this.value = value;

    }

    /**
     * The Constant CLOSE_AND_REFRESH.
     */
    public static final Integer CLOSE_AND_REFRESH = 1;

    /**
     * The Constant CLOSE.
     */
    public static final Integer CLOSE = 2;

    /**
     * The Constant CANCEL.
     */
    public static final Integer CANCEL = 3;

    /**
     * The Constant CHILDCLICK.
     */
    public static final Integer CHILDCLICK = 4;

    /**
     * The Constant CREATEROOT.
     */
    public static final Integer CREATEROOT = 5;

    /**
     * The Constant ITEMCLICK.
     */
    public static final Integer ITEMCLICK = 6;

    /**
     * The Constant SELECTUSER.
     */
    public static final Integer SELECTUSER = 7;

    /**
     * The Constant CONTEXTMENU.
     */
    public static final Integer CONTEXTMENU = 8;

    /**
     * The Constant DELETE.
     */
    public static final Integer DELETE = 9;

    /**
     * The Constant EDIT.
     */
    public static final Integer EDIT = 10;

    /**
     * The Constant UPDATE.
     */
    public static final Integer UPDATE = 11;

    /**
     * The Constant REFRESH.
     */
    public static final Integer REFRESH = 12;

    /**
     * The Constant TRANSFROM.
     */
    public static final Integer TRANSFROM = 13;

    /**
     * The Constant OK.
     */
    public static final Integer OK = 14;

    /**
     * The Constant EDITCONTENT.
     */
    public static final Integer EDITCONTENT = 15;

    /**
     * The Constant PAGE.
     */
    public static final Integer PAGE = 16;

    /**
     * The Constant NEXT.
     */
    public static final Integer NEXT = 17;

    /**
     * The Constant PREV.
     */
    public static final Integer PREV = 18;

    /**
     * The Constant FIRST.
     */
    public static final Integer FIRST = 19;

    /**
     * The Constant LAST.
     */
    public static final Integer LAST = 20;

    /**
     * The Constant SYLLABUS_CONTENT_EDIT.
     */
    public static final Integer SYLLABUS_CONTENT_EDIT = 21;

    /**
     * The Constant EDIT_SECTION.
     */
    public static final Integer EDIT_SECTION = 22;

    /**
     * The Constant SELECT.
     */
    public static final Integer SELECT = 23;

    /**
     * The Constant SAVE_TO_WEB.
     */
    public static final Integer SAVE_TO_WEB = 24;

    /**
     * The Constant PREVIEW.
     */
    public static final Integer PREVIEW = 25;

    /**
     * The Constant VALUECHANGE.
     */
    public static final Integer VALUECHANGE = 26;

    /**
     * The Constant UP.
     */
    public static final Integer UP = 27;

    /**
     * The Constant DOWN.
     */
    public static final Integer DOWN = 28;

    /**
     * The Constant SAVE_TO_TEMPLATE.
     */
    public static final Integer SAVE_TO_TEMPLATE = 29;

    /**
     * The Constant MESSAGE.
     */
    public static final Integer MESSAGE = 30;

    /**
     * The Constant SUBMIT.
     */
    public static final Integer SUBMIT = 31;

    /**
     * The Constant RESRTRICT.
     */
    public static final Integer RESRTRICT = 32;

    /**
     * The Constant NAVI.
     */
    public static final Integer NAVI = 33;

    /**
     * The Constant INFO.
     */
    public static final Integer INFO = 34;

    /**
     * The Constant DOING.
     */
    public static final Integer DOING = 35;

    /**
     * The Constant CHECK_ERROR.
     */
    public static final Integer CHECK_ERROR = 36;

    /**
     * The Constant SAVE.
     */
    public static final Integer SAVE = 37;

    /**
     * The Constant CHANGE_PWD.
     */
    public static final Integer CHANGE_PWD = 38;

    /**
     * The Constant CREATE.
     */
    public static final Integer CREATE = 39;

    /**
     * The Constant SUCCESS.
     */
    public static final Integer SUCCESS = 40;

    /**
     * The Constant FAIL.
     */
    public static final Integer FAIL = 41;

    /**
     * The Constant DETAIL.
     */
    public static final Integer DETAIL = 42;

    /**
     * The Constant BIND_PRODUCT.
     */
    public static final Integer BIND_PRODUCT = 43;

    /**
     * The Constant BIND_CLASSROOM.
     */
    public static final Integer BIND_CLASSROOM = 44;

    /**
     * The Constant LOGIN.
     */
    public static final Integer LOGIN = 45;

    /**
     * The Constant RETURN.
     */
    public static final Integer RETURN = 46;

    /**
     * The Constant COLOR.
     */
    public static final Integer COLOR = 47;

    /**
     * The Constant COLORS.
     */
    public static final Integer COLORS = 48;

    /**
     * The Constant QUIT.
     */
    public static final Integer QUIT = 49;

    /**
     * The Constant ENABLED.
     */
    public static final Integer ENABLED = 50;

    /**
     * The Constant SEARCH.
     */
    public static final Integer SEARCH = 51;

    /**
     * The Constant DBCLICK.
     */
    public static final Integer DBCLICK = 52;

    /**
     * The Constant MODIFY.
     */
    public static final Integer MODIFY = 53;

    /**
     * The READY STATE.
     */
    public static final Integer READY = 54;

    /**
     * The Constant CUSTOM.
     */
    public static final Integer CUSTOM = 3000;

}

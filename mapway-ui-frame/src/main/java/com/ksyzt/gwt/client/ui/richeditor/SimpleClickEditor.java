package com.ksyzt.gwt.client.ui.richeditor;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * The type Simple click editor.
 *
 * @author zhangjianshe
 */
public class SimpleClickEditor extends FlowPanel implements HasMessageHandlers {

    /**
     * The current editor.
     */
    private static SimpleClickEditor currentEditor;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ksyzt.gwt.client.event.HasMessageHandlers#addMessageHandler(com.ksyzt.gwt.client.event.
     * MessageHandler)
     */
    @Override
    public HandlerRegistration addMessageHandler(MessageHandler handler) {
        return addHandler(handler, MessageEvent.TYPE);
    }

    /**
     * The html panel.
     */
    HTML htmlPanel;

    /**
     * The txt box.
     */
    private static TextArea txtBox;

    /**
     * The txt rich.
     */
    private static RichEditor txtRich;

    /**
     * The btn toggle.
     */
    private Button btnToggle;

    /**
     * The btn confirm.
     */
    private Button btnConfirm;

    /**
     * The tools.
     */
    private HorizontalPanel tools;

    /**
     * The m min width.
     */
    private int mMinWidth;

    /**
     * The m min height.
     */
    private int mMinHeight;

    /**
     * The btn confirm handler.
     */
    private ClickHandler btnConfirmHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            event.stopPropagation();
            hideTxtBox();
            hideTxtRich();
            tools.setVisible(false);

        }
    };

    /**
     * 最小的宽度.
     *
     * @param w the new min width
     */
    public void setMinWidth(int w) {
        mMinWidth = w;
    }

    /**
     * 设置最小高度.
     *
     * @param h the new min height
     */
    public void setMinHeight(int h) {
        mMinHeight = h;
    }

    /**
     * Instantiates a new simple click editor.
     */
    @UiConstructor
    public SimpleClickEditor() {

        super();
        htmlPanel = new HTML();
        this.getElement().getStyle().setPosition(Position.RELATIVE);
        this.add(htmlPanel);

        htmlPanel.addClickHandler(mHtmlPanelClick);

        tools = new HorizontalPanel();
        btnToggle = new Button(EDIT_HTML);
        btnConfirm = new Button(EDIT_CONFITM);
        tools.add(btnToggle);
        tools.add(btnConfirm);
        btnConfirm.setStyleName(mToogleStyleName);
        btnToggle.setStyleName(mToogleStyleName);
        comfirmReg = btnConfirm.addClickHandler(btnConfirmHandler);
        toggleReg = btnToggle.addClickHandler(btnToggleHandler);

        btnToggle.setVisible(mbEnableHtml);
        Style style = tools.getElement().getStyle();
        style.setPosition(Position.ABSOLUTE);
        style.setLeft(0, Unit.PX);
        style.setTop(-24, Unit.PX);
        btnToggle.getElement().getStyle().setLineHeight(18, Unit.PX);
        btnConfirm.getElement().getStyle().setLineHeight(18, Unit.PX);
        this.add(tools);
        tools.setVisible(false);

    }

    /**
     * The m user object.
     */
    private Object m_userObject = null;

    /**
     * 设置关联对象.
     *
     * @param obj the new user object
     */
    public void setUserObject(Object obj) {
        m_userObject = obj;
    }

    /**
     * 获取关联对象.
     *
     * @return the user object
     */
    public Object getUserObject() {
        return m_userObject;
    }

    /**
     * 进入编辑HTML模式.
     */
    private void htmlEditMode() {

        sureRichBox();

        tools.setVisible(true);
        btnToggle.setText(EDIT_TEXT);

        String str = getValue();
        if (str.equals(tip)) {
            str = "";
        }

        txtRich.setValue(toHtmlValue(str));
        htmlPanel.setVisible(false);
        txtRich.setVisible(true);

    }

    /**
     * 将HTML数据转化为 TEXT数据.
     *
     * @param str the str
     * @return the string
     */
    private String toTextValue(String str) {
        str = str.replaceAll("<[bB]{1,1}[rR]{1,1}\\s*>", "\n");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("<", "&lt;");
        return str;
    }

    /**
     * 进入编辑基本文本模式.
     */
    private void textEditMode() {

        tools.setVisible(true);
        hideTxtRich();
        sureTextBox();

        String str = getValue();
        if (str.equals(tip)) {
            str = "";
        }
        // HTML==>TEXT
        str = toTextValue(str);

        txtBox.setValue(str);
        htmlPanel.setVisible(false);
        txtBox.setVisible(true);

        if (mbEnableHtml) {
            btnToggle.setVisible(true);
            btnToggle.setText(EDIT_HTML);
        } else {
            btnToggle.setVisible(false);
        }

        txtBox.setFocus(true);
    }

    /**
     * 进入展示模式.
     */
    public void canvasMode() {
        hideTxtBox();
        hideTxtRich();
        tools.setVisible(false);
        MessageEvent ev = new MessageEvent(MessageEvent.VALUECHANGE, getValue());
        fireEvent(ev);
    }

    /**
     * The comfirm reg.
     */
    HandlerRegistration comfirmReg = null;

    /**
     * The toggle reg.
     */
    HandlerRegistration toggleReg = null;

    /**
     * The m html panel click.
     */
    // 点击HTML内容
    private ClickHandler mHtmlPanelClick = new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            mWidth = htmlPanel.getOffsetWidth();
            mHeight = htmlPanel.getOffsetHeight();

            if (currentEditor != null) {
                currentEditor.canvasMode();
            }
            if (m_enabledEdit == true) {
                currentEditor = SimpleClickEditor.this;

                textEditMode();
            } else {
                currentEditor = null;

            }

        }
    };

    /**
     * 基本模式和HTML模式转换.
     */
    private ClickHandler btnToggleHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            event.stopPropagation();

            // 用户点击了toogle按钮
            if (btnToggle.getText().equals(EDIT_HTML)) {
                // 进入HTML编辑模式
                hideTxtBox();
                htmlEditMode();
            } else {
                // 进入Text编辑模式
                log("text mode");
                hideTxtRich();
                textEditMode();
                txtBox.setFocus(true);
            }
        }

    };

    /**
     * 隐藏编辑HTML框.
     */
    private void hideTxtRich() {

        if (txtRich != null && txtRich.isAttached() && txtRich.isVisible()) {
            if (currentEditor != null) {
                String html = txtRich.getValue();
                currentEditor.htmlPanel.setVisible(true);
                txtRich.setVisible(false);
                currentEditor.setValue(html);
            }
        }

    }

    /**
     * 隐藏编辑基本文本框.
     */
    private void hideTxtBox() {

        if (txtBox != null && txtBox.isAttached() && txtBox.isVisible()) {
            if (currentEditor != null) {
                String text = txtBox.getValue();
                // TEXT==>HTML
                String html = toHtmlValue(text);

                currentEditor.htmlPanel.setVisible(true);
                txtBox.setVisible(false);
                currentEditor.setValue(html);
            }
        }

    }

    /**
     * To html value.
     *
     * @param text the text
     * @return the string
     */
    private String toHtmlValue(String text) {
        text = text.replaceAll("&lt;", "<");
        text = text.replaceAll("&gt;", ">");
        text = text.replaceAll("\n", "<br/>");
        return text;
    }

    /**
     * Log.
     *
     * @param msg the msg
     */
    private native final static void log(String msg)/*-{
		console.log(msg);
  }-*/;

    /**
     * The m b toggle click.
     */
    boolean m_bToggleClick = false;

    /**
     * The Constant EDIT_HTML.
     */
    private final static String EDIT_HTML = "编辑HTML";

    /**
     * The Constant EDIT_TEXT.
     */
    private final static String EDIT_TEXT = "编辑文本";

    /**
     * The Constant EDIT_CONFITM.
     */
    private final static String EDIT_CONFITM = "确定";

    /**
     * The m width.
     */
    int mWidth;

    /**
     * The m height.
     */
    int mHeight;

    /**
     * Sure text box.
     */
    private void sureTextBox() {
        if (txtBox == null) {
            txtBox = new TextArea();
            if (!m_style.equals("")) {
                txtBox.setStyleName(m_style);
            }
        }
        if (txtBox.isAttached()) {
            txtBox.removeFromParent();
        }

        if (mMinWidth >= 0) {
            mWidth = Math.max(mWidth, mMinWidth);
        }
        txtBox.setWidth(mWidth + "px");

        if (mMinHeight >= 0) {
            mHeight = Math.max(mHeight, mMinHeight);
        }
        txtBox.setHeight(mHeight + "px");
        this.add(txtBox);

    }

    /**
     * Sure rich box.
     */
    private void sureRichBox() {

        if (txtRich == null) {
            txtRich = new RichEditor();
        }
        if (txtRich.isAttached()) {
            txtRich.removeFromParent();
        }

        if (mMinWidth >= 0) {
            mWidth = Math.max(mWidth, mMinWidth);
        }
        txtRich.setWidth(mWidth + "px");

        if (mMinHeight >= 0) {
            mHeight = Math.max(mHeight, mMinHeight);
        }

        txtRich.setHeight(mHeight + "px");

        this.add(txtRich);

    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        String html = htmlPanel.getHTML();
        if (html.equals(tip)) {
            return "";
        } else {
            return toTextValue(html);
        }
    }

    /**
     * Gets the html.
     *
     * @return the html
     */
    public String getHtml() {
        String html = htmlPanel.getHTML();
        if (html.equals(tip)) {
            return "";
        } else {
            return html;
        }
    }

    /**
     * Sets the tip.
     *
     * @param tip the new tip
     */
    public void setTip(String tip) {
        this.tip = tip;
    }

    /**
     * The tip.
     */
    private String tip = "点击编辑...";

    /**
     * Sets the value.
     *
     * @param text the new value
     */
    public void setValue(String text) {

        log(text);
        if (text == null || text.equals("")) {
            text = tip;
        } else {
        }
        String html = toHtmlValue(text);
        htmlPanel.setHTML(html);
    }

    /**
     * Sets the html.
     *
     * @param html the new html
     */
    public void setHtml(String html) {

        if (html == null || html.equals("")) {
            html = tip;
        } else {
        }

        htmlPanel.setHTML(html);
    }

    /**
     * The m style.
     */
    String m_style = "";

    /**
     * Sets the text box style.
     *
     * @param editstyle the new text box style
     */
    public void setTextBoxStyle(String editstyle) {
        m_style = editstyle;
    }

    /**
     * The m enabled edit.
     */
    private boolean m_enabledEdit = true;

    /**
     * Sets the enable edit.
     *
     * @param v the new enable edit
     */
    public void setEnableEdit(boolean v) {
        m_enabledEdit = v;
    }

    /**
     * The mb enable html.
     */
    boolean mbEnableHtml = true;

    /**
     * 是否允许HTML编辑.
     *
     * @param b the new enable HTML
     */
    public void setEnableHTML(boolean b) {
        mbEnableHtml = b;
    }

    /**
     * The m toogle style name.
     */
    private String mToogleStyleName = "toogleButton";

    /**
     * 设置转换按钮样式.
     *
     * @param styleName the new toggle style
     */
    public void setToggleStyle(String styleName) {
        mToogleStyleName = styleName;
        if (tools != null) {
            btnToggle.setStyleName(mToogleStyleName);
            btnConfirm.setStyleName(mToogleStyleName);
        }
    }
}

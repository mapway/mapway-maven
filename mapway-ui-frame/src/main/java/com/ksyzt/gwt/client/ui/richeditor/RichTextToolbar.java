/*
 * This software is published under the Apchae 2.0 licenses.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Author: Erik Scholtz
 * Web: http://blog.elitecoderz.net
 */

package com.ksyzt.gwt.client.ui.richeditor;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RichTextArea.Formatter;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

// TODO: Auto-generated Javadoc

/**
 * The Class RichTextToolbar.
 */
public class RichTextToolbar extends Composite {

    /**
     * The m dlg image.
     */
    ImageUploadPanel m_dlg_image;

    /**
     * The m ap shadow.
     */
    HTMLPanel m_ap_shadow;

    /**
     * The m ap content.
     */
    HTMLPanel m_ap_content;

    /**
     * The m ap line.
     */
    HTMLPanel m_ap_line;

    /**
     * The m b show popup.
     */
    boolean m_b_show_popup = false;

    /**
     * The back widget.
     */
    Widget back_widget;

    /**
     * The back css.
     */
    String back_css;

    /**
     * The m popup.
     */
    PopupPanel m_popup;

    /**
     * Show popup.
     *
     * @param attacheElement the attache element
     * @param w              the w
     */
    private void showPopup(Widget attacheElement, Widget w) {
        back_widget = attacheElement;
        if (w == null) {
            w = new HTMLPanel("");
            w.setSize("100px", "100px");
            m_ap_content.clear();
            m_ap_content.add(w);
        } else {
            m_ap_content.clear();
            m_ap_content.add(w);
        }

        m_ap_content.setVisible(true);
        m_ap_shadow.setVisible(true);
        m_ap_line.setVisible(true);

        int aleft = attacheElement.getAbsoluteLeft();
        int atop = attacheElement.getAbsoluteTop();
        int aw = attacheElement.getOffsetWidth();
        int ah = attacheElement.getOffsetHeight();

        int width = m_ap_content.getOffsetWidth();
        int height = m_ap_content.getOffsetHeight();

        int lbx = aleft;
        int lby = atop + ah;
        int rbx = lbx + aw;
        int rby = lby;

        Style style = m_ap_content.getElement().getStyle();
        style.setLeft(rbx - width, Unit.PX);
        style.setTop(lby - 1, Unit.PX);

        style = m_ap_shadow.getElement().getStyle();
        style.setLeft(rbx - width + 3, Unit.PX);
        style.setTop(lby + 2, Unit.PX);
        style.setWidth(width, Unit.PX);
        style.setHeight(height, Unit.PX);

        style = m_ap_line.getElement().getStyle();
        style.setLeft(lbx + 1, Unit.PX);
        style.setTop(lby - 1, Unit.PX);
        style.setWidth(aw - 2, Unit.PX);

        back_css = attacheElement.getStyleName();
        attacheElement.setStyleName("toolbar_over");

        m_ap_content.getElement().focus();

        m_b_show_popup = true;

    }

    /**
     * Hide popup.
     */
    public void hidePopup() {
        m_ap_content.setVisible(false);
        m_ap_shadow.setVisible(false);
        m_ap_line.setVisible(false);

        if (back_widget != null) {
            m_b_show_popup = false;
            back_widget.setStyleName(back_css);
        }

    }

    /**
     * Inits the popup panel.
     */
    private void initPopupPanel() {
        m_ap_shadow = new HTMLPanel("div", "");
        m_ap_content = new HTMLPanel("div", "");
        m_ap_line = new HTMLPanel("div", "");

        Style style = m_ap_content.getElement().getStyle();
        style.setProperty("border", "solid 1px skyblue");
        style.setBackgroundColor("white");
        style.setPadding(5, Unit.PX);
        style.setPosition(Position.ABSOLUTE);
        style.setZIndex(1000000);

        style = m_ap_shadow.getElement().getStyle();
        style.setBackgroundColor("#f0f0f0");
        style.setPosition(Position.ABSOLUTE);
        style.setZIndex(999999);

        m_ap_line.setHeight("1px");
        style = m_ap_line.getElement().getStyle();
        style.setBackgroundColor("white");
        style.setPosition(Position.ABSOLUTE);
        style.setZIndex(1000001);

        RootPanel.get().add(m_ap_shadow);
        RootPanel.get().add(m_ap_content);
        RootPanel.get().add(m_ap_line);

        m_ap_content.setVisible(false);

        m_ap_shadow.setVisible(false);

        m_ap_line.setVisible(false);

    }

    /**
     * Local CONSTANTS *.
     */
// ImageMap and CSS related
    public static final String HTTP_STATIC_ICONS_GIF = GWT.getModuleBaseURL()
            + "../images/toolbars.gif";

    /**
     * The Constant CSS_ROOT_NAME.
     */
    private static final String CSS_ROOT_NAME = "RichTextToolbar";

    // Color and Fontlists - First Value (key) is the Name to display, Second
    /**
     * The Constant GUI_COLORLIST.
     */
// Value (value) is the HTML-Definition
    public final static HashMap<String, String> GUI_COLORLIST = new HashMap<String, String>();

    static {
        GUI_COLORLIST.put("White", "#FFFFFF");
        GUI_COLORLIST.put("Black", "#000000");
        GUI_COLORLIST.put("Red", "red");
        GUI_COLORLIST.put("Green", "green");
        GUI_COLORLIST.put("Yellow", "yellow");
        GUI_COLORLIST.put("Blue", "blue");
    }

    /**
     * The Constant GUI_FONTLIST.
     */
    public final static HashMap<String, String> GUI_FONTLIST = new HashMap<String, String>();

    static {
        GUI_FONTLIST.put("Times New Roman", "Times New Roman");
        GUI_FONTLIST.put("Arial", "Arial");
        GUI_FONTLIST.put("Courier New", "Courier New");
        GUI_FONTLIST.put("Georgia", "Georgia");
        GUI_FONTLIST.put("Trebuchet", "Trebuchet");
        GUI_FONTLIST.put("Verdana", "Verdana");
    }

    /**
     * The Constant HTML_STYLE_CLOSE_SPAN.
     */
    // HTML Related (styles made by SPAN and DIV)
    private static final String HTML_STYLE_CLOSE_SPAN = "</span>";

    /**
     * The Constant HTML_STYLE_CLOSE_DIV.
     */
    private static final String HTML_STYLE_CLOSE_DIV = "</div>";

    /**
     * The Constant HTML_STYLE_OPEN_BOLD.
     */
    private static final String HTML_STYLE_OPEN_BOLD = "<span style=\"font-weight: bold;\">";

    /**
     * The Constant HTML_STYLE_OPEN_ITALIC.
     */
    private static final String HTML_STYLE_OPEN_ITALIC = "<span style=\"font-weight: italic;\">";

    /**
     * The Constant HTML_STYLE_OPEN_UNDERLINE.
     */
    private static final String HTML_STYLE_OPEN_UNDERLINE = "<span style=\"font-weight: underline;\">";

    /**
     * The Constant HTML_STYLE_OPEN_LINETHROUGH.
     */
    private static final String HTML_STYLE_OPEN_LINETHROUGH = "<span style=\"font-weight: line-through;\">";

    /**
     * The Constant HTML_STYLE_OPEN_ALIGNLEFT.
     */
    private static final String HTML_STYLE_OPEN_ALIGNLEFT = "<div style=\"text-align: left;\">";

    /**
     * The Constant HTML_STYLE_OPEN_ALIGNCENTER.
     */
    private static final String HTML_STYLE_OPEN_ALIGNCENTER = "<div style=\"text-align: center;\">";

    /**
     * The Constant HTML_STYLE_OPEN_ALIGNRIGHT.
     */
    private static final String HTML_STYLE_OPEN_ALIGNRIGHT = "<div style=\"text-align: right;\">";

    /**
     * The Constant HTML_STYLE_OPEN_INDENTRIGHT.
     */
    private static final String HTML_STYLE_OPEN_INDENTRIGHT = "<div style=\"margin-left: 40px;\">";

    /**
     * The Constant HTML_STYLE_OPEN_SUBSCRIPT.
     */
    // HTML Related (styles made by custom HTML-Tags)
    private static final String HTML_STYLE_OPEN_SUBSCRIPT = "<sub>";

    /**
     * The Constant HTML_STYLE_CLOSE_SUBSCRIPT.
     */
    private static final String HTML_STYLE_CLOSE_SUBSCRIPT = "</sub>";

    /**
     * The Constant HTML_STYLE_OPEN_SUPERSCRIPT.
     */
    private static final String HTML_STYLE_OPEN_SUPERSCRIPT = "<sup>";

    /**
     * The Constant HTML_STYLE_CLOSE_SUPERSCRIPT.
     */
    private static final String HTML_STYLE_CLOSE_SUPERSCRIPT = "</sup>";

    /**
     * The Constant HTML_STYLE_OPEN_ORDERLIST.
     */
    private static final String HTML_STYLE_OPEN_ORDERLIST = "<ol><li>";

    /**
     * The Constant HTML_STYLE_CLOSE_ORDERLIST.
     */
    private static final String HTML_STYLE_CLOSE_ORDERLIST = "</ol></li>";

    /**
     * The Constant HTML_STYLE_OPEN_UNORDERLIST.
     */
    private static final String HTML_STYLE_OPEN_UNORDERLIST = "<ul><li>";

    /**
     * The Constant HTML_STYLE_CLOSE_UNORDERLIST.
     */
    private static final String HTML_STYLE_CLOSE_UNORDERLIST = "</ul></li>";

    /**
     * The Constant HTML_STYLE_HLINE.
     */
    // HTML Related (styles without closing Tag)
    private static final String HTML_STYLE_HLINE = "<hr style=\"width: 100%; height: 2px;\">";

    /**
     * The Constant GUI_DIALOG_INSERTURL.
     */
    // GUI Related stuff
    private static final String GUI_DIALOG_INSERTURL = "Enter a link URL:";

    /**
     * The Constant GUI_DIALOG_IMAGEURL.
     */
    private static final String GUI_DIALOG_IMAGEURL = "Enter an image URL:";

    /**
     * The Constant GUI_LISTNAME_COLORS.
     */
    private static final String GUI_LISTNAME_COLORS = "Colors";

    /**
     * The Constant GUI_LISTNAME_FONTS.
     */
    private static final String GUI_LISTNAME_FONTS = "Fonts";

    /**
     * The Constant GUI_HOVERTEXT_SWITCHVIEW.
     */
    private static final String GUI_HOVERTEXT_SWITCHVIEW = "Switch View HTML/Source";

    /**
     * The Constant GUI_HOVERTEXT_REMOVEFORMAT.
     */
    private static final String GUI_HOVERTEXT_REMOVEFORMAT = "Remove Formatting";

    /**
     * The Constant GUI_HOVERTEXT_IMAGE.
     */
    private static final String GUI_HOVERTEXT_IMAGE = "Insert Image";

    /**
     * The Constant GUI_HOVERTEXT_HLINE.
     */
    private static final String GUI_HOVERTEXT_HLINE = "Insert Horizontal Line";

    /**
     * The Constant GUI_HOVERTEXT_BREAKLINK.
     */
    private static final String GUI_HOVERTEXT_BREAKLINK = "Break Link";

    /**
     * The Constant GUI_HOVERTEXT_LINK.
     */
    private static final String GUI_HOVERTEXT_LINK = "Generate Link";

    /**
     * The Constant GUI_HOVERTEXT_IDENTLEFT.
     */
    private static final String GUI_HOVERTEXT_IDENTLEFT = "Ident Left";

    /**
     * The Constant GUI_HOVERTEXT_IDENTRIGHT.
     */
    private static final String GUI_HOVERTEXT_IDENTRIGHT = "Ident Right";

    /**
     * The Constant GUI_HOVERTEXT_UNORDERLIST.
     */
    private static final String GUI_HOVERTEXT_UNORDERLIST = "Unordered List";

    /**
     * The Constant GUI_HOVERTEXT_ORDERLIST.
     */
    private static final String GUI_HOVERTEXT_ORDERLIST = "Ordered List";

    /**
     * The Constant GUI_HOVERTEXT_ALIGNRIGHT.
     */
    private static final String GUI_HOVERTEXT_ALIGNRIGHT = "Align Right";

    /**
     * The Constant GUI_HOVERTEXT_ALIGNCENTER.
     */
    private static final String GUI_HOVERTEXT_ALIGNCENTER = "Align Center";

    /**
     * The Constant GUI_HOVERTEXT_ALIGNLEFT.
     */
    private static final String GUI_HOVERTEXT_ALIGNLEFT = "Align Left";

    /**
     * The Constant GUI_HOVERTEXT_SUPERSCRIPT.
     */
    private static final String GUI_HOVERTEXT_SUPERSCRIPT = "Superscript";

    /**
     * The Constant GUI_HOVERTEXT_SUBSCRIPT.
     */
    private static final String GUI_HOVERTEXT_SUBSCRIPT = "Subscript";

    /**
     * The Constant GUI_HOVERTEXT_STROKE.
     */
    private static final String GUI_HOVERTEXT_STROKE = "Stroke";

    /**
     * The Constant GUI_HOVERTEXT_UNDERLINE.
     */
    private static final String GUI_HOVERTEXT_UNDERLINE = "Underline";

    /**
     * The Constant GUI_HOVERTEXT_ITALIC.
     */
    private static final String GUI_HOVERTEXT_ITALIC = "Italic";

    /**
     * The Constant GUI_HOVERTEXT_BOLD.
     */
    private static final String GUI_HOVERTEXT_BOLD = "Bold";

    /**
     * Private Variables *.
     */
    // The main (Vertical)-Panel and the two inner (Horizontal)-Panels
    private VerticalPanel outer;

    /**
     * The top panel.
     */
    private HorizontalPanel topPanel;

    /**
     * The bottom panel.
     */
    private HorizontalPanel bottomPanel;

    // The RichTextArea this Toolbar referes to and the Interfaces to access the
    /**
     * The style text.
     */
    // RichTextArea
    private RichTextArea styleText;

    /**
     * The style text formatter.
     */
    private Formatter styleTextFormatter;

    // We use an internal class of the ClickHandler and the KeyUpHandler to be
    /**
     * The ev handler.
     */
    // private to others with these events
    private EventHandler evHandler;

    /**
     * The bold.
     */
    // The Buttons of the Menubar
    private ToggleButton bold;

    /**
     * The italic.
     */
    private ToggleButton italic;

    /**
     * The underline.
     */
    private ToggleButton underline;

    /**
     * The stroke.
     */
    private ToggleButton stroke;

    /**
     * The subscript.
     */
    private ToggleButton subscript;

    /**
     * The superscript.
     */
    private ToggleButton superscript;

    /**
     * The alignleft.
     */
    private PushButton alignleft;

    /**
     * The alignmiddle.
     */
    private PushButton alignmiddle;

    /**
     * The alignright.
     */
    private PushButton alignright;

    /**
     * The orderlist.
     */
    private PushButton orderlist;

    /**
     * The unorderlist.
     */
    private PushButton unorderlist;

    /**
     * The indentleft.
     */
    private PushButton indentleft;

    /**
     * The indentright.
     */
    private PushButton indentright;

    /**
     * The generatelink.
     */
    private PushButton generatelink;

    /**
     * The breaklink.
     */
    private PushButton breaklink;

    /**
     * The insertline.
     */
    private PushButton insertline;

    /**
     * The insertimage.
     */
    private PushButton insertimage;

    /**
     * The removeformatting.
     */
    private PushButton removeformatting;

    /**
     * The texthtml.
     */
    private ToggleButton texthtml;

    /**
     * The fontlist.
     */
    private ListBox fontlist;

    /**
     * The colorlist.
     */
    private ColorLabel colorlist;

    /**
     * Instantiates a new rich text toolbar.
     */
    @UiConstructor
    public RichTextToolbar() {

        // HTTP_STATIC_ICONS_GIF=GWT.getModuleBaseURL()+
        // "../images/toolbars.gif";
        // Initialize the main-panel
        outer = new VerticalPanel();

        // Initialize the two inner panels
        topPanel = new HorizontalPanel();
        bottomPanel = new HorizontalPanel();
        topPanel.setStyleName(CSS_ROOT_NAME);
        bottomPanel.setStyleName(CSS_ROOT_NAME);

        // Save the reference to the RichText area we refer to and get the
        // interfaces to the stylings

        // Set some graphical options, so this toolbar looks how we like it.
        topPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
        bottomPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);

        // Add the two inner panels to the main panel
        outer.add(topPanel);
        outer.add(bottomPanel);

        // Some graphical stuff to the main panel and the initialisation of the
        // new widget
        outer.setWidth("100%");
        outer.setStyleName(CSS_ROOT_NAME);
        initWidget(outer);

        //
        evHandler = new EventHandler();

        // Now lets fill the new toolbar with life
        buildTools();

        initPopupPanel();
    }

    /**
     * Sets the rich text area.
     *
     * @param area the new rich text area
     */
    public void setRichTextArea(RichTextArea area) {
        styleText = area;
        styleTextFormatter = styleText.getFormatter();
        styleText.addKeyUpHandler(evHandler);
        styleText.addClickHandler(evHandler);

    }

    /**
     * The m image dlg handler.
     */
    private MessageHandler m_image_dlg_handler = new MessageHandler() {

        public void onMessage(Object sender, Integer message, Object value) {

            if (MessageEvent.CANCEL.equals(message)) {
                m_popup.hide(true);
            } else if (MessageEvent.OK.equals(message)) {
                String url = (String) value;
                if (!url.equals("")) {
                    if (isHTMLMode()) {
                        changeHtmlStyle(url, "");
                    } else {
                        styleTextFormatter.insertHTML(url);
                    }
                }
                m_popup.hide(true);

            }
        }
    };

    /**
     * Click Handler of the Toolbar *.
     */
    private class EventHandler implements ClickHandler, KeyUpHandler,
            ChangeHandler {

        /* (non-Javadoc)
         * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
         */
        public void onClick(ClickEvent event) {
            if (event.getSource().equals(bold)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_OPEN_BOLD, HTML_STYLE_CLOSE_SPAN);
                } else {
                    styleTextFormatter.toggleBold();
                }
            } else if (event.getSource().equals(italic)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_OPEN_ITALIC,
                            HTML_STYLE_CLOSE_SPAN);
                } else {
                    styleTextFormatter.toggleItalic();
                }
            } else if (event.getSource().equals(underline)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_OPEN_UNDERLINE,
                            HTML_STYLE_CLOSE_SPAN);
                } else {
                    styleTextFormatter.toggleUnderline();
                }
            } else if (event.getSource().equals(stroke)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_OPEN_LINETHROUGH,
                            HTML_STYLE_CLOSE_SPAN);
                } else {
                    styleTextFormatter.toggleStrikethrough();
                }
            } else if (event.getSource().equals(subscript)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_OPEN_SUBSCRIPT,
                            HTML_STYLE_CLOSE_SUBSCRIPT);
                } else {
                    styleTextFormatter.toggleSubscript();
                }
            } else if (event.getSource().equals(superscript)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_OPEN_SUPERSCRIPT,
                            HTML_STYLE_CLOSE_SUPERSCRIPT);
                } else {
                    styleTextFormatter.toggleSuperscript();
                }
            } else if (event.getSource().equals(alignleft)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_OPEN_ALIGNLEFT,
                            HTML_STYLE_CLOSE_DIV);
                } else {
                    styleTextFormatter
                            .setJustification(RichTextArea.Justification.LEFT);
                }
            } else if (event.getSource().equals(alignmiddle)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_OPEN_ALIGNCENTER,
                            HTML_STYLE_CLOSE_DIV);
                } else {
                    styleTextFormatter
                            .setJustification(RichTextArea.Justification.CENTER);
                }
            } else if (event.getSource().equals(alignright)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_OPEN_ALIGNRIGHT,
                            HTML_STYLE_CLOSE_DIV);
                } else {
                    styleTextFormatter
                            .setJustification(RichTextArea.Justification.RIGHT);
                }
            } else if (event.getSource().equals(orderlist)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_OPEN_ORDERLIST,
                            HTML_STYLE_CLOSE_ORDERLIST);
                } else {
                    styleTextFormatter.insertOrderedList();
                }
            } else if (event.getSource().equals(unorderlist)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_OPEN_UNORDERLIST,
                            HTML_STYLE_CLOSE_UNORDERLIST);
                } else {
                    styleTextFormatter.insertUnorderedList();
                }
            } else if (event.getSource().equals(indentright)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_OPEN_INDENTRIGHT,
                            HTML_STYLE_CLOSE_DIV);
                } else {
                    styleTextFormatter.rightIndent();
                }
            } else if (event.getSource().equals(indentleft)) {
                if (isHTMLMode()) {
                    // TODO nothing can be done here at the moment
                } else {
                    styleTextFormatter.leftIndent();
                }
            } else if (event.getSource().equals(generatelink)) {
                String url = Window.prompt(GUI_DIALOG_INSERTURL, "http://");
                if (url != null) {
                    if (isHTMLMode()) {
                        changeHtmlStyle("<a href=\"" + url + "\">", "</a>");
                    } else {
                        styleTextFormatter.createLink(url);
                    }
                }
            } else if (event.getSource().equals(breaklink)) {
                if (isHTMLMode()) {
                    // TODO nothing can be done here at the moment
                } else {
                    styleTextFormatter.removeLink();
                }
            } else if (event.getSource().equals(insertimage)) {
                Widget w = (Widget) event.getSource();

                m_popup = new PopupPanel(true);

                if (m_b_show_popup == false) {
                    if (m_dlg_image == null) {
                        m_dlg_image = new ImageUploadPanel();
                        m_dlg_image.addMessageHandler(m_image_dlg_handler);
                    }
                    m_popup.add(m_dlg_image);
                    m_popup.showRelativeTo(w);
                } else {
                    m_popup.hide(true);
                }

            } else if (event.getSource().equals(insertline)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_HLINE, "");
                } else {
                    styleTextFormatter.insertHorizontalRule();
                }
            } else if (event.getSource().equals(removeformatting)) {
                if (isHTMLMode()) {
                    // TODO nothing can be done here at the moment
                } else {
                    styleTextFormatter.removeFormat();
                }
            } else if (event.getSource().equals(texthtml)) {
                if (texthtml.isDown()) {
                    styleText.setText(styleText.getHTML());
                } else {
                    styleText.setHTML(styleText.getText());
                }
            } else if (event.getSource().equals(styleText)) {
                // Change invoked by the richtextArea
            }
            updateStatus();
        }

        /* (non-Javadoc)
         * @see com.google.gwt.event.dom.client.KeyUpHandler#onKeyUp(com.google.gwt.event.dom.client.KeyUpEvent)
         */
        public void onKeyUp(KeyUpEvent event) {
            updateStatus();
        }

        /* (non-Javadoc)
         * @see com.google.gwt.event.dom.client.ChangeHandler#onChange(com.google.gwt.event.dom.client.ChangeEvent)
         */
        public void onChange(ChangeEvent event) {
            if (event.getSource().equals(fontlist)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(
                            "<span style=\"font-family: "
                                    + fontlist.getValue(fontlist
                                    .getSelectedIndex()) + ";\">",
                            HTML_STYLE_CLOSE_SPAN);
                } else {
                    styleTextFormatter.setFontName(fontlist.getValue(fontlist
                            .getSelectedIndex()));
                }
            }
        }

    }

    /**
     * Native JavaScript that returns the selected text and position of the
     * start.
     *
     * @param elem the elem
     * @return the selection
     */
    public static native JsArrayString getSelection(Element elem) /*-{
		var txt = "";
		var pos = 0;
		var range;
		var parentElement;
		var container;

		if (elem.contentWindow.getSelection) {
			txt = elem.contentWindow.getSelection();
			pos = elem.contentWindow.getSelection().getRangeAt(0).startOffset;
		} else if (elem.contentWindow.document.getSelection) {
			txt = elem.contentWindow.document.getSelection();
			pos = elem.contentWindow.document.getSelection().getRangeAt(0).startOffset;
		} else if (elem.contentWindow.document.selection) {
			range = elem.contentWindow.document.selection.createRange();
			txt = range.text;
			parentElement = range.parentElement();
			container = range.duplicate();
			container.moveToElementText(parentElement);
			container.setEndPoint('EndToEnd', range);
			pos = container.text.length - range.text.length;
		}
		return [ "" + txt, "" + pos ];
	}-*/;

    /**
     * Method called to toggle the style in HTML-Mode *.
     *
     * @param startTag the start tag
     * @param stopTag  the stop tag
     */
    private void changeHtmlStyle(String startTag, String stopTag) {
        JsArrayString tx = getSelection(styleText.getElement());
        String txbuffer = styleText.getText();
        Integer startpos = Integer.parseInt(tx.get(1));
        String selectedText = tx.get(0);
        styleText.setText(txbuffer.substring(0, startpos) + startTag
                + selectedText + stopTag
                + txbuffer.substring(startpos + selectedText.length()));
    }

    /**
     * Private method with a more understandable name to get if HTML mode is on
     * or not.
     *
     * @return the boolean
     */
    private Boolean isHTMLMode() {
        return texthtml.isDown();
    }

    /**
     * Private method to set the toggle buttons and disable/enable buttons which
     * do not work in html-mode.
     */
    private void updateStatus() {
        if (styleTextFormatter != null) {
            bold.setDown(styleTextFormatter.isBold());
            italic.setDown(styleTextFormatter.isItalic());
            underline.setDown(styleTextFormatter.isUnderlined());
            subscript.setDown(styleTextFormatter.isSubscript());
            superscript.setDown(styleTextFormatter.isSuperscript());
            stroke.setDown(styleTextFormatter.isStrikethrough());
        }

        if (isHTMLMode()) {
            removeformatting.setEnabled(false);
            indentleft.setEnabled(false);
            breaklink.setEnabled(false);
        } else {
            removeformatting.setEnabled(true);
            indentleft.setEnabled(true);
            breaklink.setEnabled(true);
        }
    }

    /**
     * Initialize the options on the toolbar *.
     */
    private void buildTools() {
        // Init the TOP Panel forst
        topPanel.add(bold = createToggleButton(HTTP_STATIC_ICONS_GIF, 0, 0, 20,
                20, GUI_HOVERTEXT_BOLD));
        topPanel.add(italic = createToggleButton(HTTP_STATIC_ICONS_GIF, 0, 60,
                20, 20, GUI_HOVERTEXT_ITALIC));
        topPanel.add(underline = createToggleButton(HTTP_STATIC_ICONS_GIF, 0,
                140, 20, 20, GUI_HOVERTEXT_UNDERLINE));
        topPanel.add(stroke = createToggleButton(HTTP_STATIC_ICONS_GIF, 0, 120,
                20, 20, GUI_HOVERTEXT_STROKE));
        topPanel.add(new HTML("&nbsp;"));
        topPanel.add(subscript = createToggleButton(HTTP_STATIC_ICONS_GIF, 0,
                600, 20, 20, GUI_HOVERTEXT_SUBSCRIPT));
        topPanel.add(superscript = createToggleButton(HTTP_STATIC_ICONS_GIF, 0,
                620, 20, 20, GUI_HOVERTEXT_SUPERSCRIPT));
        topPanel.add(new HTML("&nbsp;"));
        topPanel.add(alignleft = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
                460, 20, 20, GUI_HOVERTEXT_ALIGNLEFT));
        topPanel.add(alignmiddle = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
                420, 20, 20, GUI_HOVERTEXT_ALIGNCENTER));
        topPanel.add(alignright = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
                480, 20, 20, GUI_HOVERTEXT_ALIGNRIGHT));
        topPanel.add(new HTML("&nbsp;"));
        topPanel.add(orderlist = createPushButton(HTTP_STATIC_ICONS_GIF, 0, 80,
                20, 20, GUI_HOVERTEXT_ORDERLIST));
        topPanel.add(unorderlist = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
                20, 20, 20, GUI_HOVERTEXT_UNORDERLIST));
        topPanel.add(indentright = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
                400, 20, 20, GUI_HOVERTEXT_IDENTRIGHT));
        topPanel.add(indentleft = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
                540, 20, 20, GUI_HOVERTEXT_IDENTLEFT));
        topPanel.add(new HTML("&nbsp;"));
        topPanel.add(generatelink = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
                500, 20, 20, GUI_HOVERTEXT_LINK));
        topPanel.add(breaklink = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
                640, 20, 20, GUI_HOVERTEXT_BREAKLINK));
        topPanel.add(new HTML("&nbsp;"));
        topPanel.add(insertline = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
                360, 20, 20, GUI_HOVERTEXT_HLINE));
        topPanel.add(insertimage = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
                380, 20, 20, GUI_HOVERTEXT_IMAGE));
        topPanel.add(new HTML("&nbsp;"));
        topPanel.add(removeformatting = createPushButton(HTTP_STATIC_ICONS_GIF,
                0, 580, 20, 20, GUI_HOVERTEXT_REMOVEFORMAT));
        topPanel.add(new HTML("&nbsp;"));
        topPanel.add(texthtml = createToggleButton(HTTP_STATIC_ICONS_GIF, 0,
                260, 20, 20, GUI_HOVERTEXT_SWITCHVIEW));

        // Init the BOTTOM Panel
        topPanel.add(fontlist = createFontList());
        topPanel.add(new HTML("&nbsp;"));
        topPanel.add(colorlist = createColorList());
    }

    /**
     * Method to create a Toggle button for the toolbar *.
     *
     * @param url    the url
     * @param top    the top
     * @param left   the left
     * @param width  the width
     * @param height the height
     * @param tip    the tip
     * @return the toggle button
     */
    private ToggleButton createToggleButton(String url, Integer top,
                                            Integer left, Integer width, Integer height, String tip) {
        Image extract = new Image(url, left, top, width, height);
        ToggleButton tb = new ToggleButton(extract);
        tb.setStyleName("ksyzt-ToogleButton");
        tb.setHeight(height + "px");
        tb.setWidth(width + "px");
        tb.addClickHandler(evHandler);
        if (tip != null) {
            tb.setTitle(tip);
        }
        return tb;
    }

    /**
     * Method to create a Push button for the toolbar *.
     *
     * @param url    the url
     * @param top    the top
     * @param left   the left
     * @param width  the width
     * @param height the height
     * @param tip    the tip
     * @return the push button
     */
    private PushButton createPushButton(String url, Integer top, Integer left,
                                        Integer width, Integer height, String tip) {
        Image extract = new Image(url, left, top, width, height);

        PushButton tb = new PushButton(extract);
        tb.setStyleName("ksyzt-PushButton");
        tb.setHeight(height + "px");
        tb.setWidth(width + "px");
        tb.addClickHandler(evHandler);
        if (tip != null) {
            tb.setTitle(tip);
        }
        return tb;
    }

    /**
     * Method to create the fontlist for the toolbar *.
     *
     * @return the list box
     */
    private ListBox createFontList() {
        ListBox mylistBox = new ListBox();
        mylistBox.addChangeHandler(evHandler);
        mylistBox.setVisibleItemCount(1);

        mylistBox.addItem(GUI_LISTNAME_FONTS);
        for (String name : GUI_FONTLIST.keySet()) {
            mylistBox.addItem(name, GUI_FONTLIST.get(name));
        }

        return mylistBox;
    }

    /**
     * Method to create the colorlist for the toolbar *.
     *
     * @return the color label
     */
    private ColorLabel createColorList() {

        colorlist = new ColorLabel();
        Style style = colorlist.getElement().getStyle();

        style.setDisplay(Display.BLOCK);
        style.setPropertyPx("lineHeight", 14);
        style.setCursor(Cursor.POINTER);
        colorlist.setWidth("60px");

        colorlist.addMessageHandler(m_color_handler);
        return colorlist;
    }

    /**
     * The m color handler.
     */
    private MessageHandler m_color_handler = new MessageHandler() {

        @Override
        public void onMessage(Object sender, Integer message, Object value) {
            if (message == MessageEvent.COLOR) {
                String c = (String) value;

                if (isHTMLMode()) {
                    changeHtmlStyle("<span style=\"color: " + c + ";\">",
                            HTML_STYLE_CLOSE_SPAN);
                } else {
                    styleTextFormatter.setForeColor(c);
                }
            }
        }
    };
}

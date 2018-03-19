package cn.mapway.ui.client.widget.color;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.TextBox;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;


/**
 * The Class ColorLabel.
 */
public class ColorBox extends TextBox implements HasMessageHandlers {

  /** The popup. */
  private ColorPopup popup;

  /** The m color event. */
  private MessageHandler m_color_event = new MessageHandler() {

    @Override
    public void onMessage(Object sender, Integer message, Object value) {
      if (message == MessageEvent.COLORS) {
        String[] colors = (String[]) value;

        setValue(colors[0]);
        MessageEvent ev = new MessageEvent(MessageEvent.COLOR, colors[0]);
        fireEvent(ev);
      }

    }
  };

  @Override
  public String getValue() {
    Style style = getElement().getStyle();
    return style.getBackgroundColor();
  }

    /**
     * Instantiates a new color label.
     */
    public ColorBox() {
    this.popup = new ColorPopup();
    setValue("#ffffff");

    popup.addMessageHandler(m_color_event);
    addFocusHandler(new FocusHandler() {

      @Override
      public void onFocus(FocusEvent event) {
        popup.setHex(getText());
        popup.setAutoHideEnabled(true);
        popup.setPopupPosition(getAbsoluteLeft() + 10, getAbsoluteTop() + 10);
        popup.showRelativeTo(ColorBox.this);
      }
    });
    addKeyDownHandler(new KeyDownHandler() {

      @Override
      public void onKeyDown(KeyDownEvent event) {
        String color = getValue();
        if (color.length() > 1) {
          MessageEvent ev = new MessageEvent(MessageEvent.COLOR, color);
          fireEvent(ev);
        }
      }
    });

  }

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

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.ValueBoxBase#setValue(java.lang.Object)
   */
  @Override
  public void setValue(String value) {
    Style style = getElement().getStyle();
    style.setBackgroundColor(value);
    Color color = new Color(value);
    style.setColor(color.getLightness() < 0.5 ? "#ffffff" : "#000000");
    super.setValue(value);
  }
}

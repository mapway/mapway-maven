package cn.mapway.ui.client.widget.common;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DialogBox.Caption;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;


/**
 * The Class CloseCaption.
 */
public class CloseCaption extends HorizontalPanel implements Caption {

    /**
     * The title.
     */
    Label title;

    /**
     * The btn close.
     */
    InfoButton btnClose;

  /** The close handler. */
  private ClickHandler closeHandler = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {
      DialogBox db = (DialogBox) CloseCaption.this.getParent();
      db.hide();
    }
  };

    /**
     * Instantiates a new close caption.
     */
    public CloseCaption() {
    title = new Label();
    btnClose = new InfoButton();
    btnClose.setText("关闭");
    this.setWidth("100%");
    this.add(title);
    this.add(btnClose);
    btnClose.addClickHandler(closeHandler);
    setStyleName("Caption");
    title.setStyleName("caption-title");
    this.setCellHorizontalAlignment(btnClose, HasHorizontalAlignment.ALIGN_RIGHT);
  }

  /*
   * (non-Javadoc)
   * @see
   * com.google.gwt.event.dom.client.HasMouseDownHandlers#addMouseDownHandler(com.google.gwt.event.
   * dom.client.MouseDownHandler)
   */
  @Override
  public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {

    return null;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.google.gwt.event.dom.client.HasMouseUpHandlers#addMouseUpHandler(com.google.gwt.event.dom.
   * client.MouseUpHandler)
   */
  @Override
  public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.google.gwt.event.dom.client.HasMouseOutHandlers#addMouseOutHandler(com.google.gwt.event.dom
   * .client.MouseOutHandler)
   */
  @Override
  public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.google.gwt.event.dom.client.HasMouseOverHandlers#addMouseOverHandler(com.google.gwt.event.
   * dom.client.MouseOverHandler)
   */
  @Override
  public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.google.gwt.event.dom.client.HasMouseMoveHandlers#addMouseMoveHandler(com.google.gwt.event.
   * dom.client.MouseMoveHandler)
   */
  @Override
  public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see
   * com.google.gwt.event.dom.client.HasMouseWheelHandlers#addMouseWheelHandler(com.google.gwt.event
   * .dom.client.MouseWheelHandler)
   */
  @Override
  public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.google.gwt.user.client.ui.HasHTML#getHTML()
   */
  @Override
  public String getHTML() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.google.gwt.user.client.ui.HasHTML#setHTML(java.lang.String)
   */
  @Override
  public void setHTML(String html) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * @see
   * com.google.gwt.safehtml.client.HasSafeHtml#setHTML(com.google.gwt.safehtml.shared.SafeHtml)
   */
  @Override
  public void setHTML(SafeHtml html) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * @see com.google.gwt.user.client.ui.HasText#getText()
   */
  @Override
  public String getText() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.google.gwt.user.client.ui.HasText#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    title.setText(text);

  }

}

package cn.mapway.document.ui.client.component;

import cn.mapway.document.ui.client.resource.SysResource;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.DialogBox.Caption;


/**
 * The Class CloseCaption.
 */
public class CloseCaption extends HorizontalPanel implements Caption {

    private final MouseOverHandler mouseOverHandler = new MouseOverHandler() {
        @Override
        public void onMouseOver(MouseOverEvent mouseOverEvent) {
            btnClose.setUrl(SysResource.INSTANCE.closeRed().getSafeUri());
        }
    };
    private final MouseOutHandler mouseOutHandler = new MouseOutHandler() {
        @Override
        public void onMouseOut(MouseOutEvent mouseOutEvent) {
            btnClose.setUrl(SysResource.INSTANCE.closeGray().getSafeUri());
        }
    };

    Label summary;
    /**
     * The title.
     */
    Label title;

    /**
     * The btn close.
     */
    Image btnClose;

    /**
     * The close handler.
     */
    private ClickHandler closeHandler = new ClickHandler() {
        @Override
        public void onClick(ClickEvent clickEvent) {
            DialogBox db = (DialogBox) CloseCaption.this.getParent();
            db.hide();
        }
    };

    /**
     * Instantiates a new close caption.
     */
    public CloseCaption() {
        title = new Label();
        summary = new Label();
        summary.setStyleName(SysResource.INSTANCE.getCss().dlgSummary());
        btnClose = new Image();
        btnClose.setPixelSize(26, 26);
        btnClose.setUrl(SysResource.INSTANCE.closeGray().getSafeUri());
        btnClose.addMouseOverHandler(mouseOverHandler);
        btnClose.addMouseOutHandler(mouseOutHandler);
        this.setWidth("100%");
        VerticalPanel vp = new VerticalPanel();
        vp.add(title);
        vp.add(summary);
        this.add(vp);
        this.add(btnClose);
        this.setStyleName("Caption");
        btnClose.addClickHandler(closeHandler);
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
        return this.title.getText();
    }

    /*
     * (non-Javadoc)
     * @see com.google.gwt.user.client.ui.HasText#setText(java.lang.String)
     */
    @Override
    public void setText(String text) {
        title.setText(text);

    }

    public void setSummary(String text) {
        summary.setText(text);
    }
}

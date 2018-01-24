package cn.mapway.ui.client.widget.common;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

/**
 * 图标和文字组合控件
 */
public class IconItem extends HorizontalPanel implements HasClickHandlers {

    public Image icon;
    public Label lbText;

    /**
     * Creates an empty text box.
     */
    public IconItem() {
        icon = new Image();
        lbText = new Label();
        this.add(icon);
        this.add(lbText);
        setSpacing(2);
        setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        this.icon.setVisible(false);
    }

    public void setIcon(ImageResource res, int width, int height) {
        if (res == null) {
            icon.setVisible(false);
        } else {
            icon.setVisible(true);
            icon.setUrl(res.getSafeUri());
            icon.setPixelSize(width, height);
        }
    }

    public void setIcon(SafeUri url, int width, int height) {
        if (url == null) {
            icon.setVisible(false);

        } else {

            icon.setVisible(true);
            icon.setUrl(url);
            icon.setPixelSize(width, height);
        }
    }

    public void setIcon(String url, int width, int height) {
        if (url == null || url.length() == 0) {
            icon.setVisible(false);
        } else {
            icon.setVisible(true);
            icon.setUrl(url);
            icon.setPixelSize(width, height);
        }
    }

    public void setText(String text) {
        lbText.setText(text);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    Object data;

    /**
     * Adds a {@link ClickEvent} handler.
     *
     * @param handler the click handler
     * @return {@link HandlerRegistration} used to remove this handler
     */
    public HandlerRegistration addClickHandler(ClickHandler handler) {

        return addDomHandler(handler, ClickEvent.getType());
    }
}

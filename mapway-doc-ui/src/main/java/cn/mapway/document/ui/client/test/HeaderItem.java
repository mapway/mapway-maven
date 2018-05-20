package cn.mapway.document.ui.client.test;

import cn.mapway.document.ui.client.resource.SysResource;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class HeaderItem extends Composite implements HasCloseHandlers<String> {
    @Override
    public HandlerRegistration addCloseHandler(CloseHandler<String> closeHandler) {
        return addHandler(closeHandler, CloseEvent.getType());
    }

    interface HeaderItemUiBinder extends UiBinder<HTMLPanel, HeaderItem> {
    }

    private static HeaderItemUiBinder ourUiBinder = GWT.create(HeaderItemUiBinder.class);
    @UiField
    Label lbKey;
    @UiField
    Label lbValue;
    @UiField
    Image imgDelete;

    public HeaderItem() {
        initWidget(ourUiBinder.createAndBindUi(this));
        imgDelete.setResource(SysResource.INSTANCE.delete());
    }

    @UiHandler("imgDelete")
    public void imgDeleteClick(ClickEvent event) {
        CloseEvent.fire(this, lbKey.getText());
    }

    public void setData(String key, String value) {
        lbKey.setText(key);
        lbValue.setText(value);
    }
}
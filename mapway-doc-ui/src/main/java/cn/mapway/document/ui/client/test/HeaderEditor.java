package cn.mapway.document.ui.client.test;

import cn.mapway.document.ui.client.main.storage.LocalStorage;
import cn.mapway.document.ui.client.resource.SysResource;
import cn.mapway.document.ui.client.rpc.RpcContext;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;

import static cn.mapway.document.ui.client.test.TestPanel.GWT_USER_HEADER;

public class HeaderEditor extends Composite implements HasCloseHandlers<KeyValueObjs> {
    @Override
    public HandlerRegistration addCloseHandler(CloseHandler<KeyValueObjs> closeHandler) {
        return addHandler(closeHandler, CloseEvent.getType());
    }

    interface HeaderEditorUiBinder extends UiBinder<HTMLPanel, HeaderEditor> {
    }

    private static HeaderEditorUiBinder ourUiBinder = GWT.create(HeaderEditorUiBinder.class);
    @UiField
    TextBox txtVALUE;
    @UiField
    TextBox txtKEY;
    @UiField
    Button btnAdd;
    @UiField
    Button btnClose;

    public HeaderEditor() {
        initWidget(ourUiBinder.createAndBindUi(this));
        String css = SysResource.INSTANCE.getCss().btn();
        btnAdd.setStyleName(css);
        btnClose.setStyleName(css);
    }

    @UiHandler("btnAdd")
    public void btnAddClick(ClickEvent event) {


        String key = txtKEY.getValue();
        String value = txtVALUE.getValue();
        if (key != null && key.length() > 0 && value != null && value.length() > 0) {

            String str = LocalStorage.val(GWT_USER_HEADER);
            if (str == null || str.length() == 0) {
                str = "[]";
            }
            KeyValueObjs kvs = KeyValueObjs.parse(str);
            kvs.append(key, value);

            LocalStorage.save(GWT_USER_HEADER, kvs.toJSON());

            CloseEvent.fire(this, kvs);
        }


    }

    @UiHandler("btnClose")
    public void btnCloseClick(ClickEvent event) {
        CloseEvent.fire(this, null);
    }
}
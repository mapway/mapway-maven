package cn.mapway.document.ui.client.test;

import cn.mapway.document.ui.client.component.CloseCaption;
import cn.mapway.document.ui.client.resource.SysResource;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;


public class TextEditor extends DialogBox implements HasSelectionHandlers<String> {
    private final KeyDownHandler keyDownEventHandler = new KeyDownHandler() {
        @Override
        public void onKeyDown(KeyDownEvent keyDownEvent) {
            if (keyDownEvent.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                btnSubmitClick(null);
            }
        }
    };

    @Override
    public HandlerRegistration addSelectionHandler(SelectionHandler<String> selectionHandler) {
        return addHandler(selectionHandler, SelectionEvent.getType());
    }

    interface TextEditorUiBinder extends UiBinder<DockLayoutPanel, TextEditor> {
    }

    private static TextEditorUiBinder ourUiBinder = GWT.create(TextEditorUiBinder.class);

    public TextEditor() {
        super(new CloseCaption());
        setWidget(ourUiBinder.createAndBindUi(this));
        setGlassEnabled(true);
        setText("");
        txtContent.addKeyDownHandler(keyDownEventHandler);
        btnSubmit.setStyleName(SysResource.INSTANCE.getCss().btn());
    }


    @UiField
    Label lbTEXT;

    @UiField
    TextBox txtContent;
    @UiField
    Button btnSubmit;

    private Object tag;

    public Object getTag() {
        return tag;
    }

    public TextEditor setTag(Object tag1) {
        tag = tag1;
        return this;
    }

    /**
     * 编辑内容
     *
     * @param title     标题
     * @param initValue 初始值
     */
    public TextEditor edit(String title, String hint, String initValue) {
        this.setText(title);
        lbTEXT.setText(hint);
        txtContent.setValue(initValue);
        return this;
    }

    @UiHandler("btnSubmit")
    public void btnSubmitClick(ClickEvent event) {
        SelectionEvent.fire(this, txtContent.getValue());
    }


}
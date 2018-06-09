package cn.mapway.ui.client.widget.common;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * The type Confirm dialog.
 */
public class ConfirmDialog extends DialogBoxEx {

    private static ConfirmDialogUiBinder uiBinder = GWT.create(ConfirmDialogUiBinder.class);

    /**
     * The interface Confirm dialog ui binder.
     */
    interface ConfirmDialogUiBinder extends UiBinder<Widget, ConfirmDialog> {
    }

    /**
     * Instantiates a new Confirm dialog.
     */
    public ConfirmDialog() {
        setWidget(uiBinder.createAndBindUi(this));
        getElement().getStyle().setZIndex(8001);
    }

    /**
     * The Html.
     */
    @UiField
    HTML html;
    private Callback<Void, Void> mCallback = null;

    /**
     * Sets callback.
     *
     * @param callback the callback
     */
    public void setCallback(Callback<Void, Void> callback) {
        mCallback = callback;
    }

    /**
     * On cancel.
     *
     * @param ev the ev
     */
    @UiHandler("btnCancel")
    void onCancel(ClickEvent ev) {
        this.hide();
        if (mCallback != null) {
            mCallback.onFailure(null);
        }
    }

    /**
     * On ok.
     *
     * @param ev the ev
     */
    @UiHandler("btnOK")
    void onOK(ClickEvent ev) {
        this.hide();
        if (mCallback != null) {
            mCallback.onSuccess(null);
        }
    }

    /**
     * 设置显示的消息
     *
     * @param title   the title
     * @param content the content
     */
    public void setInfo(String title, String content) {
        this.setText(title);
        html.setHTML(content);
    }
}

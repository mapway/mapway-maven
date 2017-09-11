package cn.mapway.ui.client.widget.common;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ConfirmDialog extends DialogBoxEx {

  private static ConfirmDialogUiBinder uiBinder = GWT.create(ConfirmDialogUiBinder.class);

  interface ConfirmDialogUiBinder extends UiBinder<Widget, ConfirmDialog> {
  }

  public ConfirmDialog() {
    setWidget(uiBinder.createAndBindUi(this));
    getElement().getStyle().setZIndex(8001);
  }

  @UiField
  HTML html;
  private Callback<Void, Void> mCallback = null;

  public void setCallback(Callback<Void, Void> callback) {
    mCallback = callback;
  }

  @UiHandler("btnCancel")
  void onCancel(ClickEvent ev) {
    this.hide();
    if (mCallback != null) {
      mCallback.onFailure(null);
    }
  }

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
   * @param title
   * @param content
   */
  public void setInfo(String title, String content) {
    this.setText(title);
    html.setHTML(content);
  }
}

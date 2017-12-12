package cn.ennwifi.webframe.ui.client.common;

import cn.mapway.ui.client.widget.common.PrimaryButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;

/**
 *  数据库行操作
 * @author zhangjianshe
 *
 */
public class RowOperator extends MessageComposite {

  private static RowOperatorUiBinder uiBinder = GWT.create(RowOperatorUiBinder.class);

  interface RowOperatorUiBinder extends UiBinder<Widget, RowOperator> {
  }

  public RowOperator() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  Object mData;

  public void setData(Object obj) {
    mData = obj;
  }

  public Object getData() {
    return mData;
  }

  @UiField
  PrimaryButton btnEditor;

  @UiField
  PrimaryButton btnDelete;

  @UiHandler("btnEditor")
  void onEditor(ClickEvent e) {
    MessageEvent ev = new MessageEvent(MessageEvent.EDIT, mData);
    fireEvent(ev);
  }

  @UiHandler("btnDelete")
  void onDelete(ClickEvent e) {
    MessageEvent ev = new MessageEvent(MessageEvent.DELETE, mData);
    fireEvent(ev);
  }
}

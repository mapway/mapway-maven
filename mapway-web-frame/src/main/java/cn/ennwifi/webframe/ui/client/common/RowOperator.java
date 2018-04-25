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
 * 数据库行操作
 *
 * @author zhangjianshe
 */
public class RowOperator extends MessageComposite {

  private static RowOperatorUiBinder uiBinder = GWT.create(RowOperatorUiBinder.class);

    /**
     * The interface Row operator ui binder.
     */
    interface RowOperatorUiBinder extends UiBinder<Widget, RowOperator> {
  }

    /**
     * Instantiates a new Row operator.
     */
    public RowOperator() {
    initWidget(uiBinder.createAndBindUi(this));
  }

    /**
     * The M data.
     */
    Object mData;

    /**
     * Sets data.
     *
     * @param obj the obj
     */
    public void setData(Object obj) {
    mData = obj;
  }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData() {
    return mData;
  }

    /**
     * The Btn editor.
     */
    @UiField
  PrimaryButton btnEditor;

    /**
     * The Btn delete.
     */
    @UiField
  PrimaryButton btnDelete;

    /**
     * On editor.
     *
     * @param e the e
     */
    @UiHandler("btnEditor")
  void onEditor(ClickEvent e) {
    MessageEvent ev = new MessageEvent(MessageEvent.EDIT, mData);
    fireEvent(ev);
  }

    /**
     * On delete.
     *
     * @param e the e
     */
    @UiHandler("btnDelete")
  void onDelete(ClickEvent e) {
    MessageEvent ev = new MessageEvent(MessageEvent.DELETE, mData);
    fireEvent(ev);
  }
}

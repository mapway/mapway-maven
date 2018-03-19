package cn.ennwifi.webframe.ui.client.common;

import java.util.List;

import cn.ennwifi.webframe.ui.client.data.MetaDataProvider;
import cn.ennwifi.webframe.ui.shared.repository.S_METAObj;
import cn.mapway.ui.client.mvc.Observable;
import cn.mapway.ui.client.mvc.Observer;
import cn.mapway.ui.client.widget.common.ListBoxEx;

import com.google.gwt.event.shared.HandlerRegistration;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * 元数据选择框
 *
 * @author zhangjianshe
 */
public class MetaBox extends ListBoxEx implements Observer, HasMessageHandlers {

    /**
     * Instantiates a new Meta box.
     */
    public MetaBox() {
    MetaDataProvider.get().addObserver(this);
  }

  @Override
  public HandlerRegistration addMessageHandler(MessageHandler handler) {
    return addHandler(handler, MessageEvent.TYPE);
  }

  private String mCatalog = "";

    /**
     * Sets catalog.
     *
     * @param catalog the catalog
     */
    public void setCatalog(String catalog) {
    mCatalog = catalog;
    refreshView();
  }

    /**
     * Gets catalog.
     *
     * @return the catalog
     */
    public String getCatalog() {
    return mCatalog;
  }

  @Override
  public void update(Observable sender, Object data) {
    refreshView();
  }


    /**
     * 刷新页面
     */
    public void refreshView() {
    this.clear();
    if (mCatalog != null && mCatalog.length() > 0) {
      if (mEmptyString != null && mEmptyString.length() > 0) {
        this.addItem(mEmptyString, "");
      }
      List<S_METAObj> datas = MetaDataProvider.get().findByCatalog(mCatalog);
      for (S_METAObj d : datas) {
        this.addItem(d.getName(), d.getCode());
      }
    }

    if (!selectInitValue()) {
      selectInitName();
    }
  }

  @Override
  public void selectValue(String value) {
    if (value != null && value.length() > 0) {
      for (int i = 0; i < this.getItemCount(); i++) {
        String v = this.getValue(i);
        if (v.equals(value)) {
          this.setSelectedIndex(i);
          break;
        }
      }
    } else {
      this.setSelectedIndex(0);
    }
    MessageEvent ev = new MessageEvent(MessageEvent.SELECT, "");
    fireEvent(ev);
  }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
    String v = getSelectedValue();
    return v == null ? "" : v;
  }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(String value) {
    selectValue(value);
  }

  private String mEmptyString = "";

    /**
     * Sets empty.
     *
     * @param emptyString the empty string
     */
    public void setEmpty(String emptyString) {
    mEmptyString = emptyString;
    refreshView();
  }
}

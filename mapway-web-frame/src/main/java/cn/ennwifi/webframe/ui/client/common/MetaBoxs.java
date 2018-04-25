package cn.ennwifi.webframe.ui.client.common;

import java.util.ArrayList;
import java.util.List;

import cn.ennwifi.webframe.ui.client.data.MetaDataProvider;
import cn.ennwifi.webframe.ui.shared.repository.S_METAObj;
import cn.mapway.ui.client.mvc.Observable;
import cn.mapway.ui.client.mvc.Observer;
import cn.mapway.ui.client.widget.common.CheckBoxEx;
import cn.mapway.ui.client.widget.common.IValidator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * 多选的元数据框
 *
 * @author zhangjianshe
 */
public class MetaBoxs extends HTMLPanel implements HasMessageHandlers, Observer, IValidator {


    /**
     * Instantiates a new Meta boxs.
     */
    public MetaBoxs() {
    this("div");
  }

  @Override
  public HandlerRegistration addMessageHandler(MessageHandler handler) {
    return addHandler(handler, MessageEvent.TYPE);
  }

    /**
     * Instantiates a new Meta boxs.
     *
     * @param tag the tag
     */
    public MetaBoxs(String tag) {
    super(tag);
    setStyleName("gwtEx-width-12");
    getElement().getStyle().setTextAlign(com.google.gwt.dom.client.Style.TextAlign.LEFT);
    MetaDataProvider.get().addObserver(this);
  }

  private String mCatalog = "";
  private ClickHandler checkBoxClicked = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {
      CheckBoxEx check = (CheckBoxEx) event.getSource();
      if (check.getValue()) {
        MessageEvent ev = new MessageEvent(MessageEvent.SELECT, check.getData());
        fireEvent(ev);
      } else {
        MessageEvent ev = new MessageEvent(MessageEvent.CANCEL, check.getData());
        fireEvent(ev);
      }
    }
  };

    /**
     * Sets catalog.
     *
     * @param catalog the catalog
     */
    public void setCatalog(String catalog) {
    mCatalog = catalog;
    refreshView();
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
      List<S_METAObj> datas = MetaDataProvider.get().findByCatalog(mCatalog);
      for (S_METAObj d : datas) {
        CheckBoxEx checkBox = new CheckBoxEx();
        checkBox.setText(d.getName());
        checkBox.setData(d);
        checkBox.addClickHandler(checkBoxClicked);
        this.add(checkBox);
      }
    }
    updateData(true);
  }

    /**
     * The M codes.
     */
    List<String> mCodes = new ArrayList<String>();

    /**
     * 设置代码数据
     *
     * @param codes ,00,01,
     */
    public void setValue(String codes) {
    mCodes = new ArrayList<String>();
    if (codes != null) {
      String[] codelist = codes.split(",");
      for (int i = 0; i < codelist.length; i++) {
        String code = codelist[i];
        code = code.trim();
        if (code != null && code.length() > 0) {
          mCodes.add(code);
        }
      }
    }
    updateData(true);
  }

    /**
     * 获取选中的数值
     *
     * @return value
     */
    public String getValue() {
    List<S_METAObj> selected = updateData(false);
    mCodes = new ArrayList<String>();
    for (S_METAObj meta : selected) {
      mCodes.add(meta.getCode());
    }
    if (mCodes.size() == 0) {
      return "";
    } else {
      String r = ",";
      for (String c : mCodes) {
        r = r + c + ",";
      }
      return r;
    }
  }

  /**
   * 数据绑定操作
   * @param toUI true data toUI
   * @return
   */
  private List<S_METAObj> updateData(boolean toUI) {

    List<S_METAObj> ds = new ArrayList<S_METAObj>();
    if (toUI) {
      // 向界面上绑定数据
      for (int i = 0; i < getWidgetCount(); i++) {
        CheckBoxEx checkBox = (CheckBoxEx) getWidget(i);
        checkBox.setValue(false);
        S_METAObj meta = (S_METAObj) checkBox.getData();
        for (String c : mCodes) {
          if (meta.getCode().equals(c)) {
            checkBox.setValue(true);
            break;
          }
        }
      }
    } else {
      // 从界面获取数据
      for (int i = 0; i < getWidgetCount(); i++) {
        CheckBoxEx checkBox = (CheckBoxEx) getWidget(i);
        if (checkBox.getValue()) {
          ds.add((S_METAObj) checkBox.getData());
        }
      }
    }
    return ds;
  }


  @Override
  public void setMessage(String msg) {
    this.msg = msg;
  }

  private String msg = "";
  private boolean required = false;

  @Override
  public String getMessage() {
    return msg;
  }

  @Override
  public void setPattern(String pattern) {

  }

  @Override
  public void setRequired(boolean b) {
    required = b;
  }

  @Override
  public boolean isValidate() {

    if (required) {
      List<S_METAObj> datas = updateData(false);
      if (datas.size() == 0) {
        return false;
      }

      if (minLength != null) {
        if (datas.size() < minLength) {
          return false;
        }
      }

      if (maxLength != null) {
        if (datas.size() > maxLength) {
          return false;
        }

      }

      return true;

    } else {
      return true;
    }
  }


    /**
     * The Min length.
     */
    Integer minLength = null;
    /**
     * The Max length.
     */
    Integer maxLength = null;


    /**
     * 设置最小个数
     *
     * @param minLength the min length
     */
    void setMinLength(int minLength) {
    this.minLength = minLength;

  }

    /**
     * 设置最大个数
     *
     * @param maxLength the max length
     */
    public void setMaxLength(int maxLength) {
    this.maxLength = maxLength;
  }


}

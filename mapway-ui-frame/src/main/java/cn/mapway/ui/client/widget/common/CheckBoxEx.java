package cn.mapway.ui.client.widget.common;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.CheckBox;

public class CheckBoxEx extends CheckBox implements IValidator {
  public CheckBoxEx() {
    setStyleName("gwtEx-CheckBox");
    init();
  }

  private void init() {
    this.getElement().getStyle().setMargin(0, Unit.PX);
  }

  /** 按钮的数据. */
  private Object data;

  public CheckBoxEx(String name) {
    super(name);
    init();
  }

  /**
   * Gets the data.
   *
   * @return the data
   */
  public Object getData() {
    return data;
  }

  /**
   * Sets the data.
   *
   * @param data the new data
   */
  public void setData(Object data) {
    this.data = data;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  private String tag;

  private String msg = "";
  private RegExp regex = null;
  private boolean required = false;

  @Override
  public String getMessage() {
    return msg;
  }

  @Override
  public void setPattern(String pattern) {
    if (pattern != null && pattern.length() > 0) {
      regex = RegExp.compile(pattern);
    } else {
      regex = null;
    }
  }

  @Override
  public void setRequired(boolean b) {
    required = b;
  }

  @Override
  public boolean isValidate() {
    return true;
  }

  @Override
  public void setMessage(String msg) {
    this.msg = msg;
  }
}

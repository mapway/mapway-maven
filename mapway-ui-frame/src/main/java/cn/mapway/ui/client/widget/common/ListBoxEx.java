package cn.mapway.ui.client.widget.common;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.ListBox;

public class ListBoxEx extends ListBox implements IValidator {

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

  public ListBoxEx() {
    setStyleName("gwtEx-ListBox");
  }

  private Object data;

  public Object getData() {
    return data;
  }

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
  private String initValue;

  private String initName;

  /**
   * 选中某个值
   * 
   * @param value
   */
  public void selectValue(String value) {
    this.initValue = value;
    if (this.initValue != null) {
      for (int i = 0; i < this.getItemCount(); i++) {
        String v = this.getValue(i);
        if (v.equals(value)) {
          this.setSelectedIndex(i);
          break;
        }
      }
    }
  }

  /**
   * 选中某个值
   * 
   * @param value
   */
  public void selectName(String name) {
    this.initName = name;
    if (this.initName != null) {
      for (int i = 0; i < this.getItemCount(); i++) {
        String v = this.getItemText(i);
        if (v.equals(name)) {
          this.setSelectedIndex(i);
          break;
        }
      }
    }
  }

  /**
   * 选中初始化名字
   */
  public boolean selectInitName() {
    if (getInitName() != null && getInitName().length() > 0) {
      selectName(getInitName());
      return true;
    }
    return false;
  }

  /**
   * 选中初始化值
   */
  public boolean selectInitValue() {
    if (getInitValue() != null && getInitValue().length() > 0) {
      selectValue(getInitValue());
      return true;
    }
    return false;
  }

  public String getInitValue() {
    return initValue;
  }

  public void setInitValue(String initValue) {
    this.initValue = initValue;
  }

  public void setInitName(String initName) {
    this.initName = initName;
  }

  public String getInitName() {
    return this.initName;
  }

  public void fire() {

  }

}

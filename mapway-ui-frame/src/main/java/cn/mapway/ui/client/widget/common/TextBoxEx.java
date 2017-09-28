package cn.mapway.ui.client.widget.common;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.TextBox;

public class TextBoxEx extends TextBox implements IValidator {

  public TextBoxEx() {
    setStyleName("gwtEx-TextBox");
  }

  public void setPlaceHolder(String placeholder) {
    this.getElement().setAttribute("placeholder", placeholder);
  }

  @Override
  public void setMessage(String msg) {
    this.msg = msg;
  }

  private String msg = "";
  protected RegExp regex = null;
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
    String v = getValue();

    if (required) {

      if (minLength != null) {
        if (v == null || v.length() == 0 || v.length() < minLength) {
          return false;
        }
      }
      if (maxLength != null) {
        if (v == null || v.length() == 0 || v.length() > maxLength) {
          return false;
        }
      }

      if (v == null || v.length() == 0) {
        return false;
      } else {
        return regex.test(v);
      }
    } else {
      if (v == null || v.length() == 0) {
        return true;
      }

      if (minLength != null) {
        if (v.length() < minLength) {
          return false;
        }
      }
      if (maxLength != null) {
        if (v.length() > maxLength) {
          return false;
        }
      }
      return true;
    }
  }

  Integer minLength = null;
  Integer maxLength = null;


  /**
   * 设置最小长度
   * @param minLength
   */
  public void setMinLength(int minLength) {
    this.minLength = minLength;

  }

  /**
   * 设置最大长度
   * @param maxLength
   */
  @Override
  public void setMaxLength(int maxLength) {
    this.maxLength = maxLength;
    super.setMaxLength(maxLength);
  }

}

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
    if (required) {
      String v = getValue();
      if (v == null || v.length() == 0) {
        return false;
      } else {
        return regex.test(v);
      }
    } else {
      String v = getValue();
      if (v == null || v.length() == 0) {
        return true;
      } else {
        return regex.test(v);
      }
    }
  }

}

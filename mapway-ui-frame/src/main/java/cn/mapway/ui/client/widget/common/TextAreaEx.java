package cn.mapway.ui.client.widget.common;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.TextArea;

/**
 * The type Text area ex.
 */
public class TextAreaEx extends TextArea implements IValidator {

    /**
     * Instantiates a new Text area ex.
     */
    public TextAreaEx() {
    setStyleName("gwtEx-TextBox");
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
        return regex == null ? true : regex.test(v);
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

    /**
     * The Min length.
     */
    Integer minLength = null;
    /**
     * The Max length.
     */
    Integer maxLength = null;


    /**
     * 设置最小长度
     *
     * @param minLength the min length
     */
    public void setMinLength(int minLength) {
    this.minLength = minLength;

  }

    /**
     * 设置最大长度
     *
     * @param maxLength the max length
     */
    public void setMaxLength(int maxLength) {
    this.maxLength = maxLength;
  }

}

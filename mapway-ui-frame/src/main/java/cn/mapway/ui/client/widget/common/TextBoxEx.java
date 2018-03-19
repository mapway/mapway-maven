package cn.mapway.ui.client.widget.common;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.TextBox;

/**
 * The type Text box ex.
 */
public class TextBoxEx extends TextBox implements IValidator {

  /**
   * Instantiates a new Text box ex.
   */
  public TextBoxEx() {
    setStyleName("gwtEx-TextBox");
  }

  /**
   * Sets place holder.
   *
   * @param placeholder the placeholder
   */
  public void setPlaceHolder(String placeholder) {
    this.getElement().setAttribute("placeholder", placeholder);
  }

  @Override
  public void setMessage(String msg) {
    this.msg = msg;
  }

  private String msg = "";
  /**
   * The Regex.
   */
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
     * maxLength
     * @param maxLength  the maxLength
     */
  @Override
  public void setMaxLength(int maxLength) {
    this.maxLength = maxLength;
    super.setMaxLength(maxLength);
  }
}

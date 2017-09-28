package cn.mapway.ui.client.widget.common;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;

public class NumberTextBox extends TextBoxEx implements KeyUpHandler {



  @Override
  public String getValue() {
    String v = super.getValue();
    if (v == null || v.length() == 0) {
      return "0";
    }
    return v;
  };



  public NumberTextBox() {
    setStyleName("gwtEx-TextBox");
    init();
  }

  private void init() {
    this.addKeyUpHandler(this);
    setPattern("[^\\d\\.\\-]");
  }

  @Override
  public boolean isValidate() {
    String v = getValue();
    if (regex.test(v)) {
      Double d = Double.valueOf(v);
      if (this.min != null) {
        if (d < this.min) {
          return false;
        }
      }
      if (this.max != null) {
        if (d > this.max) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  @Override
  public void onKeyUp(KeyUpEvent event) {
    String value = this.getValue();
    value = value.replaceAll("[^\\d\\.\\-]", "");
    this.setValue(value);
  }

  public boolean checkNum() {
    String value = this.getValue();
    String reg = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
    return value.matches(reg);
  }

  Double min = null;
  Double max = null;

  /**
   * 设置最小值
   * @param min
   */
  void setMin(double min) {
    this.min = min;
  }

  /**
   * 设置最大值
   * @param max
   */
  void setMax(double max) {
    this.max = max;
  }
}

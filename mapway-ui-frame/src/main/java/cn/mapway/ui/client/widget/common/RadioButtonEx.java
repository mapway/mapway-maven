package cn.mapway.ui.client.widget.common;

import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.RadioButton;

public class RadioButtonEx extends RadioButton implements IValidator {

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
    return true;
  }

  @UiConstructor
  public RadioButtonEx() {
    super("");
    setStyleName("gwtEx-RadioButton");
  }

  public RadioButtonEx(String name) {
    super(name);
  }

  public RadioButtonEx(String name, SafeHtml label) {
    super(name, label);
  }

  public RadioButtonEx(String name, SafeHtml label, DirectionEstimator directionEstimator) {
    super(name, label, directionEstimator);
  }

}

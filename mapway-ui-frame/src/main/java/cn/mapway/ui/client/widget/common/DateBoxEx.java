package cn.mapway.ui.client.widget.common;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.datepicker.client.DateBox;

public class DateBoxEx extends DateBox implements IValidator {
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

  private static String DATE_TIME_FORMAT = "yyyy/MM/dd-HH:mm:ss";

  public DateBoxEx() {
    setStyleName("gwtEx-TextBox");
    setDateFormat(DATE_TIME_FORMAT);

    initDateBox();
  }

  public void setDateFormat(String dateFormat) {
    if (!dateFormat.isEmpty()) {
      DATE_TIME_FORMAT = dateFormat;
    }
    DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(DATE_TIME_FORMAT);
    DefaultFormat df = new DateBoxEx.DefaultFormat(dateTimeFormat);
    this.setFormat(df);
  }

  public void initDateBox() {
    PrimaryButton btn = new PrimaryButton();
    btn.setText("当前时间");
    btn.addClickHandler(nowTimeHandler);
    this.getElement().appendChild(btn.getElement());
  }

  ClickHandler nowTimeHandler = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {}
  };

}

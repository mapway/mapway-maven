package cn.mapway.ui.client.widget.common;

/**
 * 电子邮箱输入框
 * @author zhangjianshe
 *
 */
public class EmailBox extends TextBoxEx {

  public final static String PATTERN = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+";

  public EmailBox() {
    setPattern(PATTERN);

  }
}

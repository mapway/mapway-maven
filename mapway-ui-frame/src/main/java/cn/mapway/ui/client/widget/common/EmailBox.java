package cn.mapway.ui.client.widget.common;

/**
 * 电子邮箱输入框
 *
 * @author zhangjianshe
 */
public class EmailBox extends TextBoxEx {

    /**
     * The constant PATTERN.
     */
    public final static String PATTERN = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+";

    /**
     * Instantiates a new Email box.
     */
    public EmailBox() {
    setPattern(PATTERN);

  }
}

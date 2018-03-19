package cn.mapway.ui.client.widget.common;

/**
 * 验证接口
 *
 * @author zhangjianshe
 */
public interface IValidator {

    /**
     * 是否验证成功
     *
     * @return boolean
     */
    boolean isValidate();

    /**
     * 设置正则表达书
     *
     * @param pattern the pattern
     */
    void setPattern(String pattern);

    /**
     * 获取验证消息
     *
     * @return message
     */
    String getMessage();

    /**
     * 设置验证消息
     *
     * @param msg the msg
     */
    void setMessage(String msg);

    /**
     * 是否必填
     *
     * @param b the b
     */
    void setRequired(boolean b);


}

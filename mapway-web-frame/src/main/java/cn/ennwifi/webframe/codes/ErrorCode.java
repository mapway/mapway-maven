package cn.ennwifi.webframe.codes;


/**
 * 错误代码条目.
 *
 * @author zhangjianshe
 */
public class ErrorCode {

    /**
     * The code.
     */
    public Integer code;

    /**
     * The message.
     */
    public String message;

    /**
     * Instantiates a new error code.
     *
     * @param code    the code
     * @param message the message
     */
    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    /**
     * Bind.
     *
     * @param objects the objects
     * @return the error code
     */
    public ErrorCode bind(Object... objects) {
        if (objects == null) {
            return this;
        }

        String str = message;
        for (int i = 0; i < objects.length; i++) {
            Object object = objects[i];
            if (object == null) {
                object = "null";
            }
            str = str.replaceAll("\\$\\{" + i + "\\}", object.toString());
        }
        return new ErrorCode(code, str);
    }
}

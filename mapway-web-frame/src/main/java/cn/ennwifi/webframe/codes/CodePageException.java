package cn.ennwifi.webframe.codes;

/**
 * 代码错误,返回相关的JSP页面.
 *
 * @author zhangjianshe
 */
public class CodePageException extends Exception {

  /** The code. */
  private Integer code;

  /**
   * Instantiates a new code exception.
   *
   * @param code the code
   * @param message the message
   */
  public CodePageException(Integer code, String message) {
    super(message);
    this.setCode(code);
  }

  /**
   * Instantiates a new code exception.
   *
   * @param errorcode the errorcode
   */
  public CodePageException(ErrorCode errorcode) {
    super(errorcode.message);
    this.code = errorcode.code;
  }

  /**
   * Gets the code.
   *
   * @return the code
   */
  public Integer getCode() {
    return code;
  }

  /**
   * Sets the code.
   *
   * @param code the new code
   */
  public void setCode(Integer code) {
    this.code = code;
  }

}

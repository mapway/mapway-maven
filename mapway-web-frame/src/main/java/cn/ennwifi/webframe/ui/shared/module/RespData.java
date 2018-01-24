package cn.ennwifi.webframe.ui.shared.module;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Code;
import cn.mapway.document.annotation.Codes;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * The Class RespData.
 *
 * @author zhangjianshe
 */
public class RespData implements IsSerializable {



  /** The msg. */
  @ApiField(value = "接口出错返回的消息,缺省返回空字符串", example = "错误内容")
  public String msg = "";

  /** The ret code. */
  @ApiField(value = "0:成功,非0:失败,", example = "1")
  @Codes(@Code(value = "0", desc = "返回成功"))
  public Integer retCode;

  /**
   * Ok.
   *
   * @return the resp data
   */
  public RespData ok() {
    return ok("操作成功");
  }

  /**
   * Ok.
   *
   * @param msg the msg
   * @return the resp data
   */
  public RespData ok(String msg) {
    this.retCode = 0;
    this.msg = msg;
    return this;
  }

  /**
   * Fail.
   *
   * @param msg the msg
   * @return the resp data
   */
  public RespData fail(String msg) {
    return codeAndMsg(1, msg);
  }

  /**
   * Code and msg.
   *
   * @param code the code
   * @param msg the msg
   * @return the resp data
   */
  public RespData codeAndMsg(int code, String msg) {
    this.retCode = code;
    this.msg = msg;
    return this;
  }


}

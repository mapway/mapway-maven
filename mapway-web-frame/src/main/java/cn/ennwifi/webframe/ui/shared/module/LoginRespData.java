package cn.ennwifi.webframe.ui.shared.module;

import cn.ennwifi.webframe.ui.shared.repository.S_USERObj;
import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

/**
 * The Class LoginRespData.
 */
@Doc("登录系统返回数据")
public class LoginRespData extends RespData {

  /** The token. */
  @ApiField(value = "用户信息，包括token")
  public S_USERObj user = new S_USERObj();
}

package cn.ennwifi.webframe.ui.shared.module;

import com.google.gwt.user.client.rpc.IsSerializable;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

/**
 * The Class LoginReqData.
 */
@Doc("登录系统数据")
public class LoginReqData implements IsSerializable {

    /**
     * The username.
     */
    @ApiField("用户名")

    public String username;

    /**
     * The password.
     */
    @ApiField(value = "密码")

    public String password;

    /**
     * The password.
     */
    @ApiField(value = "登录类型")
    public String type;

    /**
     * 授权资源的根ID
     */
    public Integer rootResourceId;

}

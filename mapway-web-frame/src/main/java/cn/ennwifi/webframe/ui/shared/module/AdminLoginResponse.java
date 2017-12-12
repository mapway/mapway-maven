package cn.ennwifi.webframe.ui.shared.module;

import cn.ennwifi.webframe.ui.shared.repository.S_USERObj;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 用户登录返回的数据.
 *
 * @author zhangjianshe
 */

public class AdminLoginResponse implements IsSerializable {
    /**
     * Instantiates a new login response.
     */
    public AdminLoginResponse() {
        configure=new DefaultClientConfigure();
    }

    /**
     * 当前登录用户.
     */
    public S_USERObj user;

    /**
     * 配置信息.
     */
    public DefaultClientConfigure configure;
}
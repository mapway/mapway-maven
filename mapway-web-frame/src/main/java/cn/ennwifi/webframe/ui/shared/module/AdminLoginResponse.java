package cn.ennwifi.webframe.ui.shared.module;

import cn.ennwifi.webframe.ui.shared.repository.S_RESOURCEObj;
import cn.ennwifi.webframe.ui.shared.repository.S_USERObj;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.List;

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
        configure = new DefaultClientConfigure();
        authorities = new ArrayList<S_RESOURCEObj>();
    }

    /**
     * 当前登录用户.
     */
    public S_USERObj user;

    /**
     * 配置信息.
     */
    public DefaultClientConfigure configure;

    /**
     * 授权的资源。
     */
    public List<S_RESOURCEObj> authorities;
}
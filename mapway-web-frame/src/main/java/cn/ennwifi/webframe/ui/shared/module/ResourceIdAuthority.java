package cn.ennwifi.webframe.ui.shared.module;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 资源权限对象
 */
public class ResourceIdAuthority implements IsSerializable {
    /**
     * 资源ID
     */
    public Long resourceId;

    /**
     * 是否授权
     */
    public Boolean authorized;
}

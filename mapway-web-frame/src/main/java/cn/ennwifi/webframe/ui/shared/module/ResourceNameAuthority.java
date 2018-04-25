package cn.ennwifi.webframe.ui.shared.module;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 资源权限对象
 */
public class ResourceNameAuthority implements IsSerializable {
    /**
     * 资源路径
     */
    public String path;

    /**
     * 资源名称
     */
    public String name;

    /**
     * 是否授权
     */
    public Boolean authorized;
}

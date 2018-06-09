package cn.ennwifi.webframe.ui.shared.module;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 数据数量
 *
 * @author zhangjianshe
 */
public class DashboardData implements IsSerializable {

    /**
     * The Name.
     */
    public String name;
    /**
     * The Count.
     */
    public int count;
    /**
     * The Icon.
     */
    public String icon;

    /**
     * Instantiates a new Dashboard data.
     */
    public DashboardData() {

    }

    /**
     * Instantiates a new Dashboard data.
     *
     * @param name  the name
     * @param count the count
     * @param icon  the icon
     */
    public DashboardData(String name, int count, String icon) {
        this.name = name;
        this.count = count;
        this.icon = icon;
    }
}

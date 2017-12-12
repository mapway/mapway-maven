package cn.ennwifi.webframe.ui.shared.module;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 数据数量
 * 
 * @author zhangjianshe
 *
 */
public class DashboardData implements IsSerializable {

  public String name;
  public int count;
  public String icon;

  public DashboardData() {

  }

  public DashboardData(String name, int count, String icon) {
    this.name = name;
    this.count = count;
    this.icon = icon;
  }
}

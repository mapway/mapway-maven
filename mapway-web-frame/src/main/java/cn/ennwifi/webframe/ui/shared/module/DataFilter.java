package cn.ennwifi.webframe.ui.shared.module;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataFilter implements IsSerializable {
  public String name;
  public String op;
  public String value;

  public DataFilter() {

  }

  public DataFilter(String n, String o, String v) {
    name = n;
    op = o;
    value = v;
  }
}

package cn.ennwifi.webframe.ui.shared.module;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * The type Data filter.
 */
public class DataFilter implements IsSerializable {
    /**
     * The Name.
     */
    public String name;
    /**
     * The Op.
     */
    public String op;
    /**
     * The Value.
     */
    public String value;

    /**
     * Instantiates a new Data filter.
     */
    public DataFilter() {

  }

    /**
     * Instantiates a new Data filter.
     *
     * @param n the n
     * @param o the o
     * @param v the v
     */
    public DataFilter(String n, String o, String v) {
    name = n;
    op = o;
    value = v;
  }
}

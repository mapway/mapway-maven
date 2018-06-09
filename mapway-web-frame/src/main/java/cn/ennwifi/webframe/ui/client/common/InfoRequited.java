package cn.ennwifi.webframe.ui.client.common;

import com.google.gwt.user.client.ui.Label;

/**
 * 必填字段信息
 *
 * @author zhangjianshe
 */
public class InfoRequited extends Label {

    /**
     * Instantiates a new Info requited.
     */
    public InfoRequited() {
        setStyleName("solu_needed");
        setText("*必填");
    }

}

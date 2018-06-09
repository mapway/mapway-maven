/******************************************************************************
 <pre>

 =============================================================
 -   ____ _  _ ____ _  _ ____  _ _ ____ _  _ ____ _  _ ____  -
 -    __] |__| |__| |\ | | __  | | |__| |\ | [__  |__| |___  -
 -   [___ |  | |  | | \| |__| _| | |  | | \| ___] |  | |___  -
 -           http://hi.baidu.com/zhangjianshe                -
 =============================================================

 </pre>
 *******************************************************************************/
package com.ksyzt.gwt.client.common;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PopupPanel;

// TODO: Auto-generated Javadoc

/**
 * 信息显示对话框.
 *
 * @author zhangjianshe @navinfo.com
 */
public class InformationDialog extends PopupPanel {

    /**
     * The dialog.
     */
    public static InformationDialog DIALOG;

    /**
     * The panel.
     */
    HTMLPanel panel;

    /**
     * The m HTML.
     */
    HTML mHTML;

    /**
     * 获取弹出消息框.
     *
     * @return the dialog
     */
    public static InformationDialog getDialog() {
        if (DIALOG == null) {
            DIALOG = new InformationDialog();
        }
        return DIALOG;
    }

    /**
     * Instantiates a new information dialog.
     */
    public InformationDialog() {
        this.setAnimationEnabled(false);
        this.setAutoHideEnabled(false);
        this.setStylePrimaryName("information-dialog");

        mHTML = new HTML();
        this.add(mHTML);
        this.setGlassEnabled(false);
        this.setModal(true);
    }

    /**
     * 设置图标.
     *
     * @param url the new icon
     */
    public void setIcon(String url) {
        mIcon = url;
    }

    /**
     * The m icon.
     */
    private String mIcon;

    /**
     * Sets the information.
     *
     * @param info     the info
     * @param loadding the loadding
     */
    public void setInformation(String info, boolean loadding) {
        String html = "";
        if (loadding) {
            html += "<table><tr><td><img src='" + mIcon + "'/></td>";
            html += "<td>" + info + "</td>";
            html += "</tr></table>";
        } else {
            html += info;
        }
        mHTML.setHTML(html);
    }

    /**
     * Sets the information.
     *
     * @param info the new information
     */
    public void setInformation(String info) {
        setInformation(info, true);
    }
}

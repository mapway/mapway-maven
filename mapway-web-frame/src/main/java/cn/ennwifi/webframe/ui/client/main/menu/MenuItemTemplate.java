package cn.ennwifi.webframe.ui.client.main.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * 构造菜单项的模板
 */
public interface MenuItemTemplate extends SafeHtmlTemplates {
    /**
     * Text safe html.
     *
     * @param text the text
     * @return the safe html
     */
    @Template("<span>{0}</span>")
    SafeHtml text(String text);

    /**
     * Text with icon safe html.
     *
     * @param icon the icon
     * @param text the text
     * @return the safe html
     */
    @Template("<img width='26px' valign='middle' width='26px' src='{0}' /><div style='padding-left:5px;display:inline-block;line-height:26px;height:26px;'>{1}</div>")
    SafeHtml textWithIcon(String icon, String text);

    /**
     * 全局唯一模板
     */
    public static final MenuItemTemplate TEMPLATE = GWT.create(MenuItemTemplate.class);
}

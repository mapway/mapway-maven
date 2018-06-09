package cn.mapway.ui.client.widget.menu.handler;

import cn.mapway.ui.client.widget.menu.IMenuItem;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 缺省的菜单处理菜单处理句柄.
 *
 * @author zhangjianshe
 */
public class DefaultMenuHandler implements MenuExecutor {

    /**
     * The constant INSTANCE.
     */
    public static DefaultMenuHandler INSTANCE = new DefaultMenuHandler();

    /**
     * Instantiates a new Default menu handler.
     */
    protected DefaultMenuHandler() {

    }

    @Override
    public void execute(Widget sender, IMenuItem menu) {
        if (menu.getSubMenus().size() == 0) {
            if (menu.getHandler() != null) {
                menu.getHandler().execute(sender, menu);
            }
            return;
        }

        Widget subMenuPanel = menu.getRender().render(sender, menu);

        if (subMenuPanel == null) {
            if (menu.getHandler() != null) {
                menu.getHandler().execute(sender, menu);

            }
            return;
        }

        PopupPanel p = new PopupPanel(true);
        p.add(subMenuPanel);
        p.showRelativeTo(sender);
    }
}

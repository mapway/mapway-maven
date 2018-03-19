package cn.mapway.ui.client.widget.menu.render;

import cn.mapway.ui.client.widget.menu.IMenuItem;

import com.google.gwt.user.client.ui.Widget;

/**
 * The interface Menu render.
 *
 * @author zhangjianshe
 */
public interface IMenuRender {

    /**
     * Render widget.
     *
     * @param parent the parent
     * @param menu   the menu
     * @return widget
     */
    Widget render(Widget parent, IMenuItem menu);
}

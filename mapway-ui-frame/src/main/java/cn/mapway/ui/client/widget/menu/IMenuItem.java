package cn.mapway.ui.client.widget.menu;

import java.util.List;
import java.util.Map;

import cn.mapway.ui.client.widget.menu.handler.MenuExecutor;
import cn.mapway.ui.client.widget.menu.render.IMenuRender;

import com.google.gwt.resources.client.ImageResource;

/**
 * 菜单数据项.
 *
 * @author zhangjianshe
 */
public interface IMenuItem {

    /**
     * Gets the direction.
     *
     * @return the direction
     */
    MenuDirection getDirection();

    /**
     * Sets the direction.
     *
     * @param direction the new direction
     */
    void setDirection(MenuDirection direction);

    /**
     * Gets the name.
     *
     * @return the name
     */
    String getName();

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    void setName(String name);

    /**
     * Gets the title.
     *
     * @return the title
     */
    String getTitle();

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    void setTitle(String title);

    /**
     * Gets the icon.
     *
     * @return the icon
     */
    ImageResource getIcon();

    /**
     * Sets the icon.
     *
     * @param icon the new icon
     */
    void setIcon(ImageResource icon);

    /**
     * Gets the parameters.
     *
     * @return the parameters
     */
    Map<String, Object> getParameters();

    /**
     * Sets the parameters.
     *
     * @param parameters the parameters
     */
    void setParameters(Map<String, Object> parameters);


    /**
     * Sets the render.
     *
     * @param render the new render
     */
    void setRender(IMenuRender render);

    /**
     * Gets the render.
     *
     * @return the render
     */
    IMenuRender getRender();


    /**
     * Adds the sub menu.
     *
     * @param item the item
     * @return the i menu item
     */
    IMenuItem addSubMenu(IMenuItem item);

    /**
     * Gets the sub menus.
     *
     * @return the sub menus
     */
    List<IMenuItem> getSubMenus();

    /**
     * Parent.
     *
     * @return the i menu item
     */
    IMenuItem parent();

    /**
     * Sets the parent.
     *
     * @param parent the new parent
     */
    void setParent(MenuItem parent);

    /**
     * Sets the handler.
     *
     * @param handler the new handler
     */
    void setHandler(MenuExecutor handler);

    /**
     * Gets the handler.
     *
     * @return the handler
     */
    MenuExecutor getHandler();
}

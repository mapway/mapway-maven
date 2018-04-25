package cn.mapway.ui.client.widget.menu;


/**
 * 菜单数据提供者
 *
 * @author zhangjianshe
 */
public interface IMenuProvider {
    /**
     * 查找子菜单项
     *
     * @param parent the parent
     * @return menu item
     */
    IMenuItem findMenu(IMenuItem parent);
}

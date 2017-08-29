package cn.mapway.ui.client.widget.menu;


/**
 * 菜单数据提供者
 * 
 * @author zhangjianshe
 *
 */
public interface IMenuProvider {
  /**
   * 查找子菜单项
   * 
   * @param parent
   * @return
   */
  IMenuItem findMenu(IMenuItem parent);
}

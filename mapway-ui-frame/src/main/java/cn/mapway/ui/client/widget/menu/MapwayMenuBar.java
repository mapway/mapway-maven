package cn.mapway.ui.client.widget.menu;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.MenuBar;


/**
 * MenuBar.
 *
 * @author zhangjianshe
 */
public class MapwayMenuBar extends MenuBar {

    /**
     * The M provider.
     */
    IMenuProvider mProvider;

    /**
     * 设置菜单内容
     *
     * @param provider the provider
     */
    public void setMenuProvider(IMenuProvider provider) {
    mProvider = provider;
    render();
  }

  /**
   * 渲染菜单
   */
  private void render() {
    IMenuItem menu = mProvider.findMenu(null);

    for (IMenuItem item : menu.getSubMenus()) {

      SafeHtml html = createHtml(item);
      MenuBar sub = createSubMenu(item);
      if (sub == null) {
        // this.addItem(html);
      }
    }

  }

  private SafeHtml createHtml(IMenuItem item) {

    return null;
  }

  private MenuBar createSubMenu(IMenuItem item) {

    return null;
  }
}

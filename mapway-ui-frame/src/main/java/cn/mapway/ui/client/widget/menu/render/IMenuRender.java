package cn.mapway.ui.client.widget.menu.render;

import cn.mapway.ui.client.widget.menu.IMenuItem;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author zhangjianshe
 *
 */
public interface IMenuRender {

  /**
   * @param parent
   * @param menu
   * @return
   */
  Widget render(Widget parent, IMenuItem menu);
}

package cn.mapway.ui.client.widget.menu.handler;

import cn.mapway.ui.client.widget.menu.IMenuItem;

import com.google.gwt.user.client.ui.Widget;


/**
 * 菜单执行句柄
 * 
 * @author zhangjianshe
 *
 */
public interface MenuExecutor {
  /**
   * 执行菜单项
   * 
   * @param menu
   */
  void execute(Widget sender, IMenuItem menu);
}

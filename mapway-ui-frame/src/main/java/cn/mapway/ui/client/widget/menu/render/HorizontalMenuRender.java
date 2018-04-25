package cn.mapway.ui.client.widget.menu.render;

import cn.mapway.ui.client.widget.menu.IMenuItem;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;


/**
 * 缺省的菜单渲染项.
 *
 * @author zhangjianshe
 */
public class HorizontalMenuRender implements IMenuRender {

    /**
     * 全局实例
     */
    public static final HorizontalMenuRender HORIZONTAL_MENU_RENDER = new HorizontalMenuRender();

    /**
     * Instantiates a new Horizontal menu render.
     */
    protected HorizontalMenuRender() {}

  /*
   * (non-Javadoc)
   * 
   * @see cn.mapway.ui.client.widget.menu.IMenuRender#render(com.google.gwt.user.client.ui.Widget,
   * cn.mapway.ui.client.widget.menu.IMenuItem)
   */
  @Override
  public Widget render(Widget parent, IMenuItem menu) {
    if (menu == null || menu.getSubMenus().size() == 0) {
      return null;
    }
    final HorizontalPanel hp = new HorizontalPanel();

    hp.setSpacing(5);
    for (final IMenuItem item : menu.getSubMenus()) {
      final Anchor a = new Anchor(item.getName());
      hp.add(a);
      if (item.getHandler() != null) {
        a.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
            item.getHandler().execute(a, item);
          }
        });
      }
    }

    return hp;

  }
}

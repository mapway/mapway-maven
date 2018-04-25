package cn.mapway.ui.client.test.menu;

import cn.mapway.ui.client.widget.menu.IMenuItem;
import cn.mapway.ui.client.widget.menu.IMenuProvider;
import cn.mapway.ui.client.widget.menu.MenuItem;
import cn.mapway.ui.client.widget.menu.handler.MenuExecutor;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * The type Main menu provider.
 */
public class MainMenuProvider implements IMenuProvider {

    /**
     * The Root.
     */
    IMenuItem root;
  private MenuExecutor menuHandler = new MenuExecutor() {

    @Override
    public void execute(Widget sender, IMenuItem menu) {
      Window.alert(menu.getName());
    }
  };

    /**
     * Instantiates a new Main menu provider.
     */
    public MainMenuProvider() {
    root = new MenuItem("主菜单");
    IMenuItem item1;

    item1 = new MenuItem("文件");
    IMenuItem file = new MenuItem("New");
    file.addSubMenu(new MenuItem("Excel"));
    file.addSubMenu(new MenuItem("PowerPointer"));
    item1.addSubMenu(file);

    item1.addSubMenu(new MenuItem("Save", menuHandler));
    item1.addSubMenu(new MenuItem("Exit", menuHandler));
    root.addSubMenu(item1);

    item1 = new MenuItem("关于", CustomRender.INSTANCE);
    item1.addSubMenu(new MenuItem("About", menuHandler));
    item1.addSubMenu(new MenuItem("Authors", menuHandler));
    root.addSubMenu(item1);

  }

  @Override
  public IMenuItem findMenu(IMenuItem parent) {
    if (parent == null) {
      return root;
    } else {
      return root;
    }
  }

}

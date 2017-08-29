package cn.mapway.ui.client.widget.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mapway.ui.client.widget.menu.handler.DefaultMenuHandler;
import cn.mapway.ui.client.widget.menu.handler.MenuExecutor;
import cn.mapway.ui.client.widget.menu.render.HorizontalMenuRender;
import cn.mapway.ui.client.widget.menu.render.IMenuRender;

import com.google.gwt.resources.client.ImageResource;

/**
 * 菜单项.
 * 
 * @author zhangjianshe
 *
 */
public class MenuItem implements IMenuItem {


  private IMenuItem parent;
  private ImageResource icon;
  private String name;
  private String title;
  private List<IMenuItem> subMenus;
  private MenuExecutor handler;
  private Map<String, Object> parameters;
  private MenuDirection direction;
  private IMenuRender render;


  public MenuItem() {
    name = "菜单项";
    title = name;
    icon = null;
    subMenus = new ArrayList<IMenuItem>();
    parameters = new HashMap<String, Object>();
    direction = MenuDirection.HORIZONTAL;
    render = HorizontalMenuRender.HORIZONTAL_MENU_RENDER;
    parent = null;
    handler = null;
  }

  public MenuItem(String name) {
    this(name, DefaultMenuHandler.INSTANCE);
  }

  public MenuItem(String name, MenuExecutor handler) {
    this(name, HorizontalMenuRender.HORIZONTAL_MENU_RENDER, handler);
  }

  public MenuItem(String name, IMenuRender render) {
    this(name, render, DefaultMenuHandler.INSTANCE);
  }

  public MenuItem(String name, IMenuRender render, MenuExecutor handler) {
    this();
    this.name = name;
    this.render = render;
    this.handler = handler;
  }


  @Override
  public MenuDirection getDirection() {

    return direction;
  }

  @Override
  public void setDirection(MenuDirection direction) {
    this.direction = direction;
  }

  @Override
  public String getName() {

    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getTitle() {

    return title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;

  }

  @Override
  public ImageResource getIcon() {

    return icon;
  }

  @Override
  public void setIcon(ImageResource icon) {

    this.icon = icon;
  }

  @Override
  public Map<String, Object> getParameters() {

    return parameters;
  }

  @Override
  public void setParameters(Map<String, Object> parameters) {
    this.parameters = parameters;
  }


  @Override
  public void setRender(IMenuRender render) {

    this.render = render;
  }

  @Override
  public IMenuRender getRender() {

    return render;
  }

  @Override
  public IMenuItem addSubMenu(IMenuItem item) {
    subMenus.add(item);
    item.setParent(this);
    return item;
  }

  @Override
  public List<IMenuItem> getSubMenus() {

    return this.subMenus;
  }

  @Override
  public IMenuItem parent() {

    return parent;
  }

  @Override
  public void setParent(MenuItem parent) {

    this.parent = parent;
  }

  @Override
  public void setHandler(MenuExecutor handler) {

    this.handler = handler;
  }

  @Override
  public MenuExecutor getHandler() {

    return this.handler;
  }


}

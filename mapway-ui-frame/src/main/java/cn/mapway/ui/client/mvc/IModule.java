package cn.mapway.ui.client.mvc;

import com.google.gwt.user.client.ui.Widget;

/**
 * 系统中模块的定义接口，根据设计原则，可以将代码划分为 系统/模块/子模块/操作 四个界别<br/>
 * 这个接口定义了如果要满足 模块和子模块的功能必须要实现的接口
 * 
 * @author zhangjianshe
 *
 */
public interface IModule {
  /**
   * 初始化模块.
   *
   * @param parameters the parameters
   * @param context the context
   * @return true 表示可以初始化本模块，false表示已经初始化子模块了，本模块可以不用初始化
   */
  boolean initialize(IModule parentModule, ModuleParameter parameters);

  /**
   * 卸载模块.
   */
  void unInitialize();

  /**
   * 返回模块的Widget.
   *
   * @return the root widget
   */
  Widget getRootWidget();


  /**
   * 用于模块向父模块注册工具栏按钮
   * 
   * @param tools
   */
  boolean updateTools(Widget... tools);

  /**
   * 用于模块向父模块注册工具栏按钮
   * 
   * @param tools
   */
  boolean appendTools(Widget tools);

  /**
   * 用于模块向父模块注册工具栏按钮
   * 
   * @param tools
   */
  boolean appendTools(Widget[] tools);

  /**
   * 获取模块信息
   * 
   * @return
   */
  ModuleInfo getModuleInfo();

  /**
   * 获取模块的父模块
   * 
   * @return
   */
  IModule getParentModule();

  /**
   * 获取模块参数.
   * 
   * @return
   */
  ModuleParameter getParameters();

}

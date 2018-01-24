package cn.mapway.ui.client.frames;



import java.util.ArrayList;
import java.util.List;

import cn.mapway.ui.client.history.HistoryManager;
import cn.mapway.ui.client.modules.common.UnAuthorityModule;
import cn.mapway.ui.client.mvc.BaseAbstractModule;
import cn.mapway.ui.client.mvc.IModule;
import cn.mapway.ui.client.mvc.IModuleDispatcher;
import cn.mapway.ui.client.mvc.ModuleInfo;
import cn.mapway.ui.client.mvc.ModuleParameter;
import cn.mapway.ui.client.widget.common.DataHolder;
import cn.mapway.ui.client.widget.common.ItemList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * 简单的模块调度器.
 * 
 * @author zhangjianshe
 *
 */
public abstract class AbstractModule extends BaseAbstractModule implements IModuleDispatcher {

  class UiFieldHolder {
    @UiField
    DockLayoutPanel root;
    @UiField
    ItemList subList;
    @UiField
    ScrollPanel subModules;


    @UiField
    HorizontalPanel tblNavi;


    @UiField
    HorizontalPanel tools;

    @UiField
    Image icon;

    @UiField
    Label lbExpand;

    @UiField
    HorizontalPanel bar;

    @UiHandler("lbExpand")
    void onExpand(ClickEvent ev) {
      double width = root.getWidgetSize(subModules);
      if (width < 1) {
        lbExpand.setText("◀");
        root.setWidgetSize(subModules, 200);
      } else {

        lbExpand.setText("▶");
        root.setWidgetSize(subModules, 0);
      }
    }

  }

  private UiFieldHolder holder;
  /**
   * 子模块
   */
  private List<ModuleInfo> mSubModules;

  /**
   * 注册子模块代码.
   * 
   * @param moduleCode
   */
  final public void registerSubModule(String moduleCode, boolean single) {
    ModuleInfo item = getModuleFactory().findModuleInfo(moduleCode);
    if (item != null) {
      boolean find = false;
      for (ModuleInfo i : mSubModules) {
        if (i.code.equals(item.code)) {
          GWT.log("重复注册子模块" + item.code);
          find = true;
          break;
        }
      }
      if (find == false) {
        mSubModules.add(item.copy().setSingle(single));
      }
    } else {
      GWT.log("没有子模块" + moduleCode + "信息");
    }
  }

  private static AbstractModuleUiBinder uiBinder = GWT.create(AbstractModuleUiBinder.class);

  interface AbstractModuleUiBinder extends UiBinder<Widget, AbstractModule.UiFieldHolder> {
  }

  private MessageHandler subModuleClicked = new MessageHandler() {

    @Override
    public void onMessage(Object sender, Integer message, Object value) {
      if (message == MessageEvent.ITEMCLICK) {
        DataHolder item = (DataHolder) value;
        ModuleInfo mi = (ModuleInfo) item.getData();
        ModuleParameter mp = new ModuleParameter();
        beforeSwitchSubModule(getParameters(), mi, mp);
        switchModule(mi.code, mp, true);
      }
    }
  };
  private ClickHandler homeClicked = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {
      String code = getModuleInfo().code;
      switchModule(code, getParameters(), true);
      initialize(getParentModule(), getParameters());
    }
  };

  private Label lbHome;
  private Anchor linkHome;


  public AbstractModule() {
    holder = new UiFieldHolder();
    lbHome = new Label();
    linkHome = new Anchor();
    initWidget(uiBinder.createAndBindUi(holder));
    mSubModules = new ArrayList<ModuleInfo>();
    holder.subList.addMessageHandler(subModuleClicked);
    linkHome.addClickHandler(homeClicked);

  }

  IModule subCurrent;
  Widget currentWidget = null;


  IModule current;


  @Override
  public boolean initialize(IModule parentModule, ModuleParameter parameters) {
    super.initialize(parentModule, parameters);
    if (parameters != null && parameters.getSubModule().length() > 0) {

      ModuleParameter mpSub = new ModuleParameter();
      beforeSwitchSubModule(parameters, getModuleFactory()
          .findModuleInfo(parameters.getSubModule()), mpSub);
      switchModule(parameters.getSubModule(), mpSub, false);
      boolean isThisModule = false;
      ModuleInfo thisModule = getModuleInfo();
      if (thisModule.code.equals(parameters.getSubModule())) {
        isThisModule = true;
      }
      parameters.setSubModule("");
      if (isThisModule) {
        return true;
      } else {

        return false;
      }
    }
    return true;
  }


  @Override
  public IModuleDispatcher switchModule(String code, ModuleParameter parameter,
      boolean saveToHistory) {

    IModuleDispatcher d = null;

    if (parameter == null) {
      parameter = new ModuleParameter();
    }

    // 两种情况 本模块 或者 子模块

    boolean isThisModule = false;
    ModuleInfo thisModule = getModuleInfo();
    if (thisModule.code.equals(code)) {
      isThisModule = true;
    }


    if (isThisModule) {
      // 本模块
      // 处理导航
      holder.tblNavi.clear();
      lbHome.setText(thisModule.name);
      holder.tblNavi.add(lbHome);
      holder.icon.setUrl(thisModule.icon);

      // 处理模块内容
      removeCurrent();
      if (content != null) {
        currentWidget = content;
      } else {
        currentWidget = new Label("没有模块主内容");
      }
      holder.root.add(currentWidget);
      handleSubModule();
      d = this;
    } else {

      ModuleInfo mi = null;
      for (ModuleInfo m : mSubModules) {
        if (m.code.equals(code)) {
          mi = m;
          break;
        }
      }

      IModule module = null;
      if (mi == null) {
        // 没有找到子模块
        mi = getModuleFactory().findModuleInfo(UnAuthorityModule.MODULE_CODE);
        parameter.put(UnAuthorityModule.PARA_MODULE_NAME, code);
        module = getModuleFactory().createModule(UnAuthorityModule.MODULE_CODE, true);
      } else {
        module = getModuleFactory().createModule(mi.code, true);
        syncSubModuleList(mi.code);
        if (module instanceof IModuleDispatcher) {
          d = (IModuleDispatcher) module;
        }

      }

      // 处理导航
      holder.tblNavi.clear();
      linkHome.setText(thisModule.name);

      holder.tblNavi.add(linkHome);

      holder.tblNavi.add(new HTML("&nbsp;&gt;&nbsp;"));
      holder.tblNavi.add(new Label(mi.name));
      holder.icon.setUrl(mi.icon);

      // 处理模块内容
      removeCurrent();
      currentWidget = module.getRootWidget();

      holder.root.add(currentWidget);
      module.initialize(this, parameter);

      // 保存历史记录
      HistoryManager.push(getModulePath(module));

      handleSubModule();

    }

    return d;
  }


  private void handleSubModule() {
    // 处理子模块
    if (mSubModules == null || mSubModules.size() == 0) {
      holder.bar.setCellWidth(holder.lbExpand, "1px");
      holder.lbExpand.setVisible(false);
      holder.root.setWidgetSize(holder.subModules, 0);
    } else {
      holder.lbExpand.setVisible(true);
      holder.bar.setCellWidth(holder.lbExpand, "20px");
      if (holder.subList.getWidgetCount() != mSubModules.size()) {
        holder.subList.clear();
        for (ModuleInfo item : mSubModules) {
          holder.subList.addItem(item.name, item.summary, item.icon, item);
        }
      }
    }
  }

  private void removeCurrent() {
    if (currentWidget != null) {
      updateTools();
      currentWidget.removeFromParent();
      currentWidget = null;
    }
  }


  /**
   * 更新按钮情况
   */
  @Override
  public boolean updateTools(Widget... toolsWidget) {
    boolean b = super.updateTools(toolsWidget);
    if (b == false) {
      holder.tools.clear();
      for (Widget w : toolsWidget) {
        holder.tools.add(w);
      }
    }
    return true;
  }


  /**
   * 追加按钮
   */
  @Override
  public boolean appendTools(Widget tools) {
    boolean b = super.appendTools(tools);
    if (b == false) {
      holder.tools.add(tools);
    }
    return true;
  };

  /**
   * 追加按钮数组
   */
  @Override
  public boolean appendTools(Widget[] tools) {
    boolean b = super.appendTools(tools);
    if (b == false) {
      for (int i = 0; i < tools.length; i++) {
        holder.tools.add(tools[i]);
      }
    }
    return true;
  };

  Widget content = null;

  /**
   * 初始化模块的界面
   * 
   * @param widget
   */
  @Override
  public void initModuleWidget(Widget widget) {
    content = widget;
    handleSubModule();
    switchModule(getModuleCode(), null, true);
  }

  @Override
  public Widget getRootWidget() {
    return this;
  }

  /**
   * 子类需要重载这个方法，提供子模块切换的参数
   * 
   * @param mi
   * @param mp
   */
  public void beforeSwitchSubModule(ModuleParameter parentModuleParameter, ModuleInfo mi,
      ModuleParameter mp) {

  }

  /**
   * 同步子模块列表的选中状态
   * 
   * @param moduleCode
   */
  private void syncSubModuleList(String moduleCode) {
    for (int i = 0; i < holder.subList.getWidgetCount(); i++) {
      DataHolder item = (DataHolder) holder.subList.getWidget(i);
      ModuleInfo mi = (ModuleInfo) item.getData();
      if (mi.code.equals(moduleCode)) {
        holder.subList.setSelected(i, true, false);
        break;
      }
    }
  }

  private void fireModuleCodeClicked(String moduleCode) {
    for (int i = 0; i < holder.subList.getWidgetCount(); i++) {
      DataHolder item = (DataHolder) holder.subList.getWidget(i);
      ModuleInfo mi = (ModuleInfo) item.getData();
      if (mi.code.equals(moduleCode)) {
        holder.subList.setSelected(i, true, true);
        break;
      }
    }
  }

  /**
   * 设置模块标题
   * 
   * @param caption
   */
  public final void setCaption(String caption) {
    lbHome.setText(caption);
    linkHome.setText(caption);
  }
}

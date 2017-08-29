package cn.mapway.ui.client.mvc;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;

/**
 * 模块基类.
 * 
 * @author zhangjianshe
 *
 */
public abstract class BaseAbstractModule extends MessageComposite implements IModule {


  private IModule mParentModule;
  private ModuleParameter mParameter;

  public BaseAbstractModule() {}


  private final static ModuleFactory FACTORY = GWT.create(ModuleFactory.class);

  /**
   * 模块工厂
   * 
   * @return
   */
  public static ModuleFactory getModuleFactory() {
    return FACTORY;
  }

  @Override
  public boolean initialize(IModule parentModule, ModuleParameter parameter) {
    if (parameter == null) {
      mParameter = new ModuleParameter();
    } else {
      mParameter = parameter;
    }
    mParentModule = parentModule;
    return true;
  }

  @Override
  public void unInitialize() {

  }



  @Override
  public void updateTools(Widget... tools) {
    if (mParentModule != null) {
      mParentModule.updateTools(tools);
    }
  }

  @Override
  public IModule getParentModule() {
    return mParentModule;
  }

  @Override
  public ModuleInfo getModuleInfo() {
    return getModuleFactory().findModuleInfo(getModuleCode());
  }

  /**
   * 获取模块代码
   * 
   * @return
   */
  public abstract String getModuleCode();

  public void initModuleWidget(Widget w) {
    initWidget(w);
  }

  @Override
  public Widget getRootWidget() {
    return this;
  }

  private List<IModule> getModuleStack(IModule module) {

    List<IModule> modules = new ArrayList<IModule>();
    IModule p = module;
    while (p != null) {
      modules.add(p);
      p = p.getParentModule();
    }
    return modules;
  }

  public List<SwitchModuleData> getModulePath(IModule module) {

    List<SwitchModuleData> r = new ArrayList<SwitchModuleData>();

    List<IModule> modules = getModuleStack(module);

    for (IModule m : modules) {
      ModuleInfo info = m.getModuleInfo();
      SwitchModuleData d = new SwitchModuleData(info.code, info.hash);
      d.setParameters(m.getParameters());
      r.add(0, d);
    }
    return r;
  }

  @Override
  public ModuleParameter getParameters() {
    return mParameter;
  }


}

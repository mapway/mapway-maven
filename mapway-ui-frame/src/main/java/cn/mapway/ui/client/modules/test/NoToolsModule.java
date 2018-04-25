package cn.mapway.ui.client.modules.test;

import cn.mapway.ui.client.mvc.BaseAbstractModule;
import cn.mapway.ui.client.mvc.ModuleMarker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

/**
 * The type No tools module.
 */
@ModuleMarker(value = NoToolsModule.MODULE_CODE, group = "/测试", name = "无tools模块")
public class NoToolsModule extends BaseAbstractModule {

    /**
     * The constant MODULE_CODE.
     */
    public final static String MODULE_CODE = "MC_NO_TOOLS";
  private static NoToolsModuleUiBinder uiBinder = GWT.create(NoToolsModuleUiBinder.class);

    /**
     * The interface No tools module ui binder.
     */
    interface NoToolsModuleUiBinder extends UiBinder<Widget, NoToolsModule> {
  }

    /**
     * Instantiates a new No tools module.
     */
    public NoToolsModule() {
    initModuleWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }

  @Override
  public Widget getRootWidget() {
    return this;
  }

}

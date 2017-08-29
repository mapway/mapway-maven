package cn.mapway.ui.client.modules.test;

import cn.mapway.ui.client.mvc.BaseAbstractModule;
import cn.mapway.ui.client.mvc.ModuleMarker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

@ModuleMarker(value = NoToolsModule.MODULE_CODE, name = "无tools模块")
public class NoToolsModule extends BaseAbstractModule {

  public final static String MODULE_CODE = "MC_NO_TOOLS";
  private static NoToolsModuleUiBinder uiBinder = GWT.create(NoToolsModuleUiBinder.class);

  interface NoToolsModuleUiBinder extends UiBinder<Widget, NoToolsModule> {
  }

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

package cn.mapway.ui.client.modules.test;

import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.mvc.ModuleMarker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

@ModuleMarker(value = NoSubModule.MODULE_CODE, group = "/测试", name = "没有子模块")
public class NoSubModule extends AbstractModule {

  public final static String MODULE_CODE = "MC_NO_SUB";

  private static NoSubModuleUiBinder uiBinder = GWT.create(NoSubModuleUiBinder.class);

  interface NoSubModuleUiBinder extends UiBinder<Widget, NoSubModule> {
  }

  public NoSubModule() {
    initModuleWidget(uiBinder.createAndBindUi(this));
    updateTools(tools);
  }


  @UiField
  HorizontalPanel tools;

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }
}

package cn.mapway.ui.client.modules.test;

import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.modules.baidu.BaiduModule;
import cn.mapway.ui.client.modules.daywork.DayWorkModule;
import cn.mapway.ui.client.mvc.IModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.mvc.ModuleParameter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;



@ModuleMarker(value = TestSubModule.MODULE_CODE, name = "测试子模块")
public class TestSubModule extends AbstractModule {

  private static TestSubModuleUiBinder uiBinder = GWT.create(TestSubModuleUiBinder.class);

  interface TestSubModuleUiBinder extends UiBinder<Widget, TestSubModule> {
  }

  public static final String MODULE_CODE = "TEST_SUB_MODULE";

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }

  public TestSubModule() {

    registerSubModule(BaiduModule.MODULE_CODE, true);
    registerSubModule(DayWorkModule.MODULE_CODE, true);
    registerSubModule(NoToolsModule.MODULE_CODE, true);
    registerSubModule(SubWithNavi.MODULE_CODE, true);
    registerSubModule(NoSubModule.MODULE_CODE, true);
    registerSubModule(ItemTest.MODULE_CODE, true);
    registerSubModule(TestDashBoard.MODULE_CODE, true);
    initModuleWidget(uiBinder.createAndBindUi(this));
  }



  @Override
  public boolean initialize(IModule parentModule, ModuleParameter parameters) {
    boolean b = super.initialize(parentModule, parameters);

    if (b) {
      TextBox t = new TextBox();
      updateTools(t);
    }
    return b;
  }
}

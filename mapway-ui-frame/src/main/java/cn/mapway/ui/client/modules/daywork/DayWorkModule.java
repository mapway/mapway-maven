package cn.mapway.ui.client.modules.daywork;

import cn.mapway.ui.client.mvc.BaseAbstractModule;
import cn.mapway.ui.client.mvc.IModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.mvc.ModuleParameter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

@ModuleMarker(value = DayWorkModule.MODULE_CODE, name = "空模块", summary = "--==--")
public class DayWorkModule extends BaseAbstractModule {

  public static final String MODULE_CODE = "MC_DAYWORK";

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }

  private static DayWorkModuleUiBinder uiBinder = GWT.create(DayWorkModuleUiBinder.class);

  interface DayWorkModuleUiBinder extends UiBinder<Widget, DayWorkModule> {
  }

  public DayWorkModule() {
    initWidget(uiBinder.createAndBindUi(this));

  }

  @Override
  public boolean initialize(IModule parentModule, ModuleParameter parameters) {
    boolean b = super.initialize(parentModule, parameters);

    return b;
  }

  @Override
  public Widget getRootWidget() {
    return this;
  }

}

package cn.mapway.ui.client.modules.common;

import cn.mapway.ui.client.mvc.BaseAbstractModule;
import cn.mapway.ui.client.mvc.ModuleMarker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

@ModuleMarker(value = NullModule.MODULE_CODE, name = "空模块")
public class NullModule extends BaseAbstractModule {

  public final static String MODULE_CODE = "NULL_MODULE";

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }

  private static NullModuleUiBinder uiBinder = GWT.create(NullModuleUiBinder.class);

  interface NullModuleUiBinder extends UiBinder<Widget, NullModule> {
  }

  public NullModule() {
    initModuleWidget(uiBinder.createAndBindUi(this));
  }

}

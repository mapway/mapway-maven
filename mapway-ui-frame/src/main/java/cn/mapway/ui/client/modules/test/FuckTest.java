package cn.mapway.ui.client.modules.test;

import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.mvc.ModuleMarker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

@ModuleMarker(value = FuckTest.MODULE_CODE, name = "FUCK模块")
public class FuckTest extends AbstractModule {

  private static FuckTestUiBinder uiBinder = GWT.create(FuckTestUiBinder.class);

  interface FuckTestUiBinder extends UiBinder<Widget, FuckTest> {
  }

  public FuckTest() {
    initModuleWidget(uiBinder.createAndBindUi(this));
  }

  public static final String MODULE_CODE = "MC_FUCK";

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }


  @UiField
  HorizontalPanel tools;

  @UiHandler("btn")
  void btn(ClickEvent ev) {
    updateTools(tools);
  }
}

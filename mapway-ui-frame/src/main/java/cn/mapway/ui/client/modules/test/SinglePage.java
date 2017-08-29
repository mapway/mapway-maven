package cn.mapway.ui.client.modules.test;

import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.mvc.ModuleMarker;

import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

@ModuleMarker(value = SinglePage.MODULE_CODE, name = "单页面模块")
public class SinglePage extends AbstractModule {

  public SinglePage() {
    super();
  }

  public static final String MODULE_CODE = "MC_SINGLE_MODULE";

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }

  Widget content;

  @Override
  public Widget getRootWidget() {
    return content;
  }

  @UiChild
  void addContent(Widget w) {
    content = w;
  }

}

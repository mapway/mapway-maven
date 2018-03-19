package cn.mapway.ui.client.modules.test;

import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.mvc.ModuleMarker;

import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

/**
 * The type Single page.
 */
@ModuleMarker(value = SinglePage.MODULE_CODE, group = "/测试", name = "单页面模块")
public class SinglePage extends AbstractModule {

    /**
     * Instantiates a new Single page.
     */
    public SinglePage() {
    super();
  }

    /**
     * The constant MODULE_CODE.
     */
    public static final String MODULE_CODE = "MC_SINGLE_MODULE";

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }

    /**
     * The Content.
     */
    Widget content;

  @Override
  public Widget getRootWidget() {
    return content;
  }

    /**
     * Add content.
     *
     * @param w the w
     */
    @UiChild
  void addContent(Widget w) {
    content = w;
  }

}

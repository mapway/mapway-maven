package cn.mapway.ui.client.modules.test;

import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.modules.baidu.BaiduModule;
import cn.mapway.ui.client.mvc.ModuleMarker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

/**
 * The type Sub with navi.
 */
@ModuleMarker(value = SubWithNavi.MODULE_CODE, group = "/测试", name = "子模块带导航")
public class SubWithNavi extends AbstractModule {

    /**
     * The constant MODULE_CODE.
     */
    public final static String MODULE_CODE = "SUB_WITH_NAVI";
  private static SubWithNaviUiBinder uiBinder = GWT.create(SubWithNaviUiBinder.class);

    /**
     * The interface Sub with navi ui binder.
     */
    interface SubWithNaviUiBinder extends UiBinder<Widget, SubWithNavi> {
  }

    /**
     * Instantiates a new Sub with navi.
     */
    public SubWithNavi() {

    registerSubModule(BaiduModule.MODULE_CODE, true);
    initModuleWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }

}

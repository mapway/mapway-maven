package cn.mapway.ui.client.modules.web;

import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.mvc.IModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.mvc.ModuleParameter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;


/**
 * The Class WebPageModule.
 */
@ModuleMarker(value = WebPageModule.MODULE_CODE, name = "网页模块")
public class WebPageModule extends AbstractModule {

  public final static String MODULE_CODE = "MC_WEB";

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }

  /** The frame. */
  @UiField
  Frame frame;


  @Override
  public boolean initialize(IModule parentModule, ModuleParameter parameters) {
    boolean b = super.initialize(parentModule, parameters);
    String p = (String) parameters.get();
    if (p != null && p.length() > 0) {
      String low = p.toLowerCase();
      if (low.startsWith("http")) {
        frame.setUrl(p);
      } else {
        frame.setUrl(GWT.getModuleBaseURL() + "../" + p);
      }
    }

    String caption = (String) parameters.get("CAPTION");
    if (caption != null && caption.length() > 0) {
      setCaption(caption);
    }

    return b;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.ennwifi.datahub.monitor.ui.client.module.IFrameModule#unInitialize()
   */
  @Override
  public void unInitialize() {

  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.ennwifi.datahub.monitor.ui.client.module.IFrameModule#getRootWidget()
   */
  @Override
  public Widget getRootWidget() {
    return this;
  }

  /** The ui binder. */
  private static WebPageModuleUiBinder uiBinder = GWT.create(WebPageModuleUiBinder.class);

  /**
   * The Interface WebPageModuleUiBinder.
   */
  interface WebPageModuleUiBinder extends UiBinder<Widget, WebPageModule> {
  }

  /**
   * Instantiates a new web page module.
   */
  public WebPageModule() {
    initModuleWidget(uiBinder.createAndBindUi(this));
    frame.getElement().setAttribute("frameborder", "no");
  }
}

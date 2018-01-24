package cn.ennwifi.webframe.ui.client.modules.test;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.modules.AbstractModuleWithEvent;
import cn.mapway.ui.client.mvc.IModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.mvc.ModuleParameter;
import cn.mapway.ui.client.widget.editor.xeditor.RichEditorBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

/**
 * 测试组件模块
 * @author zhangjianshe
 *
 */
@ModuleMarker(value = TestModule.MODULE_CODE, name = "组件测试模块", group = "/系统/测试")
public class TestModule extends AbstractModuleWithEvent {

  public final static String MODULE_CODE = "MC_TEST_COMPONENT";

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }

  private static TestModuleUiBinder uiBinder = GWT.create(TestModuleUiBinder.class);

  interface TestModuleUiBinder extends UiBinder<Widget, TestModule> {
  }

  @UiField
  RichEditorBox editor;

  public TestModule() {
    initModuleWidget(uiBinder.createAndBindUi(this));
    editor.setImagePrefix(ClientContext.getContext().getConfigure().getImagePrefix());
    editor.setUploadAction(ClientContext.getContext().getImageUploadAction());
  }


  @Override
  public boolean initialize(IModule parentModule, ModuleParameter parameters) {
    boolean b = super.initialize(parentModule, parameters);

    editor.setValue("<b> wangmingli</b>");
    return b;
  }

  @UiHandler("btn")
  void onClick(ClickEvent e) {
    ClientContext.getContext().confirm(editor.getValue());
  }
}

package cn.ennwifi.webframe.ui.client.modules.authority.authoritydefine;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

import cn.ennwifi.webframe.ui.shared.repository.S_RESOURCEObj;
import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.mvc.IModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.mvc.ModuleParameter;


/**
 * 功能点定义.
 *
 * @author zhangjianshe
 */
@ModuleMarker(value = AuthorityDefine.MODULE_CODE, name = "资源定义", icon = "ad.png")
public class AuthorityDefine extends AbstractModule {
  public final static String MODULE_CODE = "MC_AUTHORITY_DEFINE";

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }


  @Override
  public boolean initialize(IModule parentModule, ModuleParameter parameters) {
    boolean b = super.initialize(parentModule, parameters);
    updateTools(editAuthority.tools);
    return b;
  }

  /** The tree authority. */
  @UiField
  AuthorityTree treeAuthority;

  /** The edit authority. */
  @UiField
  EditAuthority editAuthority;



  /** The ui binder. */
  private static AuthorityDefineUiBinder uiBinder = GWT.create(AuthorityDefineUiBinder.class);

  /**
   * The Interface AuthorityDefineUiBinder.
   */
  interface AuthorityDefineUiBinder extends UiBinder<Widget, AuthorityDefine> {}

  /**
   * Instantiates a new authority define.
   */
  public AuthorityDefine() {
    initModuleWidget(uiBinder.createAndBindUi(this));
    initEvent();
  }



  /**
   * Inits the event.
   */
  private void initEvent() {
    treeAuthority.addMessageHandler(new MessageHandler() {

      @Override
      public void onMessage(Object sender, Integer message, Object value) {
        if (message == MessageEvent.ITEMCLICK) {
          S_RESOURCEObj obj = (S_RESOURCEObj) value;
          editAuthority.editAuthority(obj);
        }
      }
    });

    editAuthority.addMessageHandler(new MessageHandler() {

      @Override
      public void onMessage(Object sender, Integer message, Object value) {
        if (message == MessageEvent.REFRESH) {
          treeAuthority.refresh();
        }
      }
    });
  }

}

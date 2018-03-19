package cn.ennwifi.webframe.ui.client.modules.authority.roleauthority;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.modules.authority.authoritydefine.AuthorityTree;
import cn.ennwifi.webframe.ui.client.modules.authority.role.EditRole;
import cn.ennwifi.webframe.ui.client.modules.authority.role.RoleList;
import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.module.Pair;
import cn.ennwifi.webframe.ui.shared.repository.S_ROLEObj;
import cn.ennwifi.webframe.ui.shared.repository.S_ROLE_RESOURCEObj;
import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.widget.common.ButtonEx;
import cn.mapway.ui.client.widget.common.DataHolder;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * 角色授权框架.
 *
 * @author zhangjianshe
 */
@ModuleMarker(value = RoleAuthorityFrame.MC_ADMIN_ROLE_RESOURCE, group = "/系统",name = "资源授权", icon = "raf.png")
public class RoleAuthorityFrame extends AbstractModule {

    /**
     * The constant MC_ADMIN_ROLE_RESOURCE.
     */
    public final static String MC_ADMIN_ROLE_RESOURCE = "MC_ADMIN_ROLE_RESOURCE";


  @Override
  public String getModuleCode() {
    return MC_ADMIN_ROLE_RESOURCE;
  }


  /** The ui binder. */
  private static RoleAuthorityFrameUiBinder uiBinder = GWT.create(RoleAuthorityFrameUiBinder.class);

    /**
     * The Interface RoleAuthorityFrameUiBinder.
     */
    interface RoleAuthorityFrameUiBinder extends UiBinder<Widget, RoleAuthorityFrame> {
  }

  /** The tree handler. */
  private MessageHandler treeHandler = new MessageHandler() {

    @Override
    public void onMessage(Object sender, Integer message, Object value) {
      if (message == AuthorityTree.DATA_LOADED) {
        tree.resetEnabled(false);
      } else if (message == MessageEvent.VALUECHANGE) {

      }
    }
  };

    /**
     * The m role.
     */
    S_ROLEObj mRole = null;

  /** The list handler. */
  private MessageHandler listHandler = new MessageHandler() {

    @Override
    public void onMessage(Object sender, Integer message, Object value) {
      if (message == MessageEvent.ITEMCLICK) {
        DataHolder holder = (DataHolder) value;
        S_ROLEObj role = (S_ROLEObj) holder.getData();
        mRole = role;
        fetchRoleResource();
        btnDeleteRole.setEnabled(true);
        btnModifyRole.setEnabled(true);
        btnSaveRoleResource.setEnabled(true);

      }
    }
  };

    /**
     * On save role resource.
     *
     * @param e the e
     */
    @UiHandler("btnSaveRoleResource")
  void onSaveRoleResource(ClickEvent e) {

    List<Pair<Long, String>> items = tree.getCheckedItem();

    List<Long> ids = new ArrayList<Long>();
    for (Pair<Long, String> i : items) {
      ids.add(i.key);
    }
    WebFrameProxy.get().updateRoleResource(mRole.getId(), ids, new AsyncCallback<Boolean>() {

      @Override
      public void onSuccess(Boolean result) {
        ClientContext.getContext().confirm("操作提示", "保存角色权限成功", null);
      }

      @Override
      public void onFailure(Throwable caught) {
        ClientContext.getContext().confirm("操作提示", "保存角色权限失败", null);
      }
    });
  }


    /**
     * Instantiates a new role authority frame.
     */
    public RoleAuthorityFrame() {
    initModuleWidget(uiBinder.createAndBindUi(this));
    tree.enableCheckBox(true);
    tree.addMessageHandler(treeHandler);
    list.addMessageHandler(listHandler);

    btnAddRole.setEnabled(true);
    btnModifyRole.setEnabled(false);
    btnDeleteRole.setEnabled(false);
    btnSaveRoleResource.setEnabled(false);

  }

    /**
     * The Tools.
     */
    @UiField
  HorizontalPanel tools;


  @Override
  public boolean initialize(cn.mapway.ui.client.mvc.IModule parentModule,
      cn.mapway.ui.client.mvc.ModuleParameter parameters) {
    boolean b = super.initialize(parentModule, parameters);
    updateTools(tools);
    return b;
  };

    /**
     * The Edit role.
     */
    EditRole editRole;

  private MessageHandler editorHandler = new MessageHandler() {

    @Override
    public void onMessage(Object sender, Integer message, Object value) {
      if (message == MessageEvent.SAVE) {
        list.fetchData();
      }
    }
  };

    /**
     * On add role clicked.
     *
     * @param ev the ev
     */
    @UiHandler("btnAddRole")
  void onAddRoleClicked(ClickEvent ev) {

    sureEditRole();

    editRole.show();
    editRole.center();
    editRole.edit(null);
  }

    /**
     * On modify role clicked.
     *
     * @param ev the ev
     */
    @UiHandler("btnModifyRole")
  void onModifyRoleClicked(ClickEvent ev) {

    sureEditRole();
    editRole.show();
    editRole.center();
    editRole.edit(mRole);
  }

  private void sureEditRole() {
    if (editRole == null) {
      editRole = new EditRole();
      editRole.addMessageHandler(editorHandler);
    }
  }

    /**
     * On delete role clicked.
     *
     * @param ev the ev
     */
    @UiHandler("btnDeleteRole")
  void onDeleteRoleClicked(ClickEvent ev) {
    ClientContext.getContext().confirm("操作提示", "确定删除该角色？", new Callback<Void, Void>() {

      @Override
      public void onSuccess(Void result) {

        WebFrameProxy.get().deleteAdminRole(mRole.getId(), new AsyncCallback<Boolean>() {

          @Override
          public void onSuccess(Boolean result) {
            ClientContext.getContext().confirm("操作提示", "删除成功", new Callback<Void, Void>() {

              @Override
              public void onFailure(Void reason) {}

              @Override
              public void onSuccess(Void result) {
                list.fetchData();
              }
            });
          }

          @Override
          public void onFailure(Throwable caught) {
            ClientContext.getContext().processFailure(caught);
            message(caught.getMessage());
          }
        });
      }

      @Override
      public void onFailure(Void reason) {}

    });
  }

    /**
     * Fetch role authority.
     */
    protected void fetchRoleResource() {
    if (mRole != null) {
      tree.resetEnabled(true);
      WebFrameProxy.get().getRoleResource(mRole.getId(),
          new AsyncCallback<List<S_ROLE_RESOURCEObj>>() {

            @Override
            public void onSuccess(List<S_ROLE_RESOURCEObj> result) {

              tree.resetChecked(false);
              for (S_ROLE_RESOURCEObj a : result) {
                tree.enableAuthorityChecked(a.getRes_id(), true);
              }
            }

            @Override
            public void onFailure(Throwable caught) {
              message(caught.getMessage());
            }
          });
    }
  }


    /**
     * The list.
     */
    @UiField
  RoleList list;

    /**
     * The tree.
     */
    @UiField
  AuthorityTree tree;

    /**
     * The Btn add role.
     */
    @UiField
  ButtonEx btnAddRole;

    /**
     * The Btn modify role.
     */
    @UiField
  ButtonEx btnModifyRole;

    /**
     * The Btn delete role.
     */
    @UiField
  ButtonEx btnDeleteRole;

    /**
     * The Btn save role resource.
     */
    @UiField
  ButtonEx btnSaveRoleResource;

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.Widget#onLoad()
   */
  @Override
  protected void onLoad() {
    super.onLoad();
    list.fetchData();
  }


}

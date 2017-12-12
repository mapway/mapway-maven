package cn.ennwifi.webframe.ui.client.modules.authority.user;

import java.util.List;

import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.event.EventTopics;
import cn.ennwifi.webframe.ui.client.modules.AbstractModuleWithEvent;
import cn.ennwifi.webframe.ui.shared.module.PagerData;
import cn.ennwifi.webframe.ui.shared.module.SearchReq;
import cn.ennwifi.webframe.ui.shared.repository.S_USERObj;
import cn.mapway.ui.client.mvc.IModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.mvc.ModuleParameter;
import cn.mapway.ui.client.widget.common.ButtonEx;
import cn.mapway.ui.client.widget.common.LabelEx;
import cn.mapway.ui.client.widget.common.TableEx;


/**
 * 用戶管理框架.
 *
 * @author zhangjianshe
 */
@ModuleMarker(value = UserFrame.MODULE_CODE, name = "用户管理", icon = "uf.png")
public class UserFrame extends AbstractModuleWithEvent {

  public final static String MODULE_CODE = "MC_USER_MANAGER";

  @Override
  public String getModuleCode() {
    return MODULE_CODE;
  }

  /** The ui binder. */
  private static UserFrameUiBinder uiBinder = GWT.create(UserFrameUiBinder.class);

  /**
   * The Interface UserFrameUiBinder.
   */
  interface UserFrameUiBinder extends UiBinder<Widget, UserFrame> {}



  /**
   * Instantiates a new user frame.
   */
  public UserFrame() {
    initModuleWidget(uiBinder.createAndBindUi(this));
    registerEventHandler(EventTopics.SAVE_USERINFO);
    sectionHeader.setText("用户列表");
    searchUser();
  }

  @Override
  public boolean initialize(IModule parentModule, ModuleParameter parameters) {
    boolean b = super.initialize(parentModule, parameters);
    updateTools(btnCreate);
    return b;
  }

  /** The btn create. */
  @UiField
  ButtonEx btnCreate;

  /** The m data. */
  List<S_USERObj> mData;

  /** The table. */
  @UiField
  TableEx table;

  /** The section header. */
  @UiField
  LabelEx sectionHeader;

  /** The m editor. */
  UserEditor mEditor;

  /*
   * (non-Javadoc)
   * 
   * @see cn.ennwifi.datahub.monitor.ui.client.component.BaseComposite#onEvent(java.lang.String,
   * int, java.lang.Object)
   */
  @Override
  public void onEvent(String topic, int type, Object event) {
    super.onEvent(topic, type, event);
    if (EventTopics.SAVE_USERINFO.equals(topic)) {
      searchUser();
    }
  }



  // TODO 增加搜索按钮
  /**
   * 加载用户列表数据.
   */
  public void searchUser() {

    WebFrameProxy.get().searchUserList(new SearchReq(), new AsyncCallback<PagerData<S_USERObj>>() {

      @Override
      public void onSuccess(PagerData<S_USERObj> result) {
        mData = result.getData();
        refreshView();
      }

      @Override
      public void onFailure(Throwable caught) {
        ClientContext.getContext().processFailure(caught);
      }
    });
  }

  /** The edit click handler. */
  private ClickHandler editClickHandler = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {
      ButtonEx btn = (ButtonEx) event.getSource();
      S_USERObj user = (S_USERObj) btn.getData();
      sureDialog();
      mEditor = new UserEditor();
      mEditor.edit(user);
      mEditor.show();
      mEditor.center();
      if ("0".equals(user.getAccount_type())) {
        mEditor.editEnable();
      }
    }
  };

  /** The dlg user role. */
  UserRoleDialog dlgUserRole;

  /** The user role handler. */
  protected MessageHandler userRoleHandler = new MessageHandler() {

    @Override
    public void onMessage(Object sender, Integer message, Object value) {

    }
  };

  /** The role click handler. */
  private ClickHandler roleClickHandler = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {
      ButtonEx btn = (ButtonEx) event.getSource();
      S_USERObj user = (S_USERObj) btn.getData();

      if (dlgUserRole == null) {
        dlgUserRole = new UserRoleDialog();
        dlgUserRole.addMessageHandler(userRoleHandler);
      }
      dlgUserRole.show();
      dlgUserRole.showUserRole(user.getId());
    }
  };



  /**
   * Refresh view.
   */
  protected void refreshView() {
    table.removeAllRows();
    int row = 0;
    int column = 0;

    column = 0;
    table.setText(row, column++, "ID#");
    table.setText(row, column++, "账户类型");
    table.setText(row, column++, "头像");
    table.setText(row, column++, "登录名");
    table.setText(row, column++, "性别");
    table.setText(row, column++, "真实名称");

    table.setText(row, column++, "邮箱");
    table.setText(row, column++, "电话");
    table.setText(row, column++, "编辑");
    table.setText(row, column++, "角色");

    if (mData != null) {
      row = 1;
      for (S_USERObj u : mData) {
        column = 0;
        table.setText(row, column++, "" + u.getId());
        table.setText(row, column++, "0".equals(u.getAccount_type()) ? "集团账号" : "注册用户");
        table.setText(row, column++, "");
        table.setText(row, column++, u.getName());
        table.setText(row, column++, u.getGender());
        table.setText(row, column++, u.getReal_name());
        table.setText(row, column++, u.getEmail());
        table.setText(row, column++, u.getMobile());

        ButtonEx btn = new ButtonEx();
        btn.setText("编辑");
        btn.addClickHandler(editClickHandler);
        btn.setData(u);
        table.setWidget(row, column++, btn);

        btn = new ButtonEx();
        btn.setText("设置角色");
        btn.addClickHandler(roleClickHandler);
        btn.setData(u);
        table.setWidget(row, column++, btn);

        row++;
      }
    }
    table.format(true);
  }



  /** The editor handler. */
  private MessageHandler editorHandler = new MessageHandler() {

    @Override
    public void onMessage(Object sender, Integer message, Object value) {
      if (message == MessageEvent.SAVE) {

      }
    }
  };

  /**
   * Sure dialog.
   */
  void sureDialog() {
    if (mEditor == null) {
      mEditor = new UserEditor();
      // mEditor.setGlassEnabled(true);
      mEditor.setAutoHideEnabled(true);
      mEditor.setModal(true);
      mEditor.setText("编辑用户信息");
      mEditor.addMessageHandler(editorHandler);
    }
  }

  /**
   * On create click.
   *
   * @param ev the ev
   */
  @UiHandler("btnCreate")
  void onCreateClick(ClickEvent ev) {
    sureDialog();
    mEditor.edit();
    mEditor.show();
    mEditor.center();
  }
}

package cn.ennwifi.webframe.ui.client.modules.authority.user;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.repository.S_ROLEObj;
import cn.ennwifi.webframe.ui.shared.repository.S_USER_ROLEObj;
import cn.mapway.ui.client.widget.common.CheckBoxEx;
import cn.mapway.ui.client.widget.common.DialogBoxEx;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.SimpleMessage;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

import java.util.HashMap;
import java.util.List;

/**
 * 用户角色对话框.
 *
 * @author zhangjianshe
 */
public class UserRoleDialog extends DialogBoxEx implements HasMessageHandlers {

    /**
     * The ui binder.
     */
    private static UserRoleDialogUiBinder uiBinder = GWT.create(UserRoleDialogUiBinder.class);

    /**
     * The Interface UserRoleDialogUiBinder.
     */
    interface UserRoleDialogUiBinder extends UiBinder<Widget, UserRoleDialog> {
    }

    /**
     * Instantiates a new user role dialog.
     */
    public UserRoleDialog() {
        Widget content = uiBinder.createAndBindUi(this);
        content.setPixelSize(400, 500);
        setWidget(content);
        setText("配置用户角色");
        setGlassEnabled(true);
        setAutoHideEnabled(false);
        center();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * cn.ennwifi.datahub.monitor.ui.client.component.NeDialogBox#addMessageHandler(com.ksyzt.gwt.
     * client.event. MessageHandler)
     */
    @Override
    public HandlerRegistration addMessageHandler(MessageHandler handler) {
        return addHandler(handler, MessageEvent.TYPE);
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets the data.
     *
     * @param data the new data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * The data.
     */
    private Object data;

    /**
     * The content.
     */
    @UiField
    FlowPanel content;

    /**
     * The m roles.
     */
    protected List<S_ROLEObj> mRoles;

    /**
     * The lb message.
     */
    @UiField
    Label lbMessage;

    /**
     * Msg.
     *
     * @param text the text
     */
    private void msg(String text) {
        lbMessage.setText(text);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.DialogBox#show()
     */
    @Override
    public void show() {
        msg("");
        fetchAllRoles();
        super.show();
    }

    /**
     * Fetch all roles.
     */
    private void fetchAllRoles() {
        content.add(new SimpleMessage("加载角色"));
        WebFrameProxy.get().getAllRole(new AsyncCallback<List<S_ROLEObj>>() {

            @Override
            public void onSuccess(List<S_ROLEObj> result) {
                mRoles = result;
                refreshView();
                loadUserRoleData();
            }

            @Override
            public void onFailure(Throwable caught) {
                ClientContext.getContext().processFailure(caught);
                content.clear();
                content.add(new SimpleMessage(caught.getMessage()));
            }
        });
    }

    /**
     * The mapper.
     */
    HashMap<Long, CheckBoxEx> mapper;

    /**
     * The check value changed.
     */
    private ValueChangeHandler<Boolean> checkValueChanged = new ValueChangeHandler<Boolean>() {

        @Override
        public void onValueChange(ValueChangeEvent<Boolean> event) {
            if (mUserId != null) {
                CheckBoxEx check = (CheckBoxEx) event.getSource();
                S_ROLEObj role = (S_ROLEObj) check.getData();

                updateUserRole(mUserId, role.getId(), check.getValue());
            }
        }
    };

    /**
     * Refresh view.
     */
    protected void refreshView() {
        content.clear();
        mapper = new HashMap<Long, CheckBoxEx>();
        for (S_ROLEObj role : mRoles) {
            HorizontalPanel hp = new HorizontalPanel();
            CheckBoxEx check = new CheckBoxEx();
            check.setData(role);
            hp.add(check);
            check.addValueChangeHandler(checkValueChanged);
            content.add(hp);
            hp.add(new Label(role.getName()));
            mapper.put(role.getId(), check);
        }
    }

    /**
     * 更新用户角色数据.
     *
     * @param mUserId2 the m user id 2
     * @param roleId   the roleid
     * @param value    the value
     */
    protected void updateUserRole(Long mUserId2, Long roleId, Boolean value) {
        msg("更新用户角色数据");
        WebFrameProxy.get().updateAdminUserRole(mUserId2, roleId, value, new AsyncCallback<Boolean>() {

            @Override
            public void onSuccess(Boolean result) {
                msg("更新成功");
            }

            @Override
            public void onFailure(Throwable caught) {
                ClientContext.getContext().processFailure(caught);
                msg(caught.getMessage());
            }
        });
    }

    /**
     * 角色数据是否加载完成.
     */
    boolean roleLoaded = false;

    /**
     * The m user id.
     */
    Long mUserId = null;

    /**
     * 显示用户ID的授权角色.
     *
     * @param userId the userid
     */
    public void showUserRole(Long userId) {
        mUserId = userId;
        if (roleLoaded == true) {
            loadUserRoleData();
        }
    }

    /**
     * Load user role data.
     */
    private void loadUserRoleData() {
        if (mUserId != null) {
            WebFrameProxy.get().getAdminUserRole(mUserId, new AsyncCallback<List<S_USER_ROLEObj>>() {

                @Override
                public void onSuccess(List<S_USER_ROLEObj> result) {
                    resetRoleChecked();
                    refreshUserRole(result);
                }

                @Override
                public void onFailure(Throwable caught) {
                    ClientContext.getContext().processFailure(caught);
                    MessageEvent ev = new MessageEvent(MessageEvent.MESSAGE, caught.getMessage());
                    fireEvent(ev);
                    msg(caught.getMessage());
                }
            });
        }
    }

    /**
     * Refresh user role.
     *
     * @param result the result
     */
    protected void refreshUserRole(List<S_USER_ROLEObj> result) {
        for (S_USER_ROLEObj d : result) {
            CheckBoxEx check = mapper.get(d.getRole_id());
            if (check != null) {
                check.setValue(true);
            }
        }
    }

    /**
     * Reset role checked.
     */
    protected void resetRoleChecked() {
        for (Long key : mapper.keySet()) {
            CheckBoxEx check = mapper.get(key);
            check.setValue(false);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.Widget#fireEvent(com.google.gwt.event.shared.GwtEvent)
     */
    @Override
    public void fireEvent(GwtEvent<?> event) {

    }
}

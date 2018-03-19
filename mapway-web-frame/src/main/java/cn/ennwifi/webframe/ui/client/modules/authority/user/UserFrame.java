package cn.ennwifi.webframe.ui.client.modules.authority.user;

import cn.ennwifi.webframe.ui.client.common.SortableTable;
import cn.ennwifi.webframe.ui.client.data.UserDataProvider;
import cn.ennwifi.webframe.ui.client.event.EventTopics;
import cn.ennwifi.webframe.ui.client.modules.AbstractModuleWithEvent;
import cn.ennwifi.webframe.ui.shared.module.SearchReq;
import cn.ennwifi.webframe.ui.shared.repository.S_USERObj;
import cn.mapway.ui.client.mvc.IModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.mvc.ModuleParameter;
import cn.mapway.ui.client.widget.common.ButtonEx;
import cn.mapway.ui.client.widget.common.LabelEx;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

import java.util.List;

/**
 * 用戶管理框架.
 *
 * @author zhangjianshe
 */
@ModuleMarker(value = UserFrame.MODULE_CODE,group = "/系统", name = "用户管理", icon = "uf.png")
public class UserFrame extends AbstractModuleWithEvent {

    /**
     * The Data provider.
     */
    UserDataProvider dataProvider;
    private MessageHandler dataProviderHandler = new MessageHandler() {

        @Override
        public void onMessage(Object sender, Integer message, Object value) {
            if (message == MessageEvent.EDIT) {

                S_USERObj user = (S_USERObj) value;
                sureDialog();
                mEditor = new UserEditor();
                mEditor.edit(user);
                mEditor.show();
                mEditor.center();
                if ("0".equals(user.getAccount_type())) {
                    mEditor.editEnable();
                }

            } else if (message == UserDataProvider.ROLE_EDITOR) {
                S_USERObj user = (S_USERObj) value;

                if (dlgUserRole == null) {
                    dlgUserRole = new UserRoleDialog();
                    dlgUserRole.addMessageHandler(userRoleHandler);
                }
                dlgUserRole.show();
                dlgUserRole.showUserRole(user.getId());

            }
        }
    };
    /**
     * The constant MODULE_CODE.
     */
    public final static String MODULE_CODE = "MC_USER_MANAGER";

    @Override
    public String getModuleCode() {
        return MODULE_CODE;
    }

    /**
     * The ui binder.
     */
    private static UserFrameUiBinder uiBinder = GWT.create(UserFrameUiBinder.class);

    /**
     * The Interface UserFrameUiBinder.
     */
    interface UserFrameUiBinder extends UiBinder<Widget, UserFrame> {
    }

    /**
     * Instantiates a new user frame.
     */
    public UserFrame() {
        initModuleWidget(uiBinder.createAndBindUi(this));
        registerEventHandler(EventTopics.SAVE_USERINFO);
        sectionHeader.setText("用户列表");
        dataProvider = new UserDataProvider();
        dataProvider.addMessageHandler(dataProviderHandler);

        table.setDataProvider(dataProvider);
        table.addValueChangeHandler(new ValueChangeHandler<SearchReq>() {

            @Override
            public void onValueChange(ValueChangeEvent<SearchReq> event) {
                dataProvider.loadData(event.getValue());
            }
        });
    }

    @Override
    public boolean initialize(IModule parentModule, ModuleParameter parameters) {
        boolean b = super.initialize(parentModule, parameters);
        updateTools(btnCreate);
        dataProvider.loadData(null);
        return b;
    }

    /**
     * The btn create.
     */
    @UiField
    ButtonEx btnCreate;

    /**
     * The m data.
     */
    List<S_USERObj> mData;

    /**
     * The table.
     */
    @UiField
    SortableTable table;

    /**
     * The section header.
     */
    @UiField
    LabelEx sectionHeader;

    /**
     * The m editor.
     */
    UserEditor mEditor;

    /**
     * The dlg user role.
     */
    UserRoleDialog dlgUserRole;

    /**
     * The user role handler.
     */
    protected MessageHandler userRoleHandler = new MessageHandler() {

        @Override
        public void onMessage(Object sender, Integer message, Object value) {

        }
    };

    /**
     * The editor handler.
     */
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

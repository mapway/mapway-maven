package cn.ennwifi.webframe.ui.client.modules.authority.login;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.event.EventTopics;
import cn.ennwifi.webframe.ui.client.modules.BaseAbstractModuleWithEvent;
import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.module.AdminLoginResponse;
import cn.ennwifi.webframe.ui.shared.module.UserLoginType;
import cn.mapway.ui.client.mvc.IModule;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.mvc.ModuleParameter;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * 登录页面.
 *
 * @author zhangjianshe
 */
@ModuleMarker(value = LoginModule.MODULE_CODE, name = "登录模块", group = "/系统")
public class LoginModule extends BaseAbstractModuleWithEvent {
    /**
     * The constant MODULE_CODE.
     */
    public final static String MODULE_CODE = "MC_LOING";

    @Override
    public String getModuleCode() {
        return MODULE_CODE;
    }

    /**
     * The constant USER_TOKEN.
     */
    public static final String USER_TOKEN = "token";
    /**
     * The constant USER_NAME.
     */
    public static final String USER_NAME = "userName";
    /**
     * The constant USER_TYPE.
     */
    public static final String USER_TYPE = "loginType";

    /**
     * The ui binder.
     */
    private static LoginModuleUiBinder uiBinder = GWT.create(LoginModuleUiBinder.class);

    /**
     * The Interface LoginModuleUiBinder.
     */
    interface LoginModuleUiBinder extends UiBinder<Widget, LoginModule> {
    }

    /**
     * The txt USERNAME.
     */
    @UiField
    TextBox txtUserName;

    /**
     * The txt PASSWORD.
     */
    @UiField
    PasswordTextBox txtPassword;

    /**
     * The Resource root id.
     */
    Integer resourceRootId = null;

    /**
     * Instantiates a new login page.
     */
    public LoginModule() {
        initModuleWidget(uiBinder.createAndBindUi(this));
        this.setStyleName("home-LoginPage");

        initPage();
        showUserInfo(); // 显示用户信息
        txtUserName.addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    txtPassword.setFocus(true);
                }
            }
        });
        txtPassword.addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    checkLogin(UserLoginType.USER_LOGIN_TYPE_REGISTER);
                }
            }
        });
    }

    @Override
    public boolean initialize(IModule parentModule, ModuleParameter parameter) {
        String rrid = (String) parameter.get("RESOURCE_ROOT_ID");
        if (rrid != null) {
            resourceRootId = Integer.parseInt(rrid);
        }
        Boolean b = super.initialize(parentModule, parameter);

        return b;
    }

    /**
     * 初始化页面.
     */
    public void initPage() {

    }

    /**
     * The login click handler.
     */
    private ClickHandler loginClickHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            checkLogin(UserLoginType.USER_LOGIN_TYPE_LDAP);

        }
    };

//  @UiHandler("btnLogin")
//  void ldapLogin(ClickEvent ev) {
//    checkLogin(UserLoginType.USER_LOGIN_TYPE_LDAP);
//  }

    /**
     * Custom logoin.
     *
     * @param ev the ev
     */
    @UiHandler("btnCustomLogin")
    void customLogoin(ClickEvent ev) {
        checkLogin(UserLoginType.USER_LOGIN_TYPE_REGISTER);
    }

    /**
     * Check login.
     *
     * @param type the type
     */
    protected void checkLogin(String type) {
        if (txtUserName.getValue().length() == 0) {
            msg("请输入用户名");
            return;
        }
        if (txtPassword.getValue().length() == 0) {
            msg("输入密码");
            return;
        }
        msg("认证中...");

        WebFrameProxy.get().adminLogin(resourceRootId, txtUserName.getValue(), txtPassword.getValue(), type, loginResponseHandler);
    }

    /**
     * The login response handler.
     */
    private AsyncCallback<AdminLoginResponse> loginResponseHandler = new AsyncCallback<AdminLoginResponse>() {

        @Override
        public void onSuccess(AdminLoginResponse result) {

            ClientContext context = ClientContext.getContext();

            processClientContext(result, context);
            saveUserInfo();
            postTopic(EventTopics.LOGIN, 0);
        }

        @Override
        public void onFailure(Throwable caught) {
            msg("登录出了点问题:<");
        }
    };

    /**
     * 检查TOKEN
     *
     * @param resourceRootId the resource root id
     * @param handler        the handler
     */
    public static void checkUserToken(Integer resourceRootId, final Callback<Boolean, String> handler) {
        Storage localStorage = Storage.getLocalStorageIfSupported(); // 获取存储对象

        if (localStorage.getItem(USER_TOKEN) == null || localStorage.getItem(USER_TOKEN).equals("")) {
            handler.onFailure("");
        } else {
            WebFrameProxy.get().getUserByToken(resourceRootId, localStorage.getItem(USER_TOKEN), new AsyncCallback<AdminLoginResponse>() {

                @Override
                public void onSuccess(AdminLoginResponse result) {
                    GWT.log("login success ");
                    if (result.user == null) {
                        handler.onFailure("");
                    } else {
                        ClientContext context = ClientContext.getContext();
                        processClientContext(result, context);
                        handler.onSuccess(true);
                    }
                }

                @Override
                public void onFailure(Throwable caught) {
                    GWT.log(caught.getMessage());
                    ClientContext.getContext().processFailure(caught);
                    handler.onFailure(caught.getMessage());
                }
            });
        }
    }

    /**
     * @param result
     * @param context
     */
    private static void processClientContext(AdminLoginResponse result, ClientContext context) {
        context.setConfigure(result.configure);
        context.setUser(result.user);
        context.setResources(result.authorities);
    }

    private void saveUserInfo() {
        Storage localStorage = Storage.getLocalStorageIfSupported(); // 获取存储对象
        localStorage.setItem(USER_NAME, txtUserName.getValue()); // 存储用户名
        localStorage.setItem(USER_TOKEN, ClientContext.getContext().getUser().getToken()); // 存储token
        GWT.log(ClientContext.getContext().getUser().getToken());
        localStorage.setItem(USER_TYPE, ClientContext.getContext().getUser().getAccount_type().toString()); // 存储登录类型

    }

    private void showUserInfo() {

        Storage localStorage = Storage.getLocalStorageIfSupported(); // 获取存储对象
        String userName = localStorage.getItem(USER_NAME);
        if (userName != null) {
            txtUserName.setValue(userName);
        }

    }

    /**
     * The Lb message.
     */
    @UiField
    Label lbMessage;

    /**
     * Msg.
     *
     * @param msg the msg
     */
    public void msg(String msg) {
        lbMessage.setText(msg);
    }

    /**
     * Clear data.
     */
    public void clearData() {
        txtPassword.setValue("");
    }

}

package cn.ennwifi.webframe.ui.client.main;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.common.JsonParseError;
import cn.ennwifi.webframe.ui.client.common.Links;
import cn.ennwifi.webframe.ui.client.event.EventTopics;
import cn.ennwifi.webframe.ui.client.main.menu.MainMenu;
import cn.ennwifi.webframe.ui.client.modules.BaseAbstractModuleWithEvent;
import cn.ennwifi.webframe.ui.client.modules.authority.user.UserEditor;
import cn.ennwifi.webframe.ui.client.modules.dashboard.DashBoardModule;
import cn.ennwifi.webframe.ui.client.resource.SysResource;
import cn.ennwifi.webframe.ui.client.rpc.WebFrameProxy;
import cn.ennwifi.webframe.ui.shared.module.ClientConfigure;
import cn.ennwifi.webframe.ui.shared.module.PagerData;
import cn.ennwifi.webframe.ui.shared.repository.S_RESOURCEObj;
import cn.ennwifi.webframe.ui.shared.repository.S_USERObj;
import cn.mapway.ui.client.history.HistoryManager;
import cn.mapway.ui.client.modules.web.WebPageModule;
import cn.mapway.ui.client.mqtt.IMqttHandler;
import cn.mapway.ui.client.mqtt.MqttJsClient;
import cn.mapway.ui.client.mqtt.event.ConnectionLostEvent;
import cn.mapway.ui.client.mqtt.event.MessageArriveEvent;
import cn.mapway.ui.client.mvc.IModule;
import cn.mapway.ui.client.mvc.IModuleDispatcher;
import cn.mapway.ui.client.mvc.ModuleMarker;
import cn.mapway.ui.client.mvc.ModuleParameter;
import cn.mapway.ui.client.mvc.SwitchModuleData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * 主界面.
 *
 * @author zhangjianshe
 */
@ModuleMarker(value = MainFrame.MODULE_CODE, name = "主窗口", icon = "icon.png")
public class MainFrame extends BaseAbstractModuleWithEvent implements IMqttHandler,
        IModuleDispatcher {

    /**
     * The constant MODULE_CODE.
     */
    public final static String MODULE_CODE = "MC_MAIN_FRAME";
    /**
     * mqtt 连接创建
     */
    public static final String MQTT_CONNECTION_ARRIVED = "__MQTT_CONNECTION_ARRIVED__";

    /**
     * mqtt 连接失败
     */
    public static final String MQTT_CONNECTION_LOST = "__MQTT_CONNECTION_LOST__";
    public static final String MQTT_JSON_PARSE_ERROR = "__MQTT_JSON_PARSE_ERROR__";


    @Override
    public String getModuleCode() {
        return MODULE_CODE;
    }

    /**
     * The Constant COMMAND_EXIT.
     */
    private static final int COMMAND_EXIT = 6000;

    /**
     * The Constant COMMAND_USR.
     */
    private static final int COMMAND_USR = 6001;

    /**
     * The ui binder.
     */
    private static MainFrameUiBinder uiBinder = GWT.create(MainFrameUiBinder.class);

    /**
     * The interface Main frame ui binder.
     */
    interface MainFrameUiBinder extends UiBinder<Widget, MainFrame> {
    }

    /**
     * The Links.
     */
    @UiField
    Links links;

    /**
     * The img logo.
     */
    @UiField
    Image imgLogo;

    /**
     * The menu bar.
     */
    @UiField
    MainMenu menuBar;

    /**
     * The root.
     */
    @UiField
    DockLayoutPanel root;

    /**
     * The avator.
     */
    @UiField
    Image imgUser;

    /**
     * The m editor.
     */
    UserEditor mEditor = null;

    /**
     * The current.
     */
    IModule current;

    /**
     * The add message handler.
     */
    HandlerRegistration addMessageHandler;

    /**
     * The lb title.
     */
    @UiField
    Label lbTitle;

    /**
     * Instantiates a new main frame.
     */
    public MainFrame() {
        initModuleWidget(uiBinder.createAndBindUi(this));
        initPage();
        initHistory();
    }

    /**
     * The History manager.
     */
    HistoryManager historyManager;

    private void initHistory() {
        historyManager = HistoryManager.get(this);
    }

    /**
     * Inits the page.
     */
    private void initPage() {
        links.addMessageHandler(linkHandler);
        ClientConfigure configure = ClientContext.getContext().getConfigure();
        if (configure.getLogo().length() > 0) {
            imgLogo.setUrl(configure.getLogo());
        } else {
            imgLogo.setUrl(SysResource.INSTANCE.logo().getSafeUri());
        }
        registerEventHandler(EventTopics.UPLOAD_IMG);
        registerEventHandler(EventTopics.SWITCH_MAIN_FRAME_SUB_MODULE);
        registerEventHandler(EventTopics.CHANGE_TITLE);
        ClientContext.getContext().setMainFrame(this);
        registerEventHandler(EventTopics.CHANGE_MODULE_WITH_DATA);
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.ennwifi.datahub.monitor.ui.client.component.BaseComposite#onEvent(java.lang.String,
     * int, java.lang.Object)
     */
    @Override
    public void onEvent(String topic, int type, Object event) {
        super.onEvent(topic, type, event);
        if (EventTopics.SWITCH_MAIN_FRAME_SUB_MODULE.equals(topic)) {
            S_RESOURCEObj author = (S_RESOURCEObj) event;
            processSubModule(author);
        } else if (EventTopics.UPLOAD_IMG.equals(topic)) {
            refreshView();
        } else if (EventTopics.CHANGE_MODULE_WITH_DATA.equals(topic)) {
            SwitchModuleData d = (SwitchModuleData) event;
            GWT.log("before " + d.toString());
            processSubModuleWithData(d);
            GWT.log("after " + d.toString());

        } else if (EventTopics.CHANGE_TITLE.equals(topic)) {
            String title = (String) event;
            if (current != null || current instanceof WebPageModule) {
                WebPageModule m = (WebPageModule) current;
                m.setCaption(title);
            }
        }
    }

    /**
     * 切换模块with 数据
     *
     * @param d
     */
    private void processSubModuleWithData(SwitchModuleData d) {
        switchModule(d.getModuleCode(), d.getParameters(), true);
        HistoryManager.push(getModulePath(current));
    }

    @Override
    public boolean initialize(IModule parentModule, ModuleParameter parameters) {
        boolean b = super.initialize(parentModule, parameters);
        refreshView();
        String hash = Window.Location.getHash();
        if (hash.length() > 0) {
            if (hash.charAt(0) == '#') {
                hash = hash.substring(1);
            }
        }
        GWT.log("window init with hash>" + hash);
        if (hash != null && hash.length() > 0) {
            HistoryManager.get(this).popup(hash);
        } else {
            switchModule(ClientContext.getContext().getDashboardCode(), null, false);
        }
        return b;
    }

    /**
     * s
     *
     * @param authority
     */
    private void processSubModule(S_RESOURCEObj authority) {
        ModuleParameter paramers = new ModuleParameter();
        paramers.put(authority.getPara());
        switchModule(authority.getCode(), paramers, false);
        HistoryManager.push(getModulePath(current));
    }

    /**
     * 用户登录后更新首页面.
     */
    public void refreshView() {
        removeCurrent();
        addMessageHandler = null;
        current = null;
        initMenuBar();
        updateUser();

    }

    /**
     * The link handler.
     */
    private MessageHandler linkHandler = new MessageHandler() {

        @Override
        public void onMessage(Object sender, Integer message, Object value) {
            switch (message) {
                case COMMAND_EXIT: {
                    if (client != null) {
                        client.unsub(ClientContext.getContext().getMqttTopicPrefix() + "/#");
                    }
                    postTopic(EventTopics.EXIT, 0);
                    break;
                }
                case COMMAND_USR: {
                    showUser();
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * Inits the menu bar.
     */
    private void initMenuBar() {

        WebFrameProxy.get().adminUserMainMenu(new AsyncCallback<PagerData<S_RESOURCEObj>>() {

            @Override
            public void onSuccess(PagerData<S_RESOURCEObj> result) {
                buildMainMenu(result);
            }

            @Override
            public void onFailure(Throwable caught) {
                ClientContext.getContext().processFailure(caught);

            }
        });
    }

    /**
     * 更新用户界面.
     */
    private void updateUser() {
        links.clear();
        ClientContext context = ClientContext.getContext();
        links.addCommand(COMMAND_USR, context.getUser().getReal_name(), null);
        links.addCommand(COMMAND_EXIT, "退出", null);
        Window.setTitle("Hi! " + context.getUser().getReal_name());
        if (context.getUser().getAvator() == null || context.getUser().getAvator() == "") {
            imgUser.setUrl(SysResource.INSTANCE.custom().getSafeUri());
        } else {
            imgUser.setUrl(context.getConfigure().getImagePrefix() + context.getUser().getAvator());
        }
    }

    /**
     * 显示用户信息.
     */
    protected void showUser() {
        sureDialog();
        mEditor.edit(ClientContext.getContext().getUser());
        mEditor.showRelativeTo(links);
    }

    /**
     * Builds the main menu.
     *
     * @param result the result
     */
    protected void buildMainMenu(PagerData<S_RESOURCEObj> result) {
        menuBar.build(result.getData());
    }

    /**
     * The editor handler.
     */
    private MessageHandler editorHandler = new MessageHandler() {

        @Override
        public void onMessage(Object sender, Integer message, Object value) {
            if (message == MessageEvent.SAVE) {
                ClientContext.getContext().setUser((S_USERObj) value);
                mEditor.hide();
                updateUser();
            }
        }
    };

    /**
     * sure dialog.
     */
    void sureDialog() {
        if (mEditor == null) {
            mEditor = new UserEditor();

            if ("1".equals(ClientContext.getContext().getUser().getAccount_type())) {
                mEditor.addMessageHandler(editorHandler);
                mEditor.setGlassEnabled(true);
            } else {
                mEditor.setGlassEnabled(false);

                // mEditor.editEnable();
            }
            mEditor.setModal(true);
            mEditor.setAutoHideEnabled(true);
        }
    }

    /**
     * 切换模块
     */
    @Override
    public IModuleDispatcher switchModule(String code, ModuleParameter parameters,
                                          boolean saveToHistory) {

        if (parameters == null) {
            parameters = new ModuleParameter();
        }
        // 如果是主模块则不处理，直接返回主模块的调度接口
        if (getModuleInfo().code.equals(code)) {
            GWT.log("switch mainframe self>" + code);
            return this;
        }

        IModuleDispatcher d = null;

        IModule module = getModuleFactory().createModule(code, true);

        if (module != current) {
            removeCurrent();
            current = module;
            root.add(current.getRootWidget());
        }
        GWT.log("module parameter size" + parameters.size());
        current.initialize(this, parameters);
        GWT.log("switch mainframe sub module >" + code);

        if (module instanceof IModuleDispatcher) {
            d = (IModuleDispatcher) module;
        }

        return d;
    }

    /**
     * 将当前模块移除.
     */
    private void removeCurrent() {
        if (current != null) {

            current.unInitialize();
            current.getRootWidget().removeFromParent();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * cn.ennwifi.datahub.monitor.ui.client.mqtt.handler.IMqttHandler#onConnectionLost(cn.ennwifi.
     * datahub.monitor.ui.client.mqtt.MqttJsClient,
     * cn.ennwifi.datahub.monitor.ui.client.mqtt.event.ConnectionLostEvent)
     */
    @Override
    public void onConnectionLost(MqttJsClient client, ConnectionLostEvent event) {
        GWT.log("mqtt disconnected:");
        ClientContext.getContext().initMqtt();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * cn.ennwifi.datahub.monitor.ui.client.mqtt.handler.IMqttHandler#onMessageArrived(cn.ennwifi.
     * datahub.monitor.ui.client.mqtt.MqttJsClient,
     * cn.ennwifi.datahub.monitor.ui.client.mqtt.event.MessageArriveEvent)
     */
    @Override
    public void onMessageArrived(MqttJsClient client, MessageArriveEvent event) {
        final String payload = event.msg();
        final String topic = event.topic();
        GWT.log("msg arrived:" + topic + "-" + payload);

        try {
            JSONValue v = JSONParser.parseStrict(payload);
            JSONObject obj = v.isObject();
            ClientContext.getContext().postTopic(topic, 0, obj);
        } catch (Throwable e) {
            GWT.log(e.getMessage());
            JsonParseError error = new JsonParseError();
            error.error = e.getMessage();
            error.source = payload;
            error.topic = topic;
            ClientContext.getContext().postTopic(MQTT_JSON_PARSE_ERROR, 0, error);
        }
    }

    /**
     * 解析JSON错误
     *
     * @param error
     */
    public void onJsonParseError(JsonParseError error) {
    }


    /**
     * The Client.
     */
    MqttJsClient client;

    /*
     * (non-Javadoc)
     *
     * @see
     * cn.ennwifi.datahub.monitor.ui.client.mqtt.handler.IMqttHandler#onConnect(cn.ennwifi.datahub.
     * monitor.ui.client.mqtt.MqttJsClient)
     */
    @Override
    public void onConnect(final MqttJsClient client) {
        GWT.log("connected");
        this.client = client;
        // client.sub("iot/monitor/#");
        // ClientContext.getContext().setMqttTopicPrefix("iot/monitor/");


        postTopic(MQTT_CONNECTION_ARRIVED, 0, client);


    }

    /*
     * (non-Javadoc)
     *
     * @see cn.ennwifi.datahub.monitor.ui.client.mqtt.handler.IMqttHandler#onConnetFailed(cn.ennwifi.
     * datahub.monitor.ui.client.mqtt.MqttJsClient, java.lang.String)
     */
    @Override
    public void onConnetFailed(final MqttJsClient client, String message) {
        GWT.log("connection Failed,reconnect>" + message);
        // 连接失败
        //ClientContext.getContext().initMqtt();


        postTopic(MQTT_CONNECTION_LOST, 0, client);

    }

}

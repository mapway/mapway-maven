package cn.ennwifi.webframe.ui.client;

import cn.ennwifi.webframe.ui.client.event.EventTopics;
import cn.ennwifi.webframe.ui.client.main.MainFrame;
import cn.ennwifi.webframe.ui.client.modules.authority.login.LoginModule;
import cn.ennwifi.webframe.ui.client.tools.Clients;
import cn.ennwifi.webframe.ui.client.tools.Jsons;
import cn.ennwifi.webframe.ui.client.tools.XDM;
import cn.ennwifi.webframe.ui.client.tools.XDM.IOnPostMessage;
import cn.ennwifi.webframe.ui.shared.module.ClientConfigure;
import cn.ennwifi.webframe.ui.shared.repository.S_RESOURCEObj;
import cn.ennwifi.webframe.ui.shared.repository.S_USERObj;
import cn.mapway.ui.client.event.EventBus;
import cn.mapway.ui.client.event.IEventHandler;
import cn.mapway.ui.client.frames.AbstractModule;
import cn.mapway.ui.client.json.JSON;
import cn.mapway.ui.client.mqtt.IMqttHandler;
import cn.mapway.ui.client.mqtt.MqttJs;
import cn.mapway.ui.client.mqtt.MqttJsClient;
import cn.mapway.ui.client.mqtt.event.MqttMessage;
import cn.mapway.ui.client.mvc.*;
import cn.mapway.ui.client.widget.common.ConfirmDialog;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.StatusCodeException;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.ksyzt.gwt.client.event.MessageHandler;

import java.util.List;

/**
 * 客户端应用环境.
 *
 * @author zhangjianshe
 */
public class ClientContext implements IEventHandler, IOnPostMessage {

    private final XDM xdm;

    /**
     * The bus.
     */
    private final EventBus bus;

    private MqttJsClient mqttJsClient;
    private final IOnPostMessage setTitleHandler = new IOnPostMessage() {

        @Override
        public void onPostMessage(JSONObject data) {
            String title = Jsons.getAsString(data, "title", "");
            GWT.log("change title >" + title);
            if (title.length() > 0) {
                postTopic(EventTopics.CHANGE_TITLE, 0, title);
            }
        }
    };

    /**
     * 保护构造函数，不允许创建new.
     */
    private ClientContext() {
        bus = new EventBus();
        xdm = new XDM();
        xdm.addListener("switchModule", this);
        xdm.addListener("setTitle", setTitleHandler);
        initLoginHandler();
    }

    @Override
    public void onPostMessage(JSONObject data) {

        GWT.log("receive " + JSON.stringify(data));
        String moduleCode = Jsons.getAsString(data, "moduleCode", "");
        String key = Jsons.getAsString(data, "key", "");
        String value = Jsons.getAsString(data, "value", "");

        if (moduleCode.length() > 0) {
            ModuleInfo mi = AbstractModule.getModuleFactory().findModuleInfo(moduleCode);
            SwitchModuleData d = new SwitchModuleData(moduleCode, mi.hash);
            d.getParameters().put(key, value);
            postTopic(EventTopics.CHANGE_MODULE_WITH_DATA, 0, d);
        }
    }

    /**
     *
     */
    private void initLoginHandler() {
        this.getEventBus().register(EventTopics.LOGIN, this);
    }

    /**
     * 获取事件总线.
     *
     * @return the event bus
     */
    public EventBus getEventBus() {
        return bus;
    }

    /**
     * The g context.
     */
    private static ClientContext gContext = null;

    /**
     * 全局静态环境参数.
     *
     * @return the context
     */
    public static final ClientContext getContext() {
        if (gContext == null) {
            gContext = new ClientContext();
        }
        return gContext;
    }

    /**
     * 当前登录用户.
     */
    private S_USERObj user;

    /**
     * Gets the user.
     *
     * @return the user
     */
    public S_USERObj getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user the new user
     */
    public void setUser(S_USERObj user) {
        this.user = user;
    }

    /**
     * The message handler.
     */
    private final MessageHandler messageHandler = new MessageHandler() {

        @Override
        public void onMessage(Object sender, Integer message, Object value) {

        }
    };

    /**
     * 全局消息处理方法.
     *
     * @return the message handler
     */
    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    /**
     * The frame.
     */
    private MainFrame frame;

    /**
     * Gets configure.
     *
     * @return the configure
     */
    public ClientConfigure getConfigure() {
        return configure;
    }

    /**
     * Sets configure.
     *
     * @param configure the configure
     */
    public void setConfigure(ClientConfigure configure) {
        this.configure = configure;
    }

    private ClientConfigure configure;

    /**
     * 向用户确认操作.
     *
     * @param text the text
     * @return true, if successful
     */
    public final boolean confirm(String text) {
        return Window.confirm(text);
    }

    /**
     * 向总线发送一个文本消息
     *
     * @param message the message
     */
    public void postMessage(String message) {
        postTopic(EventTopics.MESSAGE, 0, message);
    }

    /**
     * 向总线发送一个文本消息
     *
     * @param topic the topic
     * @param type  the type
     * @param data  the data
     */
    public void postTopic(String topic, int type, Object data) {
        getEventBus().fire(topic, type, data);
    }

    /**
     * ss
     *
     * @param moduleCode the module code
     * @param parameters the parameters
     */
    public void switchModule(String moduleCode, ModuleParameter parameters) {
        IModule m = BaseAbstractModule.getModuleFactory().createModule(moduleCode, true);
        if (m == null) {
            GWT.log("没有找到模块:" + moduleCode);
        }
        GWT.log("start clear page");
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(m.getRootWidget());
        GWT.log("attach module to page");

        m.initialize(null, parameters);

    }

    /*
     * (non-Javadoc)
     *
     * @see cn.ennwifi.datahub.monitor.ui.client.event.IEventHandler#onEvent(java.lang.String, int,
     * java.lang.Object)
     */
    @Override
    public void onEvent(String topic, int type, Object event) {
        if (EventTopics.LOGIN.equals(topic)) {
            if (type == 0) {
                switchModule(MainFrame.MODULE_CODE, null);
            } else if (type == 1) {
                switchModule(LoginModule.MODULE_CODE, null);
            }
        } else if (EventTopics.EXIT.equals(topic)) {
            this.user = null;
            switchModule(LoginModule.MODULE_CODE, null);
        }
    }

    /**
     * Gets image upload action.
     *
     * @return the image upload action
     */
    public String getImageUploadAction() {
        return imageUploadAction;
    }

    /**
     * Sets image upload action.
     *
     * @param imageUploadAction the image upload action
     */
    public void setImageUploadAction(String imageUploadAction) {
        this.imageUploadAction = imageUploadAction;
    }

    private String imageUploadAction;
    private ConfirmDialog confirm;

    /**
     * 显示消息对话框
     *
     * @param title    the title
     * @param html     the html
     * @param callback the callback
     */
    public void confirm(String title, String html, Callback<Void, Void> callback) {
        if (confirm == null) {
            confirm = new ConfirmDialog();
            confirm.setGlassEnabled(true);
            confirm.setModal(true);
        }
        confirm.setCallback(callback);
        confirm.setInfo(title, html);
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                confirm.center();
            }
        });
    }

    /**
     * The app eventhandler.
     */
    private IMqttHandler appEventhandler;

    /**
     * Sets the main frame.
     *
     * @param frame the new main frame
     */
    public void setMainFrame(MainFrame frame) {
        this.frame = frame;
        appEventhandler = frame;
    }

    /**
     * The Mqtt js.
     */
    MqttJs mqttJs;

    /**
     * Inits the mqtt.
     */
    public void initMqtt() {
        if (configure.getMqttServer() == null || configure.getMqttServer().length() == 0) {
            GWT.log("没有MQTT配置信息，不进行MQTT连接操作");
            return;
        }
        timer.schedule(500);
    }

    /**
     * The timer.
     */
    private final Timer timer = new Timer() {
        @Override
        public void run() {
            if (appEventhandler != null) {
                String id = "iot/monitor/" + Clients.randomString(8);
                GWT.log("connect to " + configure.getMqttServer() + ":" + configure.getMqttPort() + " with " + id + " userId:"
                        + getUser().getName() + " token:" + getUser().getToken());
                mqttJsClient =
                        MqttJs.createClient(configure.getMqttServer(), Integer.parseInt(configure.getMqttPort()), id, configure.getMqttPath());
                mqttJsClient.addHandler(appEventhandler);
                mqttJsClient.connect(getUser().getName() + "", getUser().getToken());
            } else {
                timer.schedule(2000);
            }
        }
    };

    private String mqttTopicPrefix;

    /**
     * Gets mqtt topic prefix.
     *
     * @return the mqtt topic prefix
     */
    public String getMqttTopicPrefix() {
        return mqttTopicPrefix;
    }

    /**
     * Sets mqtt topic prefix.
     *
     * @param mqttTopicPrefix the mqtt topic prefix
     */
    public void setMqttTopicPrefix(String mqttTopicPrefix) {
        this.mqttTopicPrefix = mqttTopicPrefix;
    }

    /**
     * Process failure.
     *
     * @param caught the caught
     */
    public void processFailure(Throwable caught) {

        if (caught instanceof StatusCodeException) {
            Window.alert("账户信息有误，请重新登录！");
            Window.Location.reload();
        }

    }

    private String dashboardCode;

    /**
     * Gets dashboard code.
     *
     * @return the dashboard code
     */
    public String getDashboardCode() {
        return dashboardCode;
    }

    /**
     * Sets dashboard code.
     *
     * @param code the code
     */
    public void setDashboardCode(String code) {
        dashboardCode = code;
    }

    /**
     * Gets resources.
     *
     * @return the resources
     */
    public List<S_RESOURCEObj> getResources() {
        return resources;
    }

    /**
     * Sets resources.
     *
     * @param resources the resources
     */
    public void setResources(List<S_RESOURCEObj> resources) {
        this.resources = resources;
    }

    /**
     * The Resources.
     */
    List<S_RESOURCEObj> resources;

    /**
     * 用户是否对资源 resID拥有权限
     *
     * @param resId the res id
     * @return boolean
     */
    public boolean isAuthorized(Long resId) {
        Boolean authorized = false;
        if (resources != null && resId != null) {
            for (S_RESOURCEObj r : resources) {
                if (r.getId().equals(resId)) {
                    authorized = true;
                    break;
                }
            }
        }
        return authorized;
    }

    /**
     * 用户是否对资源 resID拥有权限
     *
     * @param path      the path
     * @param funcPoint the func point
     * @return boolean
     */
    public boolean isAuthorized(String path, String funcPoint) {
        Boolean authorized = false;
        if (resources != null && path != null && funcPoint != null) {
            for (S_RESOURCEObj r : resources) {
                if (path.equals(r.getPath()) && funcPoint.equals(r.getName())) {
                    authorized = true;
                    break;
                }
            }
        }
        return authorized;
    }

    /**
     * 发送MQTT消息
     *
     * @param topic 消息主题
     * @param data  消息内容
     */
    public void pub(String topic, String data) {
        if (mqttJsClient != null) {
            MqttMessage msg = MqttMessage.create(topic, data);
            mqttJsClient.pub(msg);
        }
    }

    /**
     * 订阅 MQTT TOPIC
     *
     * @param topic 消息主题
     */
    public void sub(String topic) {
        if (mqttJsClient != null) {
            mqttJsClient.sub(topic);
        }
    }

}
package cn.ennwifi.webframe.ui.client;

import cn.ennwifi.webframe.ui.client.data.MetaDataProvider;
import cn.ennwifi.webframe.ui.client.event.EventTopics;
import cn.ennwifi.webframe.ui.client.main.MainFrame;
import cn.ennwifi.webframe.ui.client.modules.authority.login.LoginModule;
import cn.ennwifi.webframe.ui.client.modules.dashboard.DashBoardModule;
import cn.ennwifi.webframe.ui.client.resource.SysResource;
import cn.mapway.ui.client.event.IEventHandler;
import cn.mapway.ui.client.mqtt.MqttJs;

import cn.mapway.ui.client.mvc.ModuleParameter;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;

/**
 * 后台管理界面入口
 *
 * @author zhangjianshe
 */
public class WebFrameEntry implements EntryPoint, IEventHandler {

    /**
     * 可以继承重载这个方法.
     *
     * @return
     */
    public String getDashBoardModuleCode() {
        return DashBoardModule.MODULE_CODE;
    }

    /**
     * 重载此方法提供一个资源权限的根节点ID null 不返回资源的权限
     *
     * @return
     */
    public Integer getResourceRootId() {
        return null;
    }

    @Override
    public void onModuleLoad() {
        // 设置全局异常捕获处理句柄
        GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

            @Override
            public void onUncaughtException(Throwable e) {
                GWT.log(e.getMessage());
                if (e.getMessage().equals("非法TOKEN")) {
                    checkUserToken();
                }
            }
        });

        ClientContext.getContext().getEventBus().register(EventTopics.EXIT, this);
        // CSS 资源注入
        SysResource.INSTANCE.getCss().ensureInjected();

        MqttJs.load(new Callback<Void, Exception>() {

            @Override
            public void onSuccess(Void result) {
                // 处理初始化流程
                checkUserToken();
            }

            @Override
            public void onFailure(Exception reason) {

            }
        });

    }

    /**
     * 处理初始化流程.
     */
    private void checkUserToken() {
        LoginModule.checkUserToken(getResourceRootId(), checkHandler);
        ClientContext.getContext().initMqtt();
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.ennwifi.datahub.monitor.ui.client.component.BaseComposite#onEvent(java.lang.String,
     * int, java.lang.Object)
     */
    @Override
    public void onEvent(String topic, int type, Object event) {

        if (EventTopics.EXIT.equals(topic)) {
            Storage localStorage = Storage.getLocalStorageIfSupported(); // 获取存储对象
            localStorage.setItem(LoginModule.USER_TOKEN, null);
            ClientContext.getContext().setUser(null);
            // checkUserToken();退出后重新加载整个页面
            Window.Location.reload();
        } else if (EventTopics.LOGIN.equals(topic)) {
            MetaDataProvider.get();
            ClientContext.getContext().setDashboardCode(getDashBoardModuleCode());
            ClientContext.getContext().switchModule(MainFrame.MODULE_CODE, null);
        }
    }

    /**
     * 主页面.
     */

    private Callback<Boolean, String> checkHandler = new Callback<Boolean, String>() {

        @Override
        public void onSuccess(Boolean result) {
            // 检查URL中的module?
            // 初始化元数据
            MetaDataProvider.get();
            ClientContext.getContext().setDashboardCode(getDashBoardModuleCode());
            ClientContext.getContext().switchModule(MainFrame.MODULE_CODE, null);
        }

        @Override
        public void onFailure(String reason) {
            ModuleParameter mp = new ModuleParameter();
            mp.put("RESOURCE_ROOT_ID", getResourceRootId() + "");
            ClientContext.getContext().switchModule(LoginModule.MODULE_CODE, null);
        }
    };

}

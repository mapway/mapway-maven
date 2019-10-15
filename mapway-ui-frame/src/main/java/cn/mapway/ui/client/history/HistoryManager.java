package cn.mapway.ui.client.history;

import java.util.ArrayList;
import java.util.List;

import cn.mapway.ui.client.event.IEventHandler;
import cn.mapway.ui.client.mvc.BaseAbstractModule;
import cn.mapway.ui.client.mvc.IModuleDispatcher;
import cn.mapway.ui.client.mvc.ModuleInfo;
import cn.mapway.ui.client.mvc.SwitchModuleData;
import cn.mapway.ui.client.tools.Clients;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.History;

/**
 * 历史回退管理器
 *
 * @author zhangjianshe
 */
public class HistoryManager implements ValueChangeHandler<String> {

    /**
     * The M empty hander.
     */
    IEventHandler mEmptyHander;

    /**
     * 设置空的hash事件
     *
     * @param handler the handler
     */
    public void setEmptyHandler(IEventHandler handler) {
        mEmptyHander = handler;
    }

    /**
     * The constant SEPRATOR.
     */
    public final static String SEPRATOR = ":";
    /**
     * The constant EMPTY_HASH_EVENT.
     */
    public static final String EMPTY_HASH_EVENT = "EMPTY_HASH_EVENT";
    private IModuleDispatcher mDispatcher;

    /**
     * Push.
     *
     * @param moduleDatas the module datas
     */
    public final static void push(List<SwitchModuleData> moduleDatas) {
        String r = encode(moduleDatas);
        if (r.length() > 0) {
            History.newItem(r, false);
        }
    }


    /**
     * Push parameters.
     *
     * @param moduleDatas the module datas
     */
    public final static void pushParameters(List<SwitchModuleData> moduleDatas) {
        encode(moduleDatas);
    }


    private static HistoryManager historyManager = null;

    /**
     * Get history manager.
     *
     * @param dispatcher the dispatcher
     * @return the history manager
     */
    public final static HistoryManager get(IModuleDispatcher dispatcher) {
        if (historyManager == null) {
            historyManager = new HistoryManager(dispatcher);
            History.addValueChangeHandler(historyManager);
        }
        return historyManager;
    }

    /**
     * Instantiates a new History manager.
     *
     * @param dispatcher the dispatcher
     */
    protected HistoryManager(IModuleDispatcher dispatcher) {
        mDispatcher = dispatcher;
    }

    /**
     * Popup.
     *
     * @param token the token
     */
    public void popup(String token) {
        List<SwitchModuleData> modules = decode(token);
        IModuleDispatcher d = mDispatcher;

        if (modules.size() > 0) {
            int index = 0;
            if (d != null) {
                if (index >= modules.size()) {
                }
                SwitchModuleData moduelData = modules.get(index++);

                GWT.log("popup hash > " + moduelData.getModuleCode() + "  "
                        + moduelData.getParameters().toString());
                d.switchModule(moduelData.getModuleCode(), moduelData.getParameters(), false);
            }
        } else {
            if (mEmptyHander != null) {
                mEmptyHander.onEvent(EMPTY_HASH_EVENT, 0, null);
            }
        }
    }

    private final static String encode(List<SwitchModuleData> moduleCodes) {
        if (moduleCodes == null || moduleCodes.size() == 0) {
            return "";
        }
        String r = "";
        // 保存参数到本地存储中.
        Storage storage = Storage.getLocalStorageIfSupported();

        for (SwitchModuleData d : moduleCodes) {
            if (r.length() > 0) {
                r += ":";
            }
            String paraKey = Clients.randomString(6);
            r += d.getHash() + "-" + paraKey;
            storage.setItem(paraKey, d.getParameters().toString());
        }
        return r;
    }

    private final static List<SwitchModuleData> decode(String token) {

        Storage storage = Storage.getLocalStorageIfSupported();

        List<SwitchModuleData> r = new ArrayList<SwitchModuleData>();

        if (token != null && token.length() > 0) {
            String[] hashs = token.split(":");
            for (int i = 0; i < hashs.length; i++) {
                String hash = hashs[i];
                String[] ks = hash.split("-");
                if (ks.length != 2) {
                    break;
                }

                ModuleInfo mi = BaseAbstractModule.getModuleFactory().findModuleInfoByHash(ks[0]);
                SwitchModuleData d = new SwitchModuleData(mi.code, mi.hash);
                d.getParameters().parse(storage.getItem(ks[1]));
                r.add(d);
            }
        }
        return r;
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();
        GWT.log("history navi to " + token);
        if (token == null || token.length() == 0) {
            return;
        }
        popup(token);
    }
}

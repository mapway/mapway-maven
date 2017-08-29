package cn.mapway.ui.client.history;

import java.util.ArrayList;
import java.util.List;

import cn.mapway.ui.client.mvc.BaseAbstractModule;
import cn.mapway.ui.client.mvc.IModuleDispatcher;
import cn.mapway.ui.client.mvc.ModuleInfo;
import cn.mapway.ui.client.mvc.SwitchModuleData;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.History;

/**
 * 历史回退管理器
 * 
 * @author zhangjianshe
 *
 */
public class HistoryManager implements ValueChangeHandler<String> {

  public final static String SEPRATOR = ":";
  private IModuleDispatcher mDispatcher;

  public final static void push(List<SwitchModuleData> moduleDatas) {
    String r = encode(moduleDatas);
    if (r.length() > 0) {
      History.newItem(r, false);
    }
  }


  public final static void pushParameters(List<SwitchModuleData> moduleDatas) {
    encode(moduleDatas);
  }



  private static HistoryManager historyManager = null;

  public final static HistoryManager get(IModuleDispatcher dispatcher) {
    if (historyManager == null) {
      historyManager = new HistoryManager(dispatcher);
      History.addValueChangeHandler(historyManager);
    }
    return historyManager;
  }

  protected HistoryManager(IModuleDispatcher dispatcher) {
    mDispatcher = dispatcher;
  }

  public void popup(String token) {
    List<SwitchModuleData> modules = decode(token);
    IModuleDispatcher d = mDispatcher;

    if (modules.size() > 0) {
      int index = 0;
      while (d != null) {
        if (index >= modules.size()) {
          break;
        }
        SwitchModuleData moduelData = modules.get(index++);

        GWT.log("popup hash > " + moduelData.getModuleCode() + "  "
            + moduelData.getParameters().toString());
        d = d.switchModule(moduelData.getModuleCode(), moduelData.getParameters(), false);

      }
    }
  }

  private final static String encode(List<SwitchModuleData> moduleCodes) {
    if (moduleCodes == null || moduleCodes.size() == 0) {
      return "";
    }
    String r = "";
    String data = "";
    for (SwitchModuleData d : moduleCodes) {
      if (r.length() > 0) {
        r += ":";
        data += "`";
      }
      r += d.getHash();
      data += d.getParameters().toString();
    }
    // 保存参数到本地存储中.
    Storage storage = Storage.getLocalStorageIfSupported();
    if (storage != null) {
      storage.setItem(r, data);
      GWT.log("push local(" + r + ">" + data);
    }
    return r;
  }

  private final static List<SwitchModuleData> decode(String token) {
    String data = "";
    // 从本地存储恢复数据.
    Storage storage = Storage.getLocalStorageIfSupported();
    if (storage != null) {
      data = storage.getItem(token);
    }

    List<SwitchModuleData> r = new ArrayList<SwitchModuleData>();

    if (token != null && token.length() > 0) {
      String[] hashs = token.split(":");
      String[] paras = data.split("`");

      for (int i = 0; i < hashs.length; i++) {
        ModuleInfo mi = BaseAbstractModule.getModuleFactory().findModuleInfoByHash(hashs[i]);
        SwitchModuleData d = new SwitchModuleData(mi.code, mi.hash);
        d.getParameters().parse(paras[i]);
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

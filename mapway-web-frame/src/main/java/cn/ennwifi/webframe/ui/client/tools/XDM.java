package cn.ennwifi.webframe.ui.client.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.mapway.ui.client.json.JSON;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.json.client.JSONObject;

/**
 * @author zhangjianshe@navinfo.com
 * 
 */
public class XDM {

  private HashMap<String, List<IOnPostMessage>> mapper;

  public interface IOnPostMessage {
    void onPostMessage(JSONObject data);
  }

  public XDM() {
    mapper = new HashMap<String, List<IOnPostMessage>>();
    registerReceiver(this);
  }

  /**
   * 向父窗口框架发送消息
   * 
   * @param frame
   * @param data
   */
  public final native void postParentMessage(JavaScriptObject data)/*-{
		var p = $wnd.parent;
		p.postMessage(data, "*");

  }-*/;

  /**
   * 向父窗口的子窗口发送消息
   * 
   * @param data
   */
  public final native void postParentFrameMessage(String frame, JavaScriptObject data)/*-{
		var p = $wnd.parent.frames[frame];
		p.postMessage(data, "*");
  }-*/;

  /**
   * 向框架发送消息
   * 
   * @param frame
   * @param data
   */
  public final native void postMessage(String frame, JavaScriptObject data)/*-{
		console.log("post>" + frame);
		if (frame == null || frame == "") {
			var p = $wnd.parent;
			while (p.parent != p) {
				p = p.parent;
			}
			p.postMessage(data, "*");
		} else {

			var f = $wnd.frames[frame];
			if (f != null) {
				f.postMessage(data, "*");
			} else {
				var p = $wnd.parent;

				var send = false;
				do {

					fr = p.frames[frame];
					if (fr != null) {
						fr.postMessage(data, "*");
						send = true;
						break;
					} else {
						p = p.parent;
					}
				} while (p != p.parent)

				if (send == false) {
					fr.postMessage(data, "*");
				}
			}
		}
  }-*/;

  public void addListener(String action, IOnPostMessage listener) {
    if (action == null || action.length() == 0) {
      return;
    }
    List<IOnPostMessage> list = mapper.get(action);
    if (list == null) {
      list = new ArrayList<IOnPostMessage>();
      mapper.put(action, list);
    }
    list.add(listener);
  }

  public void removeListener(String action, IOnPostMessage listener) {
    if (action == null || action.length() == 0) {
      return;
    }
    List<IOnPostMessage> list = mapper.get(action);
    if (list == null) {
      return;
    }
    for (IOnPostMessage l : list) {
      if (l.equals(listener)) {
        list.remove(l);
        break;
      }
    }
  }

  protected void onPostMessage(JavaScriptObject data) {
    GWT.log(JSON.stringify(data));
    JSONObject d = new JSONObject(data);
    String postAction = Jsons.getAsString(d, "postAction", "");
    if (postAction != null && postAction.length() > 0) {
      List<IOnPostMessage> listeners = mapper.get(postAction);
      if (listeners != null) {
        for (IOnPostMessage l : listeners) {
          l.onPostMessage(d);
        }
      }
    }
  }

  /**
   * 注册消息接收器
   */
  private final native void registerReceiver(XDM xdm)/*-{
		function __xdm__postMessageListener(e) {

			xdm.@cn.ennwifi.webframe.ui.client.tools.XDM::onPostMessage(Lcom/google/gwt/core/client/JavaScriptObject;)(e.data);
		}
		if ($wnd.attachEvent) {
			$wnd.attachEvent("onmessage", __xdm__postMessageListener, false);
		}
		if ($wnd.addEventListener) {
			$wnd.addEventListener("message", __xdm__postMessageListener, false);
		}
  }-*/;
}

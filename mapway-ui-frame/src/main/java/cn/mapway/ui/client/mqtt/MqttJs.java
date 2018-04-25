package cn.mapway.ui.client.mqtt;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;


/**
 * 操作MQTT.
 *
 * @author zhangjianshe
 */
public class MqttJs {

  /** The loaded. */
  private static boolean loaded = false;

    /**
     * 是否加载类库成功.
     *
     * @return true, if is loaded
     */
    public static final boolean isLoaded() {
    return loaded;
  }

    /**
     * 创建MQTT客户端.
     *
     * @param host the host
     * @param port the port
     * @param id   the id
     * @param path the path
     * @return the mqtt js client
     */
    public static final MqttJsClient createClient(String host, int port, String id, String path) {
    if (isLoaded()) {
      return MqttJsClient.create(host, port, id, path);
    } else {
      return null;
    }
  }

    /**
     * 初始化ECHART 类库.
     *
     * @param handler the handler
     */
    public static void load(final Callback<Void, Exception> handler) {
    String url = GWT.getModuleBaseURL() + "../js/mqtt.js";
    ScriptInjector.fromUrl(url).setCallback(new Callback<Void, Exception>() {
      @Override
      public void onSuccess(Void result) {
        MqttJs.loaded = true;
        if (handler != null) {
          handler.onSuccess(null);
        }
      }

      @Override
      public void onFailure(Exception reason) {
        MqttJs.loaded = false;
        if (handler != null) {
          handler.onFailure(reason);
        }
      }
    }).inject();
  }



}

package cn.mapway.ui.client.mqtt;

import cn.mapway.ui.client.mqtt.event.MqttMessage;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;


/**
 * MQTT JavaSCript 客户端.
 *
 * @author zhangjianshe
 */
public class MqttJsClient extends JavaScriptObject {

    /**
     * Instantiates a new mqtt js client.
     */
    protected MqttJsClient() {

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
    public static final native MqttJsClient create(String host, int port, String id, String path)/*-{
		console.log("init mqtt client" + host + ":" + port + "@" + id);
		client = new Paho.MQTT.Client(host, Number(port), path, id);
		console.log(client);
		client.isConnected = false;
		return client;
  }-*/;

    /**
     * 添加事件处理器.
     *
     * @param h the h
     */
    public final native void addHandler(IMqttHandler h)/*-{
        this.handler = h;
        var _this = this;
        if (this.handler != null) {
            this.onConnectionLost = function(e) {
                _this.handler.@cn.mapway.ui.client.mqtt.IMqttHandler::onConnectionLost(Lcn/mapway/ui/client/mqtt/MqttJsClient;Lcn/mapway/ui/client/mqtt/event/ConnectionLostEvent;)
                (_this,e);
            }
            this.onMessageArrived = function(e) {
                _this.handler.@cn.mapway.ui.client.mqtt.IMqttHandler::onMessageArrived(Lcn/mapway/ui/client/mqtt/MqttJsClient;Lcn/mapway/ui/client/mqtt/event/MessageArriveEvent;)
                (_this,e);
            }
        }
  }-*/;


    /**
     * 连接MQTT.
     *
     * @param userId the user id
     * @param token  the token
     */
    public final void connect(String userId, String token) {
    GWT.log("connect as " + getId() + " " + userId + " " + token);
    innerconnect(userId, token);
  }

  /**
   * Innerconnect.
   */
  private final native void innerconnect(String userId, String token)/*-{
		var _this = this;
		if (_this.handler == null) {
			this.connect({

			});
		} else {
			this
					.connect({

						onSuccess : function(e) {
							_this.isConnected = true;
							_this.handler.@cn.mapway.ui.client.mqtt.IMqttHandler::onConnect(Lcn/mapway/ui/client/mqtt/MqttJsClient;)(_this);
						},
						onFailure : function(e) {
							_this.isConnected = false;
							_this.handler
									.@cn.mapway.ui.client.mqtt.IMqttHandler::onConnetFailed(Lcn/mapway/ui/client/mqtt/MqttJsClient;Ljava/lang/String;)(
											_this, e.errorMessage);
						}
					});
		}
  }-*/;

    /**
     * Close.
     */
    public final native void close()/*-{
		this.disconnect();
  }-*/;

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public final native void setId(String id)/*-{
		this.clientId = id;
  }-*/;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public final native String getId()/*-{
		return this.clientId;
  }-*/;

    /**
     * Sub.
     *
     * @param topic the topic
     */
    public final native void sub(String topic)
  /*-{
		this.subscribe(topic);
		console.log("subscribe to " + topic);
  }-*/;

    /**
     * Unsub.
     *
     * @param topic the topic
     */
    public final native void unsub(String topic)
  /*-{
		this.unsubscribe(topic);
		console.log("unsubscribe to " + topic);
  }-*/;

    /**
     * Pub.
     *
     * @param msg the msg
     */
    public final native void pub(MqttMessage msg)/*-{
		this.send(msg);
  }-*/;

    /**
     * Checks if is connect.
     *
     * @return true, if is connect
     */
    public final native boolean isConnect()/*-{
		return this.isConnected;
  }-*/;

}

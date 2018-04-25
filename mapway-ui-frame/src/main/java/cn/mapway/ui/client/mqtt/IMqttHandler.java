package cn.mapway.ui.client.mqtt;

import cn.mapway.ui.client.mqtt.event.ConnectionLostEvent;
import cn.mapway.ui.client.mqtt.event.MessageArriveEvent;


/**
 * The Interface IMqttHandler.
 */
public interface IMqttHandler {

    /**
     * 连接断开事件.
     *
     * @param client the client
     * @param event  the event
     */
    public void onConnectionLost(MqttJsClient client, ConnectionLostEvent event);

    /**
     * 消息到达事件.
     *
     * @param client the client
     * @param event  the event
     */
    public void onMessageArrived(MqttJsClient client, MessageArriveEvent event);


    /**
     * 连接已经建立.
     *
     * @param client the client
     */
    public void onConnect(MqttJsClient client);

    /**
     * 连接失败的消息.
     *
     * @param client  the client
     * @param message the message
     */
    public void onConnetFailed(MqttJsClient client, String message);


}

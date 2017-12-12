package cn.ennwifi.webframe.ui.client.modules;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.event.EventTopics;
import cn.mapway.ui.client.event.IEventHandler;
import cn.mapway.ui.client.mvc.BaseAbstractModule;

public abstract class BaseAbstractModuleWithEvent extends BaseAbstractModule implements
    IEventHandler {

  /**
   * 向事件总线注册监听的TOPIC
   * 
   * @param topic
   */
  public void registerEventHandler(String topic) {
    ClientContext.getContext().getEventBus().register(topic, this);
  }

  /**
   * 取消事件总线的TOPIC
   * 
   * @param topic
   */
  public void unRegisterEventHandler(String topic) {
    ClientContext.getContext().getEventBus().unregister(topic, this);
  }

  /**
   * 处理总线上的消息.
   */
  @Override
  public void onEvent(String topic, int type, Object event) {

  }

  /**
   * 向总线发送一个文本消息
   * 
   * @param message
   */
  public void postMessage(String message) {
    postTopic(EventTopics.MESSAGE, 0, message);
  }

  /**
   * 向总线发送一个文本消息
   * 
   * @param message
   */
  public void postTopic(String topic, int type, Object data) {
    ClientContext.getContext().postTopic(topic, type, data);
  }

  /**
   * 向总线发送一个文本消息
   * 
   * @param message
   */
  public void postTopic(String topic, Object data) {
    postTopic(topic, 0, data);
  }

}

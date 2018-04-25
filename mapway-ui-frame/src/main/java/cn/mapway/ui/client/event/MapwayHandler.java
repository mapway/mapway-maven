package cn.mapway.ui.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * The Interface MapwayHandler.
 *
 * @param <T> 消息传递的数据类型
 */
public interface MapwayHandler<T> extends EventHandler {

    /**
     * 处理消息.
     *
     * @param sender  the sender
     * @param message the message
     * @param value   the value
     */
    void handle(Object sender,Integer message, T value);
}

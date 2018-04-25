package cn.mapway.ui.client.mvc;

/**
 * 观察者
 *
 * @author zhangjianshe
 */
public interface Observer {
    /**
     * 通知观察者，数据发生变化
     *
     * @param sender the sender
     * @param data   the data
     */
    void update(Observable sender, Object data);
}

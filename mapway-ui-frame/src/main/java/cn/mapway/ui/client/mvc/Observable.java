package cn.mapway.ui.client.mvc;

/**
 * 被监视的对象
 *
 * @author zhangjianshe
 */
public interface Observable {
    /**
     * 添加观察者
     *
     * @param obersver the obersver
     */
    void addObserver(Observer obersver);

    /**
     * 移除观察者列表
     *
     * @param observer the observer
     */
    void rmoveObserver(Observer observer);
}

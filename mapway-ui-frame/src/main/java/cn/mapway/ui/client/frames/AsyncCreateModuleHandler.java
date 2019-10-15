package cn.mapway.ui.client.frames;

import cn.mapway.ui.client.mvc.IModule;

/**
 * 异步创建模块接口回调
 */
public interface AsyncCreateModuleHandler {
    /**
     * 代码加载成功 可以使用了
     *
     * @param module
     */
    void onSuccess(IModule module);

    /**
     * 代码加载失败 不能创建
     *
     * @param message
     */
    void onFail(String message);
}

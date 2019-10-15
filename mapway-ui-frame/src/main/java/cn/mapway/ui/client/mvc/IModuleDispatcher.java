package cn.mapway.ui.client.mvc;


/**
 * 模块调度器
 *
 * @author zhangjianshe
 */
public interface IModuleDispatcher {

    /**
     * 切换模块.
     *
     * @param code          the code
     * @param parameter     the parameter
     * @param saveToHistory the save to history
     * @return module
     */
    void switchModule(String code, ModuleParameter parameter, boolean saveToHistory);
}

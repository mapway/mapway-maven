package cn.mapway.ui.client.mvc;


/**
 * 模块调度器
 * 
 * @author zhangjianshe
 *
 */
public interface IModuleDispatcher {

  /**
   * 切换模块.
   * 
   * @param code
   * @param parameter
   * @return
   */
  IModuleDispatcher switchModule(String code, ModuleParameter parameter, boolean saveToHistory);
}

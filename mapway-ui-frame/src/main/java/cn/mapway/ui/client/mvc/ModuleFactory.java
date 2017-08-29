package cn.mapway.ui.client.mvc;



/**
 * 表明某个类是可以动态生成的.
 *
 * @author zhangjianshe
 */
public interface ModuleFactory {

  /**
   * 根据代码实例化模块.
   *
   * @param moduleCode the module code
   * @return the i frame module
   */
  public IModule createModule(String moduleCode, boolean single);

  /**
   * 获取系统中的所有可用模块.
   *
   * @return the modules
   */
  public ModuleInfo[] getModules();

  /**
   * 根据模块代码找到模块信息
   * 
   * @param moduleCode
   * @return
   */
  public ModuleInfo findModuleInfo(String moduleCode);

  /**
   * 根据模块哈希找到模块信息
   * 
   * @param moduleCode
   * @return
   */
  public ModuleInfo findModuleInfoByHash(String hash);

  /**
   * 模块是否是公共模块,如果没有找到模块，缺省为非公共模块
   * 
   * @param moduleCode
   * @return true/false
   */
  public boolean isModulePublic(String moduleCode);



}

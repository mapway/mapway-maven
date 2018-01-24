package cn.mapway.ui.client.mvc;

/**
 * 模块间切换需要的参数
 * 
 * @author admin
 *
 */
public class SwitchModuleData {
  private String moduleCode;
  private String hash;
  private ModuleParameter paras;

  public String getModuleCode() {
    return moduleCode;
  }

  public String getHash() {
    return hash;
  }

  public ModuleParameter getParameters() {
    return paras;
  }

  /**
   * 设置模块参数.
   * 
   * @param ps
   */
  public void setParameters(ModuleParameter ps) {
    paras = ps;
  }

  public SwitchModuleData(String code, String hash) {
    this.moduleCode = code;
    this.hash = hash;
    this.paras = new ModuleParameter();
  }

  public SwitchModuleData put(String key, Object value) {
    this.paras.put(key, value);
    return this;
  }

  @Override
  public String toString() {
    return "Moduel switch data:" + moduleCode + ">" + paras.size();
  }

}

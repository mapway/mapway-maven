package cn.mapway.ui.client.mvc;

/**
 * 模块间切换需要的参数
 *
 * @author admin
 */
public class SwitchModuleData {
  private String moduleCode;
  private String hash;
  private ModuleParameter paras;

    /**
     * Gets module code.
     *
     * @return the module code
     */
    public String getModuleCode() {
    return moduleCode;
  }

    /**
     * Gets hash.
     *
     * @return the hash
     */
    public String getHash() {
    return hash;
  }

    /**
     * Gets parameters.
     *
     * @return the parameters
     */
    public ModuleParameter getParameters() {
    return paras;
  }

    /**
     * 设置模块参数.
     *
     * @param ps the ps
     */
    public void setParameters(ModuleParameter ps) {
    paras = ps;
  }

    /**
     * Instantiates a new Switch module data.
     *
     * @param code the code
     * @param hash the hash
     */
    public SwitchModuleData(String code, String hash) {
    this.moduleCode = code;
    this.hash = hash;
    this.paras = new ModuleParameter();
  }

    /**
     * Put switch module data.
     *
     * @param key   the key
     * @param value the value
     * @return the switch module data
     */
    public SwitchModuleData put(String key, Object value) {
    this.paras.put(key, value);
    return this;
  }

  @Override
  public String toString() {
    return "Moduel switch data:" + moduleCode + ">" + paras.size();
  }

}

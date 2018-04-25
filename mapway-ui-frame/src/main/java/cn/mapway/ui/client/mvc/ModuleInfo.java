package cn.mapway.ui.client.mvc;


/**
 * 模块对象.
 *
 * @author zhangjianshe
 */
public class ModuleInfo {

    /**
     * The name.
     */
    public String name;

    /**
     * The code.
     */
    public String code;

    /**
     * sumamry.
     */
    public String summary;
    /**
     * 是否是公共模块
     */
    public boolean isPublic;
    /**
     * module icon data base64
     */
    public String icon;

    /**
     * 是否单例创建.
     */
    public Boolean single;

    /**
     * 模块Hash值
     */
    public String hash;

    /**
     * The Is visible.
     */
    public boolean isVisible;

    /**
     * 模块分组信息
     */
    public String group;

    /**
     * Instantiates a new module item.
     *
     * @param name     the name
     * @param code     the code
     * @param summary  the summary
     * @param isPublic the is public
     * @param icon     the icon
     * @param hash     the hash
     * @param visible  the visible
     */
    public ModuleInfo(String name, String code, String summary, boolean isPublic, String icon,
      String hash, boolean visible) {
    this.name = name;
    this.code = code;
    this.isPublic = isPublic;
    this.icon = icon;
    this.summary = summary;
    this.hash = hash;
    this.isVisible = visible;
  }

    /**
     * Instantiates a new Module info.
     *
     * @param name     the name
     * @param code     the code
     * @param summary  the summary
     * @param isPublic the is public
     * @param icon     the icon
     * @param hash     the hash
     * @param visible  the visible
     * @param group    the group
     */
    public ModuleInfo(String name, String code, String summary, boolean isPublic, String icon,
      String hash, boolean visible, String group) {
    this(name, code, summary, isPublic, icon, hash, visible);
    this.group = group;
  }

    /**
     * Copy module info.
     *
     * @return the module info
     */
    public ModuleInfo copy() {
    ModuleInfo n = new ModuleInfo(name, code, summary, isPublic, icon, hash, isVisible);
    return n;
  }

    /**
     * 设置单例模式.
     *
     * @param single the single
     * @return single
     */
    public ModuleInfo setSingle(boolean single) {
    this.single = single;
    return this;
  }
}

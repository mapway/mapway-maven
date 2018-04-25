package cn.ennwifi.webframe.tools;

/**
 * 日志动作
 *
 * @author zhangjianshe
 */
public enum Actions {
    /**
     * Create actions.
     */
    CREATE("创建"), /**
     * Delete actions.
     */
    DELETE("删除"), /**
     * Update actions.
     */
    UPDATE("修改"), /**
     * Read actions.
     */
    READ("查询"), /**
     * Shenhe actions.
     */
    SHENHE("审核"), /**
     * Login actions.
     */
    LOGIN("登录");

    /**
     * The Name.
     */
    public String name;

  Actions(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}

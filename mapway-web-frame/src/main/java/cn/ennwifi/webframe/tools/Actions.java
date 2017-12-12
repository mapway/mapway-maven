package cn.ennwifi.webframe.tools;

/**
 * 日志动作
 * 
 * @author zhangjianshe
 *
 */
public enum Actions {
  CREATE("创建"), DELETE("删除"), UPDATE("修改"), READ("查询"), SHENHE("审核"), LOGIN("登录");

  public String name;

  Actions(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}

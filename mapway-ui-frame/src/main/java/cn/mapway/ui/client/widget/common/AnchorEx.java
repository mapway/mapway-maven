package cn.mapway.ui.client.widget.common;

import com.google.gwt.user.client.ui.Anchor;

public class AnchorEx extends Anchor {

  public AnchorEx() {

  }

  /** 按钮的数据. */
  private Object data;

  /**
   * Gets the data.
   *
   * @return the data
   */
  public Object getData() {
    return data;
  }

  /**
   * Sets the data.
   *
   * @param data the new data
   */
  public void setData(Object data) {
    this.data = data;
  }

  /**
   * Gets the command.
   *
   * @return the command
   */
  public Integer getCommand() {
    return command;
  }

  /**
   * Sets the command.
   *
   * @param command the new command
   */
  public void setCommand(Integer command) {
    this.command = command;
  }

  /** 按钮的命令. */
  private Integer command;
}

package cn.ennwifi.webframe.ui.shared.module;

/**
 * @author zhangjianshe
 */
public class ServerException extends RuntimeException implements
    com.google.gwt.user.client.rpc.IsSerializable {
  /**
   * Instantiates a new server exception.
   *
   * @param msg the msg
   */
  public ServerException(String msg) {
    super(msg);
  }

  /**
   * Instantiates a new server exception.
   */
  public ServerException() {
    super();
  }
}

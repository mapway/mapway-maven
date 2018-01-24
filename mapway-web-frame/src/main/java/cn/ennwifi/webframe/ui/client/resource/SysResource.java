package cn.ennwifi.webframe.ui.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;


/**
 * 系统资源.
 *
 * @author zhangjianshe
 */
public interface SysResource extends ClientBundle {

  /** The Constant INSTANCE. */
  public static final SysResource INSTANCE = GWT.create(SysResource.class);


  /**
   * Gets the css.
   *
   * @return the css
   */
  @Source("style.css")
  @CssResource.NotStrict
  CssStyle getCss();

  /**
   * Custom.
   *
   * @return the image resource
   */
  @Source("image/custom.png")
  ImageResource custom();



  /**
   * Device not online.
   *
   * @return the image resource
   */
  @Source("image/edit.png")
  ImageResource edit();

  /**
   * User.
   *
   * @return the image resource
   */
  @Source("image/user.png")
  ImageResource user();

  /**
   * Key.
   *
   * @return the image resource
   */
  @Source("image/key.png")
  ImageResource key();

  /**
   * Logo.
   *
   * @return the image resource
   */
  @Source("image/logo.png")
  ImageResource logo();


  /**
   * Upload.
   *
   * @return the image resource
   */
  @Source("image/upload.png")
  ImageResource upload();

  /**
   * Arrow left.
   *
   * @return the image resource
   */
  @Source("image/arrow_left.png")
  ImageResource arrow_left();

  /**
   * Arrow right.
   *
   * @return the image resource
   */
  @Source("image/arrow_right.png")
  ImageResource arrow_right();

  /**
   * Arrow up.
   *
   * @return the image resource
   */
  @Source("image/arrow_up.png")
  ImageResource arrow_up();

  /**
   * Arrow down.
   *
   * @return the image resource
   */
  @Source("image/arrow_down.png")
  ImageResource arrow_down();

  /**
   * Arrow left white.
   *
   * @return the image resource
   */
  @Source("image/arrow_left_white.png")
  ImageResource arrow_left_white();



  /**
   * Inter.
   *
   * @return the image resource
   */
  @Source("image/inter.png")
  ImageResource inter();

  /**
   * Tree open.
   *
   * @return the image resource
   */
  @Source("image/treeOpen.png")
  ImageResource treeOpen();

  /**
   * Tree close.
   *
   * @return the image resource
   */
  @Source("image/treeClose.png")
  ImageResource treeClose();


  /**
   * Check false.
   *
   * @return the image resource
   */
  @Source("image/check_false.png")
  ImageResource checkFalse();

  /**
   * Check true.
   *
   * @return the image resource
   */
  @Source("image/check_true.png")
  ImageResource checkTrue();

  /**
   * Check false disabled.
   *
   * @return the image resource
   */
  @Source("image/check_false_disabled.png")
  ImageResource checkFalseDisabled();

  /**
   * Check true disabled.
   *
   * @return the image resource
   */
  @Source("image/check_true_disabled.png")
  ImageResource checkTrueDisabled();


  /**
   * Wifi green.
   *
   * @return the image resource
   */
  @Source("image/wifi_green.png")
  ImageResource wifiGreen();

  /**
   * Wifi red.
   *
   * @return the image resource
   */
  @Source("image/wifi_red.png")
  ImageResource wifiRed();

  /**
   * Wifi blue.
   *
   * @return the image resource
   */
  @Source("image/wifi_blue.png")
  ImageResource wifiBlue();

  /**
   * Wifi connecting.
   *
   * @return the image resource
   */
  @Source("image/wifi_connecting.gif")
  ImageResource wifiConnecting();



  /**
   * Manual pattern on.
   *
   * @return the image resource
   */
  @Source("image/loadding.gif")
  ImageResource loadding();

  /**
   * Manual pattern on.
   *
   * @return the image resource
   */
  @Source("image/empty.png")
  ImageResource empty();

  /**
   * Manual pattern on.
   *
   * @return the image resource
   */
  @Source("image/logviewer.png")
  ImageResource logviewer();

  /**
   * Manual pattern on.
   *
   * @return the image resource
   */
  @Source("image/delete.png")
  ImageResource delete();
}

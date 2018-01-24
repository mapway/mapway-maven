package cn.mapway.ui.client.mqtt.event;

import com.google.gwt.core.client.JavaScriptObject;


/**
 * The Class BaseEvent.
 */
public class BaseEvent extends JavaScriptObject {

  /**
   * Instantiates a new base event.
   */
  protected BaseEvent() {}

  /**
   * 错误代码.
   *
   * @return the int
   */
  public final native int errorCode()/*-{
        return this.errorCode;
  }-*/;

  /**
   * 错误消息.
   *
   * @return the string
   */
  public final native String errormsg()/*-{
        return this.errorMessage;
  }-*/;

}

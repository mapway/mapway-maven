package cn.ennwifi.webframe.ui.client.common;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Tree;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

import cn.ennwifi.webframe.ui.client.data.LocalStorage;

/**
 * The type Local tree.
 */
public class LocalTree extends Tree implements HasMessageHandlers {
    /**
     * Sets the open.
     *
     * @param key  the key
     * @param open the open
     */
    public void setOpen(String key, boolean open) {
    LocalStorage.save(key, open ? "1" : "0");
  }

    /**
     * Checks if is open.
     *
     * @param key the key
     * @return true, if is open
     */
    public boolean isOpen(String key) {
    String data = LocalStorage.val(key);
    if ("1".equals(data)) {
      return true;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.ksyzt.gwt.client.event.HasMessageHandlers#addMessageHandler(com.ksyzt.gwt.client.event.
   * MessageHandler)
   */
  @Override
  public HandlerRegistration addMessageHandler(MessageHandler handler) {
    return addHandler(handler, MessageEvent.TYPE);
  }
}

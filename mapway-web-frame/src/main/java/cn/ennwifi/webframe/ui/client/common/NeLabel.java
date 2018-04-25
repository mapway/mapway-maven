package cn.ennwifi.webframe.ui.client.common;

import com.google.gwt.user.client.ui.Label;


/**
 * The Class NeLabel.
 */
public class NeLabel extends Label {
  
  /** The data. */
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
     * Instantiates a new ne label.
     */
    public NeLabel() {
    setStyleName("text");
  }

    /**
     * Instantiates a new ne label.
     *
     * @param text the text
     */
    public NeLabel(String text) {
    setStyleName("text");
    setText(text);
  }
}

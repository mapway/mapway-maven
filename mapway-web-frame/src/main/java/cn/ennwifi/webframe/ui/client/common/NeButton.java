package cn.ennwifi.webframe.ui.client.common;

import com.google.gwt.user.client.ui.Button;


/**
 * The Class NeButton.
 */
public class NeButton extends Button {

    /**
     * Instantiates a new ne button.
     */
    public NeButton() {
    }

    /**
     * Instantiates a new ne button.
     *
     * @param title the title
     */
    public NeButton(String title) {
        super(title);
    }

    /**
     * The data.
     */
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

}

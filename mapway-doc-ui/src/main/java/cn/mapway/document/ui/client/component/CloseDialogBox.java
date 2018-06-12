package cn.mapway.document.ui.client.component;

import com.google.gwt.user.client.ui.DialogBox;

public class CloseDialogBox extends DialogBox {

    /**
     * Instantiates a new Dialog box ex.
     */
    public CloseDialogBox() {
        super( new CloseCaption());


    }

    public void setSummary(String text) {
        ((CloseCaption) getCaption()).setSummary(text);
    }

}

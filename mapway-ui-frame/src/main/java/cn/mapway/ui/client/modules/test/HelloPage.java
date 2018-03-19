package cn.mapway.ui.client.modules.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * The type Hello page.
 */
public class HelloPage extends Composite {

  private static HelloPageUiBinder uiBinder = GWT.create(HelloPageUiBinder.class);

    /**
     * The interface Hello page ui binder.
     */
    interface HelloPageUiBinder extends UiBinder<Widget, HelloPage> {
  }

    /**
     * Instantiates a new Hello page.
     */
    public HelloPage() {
    initWidget(uiBinder.createAndBindUi(this));
  }

}

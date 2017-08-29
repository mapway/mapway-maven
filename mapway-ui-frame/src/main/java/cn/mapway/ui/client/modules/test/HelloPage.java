package cn.mapway.ui.client.modules.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HelloPage extends Composite {

  private static HelloPageUiBinder uiBinder = GWT.create(HelloPageUiBinder.class);

  interface HelloPageUiBinder extends UiBinder<Widget, HelloPage> {
  }

  public HelloPage() {
    initWidget(uiBinder.createAndBindUi(this));
  }

}

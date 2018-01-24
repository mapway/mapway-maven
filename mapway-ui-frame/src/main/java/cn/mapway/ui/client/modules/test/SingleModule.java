package cn.mapway.ui.client.modules.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;


public class SingleModule extends MessageComposite {

  private static SingleModuleUiBinder uiBinder = GWT.create(SingleModuleUiBinder.class);

  interface SingleModuleUiBinder extends UiBinder<Widget, SingleModule> {
  }

  Widget content;

  public SingleModule() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @UiField
  HorizontalPanel tools;



}

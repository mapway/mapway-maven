package cn.mapway.ui.client.modules.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;


/**
 * The type Single module.
 */
public class SingleModule extends MessageComposite {

  private static SingleModuleUiBinder uiBinder = GWT.create(SingleModuleUiBinder.class);

    /**
     * The interface Single module ui binder.
     */
    interface SingleModuleUiBinder extends UiBinder<Widget, SingleModule> {
  }

    /**
     * The Content.
     */
    Widget content;

    /**
     * Instantiates a new Single module.
     */
    public SingleModule() {
    initWidget(uiBinder.createAndBindUi(this));
  }

    /**
     * The Tools.
     */
    @UiField
  HorizontalPanel tools;



}

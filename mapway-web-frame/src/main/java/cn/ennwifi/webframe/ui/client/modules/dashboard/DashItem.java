package cn.ennwifi.webframe.ui.client.modules.dashboard;

import cn.ennwifi.webframe.ui.shared.module.DashboardData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * The type Dash item.
 */
public class DashItem extends Composite {

  private static DashItemUiBinder uiBinder = GWT.create(DashItemUiBinder.class);

    /**
     * The interface Dash item ui binder.
     */
    interface DashItemUiBinder extends UiBinder<Widget, DashItem> {
  }

    /**
     * Instantiates a new Dash item.
     */
    public DashItem() {
    initWidget(uiBinder.createAndBindUi(this));
  }

    /**
     * The Img.
     */
    @UiField
  Image img;
    /**
     * The Lb title.
     */
    @UiField
  Label lbTitle;

    /**
     * The Lb count.
     */
    @UiField
  Label lbCount;

    /**
     * Render.
     *
     * @param d the d
     */
    public void render(DashboardData d) {
    img.setUrl(GWT.getModuleBaseURL() + "../" + d.icon);
    lbTitle.setText(d.name);
    lbCount.setText(d.count + "");
  }
}

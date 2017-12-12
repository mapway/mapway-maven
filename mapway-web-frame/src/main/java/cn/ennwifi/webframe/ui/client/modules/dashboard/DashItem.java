package cn.ennwifi.webframe.ui.client.modules.dashboard;

import cn.ennwifi.webframe.ui.shared.module.DashboardData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DashItem extends Composite {

  private static DashItemUiBinder uiBinder = GWT.create(DashItemUiBinder.class);

  interface DashItemUiBinder extends UiBinder<Widget, DashItem> {
  }

  public DashItem() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @UiField
  Image img;
  @UiField
  Label lbTitle;

  @UiField
  Label lbCount;

  public void render(DashboardData d) {
    img.setUrl(GWT.getModuleBaseURL() + "../" + d.icon);
    lbTitle.setText(d.name);
    lbCount.setText(d.count + "");
  }
}

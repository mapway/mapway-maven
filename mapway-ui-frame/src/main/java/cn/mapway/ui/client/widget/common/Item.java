package cn.mapway.ui.client.widget.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;


/**
 * The Class Item.
 */
public class Item extends MessageComposite implements DataHolder {

  private static final String SELECTED = "selected";

  /** The ui binder. */
  private static ItemUiBinder uiBinder = GWT.create(ItemUiBinder.class);

  /**
   * The Interface ItemUiBinder.
   */
  interface ItemUiBinder extends UiBinder<Widget, Item> {
  }

  /**
   * Instantiates a new item.
   */
  public Item() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.ennwifi.datahub.monitor.ui.client.component.DataHolder#getData()
   */
  @Override
  public Object getData() {
    return data;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.ennwifi.datahub.monitor.ui.client.component.DataHolder#setData(java.lang.Object)
   */
  @Override
  public void setData(Object data) {
    this.data = data;
  }

  /** The lb name. */
  @UiField
  Label lbName;

  /** The lb summary. */
  @UiField
  Label lbSummary;

  @UiField
  Image img;

  /** The data. */
  private Object data;

  public void setSelected(boolean b) {
    if (b) {
      this.getElement().setAttribute(SELECTED, "true");
    } else {
      this.getElement().removeAttribute(SELECTED);
    }
  }
}

package cn.mapway.ui.client.widget.common;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;


/**
 * 条目列表.
 *
 * @author zhangjianshe
 */
public class ItemList extends HTMLPanel implements HasMessageHandlers {

    /**
     * Instantiates a new item list.
     */
    @UiConstructor
  public ItemList() {
    super("");
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.ksyzt.gwt.client.event.HasMessageHandlers#addMessageHandler(com.ksyzt.gwt.client.event.
   * MessageHandler)
   */
  @Override
  public HandlerRegistration addMessageHandler(MessageHandler handler) {

    return addHandler(handler, MessageEvent.TYPE);
  }

    /**
     * The m selected.
     */
    Item mSelected = null;

    /**
     * Gets selected.
     *
     * @return the selected
     */
    public Item getSelected() {
    return mSelected;
  }

    /**
     * Adds the item.
     *
     * @param title   the title
     * @param summary the summary
     * @param img     the img
     * @param data    the data
     */
    public void addItem(String title, String summary, String img, Object data) {
    Item item = new Item();
    item.setData(data);
    item.lbName.setText(title);
    item.lbSummary.setText(summary);
    if (img == null || img.length() == 0) {
      item.img.setVisible(false);
    } else {
      item.img.setUrl(img);
    }
    if (summary == null || summary.length() == 0) {
      item.lbSummary.setVisible(false);
    }
    this.add(item);
    item.addDomHandler(clickHandler, ClickEvent.getType());
  }

  /** The click handler. */
  private ClickHandler clickHandler = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {
      Item item = (Item) event.getSource();
      if (item != mSelected) {
        if (mSelected != null) {
          mSelected.setSelected(false);
        }
        mSelected = item;
        mSelected.setSelected(true);
      }

      MessageEvent ev = new MessageEvent(MessageEvent.ITEMCLICK, item);
      fireEvent(ev);
    }
  };

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.Panel#clear()
   */
  @Override
  public void clear() {
    mSelected = null;
    super.clear();
  }


    /**
     * Sets selected.
     *
     * @param index    the index
     * @param selected the selected
     * @param fired    the fired
     */
    public void setSelected(int index, Boolean selected, Boolean fired) {
    if (index >= 0 && index < this.getWidgetCount()) {
      Item item = (Item) this.getWidget(index);

      if (item != mSelected) {
        if (mSelected != null) {
          mSelected.setSelected(false);
        }
        mSelected = item;
        mSelected.setSelected(true);
      }
      if (fired) {

        MessageEvent ev = new MessageEvent(MessageEvent.ITEMCLICK, item);
        fireEvent(ev);
      }
    }

  }

    /**
     * Click.
     *
     * @param it the it
     */
    protected void click(Item it) {
    if (it != mSelected) {
      String cssSelected = "item-selected";
      if (mSelected != null) {
        mSelected.removeStyleName(cssSelected);
      }

      it.addStyleName(cssSelected);
      mSelected = it;
    }
    MessageEvent ev = new MessageEvent(MessageEvent.ITEMCLICK, it);
    fireEvent(ev);
  }
}

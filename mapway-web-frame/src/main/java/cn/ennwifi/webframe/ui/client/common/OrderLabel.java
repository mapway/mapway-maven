package cn.ennwifi.webframe.ui.client.common;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.Label;

import cn.ennwifi.webframe.ui.client.data.HeaderInfo;
import cn.ennwifi.webframe.ui.client.resource.SysResource;


/**
 * The Class OrderLabel.
 */
public class OrderLabel extends Label implements ClickHandler, HasValueChangeHandlers<String> {

    /**
     * Instantiates a new order label.
     *
     * @param name      the name
     * @param fieldName the field name
     * @param order     the order
     * @param safeUri   the safe uri
     * @param colShow   the col show
     */
    public OrderLabel(String name, String fieldName, int order, SafeUri safeUri, boolean colShow) {
    super(name);
    this.name = name;
    this.fieldName = fieldName;
    this.order = order;
    this.safeUri = safeUri;
    this.colShow = colShow;

    addClickHandler(this);
    setStylePrimaryName("ne-orderLabel");
    changeName();
  }

    /**
     * Instantiates a new order label.
     *
     * @param name      the name
     * @param fieldName the field name
     * @param order     the order
     * @param safeUri   the safe uri
     */
    public OrderLabel(String name, String fieldName, int order, SafeUri safeUri) {
    this(name, fieldName, order, safeUri, true);
  }

    /**
     * Instantiates a new order label.
     *
     * @param name      the name
     * @param fieldName the field name
     * @param order     the order
     */
    public OrderLabel(String name, String fieldName, int order) {
    this(name, fieldName, order, SysResource.INSTANCE.checkTrue().getSafeUri(), true);
  }

    /**
     * Instantiates a new order label.
     *
     * @param name      the name
     * @param fieldName the field name
     */
    public OrderLabel(String name, String fieldName) {
    this(name, fieldName, HeaderInfo.ORDER_NULL, SysResource.INSTANCE.checkTrue().getSafeUri(), true);
  }

  /**
   * Change name.
   */
  private void changeName() {
    if (this.fieldName == null || this.fieldName.length() == 0) {
      this.setText(name);
      this.removeStyleDependentName("clickable");
      return;
    } else {
      this.addStyleDependentName("clickable");

      if (this.order == HeaderInfo.ORDER_NULL) {
        this.setText(name + " -");
      } else if (this.order == HeaderInfo.ORDER_ASC) {
        this.setText(name + " ↑");
      } else if (this.order == HeaderInfo.ORDER_DESC) {
        this.setText(name + " ↓");
      }
    }
  }

    /**
     * The field name.
     */
    public String fieldName;

    /**
     * The order.
     */
    public int order = HeaderInfo.ORDER_NULL;

    /**
     * The name.
     */
    public String name;

    /**
     * The Img.
     */
    public String Img;

  /** The safe uri. */
  private SafeUri safeUri;

    /**
     * The col show.
     */
    public boolean colShow = true;

    /**
     * Gets the order sql.
     *
     * @return the order sql
     */
    public String getOrderSql() {
    if (fieldName == null || fieldName.length() == 0) {
      return "";
    }
    switch (order) {
      case HeaderInfo.ORDER_ASC:
        return fieldName + " asc";

      case HeaderInfo.ORDER_DESC:
        return fieldName + " desc";
      default:
        return "";
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.
   * ClickEvent)
   */
  @Override
  public void onClick(ClickEvent event) {
    if (this.fieldName != null && this.fieldName.length() > 0) {
      if (this.order == HeaderInfo.ORDER_NULL) {
        this.order = HeaderInfo.ORDER_ASC;

      } else if (this.order == HeaderInfo.ORDER_ASC) {
        this.order = HeaderInfo.ORDER_DESC;

      } else if (this.order == HeaderInfo.ORDER_DESC) {
        this.order = HeaderInfo.ORDER_NULL;

      }
      changeName();
      ValueChangeEvent.fire(this, getOrderSql());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.event.logical.shared.HasValueChangeHandlers#addValueChangeHandler(com.google.gwt
   * .event.logical.shared.ValueChangeHandler)
   */
  @Override
  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
    return addHandler(handler, ValueChangeEvent.getType());
  }
}

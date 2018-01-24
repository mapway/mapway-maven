package cn.ennwifi.webframe.ui.client.data;

import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.ui.Widget;

import cn.ennwifi.webframe.ui.client.resource.SysResource;


/**
 * The Class HeaderInfo.
 */
public class HeaderInfo {

  /** The Constant ORDER_NULL. */
  public static final int ORDER_NULL = 0;

  /** The Constant ORDER_ASC. */
  public static final int ORDER_ASC = 1;

  /** The Constant ORDER_DESC. */
  public static final int ORDER_DESC = 2;

  /** The name. */
  public String name;

  /** The field name. */
  public String fieldName;

  /** The order. */
  public int order;

  /** The safe uri. */
  public SafeUri safeUri;

  /** The col show. */
  public boolean colShow;

  /** The field index. */
  public Integer fieldIndex;

  public Widget widget;

  /**
   * Instantiates a new header info.
   *
   * @param name the name
   * @param fieldName the field name
   * @param order the order
   * @param safeUri the safe uri
   * @param colShow the col show
   */
  public HeaderInfo(String name, String fieldName, int order, SafeUri safeUri, boolean colShow, Widget widget) {
    this.name = name;
    this.fieldName = fieldName;
    this.order = order;
    this.safeUri = safeUri;
    this.colShow = colShow;
    this.fieldIndex = null;
    this.widget = widget;
  }

  /**
   * Instantiates a new header info.
   *
   * @param name the name
   * @param fieldName the field name
   * @param order the order
   * @param safeUri the safe uri
   */
  public HeaderInfo(String name, String fieldName, int order, SafeUri safeUri) {
    this(name, fieldName, order, safeUri, true, null);
  }

  /**
   * Instantiates a new header info.
   *
   * @param name the name
   * @param fieldName the field name
   * @param order the order
   */
  public HeaderInfo(String name, String fieldName, int order) {
    this(name, fieldName, order, SysResource.INSTANCE.checkTrue().getSafeUri(), true, null);
  }

  /**
   * Instantiates a new header info.
   *
   * @param name the name
   * @param fieldName the field name
   */
  public HeaderInfo(String name, String fieldName) {
    this(name, fieldName, ORDER_NULL, SysResource.INSTANCE.checkTrue().getSafeUri(), true, null);
  }

  /**
   * Instantiates a new header info.
   *
   * @param name the name
   */
  public HeaderInfo(String name) {
    this(name, "", ORDER_NULL, SysResource.INSTANCE.checkTrue().getSafeUri(), true, null);
  }

  public HeaderInfo(Widget widget) {
    this(null, "", ORDER_NULL, SysResource.INSTANCE.checkTrue().getSafeUri(), true, widget);
  }

  /**
   * Instantiates a new header info.
   *
   * @param name 显示名称
   * @param fieldIndex 字段索引
   * @param fieldName 字段名称
   * @param order 排序
   * @param safeUri 图标
   * @param colShow 是否显示
   */
  public HeaderInfo(String name, Integer fieldIndex, String fieldName, int order, SafeUri safeUri, boolean colShow) {
    this.name = name;
    this.fieldName = fieldName;
    this.order = order;
    this.safeUri = safeUri;
    this.colShow = colShow;
    this.fieldIndex = fieldIndex;
  }

  /**
   * Instantiates a new header info.
   *
   * @param name the name
   * @param fieldIndex the field index
   * @param fieldName the field name
   */
  public HeaderInfo(String name, Integer fieldIndex, String fieldName) {
    this(name, fieldIndex, fieldName, ORDER_NULL);
  }

  /**
   * Instantiates a new header info.
   *
   * @param name the name
   * @param fieldIndex the field index
   * @param fieldName the field name
   * @param order the order
   */
  public HeaderInfo(String name, Integer fieldIndex, String fieldName, int order) {
    this(name, fieldIndex, fieldName, order, SysResource.INSTANCE.checkTrue().getSafeUri());
  }

  /**
   * Instantiates a new header info.
   *
   * @param name the name
   * @param fieldIndex the field index
   * @param fieldName the field name
   * @param order the order
   * @param safeUri the safe uri
   */
  public HeaderInfo(String name, Integer fieldIndex, String fieldName, int order, SafeUri safeUri) {
    this(name, fieldIndex, fieldName, order, safeUri, true);
  }
}

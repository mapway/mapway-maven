package cn.ennwifi.webframe.ui.shared.module;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

import com.google.gwt.user.client.rpc.IsSerializable;

@Doc("查询基础用户数据")
public class PageTableReq implements IsSerializable {

  @ApiField("当前页数")
  private int page;

  @ApiField("每页显示多少行")
  private int size = 10;

  @ApiField("排序字段")
  private String orderField;

  @ApiField("排序方式")
  private String order;

  @ApiField("查询条件")
  private String cnd;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public String getOrderField() {
    return orderField;
  }

  public void setOrderField(String orderField) {
    this.orderField = orderField;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public String getCnd() {
    return cnd;
  }

  public void setCnd(String cnd) {
    this.cnd = cnd;
  }

}

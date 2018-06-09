package cn.ennwifi.webframe.ui.shared.module;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * The type Page table req.
 */
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

    /**
     * Gets page.
     *
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets order field.
     *
     * @return the order field
     */
    public String getOrderField() {
        return orderField;
    }

    /**
     * Sets order field.
     *
     * @param orderField the order field
     */
    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * Gets cnd.
     *
     * @return the cnd
     */
    public String getCnd() {
        return cnd;
    }

    /**
     * Sets cnd.
     *
     * @param cnd the cnd
     */
    public void setCnd(String cnd) {
        this.cnd = cnd;
    }

}

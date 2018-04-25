package cn.ennwifi.webframe.ui.shared.module;

import java.util.ArrayList;
import java.util.List;


/**
 * 数据库分页数据模型.
 *
 * @param <T> the generic type
 * @author zhangjianshe
 */
public class PagerData<T> implements com.google.gwt.user.client.rpc.IsSerializable {

  /** The page count. */
  private int pageCount;

  /** The page. 从1开始 */
  private Integer page;

  /** The page size. */
  private Integer pageSize;

  /** The total. */
  private Integer total;

  /** 返回的数据. */
  private List<T> data;


    /**
     * 缺省 page=1, pageSize=20, total=-1 需要计算返回值.
     */
    public PagerData() {
    page = 1;
    pageSize = 20;
    total = -1;
    setData(new ArrayList<T>());
  }

    /**
     * Gets the page count.
     *
     * @return the page count
     */
    public int getPageCount() {
    return pageCount;
  }

    /**
     * Sets the page count.
     *
     * @param pageCount the new page count
     */
    public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

    /**
     * Gets the page.
     *
     * @return the page
     */
    public Integer getPage() {
    return page;
  }

    /**
     * Sets the page.
     *
     * @param page the new page
     */
    public void setPage(Integer page) {
    this.page = page;
  }

    /**
     * Gets the page size.
     *
     * @return the page size
     */
    public Integer getPageSize() {
    return pageSize;
  }

    /**
     * Sets the page size.
     *
     * @param pageSize the new page size
     */
    public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

    /**
     * Gets the total.
     *
     * @return the total
     */
    public Integer getTotal() {
    return total;
  }

    /**
     * Sets the total.
     *
     * @param total the new total
     */
    public void setTotal(Integer total) {
    this.total = total;
  }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public List<T> getData() {
    return data;
  }

    /**
     * Sets the data.
     *
     * @param data the new data
     */
    public void setData(List<T> data) {
    this.data = data;
  }

}

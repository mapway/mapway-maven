package cn.ennwifi.webframe.ui.client.data;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.data.IFieldValue;


/**
 * 数据提供者接口.
 *
 * @param <T> the generic type
 * @author zhangjianshe
 */
public interface IDataProvider<T extends IFieldValue> {


    /**
     * Gets the title.
     *
     * @return the title
     */
    public abstract String getTitle();

    /**
     * 获取用户可配置的页面大小列表.
     *
     * @return the page size list
     */
    public int[] getPageSizeList();

    /**
     * 获取记录总数.
     *
     * @return the total count
     */
    public Integer getTotalCount();

    /**
     * 获取数据记录条数.
     *
     * @return the count
     */
    public long getCount();

    /**
     * 页面大小
     *
     * @return page size
     */
    public int getPageSize();

    /**
     * 当前页码
     *
     * @return page number
     */
    public int getPageNumber();

    /**
     * 获取表头数据信息.
     *
     * @return the headers
     */
    public List<HeaderInfo> getHeaders();

    /**
     * 获取第 row行数据.
     *
     * @param row the row
     * @return the row data
     */
    public T getRowData(int row);

    /**
     * 渲染Cell.
     *
     * @param row      the row
     * @param col      the col
     * @param rowdata  the rowdata
     * @param cellData the cell data
     * @return the widget
     */
    public Widget render(int row, int col, T rowdata, Object cellData);

    /**
     * 添加数据发生变化的监听器.
     *
     * @param listener the listener
     */
    public void registerListener(IListener listener);

    /**
     * 移除监听器.
     *
     * @param listener the listener
     */
    public void unregisterListener(IListener listener);

    /**
     * 通知数据发生变化.
     */
    public void notifyDataChange();


}

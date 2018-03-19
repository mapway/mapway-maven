package cn.ennwifi.webframe.service.module;

import java.util.HashMap;
import java.util.Map;

/**
 * dataTable列表查询传入数据
 *
 * @author admin
 */
public class DataTableReq {
	
	private int start;//起始位置
	
	private int length = 10;//每页显示数量
	
	private String order;//desc or asc
	
	private String orderFiled;//排序字段
	
	private String search;//检索值
	
	private Map<String , String> queryMap = new HashMap<String , String>();//条件查询

    /**
     * Gets start.
     *
     * @return the start
     */
    public int getStart() {
		return start;
	}

    /**
     * Sets start.
     *
     * @param start the start
     */
    public void setStart(int start) {
		this.start = start;
	}

    /**
     * Gets length.
     *
     * @return the length
     */
    public int getLength() {
		return length;
	}

    /**
     * Sets length.
     *
     * @param length the length
     */
    public void setLength(int length) {
		this.length = length;
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
     * Gets order filed.
     *
     * @return the order filed
     */
    public String getOrderFiled() {
		return orderFiled;
	}

    /**
     * Sets order filed.
     *
     * @param orderFiled the order filed
     */
    public void setOrderFiled(String orderFiled) {
		this.orderFiled = orderFiled;
	}

    /**
     * Gets search.
     *
     * @return the search
     */
    public String getSearch() {
		return search;
	}

    /**
     * Sets search.
     *
     * @param search the search
     */
    public void setSearch(String search) {
		this.search = search;
	}

    /**
     * Gets query map.
     *
     * @return the query map
     */
    public Map<String, String> getQueryMap() {
		return queryMap;
	}

    /**
     * Sets query map.
     *
     * @param queryMap the query map
     */
    public void setQueryMap(Map<String, String> queryMap) {
		this.queryMap = queryMap;
	}
}

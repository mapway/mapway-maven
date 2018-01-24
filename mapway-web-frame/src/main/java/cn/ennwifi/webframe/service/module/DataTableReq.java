package cn.ennwifi.webframe.service.module;

import java.util.HashMap;
import java.util.Map;

/**
 * dataTable列表查询传入数据
 * @author admin
 *
 */
public class DataTableReq {
	
	private int start;//起始位置
	
	private int length = 10;//每页显示数量
	
	private String order;//desc or asc
	
	private String orderFiled;//排序字段
	
	private String search;//检索值
	
	private Map<String , String> queryMap = new HashMap<String , String>();//条件查询

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderFiled() {
		return orderFiled;
	}

	public void setOrderFiled(String orderFiled) {
		this.orderFiled = orderFiled;
	}
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Map<String, String> getQueryMap() {
		return queryMap;
	}

	public void setQueryMap(Map<String, String> queryMap) {
		this.queryMap = queryMap;
	}
}

package cn.ennwifi.webframe.service.module;

/**
 * dataTable列表查询返回数据
 * @author admin
 *
 */
public class DataTableResp {
	
	private int start;//起始位置
	
	private int length = 10;//每页显示数量
	
	private long recordsFiltered;//总记录数
	
	private Object data ;//返回前端数据

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

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

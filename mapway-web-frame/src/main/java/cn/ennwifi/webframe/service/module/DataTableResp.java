package cn.ennwifi.webframe.service.module;

/**
 * dataTable列表查询返回数据
 *
 * @author admin
 */
public class DataTableResp {
	
	private int start;//起始位置
	
	private int length = 10;//每页显示数量
	
	private long recordsFiltered;//总记录数
	
	private Object data ;//返回前端数据

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
     * Gets records filtered.
     *
     * @return the records filtered
     */
    public long getRecordsFiltered() {
		return recordsFiltered;
	}

    /**
     * Sets records filtered.
     *
     * @param recordsFiltered the records filtered
     */
    public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData() {
		return data;
	}

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Object data) {
		this.data = data;
	}

}

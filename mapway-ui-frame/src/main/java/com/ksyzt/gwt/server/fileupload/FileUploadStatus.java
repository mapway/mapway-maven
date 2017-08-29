package com.ksyzt.gwt.server.fileupload;


// TODO: Auto-generated Javadoc
/**
 * 上传状态类.
 *
 * @author zhangjiashe@gmail.com
 */
public class FileUploadStatus {

	/** The status msg. */
	private String statusMsg = "";
	
	/** The readed bytes. */
	private long readedBytes = 0L;
	
	/** The total bytes. */
	private long totalBytes = 0L;
	
	/** The current item. */
	private int currentItem = 0;
	
	/** The error. */
	// 1 ： 错误 0 ： 正常 2:完成
	private String error = "0";
	
	/** 上传文件的服务器路径. */
	public String filePathName = "";

	/**
	 * Gets the status msg.
	 *
	 * @return the status msg
	 */
	public String getStatusMsg() {
		return statusMsg;
	}

	/**
	 * Sets the status msg.
	 *
	 * @param statusMsg the new status msg
	 */
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	/**
	 * Gets the readed bytes.
	 *
	 * @return the readed bytes
	 */
	public long getReadedBytes() {
		return readedBytes;
	}

	/**
	 * Sets the readed bytes.
	 *
	 * @param readedBytes the new readed bytes
	 */
	public void setReadedBytes(long readedBytes) {
		this.readedBytes = readedBytes;
	}

	/**
	 * Gets the total bytes.
	 *
	 * @return the total bytes
	 */
	public long getTotalBytes() {
		return totalBytes;
	}

	/**
	 * Sets the total bytes.
	 *
	 * @param totalBytes the new total bytes
	 */
	public void setTotalBytes(long totalBytes) {
		this.totalBytes = totalBytes;
	}

	/**
	 * Gets the current item.
	 *
	 * @return the current item
	 */
	public int getCurrentItem() {
		return currentItem;
	}

	/**
	 * Sets the current item.
	 *
	 * @param currentItem the new current item
	 */
	public void setCurrentItem(int currentItem) {
		this.currentItem = currentItem;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * To J son.
	 *
	 * @return the string
	 */
	public String toJSon() {
		StringBuffer strJSon = new StringBuffer();
		strJSon.append("{");
		strJSon.append("error:'").append(error).append("',");
		strJSon.append("statusMsg:'").append(statusMsg).append("',");
		strJSon.append("readedBytes:'").append(readedBytes).append("',");
		strJSon.append("totalBytes:'").append(totalBytes).append("',");
		strJSon.append("currentItem:'").append(currentItem).append("'");
		strJSon.append("filePath:'").append(filePathName).append("'");
		strJSon.append("}");
		return strJSon.toString();
	}
}

package com.ksyzt.gwt.client.data;

/**
 * 通过名称获取字段值
 * 
 * @author zhangjianshe
 *
 */
public interface IFieldValue {

	/**
	 * 获取字段值
	 * 
	 * @param fieldName
	 * @param fieldIndex
	 *            如果fieldName=null 或者 fieldName.length()==0 取此值
	 * @return
	 */
	public Object getFieldValue(String fieldName, Integer fieldIndex);
}

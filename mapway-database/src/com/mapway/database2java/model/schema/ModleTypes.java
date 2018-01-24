package com.mapway.database2java.model.schema;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ModleTypes.
 */
public class ModleTypes {
	
	/** The m dic. */
	ArrayList<ModleType> m_dic;
	
	/**
	 * Instantiates a new modle types.
	 */
	public ModleTypes()
	{
		m_dic=new ArrayList<ModleType>(); 
	}
	
	/**
	 * Adds the.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void Add(String key,String value)
	{
		ModleType mt=findModleType(key);
		if(mt==null)
			{
			mt=new ModleType();
			mt.setKey(key);
			m_dic.add(mt);
			}
		mt.setValue(value);
	}
	
	/**
	 * Find modle type.
	 *
	 * @param key the key
	 * @return the modle type
	 */
	public ModleType findModleType(String key)
	{
		for(int i=0;i<m_dic.size();i++)
		{
			ModleType mt=m_dic.get(i);
			if(mt.key.equals(key))
			{
				return mt;
			}
		}
		return null;
	}
	
	/**
	 * Find value.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String findValue(String key)
	{
		for(int i=0;i<m_dic.size();i++)
		{
			ModleType mt=m_dic.get(i);
			if(mt.key.equals(key))
			{
				return mt.getValue();
			}
		}
		return "";
	}
	
	/**
	 * Like value.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String likeValue(String key)
	{
		for(int i=0;i<m_dic.size();i++)
		{
			ModleType mt=m_dic.get(i);
			if(mt.getKey().indexOf(key)>=0)
			{
				return mt.getValue();
			}
		}
		return "";
	}
}

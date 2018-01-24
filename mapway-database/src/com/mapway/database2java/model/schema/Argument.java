package com.mapway.database2java.model.schema;


// TODO: Auto-generated Javadoc
/**
 * The Class Argument.
 */
public class Argument {

	/** The Name. */
	private String Name;
	
	/** The type. */
	private String type;
	
	/** The Property. */
	private String Property;
	
	/** The position. */
	private int    position;
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * Gets the property.
	 *
	 * @return the property
	 */
	public String getProperty() {
		return Property;
	}
	
	/**
	 * Sets the property.
	 *
	 * @param property the new property
	 */
	public void setProperty(String property) {
		Property = property;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		Name = name;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Gets the java type.
	 *
	 * @return the java type
	 */
	public String getJavaType()
	{
		TypeMapper tm=TypeMapper.getInstance();
		ModleTypes mts=tm.getOracle2JDBC();
		return mts.likeValue(this.type);
	}
	
	/**
	 * Gets the java type 2.
	 *
	 * @return the java type 2
	 */
	public String getJavaType2()
	{
		TypeMapper tm=TypeMapper.getInstance();
		ModleTypes mts=tm.getOracle2JDBC2();
		return mts.likeValue(this.type);
	}
	
	/**
	 * Gets the java type 3.
	 *
	 * @return the java type 3
	 */
	public String getJavaType3()
	{
		TypeMapper tm=TypeMapper.getInstance();
		ModleTypes mts=tm.getOracle2JDBC3();
		return mts.likeValue(this.type);
	}
	
	/**
	 * Gets the net type.
	 *
	 * @return the net type
	 */
	public String getNetType()
	{
		TypeMapper tm=TypeMapper.getInstance();
		ModleTypes mts=tm.getOracle2Net();
		return mts.likeValue(this.type);
	}
	
	/**
	 * Gets the java type 4.
	 *
	 * @return the java type 4
	 */
	public String getJavaType4() {
		TypeMapper tm=TypeMapper.getInstance();
		ModleTypes mts=tm.getOracle2JDBC4();
		return mts.likeValue(this.type);
	}
	
}

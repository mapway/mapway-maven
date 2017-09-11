package com.mapway.database2java;

/**
 * 
 * @author zhangjianshe
 *
 */
public interface IConfigure {

  /**
   * 
   *
   * @return the driver
   */
  public String getDriver();

  /**
   * ��ݿ�����URL.
   *
   * @return the jdbcurl
   */
  public String getJDBCURL();

  /**
   * Gets the max connections.
   *
   * @return the max connections
   */
  public int getMaxConnections();

  /**
   * Gets the schema.
   *
   * @return the schema
   */
  public String getSchema();

  /**
   * Gets the user.
   *
   * @return the user
   */
  public String getUser();

  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword();

  /**
   * Gets the package.
   *
   * @return the package
   */
  public String getPackage();

  /**
   * Gets the path.
   *
   * @return the path
   */
  public String getPath();

  /**
   * Gets the nutz.
   *
   * @return the nutz
   */
  public String getNutz();


  /**
   * 是否生成标准的Bean
   * 
   * @return
   */
  public String getNormalNutz();

}

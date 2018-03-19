package com.mapway.database2java;

/**
 * The interface Configure.
 *
 * @author zhangjianshe
 */
public interface IConfigure {

    /**
     * Gets driver.
     *
     * @return the driver
     */
    String getDriver();

    /**
     * ��ݿ�����URL.
     *
     * @return the jdbcurl
     */
    String getJDBCURL();

    /**
     * Gets the max connections.
     *
     * @return the max connections
     */
    int getMaxConnections();

    /**
     * Gets the schema.
     *
     * @return the schema
     */
    String getSchema();

    /**
     * Gets the user.
     *
     * @return the user
     */
    String getUser();

    /**
     * Gets the password.
     *
     * @return the password
     */
    String getPassword();

    /**
     * Gets the package.
     *
     * @return the package
     */
    String getPackage();

    /**
     * Gets the path.
     *
     * @return the path
     */
    String getPath();


    /**
     * Gets mapper.
     *
     * @return the mapper
     */
    String getMapper();

    /**
     * 是否使用字段全部大写
     *
     * @return use field style
     */
    String getUseFieldStyle();

    /**
     * 是否关联GWT
     *
     * @return use gwt
     */
    String getUseGwt();

    /**
     * 是否关联注解
     *
     * @return use document
     */
    String getUseDocument();

    /**
     * 是否关联数据Nutz注解
     *
     * @return use nutz
     */
    String getUseNutz();

    /**
     * 是否使用字段索引
     *
     * @return use field index
     */
    String getUseFieldIndex();
}

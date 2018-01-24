package com.mapway.database2java;

/**
 * @author zhangjianshe
 */
public interface IConfigure {

    /**
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


    String getMapper();

    /**
     * 是否使用字段全部大写
     *
     * @return
     */
    String getUseFieldStyle();

    /**
     * 是否关联GWT
     *
     * @return
     */
    String getUseGwt();

    /**
     * 是否关联注解
     *
     * @return
     */
    String getUseDocument();

    /**
     * 是否关联数据Nutz注解
     *
     * @return
     */
    String getUseNutz();

    /**
     * 是否使用字段索引
     * @return
     */
    String getUseFieldIndex();
}

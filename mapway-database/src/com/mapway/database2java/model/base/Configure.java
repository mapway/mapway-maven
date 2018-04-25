package com.mapway.database2java.model.base;

import java.io.File;

// TODO: Auto-generated Javadoc

/**
 * The Class Configure.
 */
public class Configure {

    /**
     * The path.
     */
    String path;

    /**
     * The Package.
     */
    String Package;

    /**
     * The database.
     */
    String database;

    /**
     * The schema.
     */
    String schema;

    /**
     * The gwtbase.
     */
    String gwtbase;

    /**
     * The net file path.
     */
    String netFilePath;

    /**
     * 是否使用全大写字段名称
     */
    String useFieldStyle;

    /**
     * 是否使用Nutz注解
     */
    String useNutz;

    /**
     * 是否使用字段索引
     */
    String useFieldIndex;

    /**
     * Gets use gwt.
     *
     * @return the use gwt
     */
    public String getUseGwt() {
        return useGwt;
    }

    /**
     * Sets use gwt.
     *
     * @param useGwt the use gwt
     */
    public void setUseGwt(String useGwt) {
        this.useGwt = useGwt;
    }

    /**
     * 是否使用GWT
     */
    String useGwt;

    /**
     * Gets use document.
     *
     * @return the use document
     */
    public String getUseDocument() {
        return useDocument;
    }

    /**
     * Sets use document.
     *
     * @param useDocument the use document
     */
    public void setUseDocument(String useDocument) {
        this.useDocument = useDocument;
    }

    /**
     * 是否使用Doc注解
     */
    String useDocument;

    /**
     * Gets the schema.
     *
     * @return the schema
     */
    public String getSchema() {
        return schema;
    }

    /**
     * Sets the schema.
     *
     * @param schema the new schema
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    /**
     * Gets the database.
     *
     * @return the database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Sets the database.
     *
     * @param database the new database
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * Gets the package.
     *
     * @return the package
     */
    public String getPackage() {
        return Package;
    }

    /**
     * Sets the package.
     *
     * @param package1 the new package
     */
    public void setPackage(String package1) {
        Package = package1;
    }

    /**
     * Gets the path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path.
     *
     * @param path the new path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the file path.
     *
     * @return the file path
     */
    public String getFilePath() {
        String s = this.path + File.separator;
        s += this.Package.replace('.', File.separatorChar);
        return s;
    }

    /**
     * Copy.
     *
     * @return the configure
     */
    public Configure copy() {
        Configure conf = new Configure();
        conf.setPath(this.getPath());
        conf.setPackage(this.getPackage());
        conf.setDatabase(this.getDatabase());
        conf.setSchema(this.getSchema());
        conf.setGwtbase(this.getGwtbase());
        conf.setUseFieldStyle(this.getUseFieldStyle());
        conf.setUseDocument(this.getUseDocument());
        conf.setUseGwt(this.getUseGwt());
        conf.setUseFieldIndex(this.getUseFieldIndex());
        conf.setUseNutz(this.getUseNutz());
        return conf;
    }

    /**
     * Gets the gwtbase.
     *
     * @return the gwtbase
     */
    public String getGwtbase() {
        return gwtbase;
    }

    /**
     * Sets the gwtbase.
     *
     * @param gwtbase the gwtbase to set
     */
    public void setGwtbase(String gwtbase) {
        this.gwtbase = gwtbase;
    }

    /**
     * Gets the net file path.
     *
     * @return the netFilePath
     */
    public String getNetFilePath() {
        return netFilePath;
    }

    /**
     * Sets the net file path.
     *
     * @param netFilePath the netFilePath to set
     */
    public void setNetFilePath(String netFilePath) {
        this.netFilePath = netFilePath;
    }

    /**
     * Gets use field style.
     *
     * @return the use field style
     */
    public String getUseFieldStyle() {
        return useFieldStyle;
    }

    /**
     * Sets use field style.
     *
     * @param useFieldStyle the use field style
     */
    public void setUseFieldStyle(String useFieldStyle) {
        this.useFieldStyle = useFieldStyle;
    }

    /**
     * Gets use nutz.
     *
     * @return the use nutz
     */
    public String getUseNutz() {
        return useNutz;
    }

    /**
     * Sets use nutz.
     *
     * @param useNutz the use nutz
     */
    public void setUseNutz(String useNutz) {
        this.useNutz = useNutz;
    }

    /**
     * Gets use field index.
     *
     * @return the use field index
     */
    public String getUseFieldIndex() {
        return useFieldIndex;
    }

    /**
     * Sets use field index.
     *
     * @param useFieldIndex the use field index
     */
    public void setUseFieldIndex(String useFieldIndex) {
        this.useFieldIndex = useFieldIndex;
    }
}

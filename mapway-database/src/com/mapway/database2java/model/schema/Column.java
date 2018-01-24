package com.mapway.database2java.model.schema;


// TODO: Auto-generated Javadoc

import com.mapway.database2java.model.base.naming.CamelConvert;
import com.mapway.database2java.model.base.naming.INameConvertor;

/**
 * The Class Column.
 *
 * @author ZhangJianshe
 */
public class Column {

    /**
     * The Database type.
     */
    String DatabaseType;

    /**
     * The Java type.
     */
    String JavaType;

    /**
     * The Name.
     */
    String Name;

    /**
     * The comment.
     */
    String comment;

    /**
     * The is PK.
     */
    boolean isPK;

    /**
     * The is auto.
     */
    boolean isAuto = false;

    /**
     * The length.
     */
    int length;

    INameConvertor convertor;

    public Column(INameConvertor convertor) {
        if (convertor == null) {
            convertor = new CamelConvert();
        }
        this.convertor = convertor;
    }

    public Column() {
        this(null);
    }

    /**
     * 返回代码中的名字
     * @return
     */
    public String getJavaName() {
        return convertor.convert(this.getName());
    }

    /**
     * Gets the length.
     *
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the length.
     *
     * @param length the new length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Gets the comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment.
     *
     * @param comment the new comment
     */
    public void setComment(String comment) {
        this.comment = comment;


        if (this.comment != null && !this.comment.equals("")) {
            int index = this.comment.indexOf("@Seq(");
            if (index >= 0) {
                int end = this.comment.indexOf(")", index);
                if (end > 0) {
                    String str = this.comment.substring(index + 5, end);

                    this.m_seq = str.trim().toUpperCase();
                    this.isAuto = true;
                }
            }
        }

    }

    /**
     * Gets the database type.
     *
     * @return the database type
     */
    public String getDatabaseType() {
        return DatabaseType;
    }

    /**
     * The m seq.
     */
    String m_seq;

    /**
     * Gets the sequence.
     *
     * @return the sequence
     */
    public String getSequence() {
        return m_seq;
    }

    /**
     * Sets the database type.
     *
     * @param databaseType the new database type
     */
    public void setDatabaseType(String databaseType) {
        DatabaseType = databaseType;
    }

    /**
     * Gets the java type.
     *
     * @return the java type
     */
    public String getJavaType() {
        TypeMapper tm = TypeMapper.getInstance();

        return tm.getOracle2JDBC().likeValue(this.DatabaseType);
    }

    /**
     * Gets the net type.
     *
     * @return the net type
     */
    public String getNetType() {
        TypeMapper tm = TypeMapper.getInstance();

        return tm.getOracle2Net().likeValue(this.DatabaseType);
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
     * Checks if is pk.
     *
     * @return true, if is pk
     */
    public boolean isPK() {
        return isPK;
    }

    /**
     * Sets the pk.
     *
     * @param isPK the new pk
     */
    public void setPK(boolean isPK) {
        this.isPK = isPK;
    }

    /**
     * Gets the java type 2.
     *
     * @return the java type 2
     */
    public String getJavaType2() {
        TypeMapper tm = TypeMapper.getInstance();
        return tm.getOracle2JDBC2().likeValue(this.DatabaseType);
    }

    /**
     * Checks if is auto.
     *
     * @return true, if is auto
     */
    public boolean isAuto() {
        return isAuto;
    }

    /**
     * Sets the auto.
     *
     * @param isAuto the new auto
     */
    public void setAuto(boolean isAuto) {
        this.isAuto = isAuto;
    }

    /**
     * Gets the java type 4.
     *
     * @return the java type 4
     */
    public String getJavaType4() {
        TypeMapper tm = TypeMapper.getInstance();
        return tm.getOracle2JDBC4().likeValue(this.DatabaseType);
    }
}

package com.mapway.database2java.model.schema;

// TODO: Auto-generated Javadoc

/**
 * The Class View.
 */
public class View implements ITable {

    /**
     * The columns.
     */
    Columns columns;

    /**
     * Instantiates a new view.
     */
    public View() {
        columns = new Columns();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.schema.ITable#getColumns()
     */
    public Columns getColumns() {
        return columns;
    }

    /**
     * The name.
     */
    String name;

    /**
     * The owner.
     */
    String owner;

    /**
     * The comment.
     */
    String comment;

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.schema.ITable#getComment()
     */
    public String getComment() {
        return comment;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.mapway.database2java.model.schema.ITable#setComment(java.lang.String)
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.schema.ITable#getName()
     */
    public String getName() {
        return name;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.schema.ITable#setName(java.lang.String)
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.schema.ITable#getOwner()
     */
    public String getOwner() {
        return owner;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.schema.ITable#setOwner(java.lang.String)
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.schema.ITable#getJavaName()
     */
    public String getJavaName() {
        return this.name.toUpperCase() + "Obj";
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.schema.ITable#getJavaNames()
     */
    public String getJavaNames() {
        return this.name + "Objs";
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.schema.ITable#getJavaAccessName()
     */
    public String getJavaAccessName() {
        return this.name + "DAO";
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.schema.ITable#hasPK()
     */
    public boolean hasPK() {

        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.mapway.database2java.model.schema.ITable#hasAuto()
     */
    public boolean hasAuto() {

        return false;
    }
}

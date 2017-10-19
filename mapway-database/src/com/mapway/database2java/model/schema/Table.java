package com.mapway.database2java.model.schema;


// TODO: Auto-generated Javadoc
/**
 * The Class Table.
 */
public class Table implements ITable {

  /** The columns. */
  Columns columns;

  /**
   * Instantiates a new table.
   */
  public Table() {
    columns = new Columns();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.schema.ITable#getColumns()
   */
  @Override
  public Columns getColumns() {
    return columns;
  }

  /** The name. */
  String name;

  /** The owner. */
  String owner;

  /** The comment. */
  String comment;

  /*
   * (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.schema.ITable#getComment()
   */
  @Override
  public String getComment() {
    return comment;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.schema.ITable#setComment(java.lang.String)
   */
  @Override
  public void setComment(String comment) {
    this.comment = comment;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.schema.ITable#getName()
   */
  @Override
  public String getName() {
    return name;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.schema.ITable#setName(java.lang.String)
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.schema.ITable#getOwner()
   */
  @Override
  public String getOwner() {
    return owner;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.schema.ITable#setOwner(java.lang.String)
   */
  @Override
  public void setOwner(String owner) {
    this.owner = owner;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.schema.ITable#getJavaName()
   */
  @Override
  public String getJavaName() {
    return this.name + "Obj";
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.schema.ITable#getJavaNames()
   */
  @Override
  public String getJavaNames() {
    return this.name + "Objs";
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.schema.ITable#getJavaAccessName()
   */
  @Override
  public String getJavaAccessName() {
    return this.name + "DAOImpl";
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.schema.ITable#hasPK()
   */
  @Override
  public boolean hasPK() {
    boolean b = false;
    Column col = null;
    for (int i = 0; i < this.getColumns().getCount(); i++) {
      col = this.getColumns().getAt(i);
      if (col.isPK()) {
        b = true;
        break;
      }
    }
    return b;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.mapway.database2java.model.schema.ITable#hasAuto()
   */
  @Override
  public boolean hasAuto() {
    boolean b = false;
    Column col = null;
    for (int i = 0; i < this.getColumns().getCount(); i++) {
      col = this.getColumns().getAt(i);
      if (col.isAuto()) {
        b = true;
        break;
      }
    }
    return b;
  }
}

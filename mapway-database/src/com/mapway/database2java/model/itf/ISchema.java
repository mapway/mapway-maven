package com.mapway.database2java.model.itf;

import com.mapway.database2java.model.base.Configure;
import com.mapway.database2java.model.schema.ITable;
import com.mapway.database2java.model.schema.Tables;
import com.mapway.database2java.model.schema.View;
import com.mapway.database2java.model.schema.Views;

import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISchema.
 */
public interface ISchema {

  /**
   * �ļ�ͷ�İ�Ȩ˵��.
   *
   * @return the copyright
   */
  public String getCopyright();

  /**
   * ����ݿ��л�ȡ��ݿ�Ԫ���.
   *
   * @return true, if successful
   */
  public boolean fetchSchema();

  /**
   * ���JSONHelper��.
   *
   * @param conf the conf
   * @return the string
   */
  public String exportJSONTools(Configure conf);

  /**
   * �����.
   *
   * @param table the table
   * @param conf the conf
   * @return the string
   */
  public String exportTable(ITable table, Configure conf);

  /**
   * Export access base.
   *
   * @param conf the conf
   */
  public void exportAccessBase(Configure conf);

  /**
   * Export pool interface.
   *
   * @param conf the conf
   */
  public void exportPoolInterface(Configure conf);

  /**
   * Gets the tables.
   *
   * @return the tables
   */
  public Tables getTables();

  /**
   * Export execute result.
   *
   * @param confbase the confbase
   */
  public void exportExecuteResult(Configure confbase);

  /**
   * Gets the views.
   *
   * @return the views
   */
  public Views getViews();

  /**
   * Export views.
   *
   * @param at the at
   * @param confTable the conf table
   */
  public void exportViews(View at, Configure confTable);

  /**
   * Export procedures.
   *
   * @param confProcedure the conf procedure
   */
  public void exportProcedures(Configure confProcedure);

  /**
   * Export sequence.
   *
   * @param conf the conf
   */
  public void exportSequence(Configure conf);

  /**
   * Export spring configure.
   *
   * @param conf the conf
   */
  public void exportSpringConfigure(Configure conf);

  /**
   * Export dwr configure.
   *
   * @param conf the conf
   */
  public void exportDwrConfigure(Configure conf);

  /**
   * Export gwt module.
   *
   * @param conf the conf
   */
  public void exportGwtModule(Configure conf);


  public void exportSimpleBean(Configure conf) throws IOException;
}

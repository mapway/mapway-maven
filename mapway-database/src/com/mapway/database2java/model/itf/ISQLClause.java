package com.mapway.database2java.model.itf;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISQLClause.
 */
public interface ISQLClause {

   
    /**
     * Gets the table SQL.
     *
     * @return the table SQL
     */
    public String getTableSQL();

    /**
     * Gets the pksql.
     *
     * @return the pksql
     */
    public String getPKSQL();

    /**
     * Gets the sequence SQL.
     *
     * @return the sequence SQL
     */
    public String getSequence_SQL();

    /**
     * Gets the view SQL.
     *
     * @return the view SQL
     */
    public String getViewSQL();

    /**
     * Gets the procedure SQL.
     *
     * @return the procedure SQL
     */
    public String getProcedureSQL();
}

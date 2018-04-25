package com.bethecoder.ascii_table.spec;

import com.bethecoder.ascii_table.*;

/**
 * The interface Iascii table.
 */
public interface IASCIITable {

    /**
     * The constant ALIGN_LEFT.
     */
    public static final int ALIGN_LEFT = -1;
    /**
     * The constant ALIGN_CENTER.
     */
    public static final int ALIGN_CENTER = 0;
    /**
     * The constant ALIGN_RIGHT.
     */
    public static final int ALIGN_RIGHT = 1;
    /**
     * The constant DEFAULT_HEADER_ALIGN.
     */
    public static final int DEFAULT_HEADER_ALIGN = 0;
    /**
     * The constant DEFAULT_DATA_ALIGN.
     */
    public static final int DEFAULT_DATA_ALIGN = 1;

    /**
     * Print table.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     */
    void printTable(String[] p0, String[][] p1);

    /**
     * Print table.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     * @param p2 the p 2
     */
    void printTable(String[] p0, String[][] p1, int p2);

    /**
     * Print table.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     * @param p2 the p 2
     * @param p3 the p 3
     */
    void printTable(String[] p0, int p1, String[][] p2, int p3);

    /**
     * Print table.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     */
    void printTable(ASCIITableHeader[] p0, String[][] p1);

    /**
     * Print table.
     *
     * @param p0 the p 0
     */
    void printTable(IASCIITableAware p0);

    /**
     * Gets table.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     * @return the table
     */
    String getTable(String[] p0, String[][] p1);

    /**
     * Gets table.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     * @param p2 the p 2
     * @return the table
     */
    String getTable(String[] p0, String[][] p1, int p2);

    /**
     * Gets table.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     * @param p2 the p 2
     * @param p3 the p 3
     * @return the table
     */
    String getTable(String[] p0, int p1, String[][] p2, int p3);

    /**
     * Gets table.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     * @return the table
     */
    String getTable(ASCIITableHeader[] p0, String[][] p1);

    /**
     * Gets table.
     *
     * @param p0 the p 0
     * @return the table
     */
    String getTable(IASCIITableAware p0);
}

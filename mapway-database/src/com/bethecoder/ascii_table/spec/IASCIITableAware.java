package com.bethecoder.ascii_table.spec;

import java.util.*;

import com.bethecoder.ascii_table.*;

/**
 * The interface Iascii table aware.
 */
public interface IASCIITableAware {

    /**
     * Gets headers.
     *
     * @return the headers
     */
    List<ASCIITableHeader> getHeaders();

    /**
     * Gets data.
     *
     * @return the data
     */
    List<List<Object>> getData();

    /**
     * Format data string.
     *
     * @param p0 the p 0
     * @param p1 the p 1
     * @param p2 the p 2
     * @param p3 the p 3
     * @return the string
     */
    String formatData(ASCIITableHeader p0, int p1, int p2, Object p3);
}

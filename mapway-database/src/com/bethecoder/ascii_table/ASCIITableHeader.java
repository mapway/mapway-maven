package com.bethecoder.ascii_table;

/**
 * The type Ascii table header.
 */
public class ASCIITableHeader {

    private String headerName;
    private int headerAlign;
    private int dataAlign;

    /**
     * Instantiates a new Ascii table header.
     *
     * @param headerName the header name
     */
    public ASCIITableHeader(final String headerName) {
        this.headerAlign = 0;
        this.dataAlign = 1;
        this.headerName = headerName;
    }

    /**
     * Instantiates a new Ascii table header.
     *
     * @param headerName the header name
     * @param dataAlign  the data align
     */
    public ASCIITableHeader(final String headerName, final int dataAlign) {
        this.headerAlign = 0;
        this.dataAlign = 1;
        this.headerName = headerName;
        this.dataAlign = dataAlign;
    }

    /**
     * Instantiates a new Ascii table header.
     *
     * @param headerName  the header name
     * @param dataAlign   the data align
     * @param headerAlign the header align
     */
    public ASCIITableHeader(final String headerName, final int dataAlign, final int headerAlign) {
        this.headerAlign = 0;
        this.dataAlign = 1;
        this.headerName = headerName;
        this.dataAlign = dataAlign;
        this.headerAlign = headerAlign;
    }

    /**
     * Gets header name.
     *
     * @return the header name
     */
    public String getHeaderName() {
        return this.headerName;
    }

    /**
     * Sets header name.
     *
     * @param headerName the header name
     */
    public void setHeaderName(final String headerName) {
        this.headerName = headerName;
    }

    /**
     * Gets header align.
     *
     * @return the header align
     */
    public int getHeaderAlign() {
        return this.headerAlign;
    }

    /**
     * Sets header align.
     *
     * @param headerAlign the header align
     */
    public void setHeaderAlign(final int headerAlign) {
        this.headerAlign = headerAlign;
    }

    /**
     * Gets data align.
     *
     * @return the data align
     */
    public int getDataAlign() {
        return this.dataAlign;
    }

    /**
     * Sets data align.
     *
     * @param dataAlign the data align
     */
    public void setDataAlign(final int dataAlign) {
        this.dataAlign = dataAlign;
    }
}

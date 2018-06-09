package cn.ennwifi.webframe.ui.client.common;

import cn.ennwifi.webframe.ui.client.resource.SysResource;
import cn.mapway.ui.client.widget.common.TableEx;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;


/**
 * 自定义表格.
 *
 * @author zhangjianshe
 */
public class NeTable extends TableEx implements HasMessageHandlers, HasSelectionHandlers<Cell> {


    /**
     * The current row.
     */
    int currentRow = -1;

    /**
     * Sets the selection row.
     *
     * @param row the new selection row
     */
    public void setSelectionRow(int row) {
        cancelRowSelect();
        if (row > 0 && row < this.getRowCount()) {
            rf.setStyleName(row, SysResource.INSTANCE.getCss().rowSelect());
            currentRow = row;

        }
    }

    /**
     * 取消当前选择的行.
     */
    private void cancelRowSelect() {
        if (currentRow > 0) {
            rf.setStyleName(currentRow, getRowCss(currentRow));
            currentRow = -1;
        }
    }

    /**
     * Gets the select row.
     *
     * @return the select row
     */
    public int getSelectRow() {
        return currentRow;
    }


    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.ui.FlexTable#removeAllRows()
     */
    @Override
    public void removeAllRows() {
        super.removeAllRows();
        currentRow = -1;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.google.gwt.event.logical.shared.HasSelectionHandlers#addSelectionHandler(com.google.gwt.
     * event.logical.shared.SelectionHandler)
     */
    @Override
    public HandlerRegistration addSelectionHandler(SelectionHandler<Cell> handler) {
        return addHandler(handler, SelectionEvent.getType());
    }

    /**
     * The m click handler.
     */
    private ClickHandler mClickHandler = new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
            Cell cell = NeTable.this.getCellForEvent(event);
            if (cell != null) {
                setSelectionRow(cell.getRowIndex());
                SelectionEvent.fire(NeTable.this, cell);
            }
        }
    };

    /**
     * The rf.
     */
    RowFormatter rf;

    /**
     * Instantiates a new ne table.
     */
    public NeTable() {
        Element ele = this.getElement();
        ele.setAttribute("cellSpacing", "0px");
        ele.setAttribute("cellPadding", "4px");
        this.addClickHandler(mClickHandler);
        rf = getRowFormatter();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ksyzt.gwt.client.event.HasMessageHandlers#addMessageHandler(com.ksyzt.gwt.client.event.
     * MessageHandler)
     */
    @Override
    public HandlerRegistration addMessageHandler(MessageHandler handler) {
        return addHandler(handler, MessageEvent.TYPE);
    }

    /**
     * 报告消息.
     *
     * @param msg the msg
     */
    public void message(String msg) {
        MessageEvent ev = new MessageEvent(MessageEvent.MESSAGE, msg);
        fireEvent(ev);
    }

    /**
     * Format.
     *
     * @param formatHeader the format header
     */
    public void format(boolean formatHeader) {
        int row = 0;
        if (formatHeader) {
            if (this.getRowCount() > 0) {
                rf.setStyleName(0, SysResource.INSTANCE.getCss().rowh());
                row++;
            }
        }

        for (int i = row; i < this.getRowCount(); i++) {
            String css = getRowCss(i);
            rf.setStyleName(i, css);
        }
    }

    /**
     * Gets the row css.
     *
     * @param row the row
     * @return the row css
     */
    private String getRowCss(int row) {
        String css =
                (row % 2 == 0) ? SysResource.INSTANCE.getCss().row1() : SysResource.INSTANCE.getCss()
                        .row0();
        return css;
    }

}

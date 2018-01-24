package cn.mapway.ui.client.widget.common;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

public class TableEx extends FlexTable implements HasSelectionHandlers<Cell>{

	public TableEx() {
		setStyleName("gwtEx-Table");
		rf = getRowFormatter();
		this.addClickHandler(tableClickTable);
	}

	private static final String THEAD_CSS = "gwtEx-table-head";

	private static final String TABLE_TR_HOVER_CSS = "gwtEx-Table-hover";

	private static final String TABLE_TR_CLICK_CSS = "gwtEx-Table-click";

	RowFormatter rf;
	
	int currentRow = -1;

	public void format(boolean format) {
		if (format) {
			int rowCount = this.getRowCount();
			if (rowCount > 0) {
				rf.setStyleName(0, THEAD_CSS);
				for (int i = 1; i < rowCount; i++) {
					rf.setStyleName(i, TABLE_TR_HOVER_CSS);
				}
			}
		}
	}
	

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Cell> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}

	ClickHandler tableClickTable = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			Cell cell = TableEx.this.getCellForEvent(event);
			if (cell != null) {
				if( cell.getRowIndex() != 0 ){
					setSelectionRow( cell.getRowIndex());
					SelectionEvent.fire(TableEx.this, cell);
				}
			}
		}
	};
	
	public void setSelectionRow(int row) {
	    cancelRowSelect();
	    if (row > 0 && row < this.getRowCount()) {
	      rf.setStyleName(row, TABLE_TR_CLICK_CSS);
	      currentRow = row;

	    }
	  }

	  /**
	   * 取消当前选择的行.
	   */
	  private void cancelRowSelect() {
	    if (currentRow > 0) {
	      rf.removeStyleName(currentRow, TABLE_TR_CLICK_CSS);
	      rf.setStyleName(currentRow, TABLE_TR_HOVER_CSS);
	      currentRow = -1;
	    }
	  }
	
}

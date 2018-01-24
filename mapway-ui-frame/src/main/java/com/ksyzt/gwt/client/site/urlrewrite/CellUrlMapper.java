package com.ksyzt.gwt.client.site.urlrewrite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class CellUrlMapper.
 */
public class CellUrlMapper extends MessageComposite {

	/** The ui binder. */
	private static CellUrlMapperUiBinder uiBinder = GWT
			.create(CellUrlMapperUiBinder.class);

	/**
	 * The Interface CellUrlMapperUiBinder.
	 */
	interface CellUrlMapperUiBinder extends UiBinder<Widget, CellUrlMapper> {
	}

	/**
	 * Instantiates a new cell url mapper.
	 */
	public CellUrlMapper() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	/** The txt description. */
	@UiField
	TextBox txtDescription;

	/** The txt from. */
	@UiField
	TextBox txtFrom;

	/** The txt to. */
	@UiField
	TextBox txtTo;

	/**
	 * Render cell.
	 *
	 * @param d the d
	 */
	public void renderCell(RewriteData d) {
		txtDescription.setText(d.desc);
		txtFrom.setText(d.from);
		txtTo.setText(d.to);
	}

	/**
	 * On modify.
	 *
	 * @param e the e
	 */
	@UiHandler("btnModify")
	void onModify(ClickEvent e) {
		RewriteData d = new RewriteData();
		d.desc = txtDescription.getValue();
		d.from = txtFrom.getValue();
		d.to = txtTo.getValue();
		MessageEvent ev = new MessageEvent(MessageEvent.EDIT, d);
		fireEvent(ev);
	}

	/**
	 * On delete.
	 *
	 * @param e the e
	 */
	@UiHandler("btnDelete")
	void onDelete(ClickEvent e) {
		RewriteData d = new RewriteData();
		d.desc = txtDescription.getValue();
		d.from = txtFrom.getValue();
		d.to = txtTo.getValue();
		MessageEvent ev = new MessageEvent(MessageEvent.DELETE, d);
		fireEvent(ev);
	}
}

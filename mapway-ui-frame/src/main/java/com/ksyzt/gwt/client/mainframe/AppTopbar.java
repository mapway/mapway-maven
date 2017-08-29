package com.ksyzt.gwt.client.mainframe;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.CustomTab;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class AppTopbar.
 */
public class AppTopbar extends MessageComposite {

	/** The ui binder. */
	private static AppTopbarUiBinder uiBinder = GWT
			.create(AppTopbarUiBinder.class);

	/**
	 * The Interface AppTopbarUiBinder.
	 */
	interface AppTopbarUiBinder extends UiBinder<Widget, AppTopbar> {
	}

	/** The m tabs handler. */
	private MessageHandler m_tabs_handler = new MessageHandler() {

		@Override
		public void onMessage(Object sender, Integer message, Object value) {
			if (message == MessageEvent.MESSAGE) {
				message((String) value);
			} else {
				MessageEvent ev = new MessageEvent(message, value);
				fireEvent(ev);
			}
		}
	};

	/**
	 * On exit.
	 *
	 * @param e the e
	 */
	@UiHandler("linkQuit")
	void onExit(ClickEvent e) {
		MessageEvent ev = new MessageEvent(MessageEvent.QUIT, 0);
		fireEvent(ev);
	}

	/**
	 * Instantiates a new app topbar.
	 */
	@UiConstructor
	public AppTopbar() {
		initWidget(uiBinder.createAndBindUi(this));
		tabs.addMessageHandler(m_tabs_handler);
		tabs.setTabAlign(HasHorizontalAlignment.ALIGN_RIGHT);
	}

	/** The tabs. */
	@UiField
	CustomTab tabs;

	/** The lb user name. */
	@UiField
	Label lbUserName;
	
	/** The m uid. */
	int m_uid;

	/**
	 * Inits the top.
	 *
	 * @param username the username
	 * @param uid the uid
	 */
	public void initTop(String username, int uid) {
		lbUserName.setText(username);
		m_uid = uid;
	}

	/**
	 * Adds the tab.
	 *
	 * @param caption the caption
	 * @param id the id
	 */
	public void addTab(String caption, Integer id) {
		tabs.addItem(caption, id);
	}

	/**
	 * Adds the tab.
	 *
	 * @param d the d
	 */
	public void addTab(ModuleData d) {
		tabs.addItem(d.getCaption(), d);
	}

	/**
	 * Sets the selected.
	 *
	 * @param index the new selected
	 */
	public void setSelected(int index) {
		tabs.setSelectIndex(index);
	}
}

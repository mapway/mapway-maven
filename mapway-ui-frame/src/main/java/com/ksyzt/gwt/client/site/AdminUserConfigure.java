package com.ksyzt.gwt.client.site;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.ICheckValue;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.shared.module.AdminUser;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminUserConfigure.
 */
public class AdminUserConfigure extends MessageComposite {

	/** The ui binder. */
	private static AdminUserConfigureUiBinder uiBinder = GWT
			.create(AdminUserConfigureUiBinder.class);

	/**
	 * The Interface AdminUserConfigureUiBinder.
	 */
	interface AdminUserConfigureUiBinder extends
			UiBinder<Widget, AdminUserConfigure> {
	}

	/** The m name dowm. */
	private KeyDownHandler m_name_dowm = new KeyDownHandler() {

		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				txtPwd.setFocus(true);
			}
		}
	};
	
	/** The m pwd key down. */
	private KeyDownHandler m_pwd_key_down = new KeyDownHandler() {

		@Override
		public void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				onOK(null);
			}
		}
	};

	/**
	 * Instantiates a new admin user configure.
	 */
	public AdminUserConfigure() {
		initWidget(uiBinder.createAndBindUi(this));
		txtUserName.addKeyDownHandler(m_name_dowm);
		txtPwd.addKeyDownHandler(m_pwd_key_down);
		txtUserName.setFocus(true);
	}

	/** The msg. */
	@UiField
	HTMLPanel msg;

	/**
	 * Sets the mesage.
	 *
	 * @param text the new mesage
	 */
	public void setMesage(String text) {
		msg.add(new HTML(text));
	}

	/** The on check value. */
	public ICheckValue onCheckValue;
	
	/** The txt user name. */
	@UiField
	TextBox txtUserName;

	/** The txt pwd. */
	@UiField
	TextBox txtPwd;

	/**
	 * On OK.
	 *
	 * @param e the e
	 */
	@UiHandler("btnOK")
	void onOK(ClickEvent e) {
		AdminUser u = new AdminUser();
		u.setUserName(txtUserName.getValue());
		u.setPassword(txtPwd.getValue());
		if (onCheckValue != null) {
			boolean b = onCheckValue.check(u);
			if (b == true) {
				MessageEvent ev = new MessageEvent(MessageEvent.OK, u);
				fireEvent(ev);
			}
		}
	}

}

package com.ksyzt.gwt.client.site;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.CommonServerProxy;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.shared.module.SiteInformation;

// TODO: Auto-generated Javadoc

/**
 * The Class PageSiteInfo.
 */
public class PageSiteInfo extends MessageComposite {

    /**
     * The m siteinfo.
     */
    SiteInformation m_siteinfo;

	/** The ui binder. */
	private static PageSiteInfoUiBinder uiBinder = GWT
			.create(PageSiteInfoUiBinder.class);

    /**
     * The Interface PageSiteInfoUiBinder.
     */
    interface PageSiteInfoUiBinder extends UiBinder<Widget, PageSiteInfo> {
	}

    /**
     * Instantiates a new page site info.
     */
    public PageSiteInfo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

    /**
     * The txt NAME.
     */
    @UiField
	TextBox txtNAME;

    /**
     * The txt DESC.
     */
    @UiField
	TextBox txtDESC;

    /**
     * The txt ICP.
     */
    @UiField
	TextBox txtICP;

    /**
     * The txt sec NO.
     */
    @UiField
	TextBox txtSecNO;

    /**
     * The txt TEL.
     */
    @UiField
	TextBox txtTEL;

    /**
     * The txt EMAIL.
     */
    @UiField
	TextBox txtEMAIL;

    /**
     * The txt OWNER.
     */
    @UiField
	TextBox txtOWNER;

    /**
     * The txt KEYWORD.
     */
    @UiField
	TextBox txtKEYWORD;

    /**
     * The check allow reg.
     */
    @UiField
	CheckBox checkAllowReg;

    /**
     * The txt SITEURL.
     */
    @UiField
	TextBox txtSITEURL;

	/** The m on site info. */
	private AsyncCallback<SiteInformation> m_on_site_info = new AsyncCallback<SiteInformation>() {

		@Override
		public void onSuccess(SiteInformation result) {
			m_siteinfo = result;
			txtNAME.setValue(m_siteinfo.getName());
			txtDESC.setValue(m_siteinfo.getSummary());
			txtEMAIL.setValue(m_siteinfo.getEmail());
			txtICP.setValue(m_siteinfo.getIcp());
			txtKEYWORD.setValue(m_siteinfo.getKeywords());
			txtOWNER.setValue(m_siteinfo.getSiteowner());
			txtSecNO.setValue(m_siteinfo.getSecno());
			txtTEL.setText(m_siteinfo.getTel());
			txtSITEURL.setValue(m_siteinfo.getWeb());
			checkAllowReg.setValue(m_siteinfo.isAllowreg());
			message("获取站点信息成功");
		}

		@Override
		public void onFailure(Throwable caught) {
			message(caught.getMessage());

		}
	};

    /**
     * On save.
     *
     * @param e the e
     */
    @UiHandler("btnSave")
	void onSave(ClickEvent e) {
		if (m_siteinfo == null) {
			m_siteinfo = new SiteInformation();
		} else {
			m_siteinfo.setName(txtNAME.getValue());
			m_siteinfo.setSummary(txtDESC.getValue());
			m_siteinfo.setEmail(txtEMAIL.getValue());
			m_siteinfo.setIcp(txtICP.getValue());
			m_siteinfo.setKeywords(txtKEYWORD.getValue());
			m_siteinfo.setSiteowner(txtOWNER.getValue());
			m_siteinfo.setSecno(txtSecNO.getValue());
			m_siteinfo.setTel(txtTEL.getValue());
			m_siteinfo.setAllowreg(checkAllowReg.getValue());
			m_siteinfo.setWeb(txtSITEURL.getValue());
			CommonServerProxy.SITE_MANAGER.updateSiteInformation(m_siteinfo,
					m_on_site_info);
		}
	}

    /**
     * Refresh.
     */
    public void refresh() {
		CommonServerProxy.SITE_MANAGER.getSiteInformation(m_on_site_info);

	}

    /**
     * The btn update bottom.
     */
    @UiField
	Button btnUpdateBottom;
	
	/** The on update template handler. */
	private AsyncCallback<String> on_update_template_handler = new AsyncCallback<String>() {

		public void onSuccess(String result) {
			message(result);

		}

		public void onFailure(Throwable caught) {
			message(caught.getMessage());

		}
	};

    /**
     * On update bottom.
     *
     * @param event the event
     */
    @UiHandler("btnUpdateBottom")
	void onUpdateBottom(ClickEvent event) {
		// CommonServerProxy.SITE_MANAGER.updateTemplate(on_update_template_handler);
	}

    /**
     * The lb message.
     */
    @UiField
	Label lbMessage;

	/* (non-Javadoc)
	 * @see com.ksyzt.gwt.client.common.MessageComposite#message(java.lang.String)
	 */
	public void message(String msg) {
		MessageEvent ev = new MessageEvent(MessageEvent.MESSAGE, msg);
		fireEvent(ev);
	}
}

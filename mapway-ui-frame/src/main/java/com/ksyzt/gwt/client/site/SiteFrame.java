package com.ksyzt.gwt.client.site;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.CssStyle;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.common.SysResource;
import com.ksyzt.gwt.client.common.TopMessage;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;
import com.ksyzt.gwt.client.site.urlrewrite.UrlRewriteConfigure;

// TODO: Auto-generated Javadoc
/**
 * The Class SiteFrame.
 */
public class SiteFrame extends MessageComposite {

	/** The style. */
	@UiField(provided = true)
	CssStyle style;
	
	/** The ui binder. */
	private static SiteFrameUiBinder uiBinder = GWT
			.create(SiteFrameUiBinder.class);

	/**
	 * The Interface SiteFrameUiBinder.
	 */
	interface SiteFrameUiBinder extends UiBinder<Widget, SiteFrame> {
	}

	/**
	 * Instantiates a new site frame.
	 */
	public SiteFrame() {
		style = SysResource.INSTANCE.getCss();
		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * On qiut.
	 *
	 * @param e the e
	 */
	@UiHandler("btnQiut")
	void onQiut(ClickEvent e)
	{
		MessageEvent ev=new MessageEvent(MessageEvent.QUIT,0);
		fireEvent(ev);
	}
	
	/** The root. */
	@UiField
	DockLayoutPanel root;

	/** The page info. */
	PageSiteInfo pageInfo;
	
	/** The page url. */
	UrlRewriteConfigure pageUrl;
	
	/** The m message handler. */
	private MessageHandler m_message_handler = new MessageHandler() {

		@Override
		public void onMessage(Object sender, Integer message, Object value) {
			if (message == MessageEvent.MESSAGE) {
				message((String) value);
			}

		}
	};

	/** The current. */
	Widget current = null;

	/**
	 * On info.
	 *
	 * @param e the e
	 */
	@UiHandler("btnSiteInfo")
	void onInfo(ClickEvent e) {
		if (pageInfo == null) {
			pageInfo = new PageSiteInfo();
			pageInfo.addMessageHandler(m_message_handler);
		}
		pageInfo.refresh();
		setCurrentWidget(pageInfo);

	}

	/**
	 * Sets the current widget.
	 *
	 * @param pageInfo2 the new current widget
	 */
	private void setCurrentWidget(Widget pageInfo2) {
		if (current != null) {
			root.remove(current);

		}
		root.add(pageInfo2);
		current = pageInfo2;
	}

	/* (non-Javadoc)
	 * @see com.ksyzt.gwt.client.common.MessageComposite#message(java.lang.String)
	 */
	public void message(String value) {
		TopMessage msg = new TopMessage();
		msg.showMesasge(value);

	}

	/**
	 * On URL.
	 *
	 * @param e the e
	 */
	@UiHandler("btnURL")
	void onURL(ClickEvent e) {
		if (pageUrl == null) {
			pageUrl = new UrlRewriteConfigure();
			pageUrl.init();
			pageUrl.addMessageHandler(m_message_handler);
		}

		setCurrentWidget(pageUrl);
	}
}

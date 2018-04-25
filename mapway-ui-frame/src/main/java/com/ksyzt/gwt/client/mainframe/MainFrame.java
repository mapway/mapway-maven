package com.ksyzt.gwt.client.mainframe;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

// TODO: Auto-generated Javadoc

/**
 * 应用程序主框架.
 *
 * @author Administrator
 */
public class MainFrame extends MessageComposite {

	/** The ui binder. */
	private static MainFrameUiBinder uiBinder = GWT
			.create(MainFrameUiBinder.class);

    /**
     * The Interface MainFrameUiBinder.
     */
    interface MainFrameUiBinder extends UiBinder<Widget, MainFrame> {
	}

	/** The m topbar handler. */
	private MessageHandler m_topbar_handler = new MessageHandler() {

		@Override
		public void onMessage(Object sender, Integer message, Object value) {
			if (message == MessageEvent.MESSAGE) {
				application.messageHandler.onMessage(sender, message, value);
			} else if (message == MessageEvent.ITEMCLICK) {
				ModuleData d = (ModuleData) value;
				switch_page(d);
			}
			if (message == MessageEvent.QUIT) {
				MessageEvent ev = new MessageEvent(message, 0);
				fireEvent(ev);
			}

		}
	};

    /**
     * 切换页面.
     *
     * @param d the d
     */
    protected void switch_page(ModuleData d) {

		if (d != null) {
			Widget page = d.getWidget();
			if (d.getWidget() == null) {
				page = d.createWidget();
			}
			if (current != page) {
				if (current != null) {
					root.remove(current);
				}
				root.add(page);
				current = page;
			}
		}
	}

    /**
     * Instantiates a new main frame.
     */
    public MainFrame() {
		initWidget(uiBinder.createAndBindUi(this));
		topbar.addMessageHandler(m_topbar_handler);
		modules = new ArrayList<ModuleData>();
	}

    /**
     * The topbar.
     */
    @UiField
	AppTopbar topbar;

    /**
     * The current.
     */
    Widget current;

    /**
     * The root.
     */
    @UiField
	DockLayoutPanel root;

    /**
     * The modules.
     */
    List<ModuleData> modules;

    /**
     * The application.
     */
    AppData application;

    /**
     * Sets the application.
     *
     * @param app the new application
     */
    public void setApplication(AppData app) {
		application = app;
		topbar.initTop(application.user.realname, app.user.id);
	}

    /**
     * Adds the module factory.
     *
     * @param mf    the mf
     * @param props the props
     */
    public void addModuleFactory(IModuleFactory mf, ModuleProperties props) {

		ModuleData d = new ModuleData(application, mf, props);
		modules.add(d);
		topbar.addTab(d);
	}

    /**
     * Show module.
     *
     * @param index the index
     */
    public void showModule(int index) {
		if (index >= 0 && index < modules.size()) {
			switch_page(modules.get(index));
			topbar.setSelected(index);
		}
	}
}

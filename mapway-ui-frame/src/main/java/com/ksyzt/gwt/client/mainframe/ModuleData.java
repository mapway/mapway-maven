package com.ksyzt.gwt.client.mainframe;

import java.util.Properties;

import com.google.gwt.user.client.ui.Widget;

// TODO: Auto-generated Javadoc

/**
 * The Class ModuleData.
 */
public class ModuleData {

    /**
     * The g index.
     */
    static int g_index = 0;

    /**
     * The m mf.
     */
    IModuleFactory m_mf;

    /**
     * The module.
     */
    IAppModule module;

    /**
     * The this index.
     */
    Integer thisIndex;

    /**
     * The app.
     */
    AppData app;

    /**
     * The m props.
     */
    ModuleProperties m_props;

	/**
	 * Instantiates a new module data.
	 */
	@SuppressWarnings("unused")
	private ModuleData() {
	}

    /**
     * Gets the widget.
     *
     * @return the widget
     */
    public Widget getWidget() {
		if (module == null) {
			return null;
		} else {
			return module.getRootWidget();
		}
	}

    /**
     * Gets the index.
     *
     * @return the index
     */
    public Integer getIndex() {
		return this.thisIndex;
	}

    /**
     * Gets the caption.
     *
     * @return the caption
     */
    public String getCaption() {
		return m_mf.getModuleCaption();
	}

    /**
     * Creates the widget.
     *
     * @return the widget
     */
    public Widget createWidget() {
		if (module == null) {
			module = m_mf.createModule();
			module.onInitialize(app, m_props);
		}
		return module.getRootWidget();
	}

    /**
     * Instantiates a new module data.
     *
     * @param application the application
     * @param mf          the mf
     * @param props       the props
     */
    public ModuleData(AppData application, IModuleFactory mf,
			ModuleProperties props) {
		m_mf = mf;
		app = application;
		module = null;
		thisIndex = g_index++;
		m_props = props;
	}
}

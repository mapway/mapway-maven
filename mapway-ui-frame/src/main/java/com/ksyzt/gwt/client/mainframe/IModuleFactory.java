package com.ksyzt.gwt.client.mainframe;


// TODO: Auto-generated Javadoc

/**
 * A factory for creating IModule objects.
 */
public interface IModuleFactory {

    /**
     * Creates a new IModule object.
     *
     * @return the i app module
     */
    public IAppModule createModule();

    /**
     * Gets the module caption.
     *
     * @return the module caption
     */
    public String getModuleCaption();
}

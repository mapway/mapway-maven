package cn.mapway.ui.client.widget.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * The interface Sys resource.
 */
public interface SysResource extends ClientBundle {
    /**
     * The constant INSTANCE.
     */
    public final static SysResource INSTANCE = GWT.create(SysResource.class);

    /**
     * Dragger image resource.
     *
     * @return the image resource
     */
    @Source("images/dragger.png")
  ImageResource dragger();
}

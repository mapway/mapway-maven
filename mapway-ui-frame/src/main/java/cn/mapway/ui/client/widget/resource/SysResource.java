package cn.mapway.ui.client.widget.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface SysResource extends ClientBundle {
  public final static SysResource INSTANCE = GWT.create(SysResource.class);

  @Source("images/dragger.png")
  ImageResource dragger();
}

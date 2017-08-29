package com.ksyzt.gwt.client.common;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Image;

// TODO: Auto-generated Javadoc
/**
 * The Class Image64.
 */
public class Image64 extends Image {


	/**
	 * Instantiates a new image 64.
	 */
	@UiConstructor
	public Image64()
	{
		super();
		setStyleName("IMAGE64");
		setFrame(SysResource.INSTANCE.back64());
	}

	/**
	 * Instantiates a new image 64.
	 *
	 * @param src the src
	 */
	public Image64(String src)
	{
		super();
		setStyleName("IMAGE64");
		setFrame(SysResource.INSTANCE.back64());
		setSrc(src);
	}
	
	/**
	 * Sets the src.
	 *
	 * @param src the new src
	 */
	public void setSrc(String src)
	{
		String imgurl="url("+src+")";
		this.getElement().getStyle().setBackgroundImage(imgurl);
	}

	/**
	 * Sets the frame.
	 *
	 * @param ir the new frame
	 */
	public void setFrame(ImageResource ir)
	{
		this.setUrl(ir.getSafeUri());
	}
	
	/**
	 * Sets the frame.
	 *
	 * @param url the new frame
	 */
	public void setFrame(String url)
	{
		this.setUrl(url);
	}
}

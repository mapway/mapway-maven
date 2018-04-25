package com.ksyzt.gwt.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.resources.client.TextResource;

// TODO: Auto-generated Javadoc

/**
 * The Interface SysResource.
 */
public interface SysResource extends ClientBundle {

    /**
     * The Constant INSTANCE.
     */
    public final static SysResource INSTANCE = GWT.create(SysResource.class);

    /**
     * Flash source.
     *
     * @return the text resource
     */
    @Source("flash.txt")
  TextResource flashSource();

    /**
     * Gets the css.
     *
     * @return the css
     */
    @Source("style.css")
  @CssResource.NotStrict
  CssStyle getCss();

    /**
     * Logo.
     *
     * @return the image resource
     */
    @Source("images/logo.png")
  ImageResource logo();

    /**
     * Back 64.
     *
     * @return the image resource
     */
    @Source("images/64back.png")
  ImageResource back64();

    /**
     * Upload.
     *
     * @return the image resource
     */
    @Source("images/upload.png")
  ImageResource upload();

    /**
     * Delete.
     *
     * @return the image resource
     */
    @Source("images/delete.png")
  ImageResource delete();

    /**
     * Edits the.
     *
     * @return the image resource
     */
    @Source("images/edit.png")
  ImageResource edit();

    /**
     * Img operator.
     *
     * @return the image resource
     */
    @Source("images/operator.png")
  ImageResource imgOperator();

    /**
     * Img system.
     *
     * @return the image resource
     */
    @Source("images/system.png")
  ImageResource imgSystem();

    /**
     * Img mall.
     *
     * @return the image resource
     */
    @Source("images/mall.png")
  ImageResource imgMall();

    /**
     * Dragger image resource.
     *
     * @return the image resource
     */
    @Source("images/dragger.png")
  ImageResource dragger();

    /**
     * Img btn exit.
     *
     * @return the image resource
     */
    @Source("images/btn_exit.png")
  ImageResource img_btn_exit();

    /**
     * Img btn exit over.
     *
     * @return the image resource
     */
    @Source("images/btn_exit_over.png")
  ImageResource img_btn_exit_over();

    /**
     * Topbar back.
     *
     * @return the image resource
     */
    @Source("images/topbar_back.png")
  @ImageOptions(repeatStyle = RepeatStyle.Horizontal, height = 30, width = 41)
  ImageResource topbar_back();

    /**
     * Frameback.
     *
     * @return the image resource
     */
    @Source("images/frameback.png")
  @ImageOptions(repeatStyle = RepeatStyle.Both, height = 150, width = 150)
  ImageResource frameback();

    /**
     * Item selected back.
     *
     * @return the image resource
     */
    @Source("images/item_selected.png")
  @ImageOptions(repeatStyle = RepeatStyle.Horizontal, height = 66, width = 170)
  ImageResource item_selected_back();

    /**
     * Col catalog.
     *
     * @return the image resource
     */
    @Source("images/col_catalog.png")
  ImageResource col_catalog();

    /**
     * Col list.
     *
     * @return the image resource
     */
    @Source("images/col_list.png")
  ImageResource col_list();

    /**
     * Col picture.
     *
     * @return the image resource
     */
    @Source("images/col_picture.png")
  @ImageOptions(height = 16, width = 16)
  ImageResource col_picture();

    /**
     * Col video.
     *
     * @return the image resource
     */
    @Source("images/col_video.png")
  ImageResource col_video();

    /**
     * Col article.
     *
     * @return the image resource
     */
    @Source("images/col_article.png")
  ImageResource col_article();

    /**
     * Lightness.
     *
     * @return the image resource
     */
    @Source("images/lightness.png")
  ImageResource lightness();

    /**
     * Switchon.
     *
     * @return the image resource
     */
    @Source("images/switch_on.png")
  ImageResource switchon();

    /**
     * Switchoff.
     *
     * @return the image resource
     */
    @Source("images/switch_off.png")
  ImageResource switchoff();

    /**
     * Newicon.
     *
     * @return the image resource
     */
    @Source("images/new.gif")
  ImageResource newicon();

    /**
     * Uptop.
     *
     * @return the image resource
     */
    @Source("images/uptop.png")
  ImageResource uptop();

    /**
     * Hue saturation.
     *
     * @return the image resource
     */
    @Source("images/hueSaturation.png")
  ImageResource hueSaturation();

    /**
     * Right shadow.
     *
     * @return the image resource
     */
    @Source("images/right_shadow.png")
  @ImageOptions(repeatStyle = RepeatStyle.Vertical, height = 10, width = 13)
  ImageResource right_shadow();
}

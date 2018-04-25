package cn.ennwifi.webframe.ui.client.data;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.common.ImageUploader;
import cn.ennwifi.webframe.ui.client.resource.SysResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;

/**
 * 图片条目组件
 *
 * @author zhangjianshe
 */
public class PictureItem extends MessageComposite {

  private static PictureItemUiBinder uiBinder = GWT.create(PictureItemUiBinder.class);

    /**
     * The interface Picture item ui binder.
     */
    interface PictureItemUiBinder extends UiBinder<Widget, PictureItem> {
  }

  private BlurHandler valueChanged = new BlurHandler() {

    @Override
    public void onBlur(BlurEvent event) {
      doBlur();
    }
  };
  private KeyDownHandler keyDownHandler = new KeyDownHandler() {

    @Override
    public void onKeyDown(KeyDownEvent event) {
      if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
        doBlur();
      }
    }
  };

  private void doBlur() {
    if (txtValue.getValue() == null || txtValue.getValue().length() == 0) {

    } else {
      lbTitle.setText(txtValue.getValue());
      mData.title(txtValue.getValue());

    }
    txtValue.setVisible(false);
    lbTitle.setVisible(true);
  }

    /**
     * Instantiates a new Picture item.
     */
    public PictureItem() {
    initWidget(uiBinder.createAndBindUi(this));
    txtValue.addBlurHandler(valueChanged);
    txtValue.addKeyDownHandler(keyDownHandler);
    delete.setUrl(SysResource.INSTANCE.delete().getSafeUri());
  }

    /**
     * The Delete.
     */
    @UiField
  Image delete;
    /**
     * The Img.
     */
    @UiField
  Image img;
    /**
     * The Lb title.
     */
    @UiField
  Label lbTitle;

    /**
     * The Link.
     */
    @UiField
  Anchor link;

    /**
     * The M data.
     */
    PicData mData;

    /**
     * Sets value.
     *
     * @param pd the pd
     */
    public void setValue(PicData pd) {
    mData = pd;
    if (ImageUploader.isPicture(mData.url())) {
      img.setVisible(true);
      img.setUrl(ClientContext.getContext().getConfigure().getImagePrefix() + pd.url());
      link.setVisible(false);
    } else {
      img.setVisible(false);
      link.setVisible(true);
      link.setHref(ClientContext.getContext().getConfigure().getImagePrefix() + mData.url());
      link.setTarget("_blank");
    }
    lbTitle.setText(pd.title());

  }


    /**
     * Gets value.
     *
     * @return the value
     */
    public PicData getValue() {
    return mData;
  }

    /**
     * On click.
     *
     * @param e the e
     */
    @UiHandler("lbTitle")
  void onClick(ClickEvent e) {
    lbTitle.setVisible(false);
    txtValue.setVisible(true);
    txtValue.setValue(mData.title());
    txtValue.setFocus(true);
  }

    /**
     * The Txt value.
     */
    @UiField
  TextBox txtValue;

    /**
     * On delete.
     *
     * @param e the e
     */
    @UiHandler("delete")
  void onDelete(ClickEvent e) {
    MessageEvent ev = new MessageEvent(MessageEvent.DELETE, mData);
    fireEvent(ev);
  }
}

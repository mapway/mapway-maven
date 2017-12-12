package cn.ennwifi.webframe.ui.client.common;

import cn.ennwifi.webframe.ui.client.data.PicData;
import cn.ennwifi.webframe.ui.client.data.Pics;
import cn.ennwifi.webframe.ui.client.data.PictureItem;
import cn.ennwifi.webframe.ui.shared.module.UploadFileReturn;
import cn.mapway.ui.client.widget.common.IValidator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * 多图片组件
 * @author zhangjianshe
 *
 */
public class Pictures extends MessageComposite implements IValidator {

  private static PicturesUiBinder uiBinder = GWT.create(PicturesUiBinder.class);

  interface PicturesUiBinder extends UiBinder<Widget, Pictures> {
  }

  private MessageHandler imgHandler = new MessageHandler() {

    @Override
    public void onMessage(Object sender, Integer message, Object value) {
      if (message == MessageEvent.OK) {
        UploadFileReturn r = (UploadFileReturn) value;
        PicData pd = PicData.create();
        pd.url(r.relPath);
        pd.title(r.fileName);
        pics.push(pd);
        updateData(true);
        uploader.setUrl("");
      }
    }
  };

  public Pictures() {
    initWidget(uiBinder.createAndBindUi(this));
    uploader.addAcceptFileExtensions("pdf", "doc", "docx", "txt", "zip", "gz", "cad", "xlsx",
        "xls", "ppt", "pptx");
    pics = Pics.create();
    uploader.setUrl("");
    uploader.addMessageHandler(imgHandler);
    uploader.setPixelSize(220, 220);
  }

  @UiField
  HTMLPanel pPictures;

  Pics pics;

  private MessageHandler itemHandler = new MessageHandler() {

    @Override
    public void onMessage(Object sender, Integer message, Object value) {
      if (message == MessageEvent.DELETE) {
        PictureItem item = (PictureItem) sender;
        pPictures.remove(item);
        updateData(false);
      }
    }
  };

  /**
   * JSON 格式 [{title:"","url":""},{title:"","url":""}]
   * @param pictures
   */
  public void setValue(String pictures) {
    pics = Pics.fromJson(pictures);
    updateData(true);
  }

  private void updateData(boolean toUI) {
    if (toUI) {
      pPictures.clear();

      for (int i = 0; i < pics.length(); i++) {
        PicData pd = pics.get(i);
        PictureItem item = new PictureItem();
        item.addMessageHandler(itemHandler);
        item.setValue(pd);
        pPictures.add(item);
      }

    } else {
      pics = Pics.create();
      for (int i = 0; i < pPictures.getWidgetCount(); i++) {
        Widget item = pPictures.getWidget(i);
        if (item instanceof PictureItem) {
          PictureItem pi = (PictureItem) item;
          pics.push(pi.getValue());
        }
      }
    }
    if (pics.length() < maxLength) {
      uploader.setVisible(true);
      pPictures.add(uploader);
    } else {
      uploader.setVisible(false);
    }
  }

  public String getValue() {
    updateData(false);
    return pics.toJson();
  }

  @UiField
  ImageUploader uploader;



  @Override
  public void setMessage(String msg) {
    this.msg = msg;
  }

  private String msg = "";
  private boolean required = false;

  @Override
  public String getMessage() {
    return msg;
  }

  @Override
  public void setPattern(String pattern) {

  }

  @Override
  public void setRequired(boolean b) {
    required = b;
  }

  @Override
  public boolean isValidate() {

    if (required) {
      updateData(false);
      if (pics.length() == 0) {
        return false;
      }

      if (minLength != 0) {
        if (pics.length() < minLength) {
          return false;
        }
      }

      if (maxLength != null) {
        if (pics.length() > maxLength) {
          return false;
        }

      }

      return true;

    } else {
      return true;
    }
  }


  Integer minLength = null;
  Integer maxLength = 5;


  /**
   * 设置最小个数
   * @param minLength
   */
  public void setMinLength(int minLength) {
    this.minLength = minLength;

  }

  /**
   * 设置最大个数
   * @param maxLength
   */
  public void setMaxLength(int maxLength) {
    this.maxLength = maxLength;
  }

}

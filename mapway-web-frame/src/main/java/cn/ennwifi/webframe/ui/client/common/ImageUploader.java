package cn.ennwifi.webframe.ui.client.common;

import java.util.ArrayList;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.resource.SysResource;
import cn.ennwifi.webframe.ui.shared.module.UploadFileReturn;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;


/**
 * 图像上传组件,上传后自动展示该图片, 上传开始 报告 SUBMIT事件 上传完成 报告OK事件.
 *
 * @author zhangjianshe
 */
public class ImageUploader extends MessageComposite {

  /** The Constant EMPTY_PICTURE. */
  public static final String EMPTY_PICTURE = GWT.getModuleBaseURL() + "../img/empty.png";

  /** The Constant DEFAULT_ACTION. */
  public static final String DEFAULT_ACTION = GWT.getModuleBaseURL() + "../fileupload";

  /** The ui binder. */
  private static ImageUploaderUiBinder uiBinder = GWT.create(ImageUploaderUiBinder.class);

  /**
   * The Interface ImageUploaderUiBinder.
   */
  interface ImageUploaderUiBinder extends UiBinder<Widget, ImageUploader> {
  }

  /** The action. */
  private String action = "";

  /** The extra. */
  String extra = "";

  /** The relpath. */
  String relpath = "";

  /**
   * 判断文件是否可以被接受.
   *
   * @param filename the filename
   * @return the string
   */
  protected String isFileAcceptable(String filename) {
    int index = 0;
    index = filename.lastIndexOf('.');
    if (index <= 0) {
      return "不能判断上传的文件格式";
    }
    String fileext = filename.substring(index + 1);
    for (String e : acceptableFiles) {
      if (e.compareToIgnoreCase(fileext) == 0) {
        return "";
      }
    }
    return "不能上传文件格式" + fileext;
  }

  /** The acceptable files. */
  ArrayList<String> acceptableFiles = new ArrayList<String>();

  /**
   * 清楚可接受的文件格式.
   */
  public void clearAcceptFileExtension() {
    acceptableFiles.clear();
  }

  /**
   * 添加可以接受的上传文件格式.
   *
   * @param ext 可以接受的上传文件后缀 不需要添加 . 如 pdf png apk etc..
   */
  public void addAcceptFileExtension(String ext) {
    acceptableFiles.add(ext);
  }

  /**
   * 添加可以接受的上传文件格式.
   *
   * @param exts 可以接受的上传文件后缀 不需要添加 . 如 pdf png apk etc..
   */
  public void addAcceptFileExtensions(String... exts) {
    for (String ext : exts) {
      acceptableFiles.add(ext);
    }
  }

  /** The file change. */
  private ChangeHandler fileChange = new ChangeHandler() {

    @Override
    public void onChange(ChangeEvent event) {
      if (uploader.getFilename() == null || uploader.getFilename().length() == 0) {
        return;
      }
      String msg = isFileAcceptable(uploader.getFilename());
      if (msg.length() > 0) {
        ClientContext.getContext().confirm("上传文件", "不支持文件类型" + uploader.getFilename(), null);
        return;
      }
      if (action.length() > 0) {
        StringBuilder sb = new StringBuilder();
        sb.append("extra=" + URL.encodeQueryString(extra));
        sb.append("&relPath=" + URL.encodeQueryString(relpath));
        String actionurl = action + "?" + sb.toString();
        form.setAction(actionurl);
        form.submit();
      } else {
        setImageTitle("没有设置上传网址");
      }
    }
  };

  /** The complete handler. */
  private SubmitCompleteHandler completeHandler = new SubmitCompleteHandler() {

    @Override
    public void onSubmitComplete(SubmitCompleteEvent event) {
      String data = event.getResults();
      UploadFileReturn r = new UploadFileReturn();
      JSONObject obj = (JSONObject) JSONParser.parseLenient(data);
      r.extra = obj.get("extra").isString().stringValue();
      r.retCode = (int) obj.get("retCode").isNumber().doubleValue();
      r.msg = obj.get("msg").isString().stringValue();
      r.relPath = obj.get("relPath").isString().stringValue();
      r.fileName = obj.get("fileName").isString().stringValue();
      if (r.retCode == 0) {
        MessageEvent ev = new MessageEvent(MessageEvent.OK, r);
        if (isPicture(r.relPath)) {
          img.setUrl(ClientContext.getContext().getConfigure().getImagePrefix() + r.relPath);
        }
        fireEvent(ev);
      } else {
        message(r.msg);
      }
      form.reset();
    }
  };
  private static String[] pictypes = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};

  /**
   * 是否是图片
   * @param url
   * @return
   */
  public static boolean isPicture(String url) {
    if (url == null || url.length() == 0) {
      return false;
    } else {
      String urllow = url.toLowerCase();

      for (int i = 0; i < pictypes.length; i++) {
        if (urllow.endsWith(pictypes[i])) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Instantiates a new image uploader.
   */
  public ImageUploader() {
    initWidget(uiBinder.createAndBindUi(this));
    this.setAction(DEFAULT_ACTION, "defualt");
    uploader.addChangeHandler(fileChange);
    form.addSubmitCompleteHandler(completeHandler);
    img.setUrl(SysResource.INSTANCE.empty().getSafeUri());
    img.addErrorHandler(new ErrorHandler() {

      @Override
      public void onError(ErrorEvent event) {
        img.setUrl(SysResource.INSTANCE.empty().getSafeUri());
      }
    });

    addAcceptFileExtension("png");
    addAcceptFileExtension("jpg");
    addAcceptFileExtension("bmp");
    addAcceptFileExtension("gif");
    btnUploader.setUrl(SysResource.INSTANCE.upload().getSafeUri());

  }

  /**
   * 设置上传的参数.
   *
   * @param action 远程接受文件的URL
   * @param relative 服务器保存的相对路径
   */
  public void setAction(String action, String relative) {
    this.action = action;
    if (relative == null || relative.length() == 0) {
      relative = "upload";
    }
    relpath = relative;
  }

  /** The img. */
  @UiField
  Image img;

  /** The lb title. */
  @UiField
  Label lbTitle;

  /** The uploader. */
  @UiField
  FileUpload uploader;

  /** The form. */
  @UiField
  FormPanel form;

  /**
   * Sets the url.
   *
   * @param url the new url
   */
  public void setUrl(String url) {
    if (url == null || url.length() == 0) {
      img.setUrl(EMPTY_PICTURE);
    } else {
      img.setUrl(url);
    }
  }

  public String getUrl() {
    return img.getUrl();
  }

  /**
   * Sets the image title.
   *
   * @param string the new image title
   */
  public void setImageTitle(String string) {
    lbTitle.setText(string);
  }
  
  public String getImageTitle(){
	  return lbTitle.getText();
  }

  /**
   * Sets the extra.
   *
   * @param value the new extra
   */
  public void setExtra(String value) {
    extra = (value);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.UIObject#setPixelSize(int, int)
   */
  @Override
  public void setPixelSize(int width, int height) {
    img.setPixelSize(width, height - 30);
    super.setPixelSize(width, height);
  }

  /** The btn uploader. */
  @UiField
  Image btnUploader;

  /**
   * @param url
   */
  public void setUrl(SafeUri url) {
    img.setUrl(url);
    btnUploader.setVisible(false);
  }
}

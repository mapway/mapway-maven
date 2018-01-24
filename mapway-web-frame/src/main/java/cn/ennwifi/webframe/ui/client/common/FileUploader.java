package cn.ennwifi.webframe.ui.client.common;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;

import cn.ennwifi.webframe.ui.shared.module.UploadFileReturn;


/**
 * 文件上传组件.
 *
 * @author zhangjianshe
 */
public class FileUploader extends MessageComposite {

  /** The ui binder. */
  private static FileUploaderUiBinder uiBinder = GWT.create(FileUploaderUiBinder.class);

  /**
   * The Interface FileUploaderUiBinder.
   */
  interface FileUploaderUiBinder extends UiBinder<Widget, FileUploader> {}

  /**
   * Instantiates a new file uploader.
   */
  public FileUploader() {
    initWidget(uiBinder.createAndBindUi(this));

    this.setAction(DEFAULT_ACTION, "default");
    uploader.addChangeHandler(fileChange);
    form.addSubmitCompleteHandler(completeHandler);
  }

  /** The Constant DEFAULT_ACTION. */
  public static final String DEFAULT_ACTION = GWT.getModuleBaseURL() + "../fileupload";

  /** The action. */
  private String action = "";

  /** The extra. */
  String extra = "";

  /** The relpath. */
  String relpath = "";

  /** The file change. */
  private ChangeHandler fileChange = new ChangeHandler() {

    @Override
    public void onChange(ChangeEvent event) {
      if (uploader.getFilename() == null || uploader.getFilename().length() == 0) {
        return;
      }
      String msg = isFileAcceptable(uploader.getFilename());
      if (msg.length() > 0) {
        message(msg);
        return;
      }
      if (action.length() > 0) {
        StringBuilder sb = new StringBuilder();
        sb.append("extra=" + URL.encodeQueryString(extra));
        sb.append("&relPath=" + URL.encodeQueryString(relpath));
        sb.append("&randomName=" + (mEnabledRandomName == true ? "1" : "0"));
        String actionurl = action + "?" + sb.toString();
        form.setAction(actionurl);
        form.submit();
        MessageEvent ev = new MessageEvent(MessageEvent.SUBMIT, null);
        fireEvent(ev);
      } else {
        setFileTitle("没有设置上传网址");
      }
    }
  };

  /** The complete handler. */
  private SubmitCompleteHandler completeHandler = new SubmitCompleteHandler() {

    @Override
    public void onSubmitComplete(SubmitCompleteEvent event) {
      String data = event.getResults();
      UploadFileReturn r = new UploadFileReturn();

      JSONObject obj = (JSONObject) JSONParser.parseStrict(data);
      r.extra = obj.get("extra").isString().stringValue();
      r.retCode = (int) obj.get("retCode").isNumber().doubleValue();
      r.msg = obj.get("msg").isString().stringValue();
      r.relPath = obj.get("relPath").isString().stringValue();
      r.md5 = obj.get("md5").isString().stringValue();
      String size = obj.get("size").isNumber().doubleValue() + "";

      Long l = Long.parseLong(size);
      r.size = l;
      if (r.retCode == 0) {
        MessageEvent ev = new MessageEvent(MessageEvent.OK, r);
        setFileTitle(r.relPath);
        fireEvent(ev);
      } else {
        message(r.msg);
      }

      form.reset();

    }
  };

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
   * Clean.
   */
  public void clean() {
    lbTitle.setText("");
  }

  /**
   * Sets the file title.
   *
   * @param string the new file title
   */
  public void setFileTitle(String string) {
    lbTitle.setText(string);
  }

  /**
   * Sets the extra.
   *
   * @param value the new extra
   */
  public void setExtra(String value) {
    extra = (value);
  }

  /**
   * Gets the file title.
   *
   * @return the file title
   */
  public String getFileTitle() {
    return lbTitle.getText();
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

  /** The m enabled random name. */
  private Boolean mEnabledRandomName = true;

  /**
   * Enable random name.
   *
   * @param enable the enable
   */
  public void enableRandomName(Boolean enable) {
    mEnabledRandomName = enable;
  }
}

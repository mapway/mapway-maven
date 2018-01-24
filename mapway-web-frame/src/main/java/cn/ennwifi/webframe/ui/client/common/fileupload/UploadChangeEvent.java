package cn.ennwifi.webframe.ui.client.common.fileupload;

import java.io.Serializable;

public class UploadChangeEvent implements Serializable {
  private static final long serialVersionUID = -7651323870985523683L;
  private String            fileName;
  private Integer           percentage;

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Integer getPercentage() {
    return percentage;
  }

  public void setPercentage(Integer percentage) {
    this.percentage = percentage;
  }

  @Override
  public String toString() {
    return this.fileName + "-" + this.percentage + "%";
  }
}

package cn.ennwifi.webframe.ui.client.common.fileupload;

import java.io.Serializable;

/**
 * The type Upload change event.
 */
public class UploadChangeEvent implements Serializable {
    private static final long serialVersionUID = -7651323870985523683L;
    private String fileName;
    private Integer percentage;

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets file name.
     *
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets percentage.
     *
     * @return the percentage
     */
    public Integer getPercentage() {
        return percentage;
    }

    /**
     * Sets percentage.
     *
     * @param percentage the percentage
     */
    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return this.fileName + "-" + this.percentage + "%";
    }
}

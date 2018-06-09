package com.ksyzt.gwt.server.fileupload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;


// TODO: Auto-generated Javadoc

/**
 * 上传监听器.
 *
 * @author zhangjianshe @gmail.com
 */
public class FileUploadListener implements ProgressListener {

    /**
     * The session.
     */
    private HttpSession session;

    /**
     * The mega bytes.
     */
    private long megaBytes = -1;

    /**
     * Instantiates a new file upload listener.
     *
     * @param request the request
     */
    public FileUploadListener(HttpServletRequest request) {
        session = request.getSession();
        FileUploadStatus newUploadStatus = new FileUploadStatus();
        session.setAttribute("upladeStatus", newUploadStatus);
    }

    /**
     * 为了进度条监听器不会引起性能问题 解决方案,是减少进步条的活动数 比如，只有当上传了1兆字节的时候才反馈给用户.
     *
     * @param pBytesRead     the bytes read
     * @param pContentLength the content length
     * @param pItems         the items
     */
    public void update(long pBytesRead, long pContentLength, int pItems) {
        /*
         * long mBytes = pBytesRead / 1048576; if (megaBytes == mBytes) {
         * return; } megaBytes = mBytes;
         */
        FileUploadStatus status = (FileUploadStatus) session
                .getAttribute("upladeStatus");
        if (pContentLength == -1) {
            status.setStatusMsg("已完成" + pItems + "个文件的上传");
        } else {
            status.setStatusMsg("正在上传第" + pItems + "个文件");
        }
        status.setError("0");
        status.setReadedBytes(pBytesRead);
        status.setTotalBytes(pContentLength);
        status.setCurrentItem(pItems);
    }

}

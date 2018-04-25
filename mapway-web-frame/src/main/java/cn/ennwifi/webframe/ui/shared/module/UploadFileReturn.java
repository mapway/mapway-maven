package cn.ennwifi.webframe.ui.shared.module;


/**
 * The type Upload file return.
 */
public class UploadFileReturn {


    /**
     * Instantiates a new upload file return.
     */
    public UploadFileReturn() {

        retCode = 1;

        msg = "unknown error";

    }


    /**
     * 客户端上传的 extra数据.
     */
    public String extra;


    /**
     * 返回代码 0成功 其他错误.
     */
    public int retCode;


    /**
     * 错误消息.
     */
    public String msg;


    /**
     * 相对路径.
     */
    public String relPath;


    /**
     * MD5.
     */
    public String md5;


    /**
     * 相对路径.
     */
    public long size;


    /**
     * 原始文件名
     */
    public String fileName;


}
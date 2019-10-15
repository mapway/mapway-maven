package cn.mapway.doc.parser.exception;

/**
 * 文件未找到 Exception
 */
public class NotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Exception e) {
        super(msg + " because of " + e.toString());
    }

}

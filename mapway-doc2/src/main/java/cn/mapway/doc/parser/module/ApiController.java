package cn.mapway.doc.parser.module;

/**
 * 控制器的基本信息
 */
public class ApiController extends ApiData {

    /**
     * 控制器类名称
     */
    public String clzName;

    public String url;

    public ApiController() {
        url = "";
        clzName = "";
    }

}

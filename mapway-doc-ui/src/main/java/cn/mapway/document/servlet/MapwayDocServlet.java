package cn.mapway.document.servlet;

import cn.mapway.document.helper.DocHelper;
import cn.mapway.document.helper.ParseType;
import cn.mapway.document.helper.Scans;
import cn.mapway.document.module.ApiDoc;
import cn.mapway.document.parser.GenContext;
import cn.mapway.document.resource.Template;
import org.nutz.json.Json;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Streams;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;


/**
 * 文档服务入口.
 *
 * @author zhangjianshe
 */
public class MapwayDocServlet extends HttpServlet {


    /**
     * 日志记录器.
     */
    private static Log log = Logs.getLog("Mapway-Document");

    /**
     * 文档上下文环境.
     */
    private GenContext context;

    /**
     * 需要扫描的包名称.
     */
    private String packageNames;

    /**
     * connector 包名.
     */
    private String connectorPackageName;

    /**
     * connector 类名.
     */
    private String connectorClassName;

    /**
     * 用于编译的ANT HOME.
     */
    private String antHome;

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of the object.
     */
    public MapwayDocServlet() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    @Override
    public void destroy() {
        super.destroy();

    }

    /**
     * The doGet method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // |-------- Server-----|--app----|servlet| pathinfo
        // PathInfo http://www.ennwifi.cn/mapwaydoc/doc/ demo/123
        String pathInfo = request.getPathInfo();
        dispatch(pathInfo, request, response);

    }

    /**
     * 调度文档命令
     * <p>
     * the path
     *
     * @param request  the request
     * @param response the response
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (Strings.isBlank(path) || path.equals("/") || path.equals("/index")
                || path.equals("/index.html")) {
            genhtml(request, response);
            return;
        }
        if (path.endsWith("/clear.cache.gif")) {
            byteout(response, DocHelper.getClearGifData(), null);
        } else if (path.equals("/help")) {
            html(response, Template.readTemplate("/main/java/mapway/document/resource/help.html"));
        } else if (path.equals("/data")) {
            DocHelper helper = new DocHelper();
            ApiDoc api = helper.toDoc(ParseType.PT_SPRING, context, packageNames);
            json(response, api);
        } else if (path.startsWith("/javascript")) {
            String exportName = request.getParameter("apiName");
            if (Strings.isBlank(exportName)) {
                exportName = "Api";
            }
            DocHelper helper = new DocHelper();
            String data = helper.toJavascript(ParseType.PT_SPRING, context, packageNames, exportName);
            out(response, data, "text/javascript");
        } else if (path.startsWith("/ref/")) {
            // 读取图片资源
            String url = path.substring(5);
            int pos = url.lastIndexOf('/');
            String packageName = url.substring(0, pos);
            String resName = url.substring(pos + 1);
            byte[] data = Scans.readBinResource(packageName, resName);
            byteout(response, data, null);
        }

    }

    /**
     * Gets the download local.
     *
     * @param req the req
     * @return the download local
     */
    private static String getDownloadLocal(HttpServletRequest req) {
        String root = req.getServletContext().getRealPath("/");
        String downloadPath = root + "/download/jar";
        Files.createDirIfNoExists(downloadPath);
        return downloadPath;
    }

    /**
     * 输出文档HTML页面.
     *
     * @param request  the request
     * @param response the response
     */
    private void genhtml(HttpServletRequest request, HttpServletResponse response) {

        File htmlFile = getCacheFileName();

        if (!htmlFile.exists()) {
            writeToLocal(htmlFile);
        }

        try {
            response.setCharacterEncoding("UTF-8");
            Streams.writeAndClose(response.getWriter(), Streams.utf8r(Streams.fileIn(htmlFile)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToLocal(File htmlFile) {
        DocHelper helper = new DocHelper();
        helper.setAntHome(antHome);

        ApiDoc api = helper.toDoc(ParseType.PT_SPRING, context, packageNames);


        // 设置下载目录

        String html = helper.genHTML(api);
        //save to local for reload

        Files.write(htmlFile, Streams.utf8r(Lang.ins(html)));
    }

    /**
     * Gets the cache file name.
     *
     * @return the cache file name
     */
    private File getCacheFileName() {

        return new File(getTempFolder() + "/html.data");
    }

    /**
     * Gets the doc root.
     *
     * @param request the request
     * @return the doc root
     */
    private String getDocRoot(HttpServletRequest request) {
        return getDownloadLocal(request);
    }

    /**
     * Byteout.
     *
     * @param response    the response
     * @param data        the data
     * @param contentType the content type
     */
    private void byteout(HttpServletResponse response, byte[] data, String contentType) {
        if (!Strings.isBlank(contentType)) {
            response.setContentType(contentType);

        }
        try {
            response.getOutputStream().write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Json.
     *
     * @param response the response
     * @param data     the data
     */
    private void json(HttpServletResponse response, Object data) {
        out(response, Json.toJson(data), "application/json");
    }

    /**
     * Html.
     *
     * @param response the response
     * @param data     the data
     */
    private void html(HttpServletResponse response, String data) {
        out(response, data, "text/html");
    }

    /**
     * Out.
     *
     * @param response    the response
     * @param data        the data
     * @param contentType the content type
     */
    private void out(HttpServletResponse response, String data, String contentType) {
        response.setContentType(contentType);
        response.setCharacterEncoding("UTF-8");
        try {
            Lang.writeAll(response.getWriter(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the base path.
     *
     * @param request the request
     * @return the base path
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String port = request.getServerPort() == 80 ? "" : (":" + request.getServerPort());

        String basePath = request.getScheme() + "://" + request.getServerName() + port + path + "/";
        return basePath;
    }

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 作者.
     */
    public final static String PARAM_AUTHOR = "author";

    /**
     * 标题.
     */
    public final static String PARAM_TITLE = "title";

    /**
     * 子标题.
     */
    public final static String PARAM_SUB_TITLE = "subtitle";

    /**
     * 接口URL基路径.
     */
    public final static String PARAM_BASE_PATH = "basePath";

    /**
     * 域名.
     */
    public final static String PARAM_DOMAIN = "domain";

    /**
     * ANT hOME路径.
     */
    public final static String PARAM_ANT_HOME = "antHome";

    /**
     * 扫描的包名，支持，号隔开多个包名.
     */
    public final static String PARAM_SCAN_PACKAGES = "scanPackages";

    /**
     * 连接的包名.
     */
    public final static String PARAM_CONNECTOR_PACKAGE_NAME = "connectorPackageName";

    /**
     * 连接的类名.
     */
    public final static String PARAM_CONNECTOR_CLASS_NAME = "connectorClassName";

    /**
     * 连接的类名.
     */
    public final static String PARAM_COPY_RIGHT = "copyright";

    /**
     * 是否允许生成JavaConnector.
     */
    public final static String PARAM_ENABLE_JAVA_CONNECTOR = "enableJavaConnector";

    /**
     * 是否允许生成GwtConnector.
     */
    public final static String PARAM_ENABLE_GWT_CONNECTOR = "enableGwtConnector";

    /**
     * 文档API版本.
     */
    public final static String PARAM_API_VERSION = "apiVersion";

    /**
     * 返回的URL.
     */
    public static final String PARAM_HOME_URL = "homeUrl";

    /**
     * LOGO base64 data
     */
    public static final String PARAM_LOGO_BASE64 = "logoBase64";


    /**
     * 样式文件,可以是 网络的文件，也可以直接是 css样式.
     */
    public static final String PARAM_CSS_STYLE = "cssStyle";

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    @Override
    public void init() throws ServletException {
        log.info("文档服务初始化");
        Enumeration<String> keys;
        keys = this.getInitParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            log(key + ">" + this.getInitParameter(key));
        }

        context = new GenContext();
        context.setAuthor(Strings.sBlank(this.getInitParameter(PARAM_AUTHOR)));
        context.setDocTitle(Strings.sBlank(this.getInitParameter(PARAM_TITLE)));
        context.setDomain(Strings.sBlank(this.getInitParameter(PARAM_DOMAIN)));
        context.setBasepath(Strings.sBlank(this.getInitParameter(PARAM_BASE_PATH)));
        antHome = Strings.sBlank(getInitParameter(PARAM_ANT_HOME));
        context.setSubtitle(Strings.sBlank(this.getInitParameter(PARAM_SUB_TITLE)));
        packageNames = Strings.sBlank(this.getInitParameter(PARAM_SCAN_PACKAGES));
        connectorPackageName = Strings.sBlank(this.getInitParameter(PARAM_CONNECTOR_PACKAGE_NAME));
        connectorClassName = Strings.sBlank(this.getInitParameter(PARAM_CONNECTOR_CLASS_NAME));

        context.setCopyright(Strings.sBlank(getInitParameter(PARAM_COPY_RIGHT)));
        context.setEnableJavaConnector(Strings.sBlank(getInitParameter(PARAM_ENABLE_JAVA_CONNECTOR)));
        context.setEnableGwtConnector(Strings.sBlank(getInitParameter(PARAM_ENABLE_GWT_CONNECTOR)));
        context.setLogobase64(Strings.sBlank(getInitParameter(PARAM_LOGO_BASE64)));
        context.setApiversion(Strings.sBlank(getInitParameter(PARAM_API_VERSION)));
        context.setHomeUrl(Strings.sBlank(getInitParameter(PARAM_HOME_URL)));
        context.setCssStyle(Strings.sBlank(getInitParameter(PARAM_CSS_STYLE)));

        // 删除临时文件
        File htmlCache = getCacheFileName();
        if (htmlCache.exists()) {
            htmlCache.delete();
        }
    }

    /**
     * Gets the temp folder.
     *
     * @return the temp folder
     */
    public String getTempFolder() {
        String folder = System.getProperty("java.io.tmpdir");
        System.out.printf("temporary fold >" + folder);
        return folder;
    }
}

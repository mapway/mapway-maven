package cn.ennwifi.webframe.ui.client.rpc;

import cn.ennwifi.webframe.ui.server.servlet.WebFrameServlet;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * 服务代理工厂类.
 *
 * @author zhangjianshe
 */
public class WebFrameProxy {

    /**
     * The proxy.
     */
    private static IWebFrameServiceAsync PROXY;
    private static RequestBuilderWithToken requestBuilder;

    /**
     * Gets the.
     *
     * @return the i ui server async
     */
    public static IWebFrameServiceAsync get() {
        if (PROXY == null) {
            PROXY = GWT.create(IWebFrameService.class);
            requestBuilder = new RequestBuilderWithToken();
            ServiceDefTarget t = (ServiceDefTarget) PROXY;
            t.setServiceEntryPoint("/" + WebFrameServlet.DEFAULt_WEB_FRAME_SERVER_PATH);
            t.setRpcRequestBuilder(requestBuilder);
        }
        return PROXY;
    }
}

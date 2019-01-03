package cn.mapway.document.ui.client.rpc;

import cn.mapway.document.ui.client.main.storage.LocalStorage;
import cn.mapway.document.ui.client.rpc.github.GithubUser;
import cn.mapway.document.ui.client.test.KeyValueObj;
import cn.mapway.document.ui.client.test.KeyValueObjs;
import cn.mapway.document.ui.client.test.TestPanel;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Github client for GWT
 */
public class Github {

    Map<String, String> createMap() {
        Map<String, String> vs = new HashMap<>();
        return vs;
    }

    Map<String, String> kv(Map<String, String> map, String key, String v) {
        map.put(key, v);
        return map;
    }

    Map<String, String> authority(Map<String, String> map, String userName, String pwd) {
        String d = userName + ":" + pwd;
        d = btoa(d);
        map.put("Authorization", "Basic " + d);
        return map;
    }


    native String btoa(String b64) /*-{
        return btoa(b64);
    }-*/;

    /**
     * 向服务器请求接口调用.
     *
     * @param <T>      the generic type
     * @param url      the url
     * @param jsonData the json data
     * @param handler  the handler
     * @throws RequestException the request exception
     * @Param headers
     */
    public <T extends JavaScriptObject> void post(final String url, String jsonData,
                                                  Map<String, String> headers, final IOnData<T> handler) throws RequestException {

        RequestBuilder builder =
                new RequestBuilder(RequestBuilder.POST,
                        URL.encode(url));
        if (headers != null) {
            for (String key : headers.keySet()) {
                builder.setHeader(key, headers.get(key));
            }
        }
        Request request = builder.sendRequest(jsonData, new RequestCallback() {
            @Override
            public void onError(Request request, Throwable exception) {
                handler.onError(url, exception.getMessage());
            }

            @Override
            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    String data = response.getText();
                    T obj = JsonUtils.unsafeEval(data);
                    handler.onSuccess(url, obj);
                } else {
                    handler.onError(url, response.getStatusText());
                }
            }
        });
    }

    /**
     * 向服务器请求接口调用.
     *
     * @param <T>     the generic type
     * @param url     the url
     * @param handler the handler
     * @throws RequestException the request exception
     * @Param headers
     */
    public <T extends JavaScriptObject> void get(final String url,
                                                 Map<String, String> headers, final IOnData<T> handler) throws RequestException {

        RequestBuilder builder =
                new RequestBuilder(RequestBuilder.GET,
                        URL.encode(url));
        if (headers != null) {
            for (String key : headers.keySet()) {
                builder.setHeader(key, headers.get(key));
            }
        }
        Request request = builder.sendRequest("", new RequestCallback() {
            @Override
            public void onError(Request request, Throwable exception) {
                handler.onError(url, exception.getMessage());
            }

            @Override
            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    String data = response.getText();
                    T obj = JsonUtils.unsafeEval(data);
                    handler.onSuccess(url, obj);
                } else {
                    handler.onError(url, response.getStatusText());
                }
            }
        });
    }

    public void login(String user, String pwd, IOnData<GithubUser> onData) {
        String url = "https://api.github.com/user";

        Map<String, String> header = createMap();
        authority(header, user, pwd);
        try {
            get(url, header, onData);
        } catch (RequestException e) {
            e.printStackTrace();
            onData.onError(url, e.getMessage());
        }
    }
}

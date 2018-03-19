package cn.ennwifi.webframe.ui.client.rpc;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;

import cn.ennwifi.webframe.ui.shared.module.RpcInfo;
import cn.ennwifi.webframe.ui.shared.repository.S_USERObj;


/**
 * Google rpc call with additional headers.
 *
 * @author zhangjianshe
 */
public class RequestBuilderWithToken extends RpcRequestBuilder {


    /**
     * Instantiates a new Request builder with token.
     */
    public RequestBuilderWithToken() {

  }

  @Override
  protected void doFinish(RequestBuilder rb) {
    super.doFinish(rb);
    S_USERObj user = cn.ennwifi.webframe.ui.client.ClientContext.getContext().getUser();

    if (user != null) {
      GWT.log("user>" + user.toString());
      rb.setHeader(RpcInfo.RPC_TOKEN, user.getToken());
      GWT.log("user>" + user.getToken());
    } else {
      GWT.log("user is null");
    }
  }
}

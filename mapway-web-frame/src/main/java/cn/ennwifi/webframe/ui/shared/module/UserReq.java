package cn.ennwifi.webframe.ui.shared.module;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

/**
 * 继承对象的请求 都要求进行用户验证.
 *
 * @author zhangjianshe
 */
@Doc("验证合法用户的请求")
public class UserReq {

    /**
     * The Token.
     */
    @ApiField("token")
    public String token;
}

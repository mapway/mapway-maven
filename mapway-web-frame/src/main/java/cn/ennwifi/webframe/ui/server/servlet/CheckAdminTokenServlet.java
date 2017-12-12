package cn.ennwifi.webframe.ui.server.servlet;

import cn.ennwifi.webframe.tools.Actions;
import cn.ennwifi.webframe.ui.shared.module.ClientConfigure;
import cn.ennwifi.webframe.ui.shared.module.DefaultClientConfigure;
import cn.ennwifi.webframe.ui.shared.module.RpcInfo;
import cn.ennwifi.webframe.ui.shared.repository.S_USERObj;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public abstract class CheckAdminTokenServlet extends RemoteServiceServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 需要提供一个DAO
     */
    public abstract Dao createDao();

    /**
     * 返回 一个客户端配置.
     *
     * @return
     */
    public ClientConfigure getClientConfigure() {
        return new DefaultClientConfigure();
    }

    /**
     *
     */
    public CheckAdminTokenServlet() {
        excludesMethod = new ArrayList<String>();
        extendCheckToken(excludesMethod);
    }

    public final static String SESSION_ADMIN_USER = "SESSION_ADMIN_USER";

    private Dao dao;

    /**
     * @return
     */
    public synchronized Dao getDao() {
        if (dao == null) {
            dao = createDao();
        }
        return dao;
    }

    /**
     * 检查TOKEN
     */
    @Override
    public String processCall(RPCRequest rpcRequest) throws SerializationException {
        this.log(rpcRequest.getMethod().getName());
        boolean canCall = checkToken(rpcRequest);
        if (canCall == false) {
            throw new SerializationException("非法TOKEN");
        }
        String r = "";
        try {
            r = super.processCall(rpcRequest);
        } catch (Exception e) {
            throw new SerializationException(e.getMessage());
        }

        return r;
    }

    /**
     * @param rpcRequest
     * @return
     */
    public boolean checkToken(RPCRequest rpcRequest) {

        boolean excluded = excludeFromCheckToken(rpcRequest);
        if (excluded == true) {
            return true;
        } else {
            // 需要检查TOKEN
            HttpServletRequest request = this.getThreadLocalRequest();
            String token = request.getHeader(RpcInfo.RPC_TOKEN);
            S_USERObj user = findUserByToken(token);
            if (user == null) {
                return false;
            }
            request.setAttribute(SESSION_ADMIN_USER, user);
            return true;
        }
    }

    /**
     * 从Request中获取用户信息.
     *
     * @return
     */
    public S_USERObj requestUser() {
        HttpServletRequest request = this.getThreadLocalRequest();
        S_USERObj user = (S_USERObj) request.getAttribute(SESSION_ADMIN_USER);
        return user;
    }

    /**
     * 从数据库中获取用户信息
     *
     * @param token
     * @return
     */
    private S_USERObj findUserByToken(String token) {

        S_USERObj user = getDao().fetch(S_USERObj.class, Cnd.where(S_USERObj.FLD_TOKEN, "=", token));

        return user;
    }

    private ArrayList<String> excludesMethod;

    /**
     * 检查过滤的TOKEN
     *
     * @param rpcRequest
     * @return
     */
    private boolean excludeFromCheckToken(RPCRequest rpcRequest) {
        String name = rpcRequest.getMethod().getName();
        for (String excludeName : excludesMethod) {
            if (excludeName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 子类重构
     *
     * @param methodList
     */
    public void extendCheckToken(List<String> methodList) {

    }

    public final String getBasePath() {
        String path = getThreadLocalRequest().getContextPath();
        String basePath =
                getThreadLocalRequest().getScheme() + "://" + getThreadLocalRequest().getServerName() + ":"
                        + getThreadLocalRequest().getServerPort() + path + "/";
        return basePath;
    }

    /**
     * 日志记录方法
     *
     * @param moduleName 模块名称
     * @param subModule  子模块名称 可以为空
     * @param action     动作枚举值
     * @param detail     操作详情
     */
    public void log(String moduleName, String subModule, Actions action, String detail) {
    }
}

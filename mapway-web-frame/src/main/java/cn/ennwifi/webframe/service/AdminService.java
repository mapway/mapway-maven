package cn.ennwifi.webframe.service;

import cn.ennwifi.webframe.tools.Times;
import cn.ennwifi.webframe.ui.shared.module.*;
import cn.ennwifi.webframe.ui.shared.repository.S_METAObj;
import cn.ennwifi.webframe.ui.shared.repository.S_RESOURCEObj;
import cn.ennwifi.webframe.ui.shared.repository.S_USERObj;
import cn.ennwifi.webframe.ui.server.servlet.CheckAdminTokenServlet;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.OrderBy;
import org.nutz.dao.sql.Sql;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * 管理后台登录.
 *
 * @author zhangjianshe
 */

public class AdminService {

    /**
     * ss.
     *
     * @param dao
     * @param clientConfigure
     */
    public AdminService(Dao dao, ClientConfigure clientConfigure) {
        this.dao = dao;
        this.uiProperties = clientConfigure;
    }

    public static final String MODULE_CODE_SYSTEM = "030601";

    private static final String CURRENT_ADMIN = "CURRENT_ADMIN";

    /**
     * 存储在Cookie中的值
     */
    public static final String USER_TOKEN = "USER_TOKEN";

    /**
     * The dao.
     */

    private Dao dao;

    private ClientConfigure uiProperties;

    /**
     * 用户登录过程.
     *
     * @param req     the req
     * @param request the request
     * @return the login resp data
     */
    public AdminLoginResponse login(LoginReqData req, HttpServletRequest request,
                                    HttpServletResponse response) {
        AdminLoginResponse resp = new AdminLoginResponse();
        S_USERObj user = new S_USERObj();

        if (req.type.equals(UserLoginType.USER_LOGIN_TYPE_REGISTER)) {
            String pwd = Lang.md5(req.password);
            Cnd where = Cnd.where(S_USERObj.FLD_NAME, "=", req.username);
            where = where.and(S_USERObj.FLD_PWD, "=", pwd);
            user = dao.fetch(S_USERObj.class, where);
        }

        if (user == null) {
            throw new RuntimeException("登录失败 " + Json.toJson(req));
        } else {
            user.setUpdate_time(Times.now());
            user.setToken(UUID.randomUUID().toString());
            dao.update(user, "^" + S_USERObj.FLD_TOKEN + "|" + S_USERObj.FLD_LAST_TIME + "$");
            request.getSession(true).setAttribute(CheckAdminTokenServlet.SESSION_ADMIN_USER, user);

            resp.user = user;
        }
        processLoginResult(resp);
        return resp;
    }

    /**
     * 处理登录成功后返回的客户端配置信息
     *
     * @param response
     */
    private void processLoginResult(AdminLoginResponse response) {

        response.configure.imagePrefix = uiProperties.getImagePrefix();
        response.configure.imageUploadProxyUrl = uiProperties.getImageUploadProxyUrl();
        response.configure.mqttPath = uiProperties.getMqttPort();
        response.configure.mqttServer = uiProperties.getMqttServer();
        response.configure.mqttPath = uiProperties.getMqttPath();
        response.configure.compileInformation = uiProperties.getCompileInformation();
        response.configure.logo = uiProperties.getLogo();
    }

    /**
     * 根据TOKEN找到用户信息.
     *
     * @param token the token
     * @return the s USER obj
     */
    public S_USERObj findUserByToken(String token) {
        S_USERObj user = dao.fetch(S_USERObj.class, Cnd.where(S_USERObj.FLD_TOKEN, "=", token));
        return user;
    }

    /**
     * 退出登录.
     *
     * @param request  the request
     * @param req      the req
     * @param response the response
     * @return the resp data
     */
    public RespData logout(HttpServletRequest request, UserReq req, HttpServletResponse response) {
        RespData resp = new RespData();
        response.addCookie(new Cookie("token", ""));
        resp.ok();
        return resp;
    }

    /**
     * 用户拥有的功能菜单,根据权限进行查找 <br/>
     * 查找方法: 用户->role->S_ATHOrity.
     *
     * @param userId the userid
     * @return the list
     */
    public List<S_RESOURCEObj> userMainMenu(Long userId, Integer menuRootId) {

        String where =
                S_RESOURCEObj.FLD_ROOTID + " = " + menuRootId
                        + " and id in (select res_id from s_role_resource s"
                        + " where s.role_id in (select role_id from s_user_role where user_id=" + userId
                        + ")) order by pid asc, rank asc";

        Condition cnd = Cnd.wrap(where);
        List<S_RESOURCEObj> authorities = dao.query(S_RESOURCEObj.class, cnd);

        return authorities;
    }

    public List<S_RESOURCEObj> userMenu(Long userId) {
        String where =
                S_RESOURCEObj.FLD_PID + "=1" + " and id in (select res_id from admin_role_resource s"
                        + " where s.role_id in (select role_id from admin_user_role where user_id=" + userId
                        + ")) order by pid asc,rank asc";

        Condition cnd = Cnd.wrap(where);
        List<S_RESOURCEObj> authorities = dao.query(S_RESOURCEObj.class, cnd);

        return authorities;
    }

    /**
     * 用户查找菜单的子菜单 <br/>
     * 查找方法: 用户->role->S_ATHOrity.
     *
     * @param userId the userid
     * @return the list
     */
    public List<S_RESOURCEObj> userSubMenu(Long userId, Integer menuId) {

        Cnd where = Cnd.where(S_RESOURCEObj.FLD_PID, "=", menuId);
        where.asc(S_RESOURCEObj.FLD_RANK);
        List<S_RESOURCEObj> authorities = dao.query(S_RESOURCEObj.class, where);
        return authorities;
    }

    /**
     * 用户对资源是否拥有权限
     *
     * @param userId
     * @param resourceId
     * @return
     */
    public Boolean userOwnResource(Long userId, Long resourceId) {
        // 用户 角色 资源
        String strsql =
                "select count(rm.res_id) from admin_user_role ur,admin_role_resource rm where ur.role_id=rm.role_id and ur.user_id=@userId and rm.res_id=@resourceId";
        Sql sql = Sqls.create(strsql);
        sql.params().set("userId", userId).set("resourceId", resourceId);
        sql.setCallback(Sqls.callback.integer());
        dao.execute(sql);
        int count = sql.getInt(0);
        return count > 0;
    }

    /**
     * 直接查找子菜单
     *
     * @param menuId
     * @return
     */
    public List<S_RESOURCEObj> subMenu(int menuId) {

        Cnd where = Cnd.where(S_RESOURCEObj.FLD_PID, "=", menuId);
        where.asc(S_RESOURCEObj.FLD_RANK);
        List<S_RESOURCEObj> resources = dao.query(S_RESOURCEObj.class, where);
        return resources;
    }

    public List<S_METAObj> fetchAllMetaData() {
        OrderBy where = Cnd.orderBy().asc(S_METAObj.FLD_PID).asc(S_METAObj.FLD_RANK);
        return dao.query(S_METAObj.class, where);
    }

    public Boolean deleteMetaData(Integer metaId) throws ServerException {
        Cnd where = Cnd.where(S_METAObj.FLD_PID, "=", metaId);
        int count = dao.count(S_METAObj.class, where);
        if (count > 0) {
            throw new ServerException("不能删除有子节点的节点");
        } else {
            dao.delete(S_METAObj.class, metaId);
        }
        return true;
    }

    public S_METAObj saveOrUpdateMetaData(final S_METAObj meta) throws ServerException {
        if (meta == null) {
            throw new ServerException("创建元数据提供空数据");
        }
        if (meta.getId() == null) {
            dao.insert(meta);
            if (meta.getRoot_id() == null) {
                meta.setRoot_id(meta.getId());
                dao.update(meta);
            }
        } else {

            final S_METAObj old = dao.fetch(S_METAObj.class, meta.getId().intValue());

            // 需要更新所有子元素的CATALOG
            Trans.exec(new Atom() {

                @Override
                public void run() {

                    if (!old.getName().equals(meta.getName())) {
                        String catalogOld = old.getCatalog() + "/" + old.getName();
                        String catalogNew = old.getCatalog() + "/" + meta.getName();

                        String strsql =
                                "update " + S_METAObj.TBL_S_META + " set " + S_METAObj.FLD_CATALOG + "=replace("
                                        + S_METAObj.FLD_CATALOG + ",'" + catalogOld + "','" + catalogNew + "') where "
                                        + S_METAObj.FLD_CATALOG + " like '" + catalogOld + "%'";
                        Sql sql = Sqls.create(strsql);
                        dao.execute(sql);
                    }
                    dao.update(meta);
                }
            });

        }
        return meta;
    }

    public S_USERObj sessionUser(HttpServletRequest threadLocalRequest) {

        return (S_USERObj) threadLocalRequest.getAttribute(CURRENT_ADMIN);
    }

    /**
     * 搜索用户
     *
     * @param req
     * @return
     */
    public PagerData<S_USERObj> searchUserList(SearchReq req) {
        PagerData<S_USERObj> r = new PagerData<S_USERObj>();

        Pager pager = dao.createPager(req.getPageNumber(), req.getPageSize());
        Cnd where = Cnd.NEW();
        for (DataFilter df : req.filters) {
            where = where.and(df.name, df.op, df.value);
        }

        for (Pair<String, String> p : req.orders) {
            if ("asc".equalsIgnoreCase(p.value)) {
                where.asc(p.key);
            } else if ("desc".equalsIgnoreCase(p.value)) {
                where.desc(p.key);
            }
        }
        if (req.orders.size() == 0) {
            where.desc(S_USERObj.FLD_ID);
        }
        r.setData(dao.query(S_USERObj.class, where, pager));
        r.setPage(req.getPageNumber());
        r.setPageCount(req.getPageSize());
        if (req.getCount() != null && req.getCount() > 0) {
            r.setTotal(req.getCount().intValue());
        } else {
            r.setTotal(dao.count(S_USERObj.class, where));
        }
        return r;
    }

}

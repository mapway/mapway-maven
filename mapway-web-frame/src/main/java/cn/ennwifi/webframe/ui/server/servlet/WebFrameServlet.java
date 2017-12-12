package cn.ennwifi.webframe.ui.server.servlet;

import cn.ennwifi.webframe.service.AdminService;
import cn.ennwifi.webframe.tools.Actions;
import cn.ennwifi.webframe.tools.CookieTools;

import cn.ennwifi.webframe.tools.Times;
import cn.ennwifi.webframe.ui.client.rpc.IWebFrameService;
import cn.ennwifi.webframe.ui.shared.module.*;
import cn.ennwifi.webframe.ui.shared.repository.*;
import org.nutz.dao.Cnd;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.util.Daos;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * UI Service.
 *
 * @author zhangjianshe
 */
public abstract class WebFrameServlet extends CheckAdminTokenServlet implements IWebFrameService {

    /**
     * 缺省的WEBFrame Server URL映射路径
     */
    public final static String DEFAULt_WEB_FRAME_SERVER_PATH = "webFrameServer";


    /*
     * (non-Javadoc)
     *
     * @see cn.ennwifi.hangye.monitor.ui.server.servlet.CheckTokenServlet#
     * extendCheckToken(java.util.List)
     */
    @Override
    public void extendCheckToken(List<String> methodList) {
        super.extendCheckToken(methodList);

        methodList.add("adminLogin");
        methodList.add("sessionUser");
        methodList.add("getUserByToken");
    }

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The authority service.
     */
    private AdminService adminService;


    /**
     * 获取 Spring容器中的 认证对象.
     *
     * @return the authority service
     */
    private synchronized AdminService getAdminService() {
        if (adminService == null) {
            adminService = new AdminService(getDao(), getClientConfigure());
        }
        return adminService;
    }


    private final static String MODULE_SYSTEM_ADMIN = "系统管理";
    private final static String MODULE_RESOURCE = "资源处理";
    private final static String MODULE_METADATA = "元数据";
    private final static String MODULE_ACCOUNT = "账户";

    @Override
    public AdminLoginResponse adminLogin(String username, String pwd, String type)
            throws ServerException {
        LoginReqData req = new LoginReqData();
        req.username = username;
        req.password = pwd;
        req.type = type;

        AdminLoginResponse resp = null;
        try {
            resp = getAdminService().login(req, getThreadLocalRequest(), getThreadLocalResponse());
        } catch (Exception e) {
            String content = "尝试登陆系统失败" + e.getMessage();

            throw new ServerException(e.getMessage());
        }
        String content = "用户" + req.username + "登陆成功";

        processLoginResult(resp);
        return resp;
    }

    @Override
    public List<S_RESOURCEObj> getAllMenu() throws ServerException {
        List<S_RESOURCEObj> l =
                getDao().query(S_RESOURCEObj.class,
                        Cnd.NEW().asc(S_RESOURCEObj.FLD_PID).asc(S_RESOURCEObj.FLD_RANK));
        return l;
    }


    @Override
    public S_RESOURCEObj saveMenu(S_RESOURCEObj resource) throws ServerException {
        if (resource.getId() == null) {
            resource.setClick(0l);
            log(MODULE_SYSTEM_ADMIN, MODULE_RESOURCE, Actions.CREATE, Json.toJson(resource));
            getDao().insert(resource);

            if (resource.getRootid() == null) {
                resource.setRootid(resource.getId());
                getDao().update(resource);
                log(MODULE_SYSTEM_ADMIN, MODULE_RESOURCE, Actions.UPDATE, Json.toJson(resource));
            }
        } else {
            log(MODULE_SYSTEM_ADMIN, MODULE_RESOURCE, Actions.UPDATE, Json.toJson(resource));
            getDao().update(resource);
        }
        return resource;
    }

    @Override
    public void deleteResource(final Long resourceId) throws ServerException {
        final S_USERObj admin = requestUser();

        Trans.exec(new Atom() {
            @Override
            public void run() {
                S_RESOURCEObj res = getDao().fetch(S_RESOURCEObj.class, resourceId);
                int count =
                        getDao().count(S_RESOURCEObj.class, Cnd.where(S_RESOURCEObj.FLD_PID, "=", resourceId));
                if (count > 0) {
                    String content = "试图删除资源" + res.getName() + "失败，不能删除有子节点的目录";
                    systemLog(admin, Actions.UPDATE, content);
                    throw new ServerException("不能删除有子节点的目录");
                }


                Cnd where = Cnd.where(S_ROLE_RESOURCEObj.FLD_RES_ID, "=", resourceId);
                getDao().clear(S_ROLE_RESOURCEObj.class, where);
                getDao().delete(S_RESOURCEObj.class, resourceId);
                String content = "删除资源" + res.getName();
                systemLog(admin, Actions.UPDATE, content);
            }
        });
    }

    @Override
    public List<S_ROLEObj> getAllRole() throws ServerException {
        return getDao().query(S_ROLEObj.class, null);
    }

    @Override
    public S_ROLEObj saveAdminRole(S_ROLEObj role) throws ServerException {
        S_USERObj admin = requestUser();
        if (role.getId() == null) {
            Cnd where = Cnd.where(S_ROLEObj.FLD_NAME, "=", role.getName());
            S_ROLEObj roleS = getDao().fetch(S_ROLEObj.class, where);
            if (roleS != null) {
                throw new ServerException("服务器中已存在这个角色");
            }


            String content = "新建角色" + role.getName() + "成功";
            systemLog(admin, Actions.CREATE, content);

            getDao().insert(role);
        } else {
            Cnd where = Cnd.where("NAME", "=", role.getName());
            S_ROLEObj roleS = getDao().fetch(S_ROLEObj.class, where);
            if (roleS != null && !roleS.getId().equals(role.getId())) {
                throw new ServerException("服务器中已存在这个角色");
            }

            String content = "更新角色" + role.getName() + "成功";
            systemLog(admin, Actions.UPDATE, content);
            getDao().update(role);
        }
        return role;
    }

    @Override
    public boolean deleteAdminRole(Long roleid) throws ServerException {
        S_USERObj admin = requestUser();
        S_ROLEObj role = getDao().fetch(S_ROLEObj.class, roleid);
        if (role == null) {
            String content = "删除角色" + roleid + "失败";
            systemLog(admin, Actions.DELETE, content);
        }
        String content = "删除角色" + role.getName() + "成功";
        systemLog(admin, Actions.DELETE, content);
        getDao().delete(S_ROLEObj.class, roleid);
        return true;
    }

    @Override
    public Boolean updateAdminRoleMenu(Long roleid, Long resourceId, boolean addOrRemove)
            throws ServerException {
        S_USERObj admin = requestUser();
        Cnd where = Cnd.where(S_ROLE_RESOURCEObj.FLD_ROLE_ID, "=", roleid);
        where = where.and(S_ROLE_RESOURCEObj.FLD_RES_ID, "=", resourceId);

        if (addOrRemove) {
            S_ROLE_RESOURCEObj obj = getDao().fetch(S_ROLE_RESOURCEObj.class, where);
            if (obj == null) {
                obj = new S_ROLE_RESOURCEObj();
                obj.setRes_id(resourceId);
                obj.setRole_id(roleid);
                getDao().insert(obj);
                String content = "对角色" + roleid + "授权" + resourceId;
                systemLog(admin, Actions.CREATE, content);


            }
        } else {
            getDao().clear(S_ROLE_RESOURCEObj.class, where);
            String content = "对角色" + roleid + "取消授权" + resourceId;
            systemLog(admin, Actions.DELETE, content);

        }
        return true;
    }

    /**
     * 后台保存用户的配置信息
     */
    @Override
    public S_USERObj saveAdminUser(S_USERObj user) throws ServerException {


        if (user.getId() == null) {
            // 检查是否有相同的用户名
            S_USERObj adminUser =
                    getDao().fetch(S_USERObj.class, Cnd.where(S_USERObj.FLD_NAME, "=", user.getName()));
            // 检查前台是否有相同的用户名
            S_USERObj frontUser =
                    getDao().fetch(S_USERObj.class, Cnd.where(S_USERObj.FLD_NAME, "=", user.getName()));

            if (adminUser != null || frontUser != null) {
                throw new ServerException("数据库中有相同的用户名");
            }
            user.setPwd(Lang.md5(user.getPwd()));
            user.setUpdate_time(Times.now());

            // 检查真实名称
            if (Strings.isEmpty(user.getReal_name())) {
                user.setReal_name(user.getName());
            }
            user.setAccount_type("1");
            user.setToken(R.UU16());
            String hash = Lang.sha1(user.getName() + user.getAccount_type());
            user.setHash(hash);
            user.setEnabled(1);
            user.setTitle("");

            getDao().insert(user);

            String content = "用户" + user.getName() + "登陆 创建相关的前台用户";
            systemLog(user, Actions.CREATE, content);

        } else {
            // 名字不允许修改
            if (!Strings.isEmpty(user.getPwd())) {
                // 需要修改密码
                user.setPwd(Lang.md5(user.getPwd()));
                Daos.ext(getDao(), FieldFilter.locked(S_USERObj.class, "^real_name|update_time|token$"))
                        .update(user);
            } else {
                // 不许要修改密码
                Daos.ext(getDao(), FieldFilter.locked(S_USERObj.class, "^real_name|update_time|pwd|token$"))
                        .update(user);
            }
        }
        return user;
    }


    @Override
    public boolean updateAdminUserRole(Long userid, Long roleid, Boolean addOrRemove)
            throws ServerException {
        S_USERObj admin = requestUser();
        Cnd where = Cnd.where(S_USER_ROLEObj.FLD_ROLE_ID, "=", roleid);
        where = where.and(S_USER_ROLEObj.FLD_USER_ID, "=", userid);
        if (addOrRemove) {
            S_USER_ROLEObj obj = getDao().fetch(S_USER_ROLEObj.class, where);
            if (obj == null) {
                obj = new S_USER_ROLEObj();
                obj.setUser_id(userid);
                obj.setRole_id(roleid);
                getDao().insert(obj);
                String content = "对用户" + userid + "授权角色" + roleid;
                systemLog(admin, Actions.UPDATE, content);

            }
        } else {
            String content = "对用户" + userid + "取消角色授权" + roleid;
            systemLog(admin, Actions.UPDATE, content);

            getDao().clear(S_USER_ROLEObj.class, where);
        }
        return true;
    }

    @Override
    public List<S_USER_ROLEObj> getAdminUserRole(Long userid) throws ServerException {
        return getDao().query(S_USER_ROLEObj.class, Cnd.where(S_USER_ROLEObj.FLD_USER_ID, "=", userid));
    }

    @Override
    public PagerData<S_RESOURCEObj> adminUserMainMenu() throws ServerException {
        S_USERObj user = requestUser();
        List<S_RESOURCEObj> list = getAdminService().userMainMenu(user.getId(), 1);
        PagerData<S_RESOURCEObj> r = new PagerData<S_RESOURCEObj>();
        r.setData(list);
        return r;
    }


    @Override
    public List<S_RESOURCEObj> adminSubMenu(int menuId) throws ServerException {
        List<S_RESOURCEObj> menus = getAdminService().subMenu(menuId);
        return menus;
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.ennwifi.hangye.monitor.ui.client.rpc.IUiServer#getUserByToken(java. lang.String)
     */
    @Override
    public AdminLoginResponse getUserByToken(String token) throws ServerException {

        // 检查Session中是否有该用户

        S_USERObj admin = getAdminService().sessionUser(getThreadLocalRequest());
        if (admin != null) {
        } else {
            admin = getAdminService().findUserByToken(token);
        }

        AdminLoginResponse r = new AdminLoginResponse();
        r.user = admin;
        processLoginResult(r);
        return r;
    }

    /**
     * 处理登录成功后返回的客户端配置信息
     *
     * @param response
     */
    private void processLoginResult(AdminLoginResponse response) {

        if (response.user != null) {
            CookieTools.addCookie(getThreadLocalResponse(), "ADMIN-TOKEN", response.user.getToken(), "/",
                    -1);
            CookieTools
                    .addCookie(getThreadLocalResponse(), "USER-NAME", response.user.getName(), "/", -1);
            CookieTools.addCookie(getThreadLocalResponse(), AdminService.USER_TOKEN,
                    response.user.getToken(), "/", -1);
        } else {
            CookieTools.addCookie(getThreadLocalResponse(), AdminService.USER_TOKEN, "", "/", -1);
        }
    }


    /*
     * (non-Javadoc)
     *
     * @see cn.ennwifi.hangye.monitor.ui.client.rpc.IUiServer#getAuthorityInfo(java. lang.String)
     */
    @Override
    public S_RESOURCEObj getMenuInfo(Integer id) throws ServerException {
        Cnd where = Cnd.where(S_RESOURCEObj.FLD_ID, "=", id);
        return getDao().fetch(S_RESOURCEObj.class, where);
    }

    @Override
    public List<DashboardData> dashboardIndex() throws ServerException {
        return null;
    }

    @Override
    public PagerData<S_USERObj> findUserByQuery(String query, PagerData<S_USERObj> pager)
            throws ServerException {
        String val = "%" + query + "%";
        Cnd where = Cnd.where(S_USERObj.FLD_NAME, "like", val);
        where.or(S_USERObj.FLD_NAME, "like", val);
        where.or(S_USERObj.FLD_MOBILE, "like", val);
        where.limit(1, 10);
        List<S_USERObj> list = getDao().query(S_USERObj.class, where);
        pager.setData(list);
        return pager;
    }

    @Override
    public PagerData<S_USERObj> searchUserList(SearchReq req) throws ServerException {
        return getAdminService().searchUserList(req);

    }

    @Override
    public List<S_RESOURCEObj> adminAllMenu(int rootMenuId) throws ServerException {
        return getDao().query(
                S_RESOURCEObj.class,
                Cnd.where(S_RESOURCEObj.FLD_ROOTID, "=", rootMenuId).asc(S_RESOURCEObj.FLD_PID)
                        .asc(S_RESOURCEObj.FLD_RANK));
    }

    @Override
    public List<S_ROLE_RESOURCEObj> getRoleResource(Long roleId) {
        Cnd where = Cnd.where(S_ROLE_RESOURCEObj.FLD_ROLE_ID, "=", roleId);
        where.asc(S_ROLE_RESOURCEObj.FLD_RES_ID);
        return getDao().query(S_ROLE_RESOURCEObj.class, where);
    }


    /**
     * 简化版的系统模块日志
     *
     * @param admin
     * @param action
     * @param content
     */
    private void systemLog(S_USERObj admin, Actions action, String content) {
        log(MODULE_SYSTEM_ADMIN, MODULE_RESOURCE, action, content);
    }


    /**
     * 用户是否拥有某个资源的访问权限
     *
     * @param resid
     * @return
     * @throws ServerException
     */
    @Override
    public Boolean isUserOwnResource(Long resid) throws ServerException {
        try {
            S_USERObj admin = requestUser();
            return getAdminService().userOwnResource(admin.getId(), resid);
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public List<S_METAObj> fetchMetaData() throws ServerException {
        return getAdminService().fetchAllMetaData();
    }

    @Override
    public Boolean deleteMetaData(Integer metaId) throws ServerException {
        return getAdminService().deleteMetaData(metaId);
    }

    @Override
    public S_METAObj saveOrUpdateMetaData(S_METAObj meta) throws ServerException {
        if (meta.getId() == null) {
            log(MODULE_SYSTEM_ADMIN, MODULE_METADATA, Actions.CREATE, Json.toJson(meta));
        } else {
            log(MODULE_SYSTEM_ADMIN, MODULE_METADATA, Actions.UPDATE, Json.toJson(meta));
        }
        return getAdminService().saveOrUpdateMetaData(meta);
    }


    @Override
    public List<S_METAObj> getMetaData(String catalog, Boolean includeSub) throws ServerException {
        Cnd where = null;
        if (includeSub) {
            where = Cnd.where(S_METAObj.FLD_CATALOG, "like", catalog + "%");
            where.asc(S_METAObj.FLD_PID).asc(S_METAObj.FLD_RANK);
        } else {
            where = Cnd.where(S_METAObj.FLD_CATALOG, "=", catalog);
            where.asc(S_METAObj.FLD_PID).asc(S_METAObj.FLD_RANK);
        }
        return getDao().query(S_METAObj.class, where);
    }

    @Override
    public boolean updateRoleResource(Long roleId, List<Long> ids) {
        S_USERObj admin = requestUser();
        Cnd where = Cnd.where(S_ROLE_RESOURCEObj.FLD_ROLE_ID, "=", roleId);

        List<S_ROLE_RESOURCEObj> list = new ArrayList<S_ROLE_RESOURCEObj>();
        for (Long resId : ids) {
            S_ROLE_RESOURCEObj o = new S_ROLE_RESOURCEObj();
            o.setRes_id(resId);
            o.setRole_id(roleId);
            list.add(o);
        }
        getDao().clear(S_ROLE_RESOURCEObj.class, where);
        String content = "对角色" + roleId + "授权" + Lang.concat(",", ids).toString();
        systemLog(admin, Actions.UPDATE, content);


        getDao().fastInsert(list);
        return true;

    }

}

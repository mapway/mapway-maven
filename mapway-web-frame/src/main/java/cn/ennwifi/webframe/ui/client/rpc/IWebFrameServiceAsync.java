package cn.ennwifi.webframe.ui.client.rpc;

import cn.ennwifi.webframe.ui.shared.module.*;
import cn.ennwifi.webframe.ui.shared.repository.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * The Interface IUiServerAsync.
 */
public interface IWebFrameServiceAsync {

    /**
     * Admin login.
     *
     * @param resourceRootId the resource root id
     * @param username       the username
     * @param pwd            the pwd
     * @param type           the type
     * @param callback       the callback
     */
    void adminLogin(Integer resourceRootId, String username, String pwd, String type,
                    AsyncCallback<AdminLoginResponse> callback);

    /**
     * Gets all menu.
     *
     * @param callback the callback
     */
    void getAllMenu(AsyncCallback<List<S_RESOURCEObj>> callback);

    /**
     * Admin sub menu.
     *
     * @param menuId   the menu id
     * @param callback the callback
     */
    void adminSubMenu(int menuId, AsyncCallback<List<S_RESOURCEObj>> callback);

    /**
     * Admin all menu.
     *
     * @param rootMenuId the root menu id
     * @param callback   the callback
     */
    void adminAllMenu(int rootMenuId, AsyncCallback<List<S_RESOURCEObj>> callback);

    /**
     * Admin user main menu.
     *
     * @param callback the callback
     */
    void adminUserMainMenu(AsyncCallback<PagerData<S_RESOURCEObj>> callback);

    /**
     * Delete admin role.
     *
     * @param roleid   the roleid
     * @param callback the callback
     */
    void deleteAdminRole(Long roleid, AsyncCallback<Boolean> callback);

    /**
     * Delete resource.
     *
     * @param resourceId the resource id
     * @param callback   the callback
     */
    void deleteResource(Long resourceId, AsyncCallback<Void> callback);

    /**
     * Fetch meta data.
     *
     * @param callback the callback
     */
    void fetchMetaData(AsyncCallback<List<S_METAObj>> callback);

    /**
     * Find user by query.
     *
     * @param query    the query
     * @param pager    the pager
     * @param callback the callback
     */
    void findUserByQuery(String query, PagerData<S_USERObj> pager,
                         AsyncCallback<PagerData<S_USERObj>> callback);

    /**
     * Gets all role.
     *
     * @param callback the callback
     */
    void getAllRole(AsyncCallback<List<S_ROLEObj>> callback);

    /**
     * Gets admin user role.
     *
     * @param userid   the userid
     * @param callback the callback
     */
    void getAdminUserRole(Long userid, AsyncCallback<List<S_USER_ROLEObj>> callback);

    /**
     * Gets meta data.
     *
     * @param catalog    the catalog
     * @param includeSub the include sub
     * @param callback   the callback
     */
    void getMetaData(String catalog, Boolean includeSub, AsyncCallback<List<S_METAObj>> callback);

    /**
     * Gets role resource.
     *
     * @param roleId   the role id
     * @param callback the callback
     */
    void getRoleResource(Long roleId, AsyncCallback<List<S_ROLE_RESOURCEObj>> callback);

    /**
     * Gets user by token.
     *
     * @param resourceRootId the resource root id
     * @param token          the token
     * @param callback       the callback
     */
    void getUserByToken(Integer resourceRootId, String token, AsyncCallback<AdminLoginResponse> callback);

    /**
     * Save admin role.
     *
     * @param role     the role
     * @param callback the callback
     */
    void saveAdminRole(S_ROLEObj role, AsyncCallback<S_ROLEObj> callback);

    /**
     * Is user own resource.
     *
     * @param resid    the resid
     * @param callback the callback
     */
    void isUserOwnResource(Long resid, AsyncCallback<Boolean> callback);

    /**
     * Is user own resource.
     *
     * @param request  the request
     * @param rootid   the rootid
     * @param callback the callback
     */
    void isUserOwnResource(List<ResourceNameAuthority> request, Integer rootid, AsyncCallback<List<ResourceNameAuthority>> callback);

    /**
     * Save admin user.
     *
     * @param user     the user
     * @param callback the callback
     */
    void saveAdminUser(S_USERObj user, AsyncCallback<S_USERObj> callback);

    /**
     * Save menu.
     *
     * @param resource the resource
     * @param callback the callback
     */
    void saveMenu(S_RESOURCEObj resource, AsyncCallback<S_RESOURCEObj> callback);

    /**
     * Save or update meta data.
     *
     * @param meta     the meta
     * @param callback the callback
     */
    void saveOrUpdateMetaData(S_METAObj meta, AsyncCallback<S_METAObj> callback);

    /**
     * Search user list.
     *
     * @param req      the req
     * @param callback the callback
     */
    void searchUserList(SearchReq req, AsyncCallback<PagerData<S_USERObj>> callback);

    /**
     * Update admin user role.
     *
     * @param userid      the userid
     * @param roleid      the roleid
     * @param addOrRemove the add or remove
     * @param callback    the callback
     */
    void updateAdminUserRole(Long userid, Long roleid, Boolean addOrRemove,
                             AsyncCallback<Boolean> callback);

    /**
     * Update admin role menu.
     *
     * @param roleid      the roleid
     * @param resourceId  the resource id
     * @param addOrRemove the add or remove
     * @param callback    the callback
     */
    void updateAdminRoleMenu(Long roleid, Long resourceId, boolean addOrRemove,
                             AsyncCallback<Boolean> callback);

    /**
     * Delete meta data.
     *
     * @param metaId   the meta id
     * @param callback the callback
     */
    void deleteMetaData(Integer metaId, AsyncCallback<Boolean> callback);

    /**
     * Gets menu info.
     *
     * @param id       the id
     * @param callback the callback
     */
    void getMenuInfo(Integer id, AsyncCallback<S_RESOURCEObj> callback);

    /**
     * Update role resource.
     *
     * @param id       the id
     * @param ids      the ids
     * @param callback the callback
     */
    void updateRoleResource(Long id, List<Long> ids, AsyncCallback<Boolean> callback);

    /**
     * Dashboard index.
     *
     * @param callback the callback
     */
    void dashboardIndex(AsyncCallback<List<DashboardData>> callback);

    /**
     * User resources.
     *
     * @param rootid   the rootid
     * @param callback the callback
     * @throws ServerException the server exception
     */
    void userResources(Integer rootid, AsyncCallback<List<S_RESOURCEObj>> callback) throws ServerException;

}

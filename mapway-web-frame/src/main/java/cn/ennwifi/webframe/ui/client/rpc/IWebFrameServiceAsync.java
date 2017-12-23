package cn.ennwifi.webframe.ui.client.rpc;

import cn.ennwifi.webframe.ui.shared.module.*;
import cn.ennwifi.webframe.ui.shared.repository.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * The Interface IUiServerAsync.
 */
public interface IWebFrameServiceAsync {

    void adminLogin(String username, String pwd, String type,
                    AsyncCallback<AdminLoginResponse> callback);

    void getAllMenu(AsyncCallback<List<S_RESOURCEObj>> callback);

    void adminSubMenu(int menuId, AsyncCallback<List<S_RESOURCEObj>> callback);

    void adminAllMenu(int rootMenuId, AsyncCallback<List<S_RESOURCEObj>> callback);

    void adminUserMainMenu(AsyncCallback<PagerData<S_RESOURCEObj>> callback);

    void deleteAdminRole(Long roleid, AsyncCallback<Boolean> callback);

    void deleteResource(Long resourceId, AsyncCallback<Void> callback);

    void fetchMetaData(AsyncCallback<List<S_METAObj>> callback);

    void findUserByQuery(String query, PagerData<S_USERObj> pager,
                         AsyncCallback<PagerData<S_USERObj>> callback);

    void getAllRole(AsyncCallback<List<S_ROLEObj>> callback);

    void getAdminUserRole(Long userid, AsyncCallback<List<S_USER_ROLEObj>> callback);

    void getMetaData(String catalog, Boolean includeSub, AsyncCallback<List<S_METAObj>> callback);

    void getRoleResource(Long roleId, AsyncCallback<List<S_ROLE_RESOURCEObj>> callback);

    void getUserByToken(String token, AsyncCallback<AdminLoginResponse> callback);

    void saveAdminRole(S_ROLEObj role, AsyncCallback<S_ROLEObj> callback);

    void isUserOwnResource(Long resid, AsyncCallback<Boolean> callback);

    void isUserOwnResource(List<ResourceNameAuthority> request, Integer rootid, AsyncCallback<List<ResourceNameAuthority>> callback);

    void saveAdminUser(S_USERObj user, AsyncCallback<S_USERObj> callback);

    void saveMenu(S_RESOURCEObj resource, AsyncCallback<S_RESOURCEObj> callback);

    void saveOrUpdateMetaData(S_METAObj meta, AsyncCallback<S_METAObj> callback);

    void searchUserList(SearchReq req, AsyncCallback<PagerData<S_USERObj>> callback);

    void updateAdminUserRole(Long userid, Long roleid, Boolean addOrRemove,
                             AsyncCallback<Boolean> callback);

    void updateAdminRoleMenu(Long roleid, Long resourceId, boolean addOrRemove,
                             AsyncCallback<Boolean> callback);

    void deleteMetaData(Integer metaId, AsyncCallback<Boolean> callback);

    void getMenuInfo(Integer id, AsyncCallback<S_RESOURCEObj> callback);

    void updateRoleResource(Long id, List<Long> ids, AsyncCallback<Boolean> callback);

    void dashboardIndex(AsyncCallback<List<DashboardData>> callback);

}

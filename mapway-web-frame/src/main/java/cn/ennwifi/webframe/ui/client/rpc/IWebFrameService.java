package cn.ennwifi.webframe.ui.client.rpc;

import cn.ennwifi.webframe.ui.server.servlet.WebFrameServlet;
import cn.ennwifi.webframe.ui.shared.module.*;
import cn.ennwifi.webframe.ui.shared.repository.*;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * The Interface IWebFrameService.
 */
@RemoteServiceRelativePath(WebFrameServlet.DEFAULt_WEB_FRAME_SERVER_PATH)
public interface IWebFrameService extends RemoteService {

    AdminLoginResponse adminLogin(Integer resourceRootId, String username, String pwd, String type) throws ServerException;

    List<S_RESOURCEObj> getAllMenu() throws ServerException;

    S_RESOURCEObj saveMenu(S_RESOURCEObj resource) throws ServerException;

    void deleteResource(Long resourceId) throws ServerException;

    List<S_ROLEObj> getAllRole() throws ServerException;

    S_ROLEObj saveAdminRole(S_ROLEObj role) throws ServerException;

    boolean deleteAdminRole(Long roleid) throws ServerException;

    Boolean updateAdminRoleMenu(Long roleid, Long resourceId, boolean addOrRemove)
            throws ServerException;

    S_USERObj saveAdminUser(S_USERObj user) throws ServerException;

    boolean updateAdminUserRole(Long userid, Long roleid, Boolean addOrRemove) throws ServerException;

    List<S_USER_ROLEObj> getAdminUserRole(Long userid) throws ServerException;

    PagerData<S_RESOURCEObj> adminUserMainMenu() throws ServerException;

    List<S_RESOURCEObj> adminSubMenu(int menuId) throws ServerException;

    AdminLoginResponse getUserByToken(Integer resourceRootId, String token) throws ServerException;

    PagerData<S_USERObj> findUserByQuery(String query, PagerData<S_USERObj> pager)
            throws ServerException;

    PagerData<S_USERObj> searchUserList(SearchReq req) throws ServerException;

    List<S_RESOURCEObj> adminAllMenu(int rootMenuId) throws ServerException;

    List<S_ROLE_RESOURCEObj> getRoleResource(Long roleId);

    boolean updateRoleResource(Long id, List<Long> ids);

    Boolean isUserOwnResource(Long resid) throws ServerException;

    List<ResourceNameAuthority> isUserOwnResource(List<ResourceNameAuthority> request, Integer rootid) throws ServerException;

    List<S_RESOURCEObj> userResources(Integer rootid) throws ServerException;

    List<S_METAObj> fetchMetaData() throws ServerException;

    S_METAObj saveOrUpdateMetaData(S_METAObj meta) throws ServerException;

    List<S_METAObj> getMetaData(String catalog, Boolean includeSub) throws ServerException;

    Boolean deleteMetaData(Integer metaId) throws ServerException;

    S_RESOURCEObj getMenuInfo(Integer id) throws ServerException;

    List<DashboardData> dashboardIndex() throws ServerException;

}

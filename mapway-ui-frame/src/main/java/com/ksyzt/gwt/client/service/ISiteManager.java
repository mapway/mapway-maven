package com.ksyzt.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ksyzt.gwt.client.site.urlrewrite.RewriteData;
import com.ksyzt.gwt.shared.exception.AdminLoginException;
import com.ksyzt.gwt.shared.module.AdminUser;
import com.ksyzt.gwt.shared.module.SiteInformation;

// TODO: Auto-generated Javadoc

/**
 * The Interface ISiteManager.
 */
@RemoteServiceRelativePath("../ksyzt_site_manager")
public interface ISiteManager extends RemoteService {

	/**
	 * *************************** site infomation ****************************.
	 *
	 * @param info the info
	 * @return the site information
	 * @throws Exception the exception
	 */
	public SiteInformation updateSiteInformation(SiteInformation info)
			throws Exception;

	/**
	 * Gets the site information.
	 *
	 * @return the site information
	 * @throws Exception the exception
	 */
	public SiteInformation getSiteInformation() throws Exception;

	/**
	 * ************************* url rewrite ***********************************.
	 *
	 * @return the rewrite data
	 * @throws Exception the exception
	 */
	public List<RewriteData> getRewriteData() throws Exception;

	/**
	 * Update rewrite data.
	 *
	 * @param data the data
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<RewriteData> updateRewriteData(RewriteData data)
			throws Exception;

	/**
	 * Delete rewrite data.
	 *
	 * @param data the data
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<RewriteData> deleteRewriteData(RewriteData data)
			throws Exception;

	/**
	 * *********************** site configure user login ***********************.
	 *
	 * @param username the username
	 * @param pwd the pwd
	 * @return the admin user
	 * @throws AdminLoginException the admin login exception
	 */
	public AdminUser adminLogin(String username, String pwd)
			throws AdminLoginException;

	/**
	 * Admin update.
	 *
	 * @param username the username
	 * @param pwd the pwd
	 * @return the admin user
	 * @throws AdminLoginException the admin login exception
	 */
	public AdminUser adminUpdate(String username, String pwd)
			throws AdminLoginException;

	/**
	 * Check admin login.
	 *
	 * @return the admin user
	 * @throws AdminLoginException the admin login exception
	 */
	public AdminUser checkAdminLogin() throws AdminLoginException;

	/**
	 * Quit.
	 */
	public void quit();

}

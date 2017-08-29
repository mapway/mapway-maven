package com.ksyzt.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ksyzt.gwt.client.site.urlrewrite.RewriteData;
import com.ksyzt.gwt.shared.module.AdminUser;
import com.ksyzt.gwt.shared.module.SiteInformation;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISiteManagerAsync.
 */
public interface ISiteManagerAsync {

	/**
	 * Gets the site information.
	 *
	 * @param callback the callback
	 */
	void getSiteInformation(AsyncCallback<SiteInformation> callback);

	/**
	 * Update site information.
	 *
	 * @param info the info
	 * @param callback the callback
	 */
	void updateSiteInformation(SiteInformation info,
			AsyncCallback<SiteInformation> callback);

	/**
	 * Gets the rewrite data.
	 *
	 * @param callback the callback
	 */
	void getRewriteData(AsyncCallback<List<RewriteData>> callback);

	/**
	 * Update rewrite data.
	 *
	 * @param data the data
	 * @param callback the callback
	 */
	void updateRewriteData(RewriteData data,
			AsyncCallback<List<RewriteData>> callback);

	/**
	 * Delete rewrite data.
	 *
	 * @param data the data
	 * @param callback the callback
	 */
	void deleteRewriteData(RewriteData data,
			AsyncCallback<List<RewriteData>> callback);

	/**
	 * Admin login.
	 *
	 * @param username the username
	 * @param pwd the pwd
	 * @param callback the callback
	 */
	void adminLogin(String username, String pwd,
			AsyncCallback<AdminUser> callback);

	/**
	 * Admin update.
	 *
	 * @param username the username
	 * @param pwd the pwd
	 * @param callback the callback
	 */
	void adminUpdate(String username, String pwd,
			AsyncCallback<AdminUser> callback);

	/**
	 * Check admin login.
	 *
	 * @param callback the callback
	 */
	void checkAdminLogin(AsyncCallback<AdminUser> callback);

	/**
	 * Quit.
	 *
	 * @param callback the callback
	 */
	void quit(AsyncCallback<Void> callback);

}

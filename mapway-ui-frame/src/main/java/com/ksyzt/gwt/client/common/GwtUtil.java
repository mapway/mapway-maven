package com.ksyzt.gwt.client.common;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;

// TODO: Auto-generated Javadoc
/**
 * The Class GwtUtil.
 */
public class GwtUtil {

	/** The g mf. */
	private static DateTimeFormat g_mf;

	/**
	 * Gets the date format.
	 *
	 * @return the date format
	 */
	public static DateTimeFormat getDateFormat() {
		if (g_mf == null) {
			g_mf = DateTimeFormat.getFormat("yyyy年MM月dd日");
		}

		return g_mf;
	}

	/**
	 * Redirect.
	 *
	 * @param url the url
	 */
	public static native void redirect(String url)/*-{
													$wnd.location = url;
													}-*/;

	/**
	 * Open.
	 *
	 * @param url the url
	 */
	public static native void open(String url)/*-{
												$wnd.open(url);
												}-*/;

	/**
	 * Nocopy.
	 *
	 * @param e the e
	 */
	public static native void nocopy(Element e)/*-{
												e.onselectstart = function() {
												return false;
												}
												e.oncopy = function() {
												return false;
												}
												e.ondoubleclick = function() {
												return false;
												}
												}-*/;

	/**
	 * Refresh.
	 */
	public static native void refresh()/*-{
										$wnd.location.reload();
										}-*/;

	/**
	 * Gets the current page URL.
	 *
	 * @return the current page URL
	 */
	public static String getCurrentPageURL() {
		return Window.Location.getHref();
	}

	/**
	 * Gets the encode URL.
	 *
	 * @param url the url
	 * @return the encode URL
	 */
	public static String getEncodeURL(String url) {
		if (url == null) {
			return "";
		} else {
			return URL.encode(url);
		}
	}

	/**
	 * Gets the encode query string.
	 *
	 * @param uri the uri
	 * @return the encode query string
	 */
	public static String getEncodeQueryString(String uri) {
		if (uri == null) {
			return "";
		} else {
			return URL.encodeQueryString(uri);
		}
	}

	/**
	 * Gets the dncode query string.
	 *
	 * @param uri the uri
	 * @return the dncode query string
	 */
	public static String getDncodeQueryString(String uri) {
		if (uri == null) {
			return "";
		} else {
			return URL.decodeQueryString(uri);
		}
	}

	/**
	 * Gets the decode URL.
	 *
	 * @param url the url
	 * @return the decode URL
	 */
	public static String getDecodeURL(String url) {
		if (url == null) {
			return "";
		} else {
			return URL.decode(url);
		}
	}

	/**
	 * Gets the current page login URL.
	 *
	 * @return the current page login URL
	 */
	public static String getCurrentPageLoginURL() {
		String base = GWT.getModuleBaseURL();
		String current_page = GwtUtil.getCurrentPageURL();

		String encodePath = URL.encodeQueryString(current_page);
		String url = base + "../user/login?url=" + encodePath;
		return url;
	}

	/**
	 * Int 2 path.
	 *
	 * @param v the v
	 * @return the string
	 */
	public final static String int2path(int v) {
		String id = v + "";
		String str = "";
		for (int i = 0; i < id.length(); i++) {
			str += id.charAt(i) + "/";
		}
		return str;
	}

	/**
	 * Gets the debug.
	 *
	 * @return the debug
	 */
	public static String getDebug() {
		String v = Window.Location.getParameter("gwt.codesvr");
		if (v != null && v.length() > 0) {
			return "&gwt.codesvr=" + v;
		}
		return "";
	}

	/**
	 * Gets the image url.
	 *
	 * @param path the path
	 * @param id the id
	 * @return the image url
	 */
	public static String getImageUrl(String path, int id) {
		String picurl = GWT.getModuleBaseURL() + "../" + path + "/"
				+ GwtUtil.int2path(id) + "64.jpg?t=" + Random.nextDouble();
		return picurl;
	}

	/**
	 * Gets the user icon URL.
	 *
	 * @param uid the uid
	 * @param size the size
	 * @return the user icon URL
	 */
	public static String getUserIconURL(int uid, int size) {
		return GWT.getModuleBaseURL() + "../images/user/"
				+ GwtUtil.int2path(uid) + size + ".jpg";
	}

	/** The chars. */
	public static String chars = "abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * Rand string.
	 *
	 * @param i the i
	 * @return the string
	 */
	public static String RandString(int i) {

		String str = "";
		for (int count = 0; count < i; count++) {
			str += chars.charAt(Random.nextInt(chars.length()));
		}
		return str;
	}

	/** The m dfshort. */
	static DateTimeFormat m_dfshort = null;
	
	/** The m dflong. */
	static DateTimeFormat m_dflong = null;

	/**
	 * Gets the long format.
	 *
	 * @return the long format
	 */
	public static DateTimeFormat getLongFormat() {
		if (m_dflong == null) {
			m_dflong = DateTimeFormat.getFormat("yyyy年MM月dd日 HH:mm:ss");
		}
		return m_dflong;
	}

	/**
	 * Gets the short format.
	 *
	 * @return the short format
	 */
	public static DateTimeFormat getShortFormat() {
		if (m_dfshort == null) {
			m_dfshort = DateTimeFormat.getFormat("yyyy年MM月dd日");
		}
		return m_dfshort;
	}

	/**
	 * Format timestamp.
	 *
	 * @param time the time
	 * @param longformat the longformat
	 * @return the string
	 */
	public static String formatTimestamp(Timestamp time, boolean longformat) {
		if (time == null) {
			return "----";
		} else {
			if (longformat == true) {
				return getLongFormat().format(new Date(time.getTime()));
			} else {
				return getShortFormat().format(new Date(time.getTime()));
			}
		}
	}

	/**
	 * Gets the home.
	 *
	 * @return the home
	 */
	public static String getHome() {
		String base = GWT.getModuleBaseURL();
		String url = base + "../?" + getDebug();
		return url;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @param value the value
	 * @return the timestamp
	 */
	public static Timestamp getTimestamp(Date value) {

		return new Timestamp(value.getTime());
	}

	/**
	 * Gets the now.
	 *
	 * @return the now
	 */
	public static Date getNow() {
		return new Date();
	}

	/**
	 * Gets the date.
	 *
	 * @param establishdate the establishdate
	 * @return the date
	 */
	public static Date getDate(Timestamp establishdate) {
		if (establishdate == null)
			return new Date();
		else {
			return new Date(establishdate.getTime());
		}
	}

	/**
	 * Parses the post image result.
	 *
	 * @param json the json
	 * @return the string[]
	 */
	public static String[] parsePostImageResult(String json) {
		JSONValue jv = JSONParser.parse(json);
		JSONObject jo = (JSONObject) (jv);
		JSONString strobj;
		strobj = (JSONString) jo.get("returncode");
		String rcode = strobj.stringValue();
		strobj = (JSONString) jo.get("msg");
		String msg = strobj.stringValue();
		String[] r = new String[2];
		r[0] = rcode;
		r[1] = msg;
		return r;
	}
}

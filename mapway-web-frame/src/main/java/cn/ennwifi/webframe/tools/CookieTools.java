package cn.ennwifi.webframe.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * Cookie操作辅助类
 * 
 * @author admin
 *
 */
public class CookieTools {

  /**
   * 新增cookie
   */
  public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue,
      String path, Integer date) {
    Cookie cookie = new Cookie(cookieName, cookieValue);
    cookie.setPath(path);
    if (date != null) {
      cookie.setMaxAge(date);
    }
    response.addCookie(cookie);
  }

  /**
   * 根据cookie名称 ，获取cookie值
   * 
   * @param request
   * @return
   */
  public static String getCookieValue(HttpServletRequest request, String cookieName) {
    String token = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equalsIgnoreCase(cookieName)) {
          token = cookie.getValue();
          break;
        }
      }
    }
    return token;
  }

  /**
   * 删除Cookie
   * 
   * @param request
   */
  public static void deleteCookie(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
      for (Cookie cookie : cookies) {
        if (StringUtils.equals(cookieName, cookie.getName())) {
          cookie.setValue(null);
          cookie.setMaxAge(0);// 立即销毁cookie
          cookie.setPath("/");
          break;
        }
      }
    }
  }


}

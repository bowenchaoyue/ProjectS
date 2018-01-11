package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wumingchao on 2015/10/30.
 */
public class CookieUtils {
    /**
     * 获取当前请求所有cookie
     *
     * @return
     */
    public static Cookie[] getCookies(HttpServletRequest request) {
        return request.getCookies();
    }

    /**
     * 获取指定name的cookie
     *
     * @param name
     * @param request
     * @return
     */
    public static Cookie getCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = getCookies(request);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 获取指定name的cookie值
     *
     * @param name
     * @param request
     * @return
     */
    public static String getCookieValue(String name, HttpServletRequest request) {
        Cookie[] cookies = getCookies(request);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     *
     * 删除cookie
     *
     * @param cookie
     *            需要删除的某个cookie
     * @param response
     *            响应对象
     * @param domain
     * @param path
     *
     */
    public static void deleteCookie(Cookie cookie, HttpServletResponse response, String domain, String path) {
        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath(path);
            cookie.setDomain(domain);
            response.addCookie(cookie);
        }
    }

    public static void deleteCookie(Cookie cookie, HttpServletResponse response, String path){
        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath(path);
            response.addCookie(cookie);
        }
    }
    /**
     *
     * 新增cookie
     *
     * @param cookie
     *            需要保存的cookie
     * @param response
     *            响应对象
     *
     */
    public static void addCookie(Cookie cookie, HttpServletResponse response) {
        response.addCookie(cookie);
    }

    /**
     * 新增cookie
     *
     * @param response
     * @param name
     * @param value
     * @param domain
     * @param maxAge
     * @param isHttpOnly
     */
    public static void addCookie(HttpServletResponse response, String name, String value, String domain,
                                 Integer maxAge, boolean isHttpOnly, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setHttpOnly(isHttpOnly);
        if (maxAge != null) {
            cookie.setMaxAge(maxAge);
        }
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    public static void addCookie(HttpServletResponse response, String name, String value, String domain,
                                 Integer maxAge, boolean isHttpOnly, String path, boolean isSecure) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setHttpOnly(isHttpOnly);
        if (maxAge != null) {
            cookie.setMaxAge(maxAge);
        }
        cookie.setPath(path);
        cookie.setSecure(isSecure);
        response.addCookie(cookie);
    }
}

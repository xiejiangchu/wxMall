package com.xie.auth;

import com.xie.bean.User;
import com.xie.service.UserService;
import com.xie.utils.MallConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xie
 * @Date 17/2/23 上午8:22.
 */
@Component
public class AdminInteceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    public AdminInteceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI(); //请求完整路径，可用于登陆后跳转
        String contextPath = request.getContextPath();  //项目下完整路径
        String url = requestUri.substring(contextPath.length()); //请求页面

        User user = (User) request.getSession().getAttribute(MallConstants.SESSION_USER);
        if (user != null) {
            return true;
        }

        Cookie cookie = getCookieByName(request, MallConstants.COOKIE_UID);
        if (cookie == null) {
            response.sendRedirect("admin/login");
            return false;
        }
        User user_query = userService.getById(Integer.parseInt(cookie.getValue()));
        if (user_query == null) {
            if (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
                return true;
            } else {
                response.sendRedirect("admin/login");
            }
            return false;
        } else {
            request.getSession().setAttribute(MallConstants.SESSION_USER, user_query);
            return true;
        }
    }

    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

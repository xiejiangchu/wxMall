package com.xie.auth;

import com.xie.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author xie
 * @Date 17/2/23 上午8:22.
 */
@Component
public class LoginInteceptor implements HandlerInterceptor {

    public LoginInteceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI(); //请求完整路径，可用于登陆后跳转
        String contextPath = request.getContextPath();  //项目下完整路径
        String url = requestUri.substring(contextPath.length()); //请求页面

        if (request.getHeader("Referer") != null && request.getHeader("Referer").contains("swagger-ui.html"))
            return true;

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            if (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
                return true;
            } else {
                request.getRequestDispatcher("/login").forward(request, response);//转发到登录界面
            }
            return false;
        } else
            return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

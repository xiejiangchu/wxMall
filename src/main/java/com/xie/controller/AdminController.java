package com.xie.controller;

import com.xie.bean.User;
import com.xie.response.BaseResponse;
import com.xie.service.ItemService;
import com.xie.service.UserService;
import com.xie.utils.MallConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author xie
 * @Date 17/1/19 下午4:37.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "admin/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "admin/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(HttpSession session) {
        if (session.getAttribute(MallConstants.SESSION_USER) != null) {
            return "redirect:admin/index";
        }
        return "admin/login";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse user(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(MallConstants.SESSION_USER);
        return BaseResponse.ok(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(MallConstants.SESSION_USER);
        Cookie cookie = new Cookie(MallConstants.COOKIE_UID, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return BaseResponse.ok();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletResponse response,
                            HttpSession session, Model model) {
        if (userService.check(username, password) > 0) {
            User user = userService.getByName(username);
            session.setAttribute(MallConstants.SESSION_USER, user);
            Cookie cookie = new Cookie(MallConstants.COOKIE_UID, String.valueOf(user.getId()));
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:admin/index";
        }
        model.addAttribute("error", "用户名或者密码错误");
        return "admin/login";
    }

    @RequestMapping(value = "/document", method = RequestMethod.GET)
    public String document() {
        return "admin/document";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "admin/register";
    }

}

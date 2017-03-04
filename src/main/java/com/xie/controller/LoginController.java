package com.xie.controller;

import com.xie.bean.Item;
import com.xie.bean.User;
import com.xie.service.ItemService;
import com.xie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author xie
 * @Date 17/1/19 下午4:37.
 */
@Controller
public class LoginController {

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session,
                            HttpServletResponse response) {
        if (userService.check(username, password) > 0) {
            User user = userService.getByName(username);
            session.setAttribute("user", user);
            Cookie cookie = new Cookie("uid", String.valueOf(user.getId()));
            cookie.setMaxAge(60*60*24);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "logoutSuccess";
        }
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/logoutSuccess", method = RequestMethod.GET)
    public String logoutSuccess() {
        return "logoutSuccess";
    }

    @RequestMapping(value = "/dowload")
    @ResponseBody
    public String dowload(HttpServletRequest request) throws Exception {

        List<Item> items = itemService.getAll();
        for (int i = 0; i < items.size(); i++) {
            String src = items.get(i).getSrc();
            String name = src.substring(src.lastIndexOf("/") + 1, src.length());
            items.get(i).setThumb(name);
            itemService.update(items.get(i));
        }
        return "fail";
    }
}

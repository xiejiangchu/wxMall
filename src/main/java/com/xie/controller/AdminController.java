package com.xie.controller;

import com.xie.service.ItemService;
import com.xie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "/admin/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/admin/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/admin/index";
        }
        return "/admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) {
        if (userService.check(username, password) > 0) {
            session.setAttribute("user", userService.getByName(username));
            return "redirect:/admin/index";
        }
        model.addAttribute("error", "用户名或者密码错误");
        return "/admin/login";
    }

    @RequestMapping(value = "/document", method = RequestMethod.GET)
    public String document() {
        return "/admin/document";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "/admin/register";
    }

}

package com.xie.controller;

import com.xie.service.ItemService;
import com.xie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }
}

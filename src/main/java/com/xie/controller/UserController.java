package com.xie.controller;

import com.github.pagehelper.PageInfo;
import com.xie.bean.User;
import com.xie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by xie on 16/11/24.
 */
@RestController()
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    User getUser(@PathVariable("id") int id){
        return userService.getUserById(id);
    }


    @RequestMapping(value = "users",method = RequestMethod.GET)
    @ResponseBody
    PageInfo<User> getAllUser(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        return userService.getAllUsers(pageNum,pageSize);
    }


    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        userService.insert(user);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User

        return "success";
    }
}

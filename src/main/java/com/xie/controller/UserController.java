package com.xie.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.User;
import com.xie.mapper.UserMapper;
import com.xie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xie on 16/11/24.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "user/{id}",method = RequestMethod.GET)
    @ResponseBody
    User getUser(@PathVariable("id") String id){
        return userService.getUserById(id);
    }


    @RequestMapping(value = "users",method = RequestMethod.GET)
    @ResponseBody
    PageInfo<User> getAllUser(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        return userService.getAllUsers(pageNum,pageSize);
    }
}

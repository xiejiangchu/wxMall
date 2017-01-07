package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.User;
import com.xie.dao.UserDao;
import com.xie.mapper.UserMapper;
import com.xie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xie on 16/11/24.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        return userDao.selectUserById(id);
    }

    @Override
    public PageInfo<User> getAllUsers(int pageNum, int pageSize) {
        PageInfo<User> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> userMapper.getAllUsers());

        return page;
    }
}

package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.User;
import com.xie.dao.UserDao;
import com.xie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by xie on 16/11/24.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public User getByOpenId(String openId) {
        return userDao.getByOpenId(openId);
    }

    @Override
    public User getBySessionId(String sessionId) {
        return userDao.getBySessionId(sessionId);
    }

    @Override
    public User getByName(String username) {
        return userDao.getByName(username);
    }

    @Override
    public User getByNameOrSessionId(String token) {
        return userDao.getByNameOrSessionId(token);
    }

    @Override
    public User getByWx(String wx) {
        return userDao.getByWx(wx);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public PageInfo<User> getAllUsers(int pageNum, int pageSize) {
        PageInfo<User> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> userDao.getAll());
        return page;
    }

    @Override
    public int insert(User user) {
        Assert.notNull(user);
        return userDao.insert(user);
    }

    @Override
    public int check(String username, String password) {
        Assert.notNull(username);
        Assert.notNull(password);
        return userDao.check(username, password);
    }

    @Override
    public int insertAll(User user) {
        return userDao.insertAll(user);
    }

    @Override
    public int updateAll(User user) {
        return userDao.updateAll(user);
    }

    @Override
    public int checkUsername(String username) {
        return 0;
    }

    @Override
    public int checkMobile(String mobile) {
        return 0;
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public int delete(User user) {
        Assert.notNull(user);
        return userDao.delete(user.getId());
    }

    @Override
    public int delete(int id) {
        return userDao.delete(id);
    }

    @Override
    public int softDelete(int id) {
        return userDao.softDelete(id);
    }

    @Override
    public int count() {
        return userDao.count();
    }
}

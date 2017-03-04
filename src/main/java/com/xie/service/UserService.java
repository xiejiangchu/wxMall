package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.User;

/**
 * Created by xie on 16/11/24.
 */
public interface UserService {

    User getById(int id);

    User getByName(String username);

    User getByWx(String wx);

    User getByEmail(String email);

    PageInfo<User> getAllUsers(int pageNum, int pageSize);

    int insert(User user);

    int update(User user);

    int delete(User user);

    int delete(int id);

    int check(String username, String password);

    int softDelete(int id);

    int count();
}

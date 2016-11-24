package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.User;

/**
 * Created by xie on 16/11/24.
 */
public interface UserService {

    User getUserById(String id);

    PageInfo<User> getAllUsers(int pageNum,int pageSize);
}

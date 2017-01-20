package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by xie on 16/11/24.
 */
public interface UserService {

    User getUserById(int id);

    PageInfo<User> getAllUsers(int pageNum,int pageSize);

    User insert(User user);
}

package com.xie.auth;

import com.xie.bean.User;
import com.xie.dao.RoleDao;
import com.xie.dao.UserDao;
import com.xie.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author xie
 * @Date 17/1/19 下午5:06.
 */
@Component
public class MyPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleDao roleDao;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        String username = authentication.getName();
        User user = userDao.getByName(username);
        return roleDao.authorized(user.getId(), permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}

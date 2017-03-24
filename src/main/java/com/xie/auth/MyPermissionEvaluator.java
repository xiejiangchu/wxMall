package com.xie.auth;

import com.xie.service.PermissionService;
import com.xie.service.RoleService;
import com.xie.service.UserService;
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
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getDetails();
        return permissionService.checkPermission(myUserDetails.getUser().getId(), permission.toString()) > 0;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getDetails();
        return permissionService.checkPermission(myUserDetails.getUser().getId(), permission.toString()) > 0;
    }
}

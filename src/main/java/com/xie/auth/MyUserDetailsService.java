package com.xie.auth;

import com.xie.bean.Role;
import com.xie.bean.User;
import com.xie.service.RoleService;
import com.xie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author xie
 * @Date 17/1/19 下午5:07.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByNameOrSessionId(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Role> roles = roleService.getRolesByUid(user.getId());
        return new MyUserDetails(user, roles);
    }
}

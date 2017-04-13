package com.xie.auth;

import com.xie.bean.Permission;
import com.xie.bean.Role;
import com.xie.bean.User;
import com.xie.utils.MallConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-23 下午3:27
 */
public class MyUserDetails implements UserDetails {

    private User user;
    private List<Role> roleList;
    private List<Permission> permissionList;

    public MyUserDetails(User user, List<Role> roleList, List<Permission> permissionList) {
        this.user = user;
        this.roleList = roleList;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roleList == null || roleList.size() < 1) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        StringBuilder commaBuilder = new StringBuilder();
        for (Role role : roleList) {
            commaBuilder.append("ROLE_" + role.getName().trim()).append(",");
        }
        String authorities = commaBuilder.substring(0, commaBuilder.length() - 1);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getVerified() == 1;
    }

    public boolean isNonExpired() {
        if (user.getExpired() != null) {
            return user.getExpired().getTime() > System.currentTimeMillis();
        }
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled() > 0;
    }

    public boolean isAdmin() {
        if (roleList == null || roleList.size() == 0) {
            return false;
        } else {
            for (int i = 0; i < roleList.size(); i++) {
                if (roleList.get(i).getName().equals(MallConstants.ADMIN)) {
                    return true;
                }
            }
        }
        return false;
    }
}

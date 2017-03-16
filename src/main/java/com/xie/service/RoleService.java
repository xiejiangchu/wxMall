package com.xie.service;

import com.xie.bean.Permission;
import com.xie.bean.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-15 下午3:33
 */
public interface RoleService {

    Role getById(int id);

    List<Role> getRolesByUid(int uid);

    int checkRole(int uid, String name);

    int assignRole(int uid, int role_id);

    int assignRole(int uid, Role role);

    List<Permission> getPermissionsByUid(int uid);

    int insert(Role role);

    int update(Role role);

    int delete(Role role);

    int delete(int id);
}

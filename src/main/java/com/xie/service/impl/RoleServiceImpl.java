package com.xie.service.impl;

import com.xie.bean.Permission;
import com.xie.bean.Role;
import com.xie.dao.PermissionDao;
import com.xie.dao.RoleDao;
import com.xie.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-15 下午3:52
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getById(int id) {
        return roleDao.getById(id);
    }

    @Override
    public int checkRole(int uid, String name) {
        return roleDao.checkRole(uid, name);
    }

    @Override
    public List<Role> getRolesByUid(int uid) {
        return roleDao.getRolesByUid(uid);
    }

    @Override
    public int assignRole(int uid, int role_id) {
        return roleDao.assignRole(uid, role_id);
    }

    @Override
    public int assignRole(int uid, Role role) {
        int role_id = role.getId();
        return roleDao.assignRole(uid, role_id);
    }

    @Override
    public List<Permission> getPermissionsByUid(int uid) {
        return permissionDao.getByUid(uid);
    }

    @Override
    public int insert(Role role) {
        return roleDao.insert(role);
    }

    @Override
    public int update(Role role) {
        return roleDao.update(role);
    }

    @Override
    public int delete(Role role) {
        return roleDao.delete(role);
    }

    @Override
    public int delete(int id) {
        return roleDao.delete(id);
    }
}

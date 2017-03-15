package com.xie.service.impl;

import com.xie.bean.Permission;
import com.xie.dao.PermissionDao;
import com.xie.service.PermissionService;
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
 * @since 2017-03-15 下午3:33
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Permission getById(int id) {
        return permissionDao.getById(id);
    }

    @Override
    public List<Permission> getByUid(int uid) {
        return permissionDao.getByUid(uid);
    }

    @Override
    public int checkPermission(int uid, String permission) {
        return permissionDao.checkPermission(uid, permission);
    }

    @Override
    public int insert(Permission permission) {
        return permissionDao.insert(permission);
    }

    @Override
    public int update(Permission permission) {
        return permissionDao.update(permission);
    }

    @Override
    public int delete(Permission permission) {
        return permissionDao.delete(permission);
    }

    @Override
    public int delete(int id) {
        return permissionDao.delete(id);
    }
}

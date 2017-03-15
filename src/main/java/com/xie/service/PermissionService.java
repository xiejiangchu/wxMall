package com.xie.service;

import com.xie.bean.Permission;

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
public interface PermissionService {

    Permission getById(int id);

    List<Permission> getByUid(int uid);

    int checkPermission(int uid, String permission);

    int insert(Permission permission);

    int update(Permission permission);

    int delete(Permission permission);

    int delete(int id);
}

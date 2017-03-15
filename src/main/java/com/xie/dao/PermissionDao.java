package com.xie.dao;

import com.xie.bean.Permission;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xie
 * @Date 17/2/22 下午2:39.
 */
@Component
public class PermissionDao extends BaseDao {

    public Permission getById(int id) {
        return this.sqlSession.selectOne("PermissionMapper.getById", id);
    }

    public int insert(Permission permission) {
        this.sqlSession.insert("PermissionMapper.insert", permission);
        return permission.getId();
    }

    public List<Permission> getByUid(int uid) {
        return this.sqlSession.selectList("PermissionMapper.getByUid", uid);
    }

    public int checkPermission(int uid, String permission) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("permission", permission);
        return this.sqlSession.selectOne("PermissionMapper.checkPermission", map);
    }

    public int update(Permission permission) {
        return this.sqlSession.update("PermissionMapper.update", permission);
    }

    public int delete(Permission permission) {
        Assert.notNull(permission);
        Assert.isTrue(permission.getId() > 0);
        return this.sqlSession.delete("PermissionMapper.delete", permission.getId());
    }

    public int delete(int id) {
        return this.sqlSession.delete("PermissionMapper.delete", id);
    }
}

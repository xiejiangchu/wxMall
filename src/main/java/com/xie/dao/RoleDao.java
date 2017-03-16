package com.xie.dao;

import com.xie.bean.Role;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xie
 * @Date 17/1/19 下午4:55.
 */
@Component
public class RoleDao extends BaseDao {

    public List<Role> selectByUid(Integer uid) {
        return this.sqlSession.selectList("RoleMapper.selectByUid", uid);
    }

    public boolean authorized(int uid, String permission) {
        Assert.notNull(permission);
        Assert.isTrue(uid > 0);

        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("permission", permission);
        Role role = this.sqlSession.selectOne("RoleMapper.checkPermission", map);
        return null == role;
    }

    public int checkRole(int uid, String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("name", name);
        return this.sqlSession.selectOne("RoleMapper.checkRole", map);
    }

    public int assignRole(int uid, int role_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("role_id", role_id);
        return this.sqlSession.selectOne("RoleMapper.assignRole", map);
    }


    public List<Role> getRolesByUid(int uid) {
        return this.sqlSession.selectList("RoleMapper.getRolesByUid", uid);
    }

    public Role getById(int id) {
        return this.sqlSession.selectOne("RoleMapper.getById", id);
    }

    public int insert(Role role) {
        this.sqlSession.insert("RoleMapper.insert", role);
        return role.getId();
    }

    public int update(Role role) {
        return this.sqlSession.update("RoleMapper.update", role);
    }

    public int delete(Role role) {
        Assert.notNull(role);
        Assert.isTrue(role.getId() > 0);
        return this.sqlSession.delete("RoleMapper.delete", role.getId());
    }

    public int delete(int id) {
        return this.sqlSession.delete("RoleMapper.delete", id);
    }
}

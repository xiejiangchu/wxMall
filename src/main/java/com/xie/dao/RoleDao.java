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
}

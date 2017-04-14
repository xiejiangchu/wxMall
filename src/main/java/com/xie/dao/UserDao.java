package com.xie.dao;

import com.xie.bean.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class UserDao extends BaseDao {

    public User getById(int id) {
        return this.sqlSession.selectOne("UserMapper.getById", id);
    }

    public User getByOpenId(String openId) {
        return this.sqlSession.selectOne("UserMapper.getByOpenId", openId);
    }

    public User getBySessionId(String sessionId) {
        return this.sqlSession.selectOne("UserMapper.getBySessionId", sessionId);
    }

    public User getByName(String name) {
        return this.sqlSession.selectOne("UserMapper.getByName", name);
    }

    public User getByWx(String wx) {
        return this.sqlSession.selectOne("UserMapper.getByWx", wx);
    }

    public User getByEmail(String email) {
        return this.sqlSession.selectOne("UserMapper.getByEmail", email);
    }

    public int insert(User user) {
        this.sqlSession.insert("UserMapper.insert", user);
        return user.getId();
    }

    public int insertAll(User user) {
        this.sqlSession.insert("UserMapper.insertAll", user);
        return user.getId();
    }

    public User getByNameOrSessionId(String token){
        return this.sqlSession.selectOne("UserMapper.getByNameOrSessionId", token);
    }

    public List<User> getAll() {
        return this.sqlSession.selectList("UserMapper.getAll");
    }

    public int count() {
        return this.sqlSession.selectOne("UserMapper.count");
    }

    public int update(User user) {
        return this.sqlSession.update("UserMapper.update", user);
    }

    public int updateAll(User user) {
        return this.sqlSession.update("UserMapper.updateAll", user);
    }

    public int delete(int id) {
        return this.sqlSession.delete("UserMapper.delete", id);
    }

    public int check(String username, String password) {
        Map map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return this.sqlSession.selectOne("UserMapper.check", map);
    }

    public int softDelete(int id) {
        return this.sqlSession.update("UserMapper.softDelete", id);
    }

    public int checkUsername(String username) {
        return this.sqlSession.selectOne("UserMapper.checkUsername", username);
    }

    public int checkMobile(String mobile) {
        return this.sqlSession.selectOne("UserMapper.checkMobile", mobile);
    }
}
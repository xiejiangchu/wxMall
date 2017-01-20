package com.xie.dao;

import com.xie.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class UserDao extends BaseDao{

    public User selectUserById(int id) {
        return this.sqlSession.selectOne("userDao.selectUserById", id);
    }

    public User selectUserByName(String name) {
        return this.sqlSession.selectOne("userDao.selectUserByName", name);
    }

    public int insert(User user){
        return this.sqlSession.insert("userDao.insert", user);
    }
}
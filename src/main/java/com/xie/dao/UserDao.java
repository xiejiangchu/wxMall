package com.xie.dao;

import com.xie.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class UserDao {

    @Autowired
    private SqlSession sqlSession;

    public User selectUserById(String id) {
        return this.sqlSession.selectOne("userDao.selectUserById", id);
    }

}
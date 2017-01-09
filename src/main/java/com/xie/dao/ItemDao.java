package com.xie.dao;

import com.xie.bean.Item;
import com.xie.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class ItemDao {

    @Autowired
    private SqlSession sqlSession;

    public Item getById(int id) {
        return this.sqlSession.selectOne("itemDao.getById", id);
    }

}
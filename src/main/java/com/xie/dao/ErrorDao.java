package com.xie.dao;

import com.xie.bean.Error;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by xie on 17/5/4.
 */
@Component
public class ErrorDao extends BaseDao {

    public List<Error> getAll(){
        return this.sqlSession.selectList("ErrorMapper.getAll");
    }

    public Error getById(int id){
        return this.sqlSession.selectOne("ErrorMapper.getById", id);
    }

    public int count(){
        return this.sqlSession.selectOne("ErrorMapper.count");
    }

    public int insert(Error Error){
        return this.sqlSession.insert("ErrorMapper.insert", Error);
    }

    public int delete(Error Error){
        Assert.isTrue(Error !=null);
        return this.sqlSession.delete("ErrorMapper.delete", Error.getId());
    }

    public int delete(int id){
        return this.sqlSession.delete("ErrorMapper.delete", id);
    }
}

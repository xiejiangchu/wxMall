package com.xie.dao;

import com.xie.bean.CnArea;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-30 上午9:10
 */
@Component
public class CnAreaDao extends BaseDao {

    public CnArea getById(int id) {
        return this.sqlSession.selectOne("CnAreaMapper.getById", id);
    }

    public CnArea getByName(String name) {
        return this.sqlSession.selectOne("CnAreaMapper.getByName", name);
    }

    public List<CnArea> getByPid(int pid) {
        return this.sqlSession.selectList("CnAreaMapper.getByPid", pid);
    }

    public List<CnArea> getByLevel(int level) {
        return this.sqlSession.selectList("CnAreaMapper.getByLevel", level);
    }

    public int insert(CnArea cnArea) {
        return this.sqlSession.insert("CnAreaMapper.update", cnArea);
    }

    public int update(CnArea cnArea) {
        return this.sqlSession.update("CnAreaMapper.update", cnArea);
    }

    public int delete(CnArea cnArea) {
        Assert.notNull(cnArea);
        Assert.isTrue(cnArea.getId() > 0);
        return this.sqlSession.delete("CnAreaMapper.delete", cnArea.getId());
    }

    public int delete(int id) {
        Assert.isTrue(id > 0);
        return this.sqlSession.delete("CnAreaMapper.delete", id);
    }
}

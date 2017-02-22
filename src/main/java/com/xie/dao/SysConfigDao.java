package com.xie.dao;

import com.xie.bean.SysConfig;
import org.springframework.util.Assert;

/**
 * @Author xie
 * @Date 17/2/22 下午7:40.
 */
public class SysConfigDao extends BaseDao {

    public SysConfig getById(int id) {
        return this.sqlSession.selectOne("SysConfigMapper.getById", id);
    }

    public int insert(SysConfig sysConfig) {
        return this.sqlSession.insert("SysConfigMapper.insert", sysConfig);
    }

    public int update(SysConfig sysConfig) {
        Assert.notNull(sysConfig);
        Assert.isTrue(sysConfig.getId() > 0);
        return this.sqlSession.update("SysConfigMapper.insert", sysConfig);
    }

    public int delete(SysConfig sysConfig) {
        return this.sqlSession.delete("SysConfigMapper.delete", sysConfig.getId());
    }

    public int delete(int id) {
        return this.sqlSession.delete("SysConfigMapper.delete", id);
    }
}

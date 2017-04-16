package com.xie.dao;

import com.xie.bean.SysConfig;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xie
 * @Date 17/2/22 下午7:40.
 */
@Component
public class SysConfigDao extends BaseDao {

    public SysConfig getById(int id) {
        return this.sqlSession.selectOne("SysConfigMapper.getById", id);
    }

    public SysConfig getByName(String name) {
        return this.sqlSession.selectOne("SysConfigMapper.getByName", name);
    }

    public List<SysConfig> getAll() {
        return this.sqlSession.selectList("SysConfigMapper.getAll");
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

    public int saveByName(String name,String content){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("content", content);
        return this.sqlSession.update("SysConfigMapper.saveByName", map);
    }
}

package com.xie.dao;

import com.xie.bean.BonusType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class BonusTypeDao extends BaseDao {

    public List<BonusType> getAll() {
        return this.sqlSession.selectList("BonusTypeMapper.getAll");
    }

    public List<BonusType> getAllEnabled() {
        return this.sqlSession.selectList("BonusTypeMapper.getAllEnabled");
    }

    public List<BonusType> getAllByGid(Integer gid) {
        return this.sqlSession.selectList("BonusTypeMapper.getAllByGid", gid);
    }

    public List<BonusType> getAllByCid(Integer cid1, Integer cid2) {
        Map map = new HashMap<String, Object>();
        map.put("cid1", cid1);
        map.put("cid2", cid2);
        return this.sqlSession.selectList("BonusTypeMapper.getAllByCid", map);
    }

    public BonusType getById(Integer id) {
        return this.sqlSession.selectOne("BonusTypeMapper.getById", id);
    }

    public int countByGid(Integer gid) {
        return this.sqlSession.selectOne("BonusTypeMapper.countByGid", gid);
    }

    public int insert(BonusType bonusType) {
        return this.sqlSession.insert("BonusTypeMapper.insert", bonusType);
    }

    public int update(BonusType bonusType) {
        return this.sqlSession.update("BonusTypeMapper.update", bonusType);
    }

    public int delete(Integer id) {
        return this.sqlSession.delete("BonusTypeMapper.delete", id);
    }

    public int delete(BonusType bonusType) {
        Assert.notNull(bonusType);
        Assert.isTrue(bonusType.getId() > 0);
        return this.sqlSession.delete("BonusTypeMapper.delete", bonusType.getId());
    }

    public int saveOrUpdate(BonusType bonus) {
        return this.sqlSession.insert("BonusTypeMapper.saveOrUpdate", bonus);
    }

    public int offline(int id, int online) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("online", online);
        return this.sqlSession.insert("BonusTypeMapper.offline", map);
    }
}
package com.xie.dao;

import com.xie.bean.Bonus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class BonusDao extends BaseDao {

    public List<Bonus> getAllByUid(Integer uid) {
        return this.sqlSession.selectList("BonusMapper.getAllByUid", uid);
    }

    public List<Bonus> getAll() {
        return this.sqlSession.selectList("BonusMapper.getAll");
    }

    public Bonus getById(Integer id) {
        return this.sqlSession.selectOne("BonusMapper.getById", id);
    }

    public Bonus getEnabledById(Integer id) {
        return this.sqlSession.selectOne("BonusMapper.getEnabledById", id);
    }

    public int countByUid(Integer uid) {
        return this.sqlSession.selectOne("BonusMapper.countByUid", uid);
    }

    public int countEnabledByUid(Integer uid) {
        return this.sqlSession.selectOne("BonusMapper.countEnabledByUid", uid);
    }

    public int insert(Bonus bonus) {
        return this.sqlSession.insert("BonusMapper.insert", bonus);
    }

    public int update(Bonus bonus) {
        return this.sqlSession.update("BonusMapper.update", bonus);
    }

    public int delete(Integer id) {
        return this.sqlSession.delete("BonusMapper.delete", id);
    }

    public int delete(Bonus bonus) {
        Assert.notNull(bonus);
        Assert.isTrue(bonus.getId() > 0);
        return this.sqlSession.delete("BonusMapper.delete", bonus.getId());
    }

    public int softDelete(Integer id) {
        return this.sqlSession.update("BonusMapper.softDelete", id);
    }

    public int saveOrUpdate(Bonus bonus) {
        return this.sqlSession.insert("BonusMapper.saveOrUpdate", bonus);
    }

    public List<Bonus> getListInvalidate(int uid) {
        Map map = new HashMap<String, Object>();
        map.put("uid", uid);
        return this.sqlSession.selectList("BonusMapper.getListInvalidate", map);
    }

    public List<Bonus> getListValidate(int uid) {
        Map map = new HashMap<String, Object>();
        map.put("uid", uid);
        return this.sqlSession.selectList("BonusMapper.getListValidate", map);
    }

    public Bonus fetchBonusByCode(int uid,String code){
        Map map = new HashMap<String, Object>();
        map.put("uid", uid);
        map.put("code", code);
        return this.sqlSession.selectOne("BonusMapper.fetchBonusByCode", map);
    }

    public int offline(int id,int online){
        Map map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("online", online);
        return this.sqlSession.update("BonusMapper.offline", map);
    }

}
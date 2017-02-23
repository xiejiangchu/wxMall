package com.xie.dao;

import com.xie.bean.Bonus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class BonusDao extends BaseDao {

    public List<Bonus> getAllByUid(Integer uid) {
        return this.sqlSession.selectList("BonusMapper.getAllByUid", uid);
    }

    public Bonus getById(Integer id) {
        return this.sqlSession.selectOne("BonusMapper.getById", id);
    }

    public int countByUid(Integer uid) {
        return this.sqlSession.selectOne("BonusMapper.countByUid", uid);
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

}
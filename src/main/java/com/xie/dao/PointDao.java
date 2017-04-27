package com.xie.dao;

import com.xie.bean.Point;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xie
 * @Date 17/1/19 下午4:55.
 */
@Component
public class PointDao extends BaseDao {

    public Point getByUid(Integer uid) {
        return this.sqlSession.selectOne("PointMapper.getByUid", uid);
    }

    public Point getById(int id) {
        return this.sqlSession.selectOne("PointMapper.getById", id);
    }

    public int insert(Point point) {
        this.sqlSession.insert("PointMapper.insert", point);
        return point.getId();
    }

    public int add(int uid, double money, int points) {
        Map map = new HashMap<>();
        map.put("uid", uid);
        map.put("money", money);
        map.put("points", points);
        return this.sqlSession.update("PointMapper.add", map);
    }

    public int update(Point point) {
        return this.sqlSession.update("PointMapper.update", point);
    }

    public int delete(Point point) {
        Assert.notNull(point);
        Assert.isTrue(point.getId() > 0);
        return this.sqlSession.delete("PointMapper.delete", point.getId());
    }

    public int delete(int id) {
        return this.sqlSession.delete("PointMapper.delete", id);
    }
}

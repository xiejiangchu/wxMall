package com.xie.dao;

import com.xie.bean.Banner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class BannerDao extends BaseDao {

    public List<Banner> getAll() {
        return this.sqlSession.selectList("BannerMapper.getAll");
    }

    public List<Banner> getAllCanShow() {
        return this.sqlSession.selectList("BannerMapper.getAllCanShow");
    }

    public Banner getById(Integer id) {
        return this.sqlSession.selectOne("BannerMapper.getById", id);
    }

    public int count(boolean all) {
        return this.sqlSession.selectOne("BannerMapper.count", all);
    }

    public int insert(Banner banner) {
        return this.sqlSession.insert("BannerMapper.insert",banner);
    }

    public int update(Banner banner) {
        return this.sqlSession.update("BannerMapper.update", banner);
    }

    public int delete(Integer id) {
        return this.sqlSession.delete("BannerMapper.delete", id);
    }

    public int softDelete(Integer id) {
        return this.sqlSession.update("BannerMapper.softDelete", id);
    }

    public int offline(int id, int online) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("online", online);
        return this.sqlSession.update("BannerMapper.offline", map);
    }

}
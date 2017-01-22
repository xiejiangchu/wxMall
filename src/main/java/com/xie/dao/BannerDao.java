package com.xie.dao;

import com.xie.bean.Banner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class BannerDao extends BaseDao {

    public List<Banner> getAll() {
        return this.sqlSession.selectList("BannerDao.getAll");
    }

    public List<Banner> getAllCanShow() {
        return this.sqlSession.selectList("BannerDao.getAllCanShow");
    }

    public Banner getById(Integer id) {
        return this.sqlSession.selectOne("BannerDao.getById", id);
    }

    public int count(boolean all) {
        return this.sqlSession.selectOne("BannerDao.count", all);
    }

    public int insert(Banner banner) {
        return this.sqlSession.selectOne("BannerDao.insert");
    }

    public int update(Banner banner) {
        return this.sqlSession.selectOne("BannerDao.update", banner);
    }

    public int delete(Integer id) {
        return this.sqlSession.selectOne("BannerDao.delete", id);
    }

    public int softDelete(Integer id) {
        return this.sqlSession.selectOne("BannerDao.softDelete", id);
    }

}
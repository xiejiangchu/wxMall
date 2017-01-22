package com.xie.service.impl;

import com.xie.bean.Banner;
import com.xie.dao.BannerDao;
import com.xie.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    public List<Banner> getAllCanShow() {
        return bannerDao.getAllCanShow();
    }

    @Override
    public List<Banner> getAll() {
        return bannerDao.getAll();
    }

    @Override
    public Banner getById(int id) {
        return bannerDao.getById(id);
    }

    @Override
    public int count(boolean all) {
        return bannerDao.count(all);
    }

    @Override
    public int insert(Banner banner) {
        return bannerDao.insert(banner);
    }

    @Override
    public int update(Banner banner) {
        return bannerDao.update(banner);
    }

    @Override
    public int delete(Banner banner) {
        Assert.notNull(banner);
        Assert.isTrue(banner.getId() > 0);
        return bannerDao.delete(banner.getId());
    }

    @Override
    public int delete(int id) {
        Assert.isTrue(id > 0);
        return bannerDao.delete(id);
    }

    @Override
    public int softDelete(int id) {
        Assert.isTrue(id > 0);
        return bannerDao.softDelete(id);
    }
}

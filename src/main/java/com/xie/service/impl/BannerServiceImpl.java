package com.xie.service.impl;

import com.xie.bean.Banner;
import com.xie.mapper.BannerMapper;
import com.xie.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    BannerMapper bannerMapper;

    @Override
    public List<Banner> getAllCanShow() {
        return bannerMapper.getAllBannersCanShow();
    }

    @Override
    public List<Banner> getAll() {
        return bannerMapper.getAllBanners();
    }

    @Override
    public Banner getById(int id) {
        return bannerMapper.getBannerById(id);
    }
}

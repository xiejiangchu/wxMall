package com.xie.service;

import com.xie.bean.Banner;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
public interface BannerService {

    List<Banner> getAll();

    List<Banner> getAllCanShow();

    Banner getById(int id);
}

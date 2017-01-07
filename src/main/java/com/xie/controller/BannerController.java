package com.xie.controller;

import com.xie.bean.Banner;
import com.xie.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
@RestController
public class BannerController {

    @Autowired
    BannerService bannerService;

    @RequestMapping(value = "/banner/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Banner> getAllBanners() {
        return bannerService.getAll();
    }
}

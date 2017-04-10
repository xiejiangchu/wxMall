package com.xie.controller.api;

import com.xie.bean.Banner;
import com.xie.response.BaseResponse;
import com.xie.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/banner")
public class BannerController extends BaseController{

    @Autowired
    BannerService bannerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        return BaseResponse.ok(bannerService.getById(id));
    }

    /**
     * 后台管理
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(bannerService.getAll(pageNum, pageSize));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list() {
        return BaseResponse.ok(bannerService.getAllCanShow());
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@ModelAttribute Banner banner) {
        int result = bannerService.insert(banner);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@PathVariable("id") int id, @ModelAttribute Banner banner) {
        int result = bannerService.update(banner);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse delete(@PathVariable("id") int id) {
        int result = bannerService.softDelete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }

    }
}

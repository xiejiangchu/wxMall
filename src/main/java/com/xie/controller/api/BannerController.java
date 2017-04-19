package com.xie.controller.api;

import com.xie.bean.Banner;
import com.xie.bean.Image;
import com.xie.request.BannerDto;
import com.xie.response.BaseResponse;
import com.xie.service.BannerService;
import com.xie.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/banner")
public class BannerController extends BaseController {

    @Autowired
    BannerService bannerService;

    @Autowired
    ImageFileService imageFileService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        return BaseResponse.ok(bannerService.getById(id));
    }

    /**
     * 后台管理
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
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
    public BaseResponse post(@RequestBody BannerDto bannerDto) {
        Banner banner = bannerDto.getBanner();
        if (bannerDto.getImage().size() > 0) {
            Image image = imageFileService.getById(bannerDto.getImage().get(0));
            if (image != null) {
                banner.setUrl(image.getUri());
            }
        }
        int result = bannerService.insert(banner);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/offline", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse offline(@RequestParam("id") int id,
                                @RequestParam("online") int online) {
        int result = bannerService.offline(id, online);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@PathVariable("id") int id, @RequestBody BannerDto bannerDto) {
        Banner banner = bannerDto.getBanner();
        if (bannerDto.getImage().size() > 0) {
            Image image = imageFileService.getById(bannerDto.getImage().get(0));
            if (image != null) {
                banner.setUrl(image.getUri());
            }
        }

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
        int result = bannerService.delete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }

    }
}

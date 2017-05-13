package com.xie.controller.api;

import com.xie.bean.Error;
import com.xie.bean.SysConfig;
import com.xie.request.SysConfigDto;
import com.xie.response.BaseResponse;
import com.xie.service.ErrorService;
import com.xie.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/sysConfig")
public class SysConfigController extends BaseController {

    @Autowired
    private ErrorService errorService;

    @Autowired
    private SystemConfigService systemConfigService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        return BaseResponse.ok(systemConfigService.getById(id));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list() {
        return BaseResponse.ok(systemConfigService.getAll());
    }

    @RequestMapping(value = "/error", method = RequestMethod.POST)
    @ResponseBody
    BaseResponse error(@RequestBody Error error) {
        int result = errorService.insert(error);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/errors", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse errors(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "10")int pageSize) {
        return BaseResponse.ok(errorService.getAll(pageNum,pageSize));
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@ModelAttribute SysConfig sysConfig) {
        int result = systemConfigService.insert(sysConfig);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@PathVariable("id") int id, @ModelAttribute SysConfig sysConfig) {
        int result = systemConfigService.update(sysConfig);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse delete(@PathVariable("id") int id) {
        int result = systemConfigService.delete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }

    }

    @RequestMapping(value = "/saveQuestionAndAbout", method = RequestMethod.PUT)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse saveQuestionAndAbout(@RequestBody SysConfigDto sysConfigDto) {
        return BaseResponse.ok(systemConfigService.saveQuestionAndAbout(sysConfigDto.getQuestions(), sysConfigDto.getAbout(), sysConfigDto.getNotice()));
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse questions() {
        return BaseResponse.ok(systemConfigService.questions());
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse about() {
        return BaseResponse.ok(systemConfigService.about());
    }

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse notice() {
        return BaseResponse.ok(systemConfigService.notice());
    }
}

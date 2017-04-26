package com.xie.controller.api;

import com.xie.bean.Notice;
import com.xie.response.BaseResponse;
import com.xie.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-04-22 下午9:14
 */
@Controller
@RequestMapping(value = "/notice")
public class NoticeController extends BaseController {

    @Autowired
    SystemConfigService systemConfigService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse list() {

        Notice notice = new Notice();
        notice.setId(1);
        notice.setContent(systemConfigService.notice().getValue());
        return BaseResponse.ok(notice);
    }
}

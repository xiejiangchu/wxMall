package com.xie.controller;

import com.xie.bean.User;
import com.xie.response.BaseResponse;
import com.xie.utils.MallConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xie on 17/1/7.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin/index";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "admin/404";
    }


}

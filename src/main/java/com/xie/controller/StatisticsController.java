package com.xie.controller;

import com.xie.response.BaseResponse;
import com.xie.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author xie
 * @Date 17/3/4 上午9:25.
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse dashboard() {
        return BaseResponse.ok(statisticsService.dashboard());
    }
}

package com.xie.controller.api;

import com.xie.bean.CategoryFood;
import com.xie.response.BaseResponse;
import com.xie.service.CategoryFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-25 下午1:04
 */
@Controller
@RequestMapping("categoryFood")
public class CategoryFoodController extends BaseController {

    @Autowired
    private CategoryFoodService categoryFoodService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(categoryFoodService.getAll(pageNum, pageSize));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathParam("id") int id) {
        return BaseResponse.ok(categoryFoodService.getById(id));
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@RequestBody CategoryFood categoryFood) {
        return BaseResponse.ok(categoryFoodService.insert(categoryFood));
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@RequestBody CategoryFood categoryFood) {
        return BaseResponse.ok(categoryFoodService.update(categoryFood));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse delete(@PathParam("id") int id) {
        return BaseResponse.ok(categoryFoodService.delete(id));
    }
}

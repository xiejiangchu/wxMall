package com.xie.controller.api;

import com.xie.bean.Food;
import com.xie.response.BaseResponse;
import com.xie.service.FoodService;
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
@RequestMapping("food")
public class FoodController extends BaseController {

    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(foodService.getAll(pageNum, pageSize));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse search(@RequestParam(value = "name") String name) {
        return BaseResponse.ok(foodService.search(name));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathParam("id") int id) {
        return BaseResponse.ok(foodService.getById(id));
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@RequestBody Food food) {
        return BaseResponse.ok(foodService.insert(food));
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@RequestBody Food food) {
        return BaseResponse.ok(foodService.update(food));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse delete(@PathParam("id") int id) {
        return BaseResponse.ok(foodService.delete(id));
    }
}

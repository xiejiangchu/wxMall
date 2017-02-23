package com.xie.controller.api;

import com.xie.response.BaseResponse;
import com.xie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController{

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getAll() {
        return BaseResponse.ok(categoryService.getAll());
    }

    @RequestMapping(value = "/getCategoryLevel1", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getCategoryLevel1() {
        return BaseResponse.ok(categoryService.getCategoryLevel1());
    }


    @RequestMapping(value = "/getCategoryLevel2/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getCategoryLevel2(@PathVariable("pid") int pid) {
        return BaseResponse.ok(categoryService.getCategoryLevel2(pid));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getCategoryDetail(@PathVariable("id") int id) {
        return BaseResponse.ok(categoryService.getById(id));
    }
}

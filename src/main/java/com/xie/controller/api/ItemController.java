package com.xie.controller.api;

import com.xie.response.BaseResponse;
import com.xie.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 16/11/24.
 */
@Controller
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getGoods(@PathVariable("id") int id) {
        return BaseResponse.ok(itemService.getById(id));
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getAll(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return BaseResponse.ok(itemService.getAll(pageNum, pageSize));
    }

    @RequestMapping(value = "/getByCategory", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getByCategory(@RequestParam("level1") int level1, @RequestParam("level2") int level2, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return BaseResponse.ok(itemService.getByCategory(level1, level2, pageNum, pageSize));
    }
}

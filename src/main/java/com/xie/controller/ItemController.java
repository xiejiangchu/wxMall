package com.xie.controller;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Item;
import com.xie.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 16/11/24.
 */
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "item/{id}", method = RequestMethod.GET)
    @ResponseBody
    Item getGoods(@PathVariable("id") int id) {
        return itemService.getById(id);
    }


    @RequestMapping(value = "item/getAll", method = RequestMethod.GET)
    @ResponseBody
    PageInfo<Item> getAll(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return itemService.getAll(pageNum, pageSize);
    }

    @RequestMapping(value = "item/getByCategory", method = RequestMethod.GET)
    @ResponseBody
    PageInfo<Item> getByCategory(@RequestParam("level1") int level1, @RequestParam("level2") int level2, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return itemService.getByCategory(level1, level2, pageNum, pageSize);
    }
}

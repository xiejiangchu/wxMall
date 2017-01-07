package com.xie.controller;

import com.xie.bean.Category;
import com.xie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/category/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/category/getCategoryLevel1", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getCategoryLevel1() {
        return categoryService.getCategoryLevel1();
    }


    @RequestMapping(value = "/category/getCategoryLevel2/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getCategoryLevel2(@PathVariable("pid") int pid) {
        return categoryService.getCategoryLevel2(pid);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Category getCategoryDetail(@PathVariable("id") int id) {
        return categoryService.getById(id);
    }
}

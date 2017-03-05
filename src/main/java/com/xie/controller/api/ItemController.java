package com.xie.controller.api;

import com.xie.bean.Item;
import com.xie.enums.ImageType;
import com.xie.request.ItemDto;
import com.xie.response.BaseResponse;
import com.xie.service.ImageFileService;
import com.xie.service.ItemImageService;
import com.xie.service.ItemService;
import com.xie.service.ItemSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xie on 16/11/24.
 */
@Controller
@RequestMapping(value = "/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemSpecService itemSpecService;

    @Autowired
    private ItemImageService itemImageService;

    @Autowired
    private ImageFileService imageFileService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(itemService.getAllAvailable(pageNum, pageSize));
    }

    @RequestMapping(value = "/getByCategory", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getByCategory(@RequestParam("cid1") Integer cid1,
                               @RequestParam("cid2") Integer cid2,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(itemService.getByCategory(cid1, cid2, pageNum, pageSize));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        return BaseResponse.ok(itemService.getDetailById(id));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse search(@RequestParam("keyword") String keyword,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(itemService.search(keyword, pageNum, pageSize));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(itemService.getAllCanShow(pageNum, pageSize));
    }

    @RequestMapping(value = "/top", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse top() {
        return BaseResponse.ok(itemService.top(1, 10));
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@ModelAttribute Item item) {
        int result = itemService.insert(item);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@PathVariable int id, @RequestBody ItemDto itemDto) {
        List<Integer> masterImageSelected = itemDto.getMasterImageSelected();
        Item item = itemDto.getItem();
        if (null != masterImageSelected && masterImageSelected.size() > 0) {
            item.setSrc(imageFileService.getById(masterImageSelected.get(0)).getUri());
            item.setThumb(imageFileService.getById(masterImageSelected.get(0)).getUri());
        }
        int result = itemService.update(item);
        List<Integer> slaveImageSelected = itemDto.getSlaveImageSelected();
        if (null != slaveImageSelected) {
            for (int i = 0; i < slaveImageSelected.size(); i++) {
                if (itemImageService.check(id, slaveImageSelected.get(i)) <= 0) {
                    itemImageService.insert(id, slaveImageSelected.get(i), ImageType.详情图片.value());
                }
            }
        }
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse delete(@PathVariable int id) {
        int result = itemService.softDelete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }

    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse count(@RequestParam(value = "all", defaultValue = "false") boolean all) {
        return BaseResponse.ok(itemService.count(all));
    }

    @RequestMapping(value = "/build", method = RequestMethod.GET)
    @ResponseBody
    public String build() {
        List<Item> items = itemService.getAll();
        for (int i = 0; i < items.size(); i++) {

        }
        return "fail";
    }
}

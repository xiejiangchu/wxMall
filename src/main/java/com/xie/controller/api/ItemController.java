package com.xie.controller.api;

import com.xie.bean.Item;
import com.xie.enums.ImageType;
import com.xie.request.ItemDto;
import com.xie.response.BaseResponse;
import com.xie.service.ImageFileService;
import com.xie.service.ItemImageService;
import com.xie.service.ItemService;
import com.xie.service.ItemSpecService;
import com.xie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * 获取能够显示的
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getAllAvailable", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getAllAvailable(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(itemService.getAllAvailable(pageNum, pageSize));
    }

    /**
     * 获取所有的
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    BaseResponse getAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(itemService.getAll(pageNum, pageSize));
    }

    /**
     * 由分类获取所有商品
     *
     * @param cid1
     * @param cid2
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getByCategory", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getByCategory(@RequestParam("cid1") Integer cid1,
                               @RequestParam("cid2") Integer cid2,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(itemService.getByCategory(cid1, cid2, pageNum, pageSize));
    }

    /**
     * 由分类获取所有商品
     *
     * @param cid1
     * @param cid2
     * @return
     */
    @RequestMapping(value = "/getByCategoryWithoutPaginate", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getByCategoryWithoutPaginate(@RequestParam("cid1") Integer cid1,
                                              @RequestParam("cid2") Integer cid2) {
        return BaseResponse.ok(itemService.getByCategoryWithoutPaginate(cid1, cid2));
    }

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        if (itemService.isOnline(id)) {
            return BaseResponse.ok(itemService.getDetailById(id));
        } else {
            return BaseResponse.fail("商品未上架");
        }

    }

    /**
     * 获取详情(后台管理)
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse detail(@PathVariable("id") int id) {
        return BaseResponse.ok(itemService.getDetailById(id));
    }

    /**
     * 查找
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse search(@RequestParam("keyword") String keyword,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(itemService.search(keyword, pageNum, pageSize));
    }

    /**
     * 列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list(@RequestParam(value = "orderby", required = false, defaultValue = "0") int orderby,
                      @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(itemService.getAllCanShow(orderby, pageNum, pageSize));
    }

    /**
     * top 10个
     *
     * @return
     */
    @RequestMapping(value = "/top", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse top() {
        return BaseResponse.ok(itemService.top(1, 10));
    }


    /**
     * 修改
     *
     * @param id
     * @param itemDto
     * @return
     */
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
        if (null != slaveImageSelected && slaveImageSelected.size() > 0) {
            itemImageService.deleteByIid(id);
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

    /**
     * 增加
     *
     * @param itemDto
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@RequestBody ItemDto itemDto) {
        List<Integer> masterImageSelected = itemDto.getMasterImageSelected();
        Item item = itemDto.getItem();
        item.setNo(StringUtils.generateItemNo());
        if (null != masterImageSelected && masterImageSelected.size() > 0) {
            item.setSrc(imageFileService.getById(masterImageSelected.get(0)).getUri());
            item.setThumb(imageFileService.getById(masterImageSelected.get(0)).getUri());
        }
        int id = itemService.insert(item);
        List<Integer> slaveImageSelected = itemDto.getSlaveImageSelected();
        if (null != slaveImageSelected) {
            for (int i = 0; i < slaveImageSelected.size(); i++) {
                if (itemImageService.check(id, slaveImageSelected.get(i)) <= 0) {
                    itemImageService.insert(id, slaveImageSelected.get(i), ImageType.详情图片.value());
                }
            }
        }
        if (id > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    /**
     * 下架商品
     *
     * @param item
     * @return
     */
    @RequestMapping(value = "/offline", method = RequestMethod.PUT)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse offline(@RequestBody Item item) {
        int result = itemService.offline(item.getId(), item.getIs_online());
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/offline2", method = RequestMethod.PUT)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse offline2(@RequestParam(value = "id") int id,
                                 @RequestParam(value = "online") int online) {
        int result = itemService.offline(id, online);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }


    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse delete(@PathVariable int id) {
        int result = itemService.softDelete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    /**
     * 个人页面的数量
     *
     * @param all
     * @return
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse count(@RequestParam(value = "all", defaultValue = "false") boolean all) {
        return BaseResponse.ok(itemService.count(all));
    }
}

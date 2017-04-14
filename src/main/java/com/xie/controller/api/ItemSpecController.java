package com.xie.controller.api;

import com.xie.bean.ItemSpec;
import com.xie.response.BaseResponse;
import com.xie.service.ItemSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author xie
 * @Date 17/3/14 上午11:26.
 */
@Controller
@RequestMapping(value = "/itemSpec")
public class ItemSpecController {

    @Autowired
    private ItemSpecService itemSpecService;

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        return BaseResponse.ok(itemSpecService.getById(id));
    }

    /**
     * 获取能够显示的
     *
     * @param gid
     * @return
     */
    @RequestMapping(value = "/getAllByGid", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getAllByGid(@RequestParam(value = "gid") int gid) {
        return BaseResponse.ok(itemSpecService.getAllByGid(gid));
    }

    @RequestMapping(value = "/getOnlineByGid", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getOnlineByGid(@RequestParam(value = "gid") int gid) {
        return BaseResponse.ok(itemSpecService.getOnlineByGid(gid));
    }


    @RequestMapping(value = "/offline", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse offline(@RequestParam(value = "id") int id,
                                @RequestParam(value = "online") int online) {
        int result = itemSpecService.offline(id, online);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    /**
     * 修改
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@RequestBody ItemSpec itemSpec) {
        int result = itemSpecService.update(itemSpec);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    /**
     * 修改
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@RequestBody ItemSpec itemSpec) {
        int result = itemSpecService.insert(itemSpec);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse delete(@PathVariable int id) {
        int result = itemSpecService.delete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }
}

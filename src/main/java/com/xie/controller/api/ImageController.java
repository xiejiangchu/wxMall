package com.xie.controller.api;

import com.xie.bean.Image;
import com.xie.response.BaseResponse;
import com.xie.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/image")
public class ImageController extends BaseController {

    @Autowired
    ImageFileService imageFileService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        return BaseResponse.ok(imageFileService.getById(id));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(imageFileService.getAll(pageNum, pageSize));
    }

    @RequestMapping(value = "/listDirServer", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse listDirServer(@RequestParam(value = "dir", defaultValue = "", required = false) String dir,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(imageFileService.getDirsServer(dir, pageNum, pageSize));
    }

    @RequestMapping(value = "/listFileServer", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse listFileServer(@RequestParam(value = "dir", defaultValue = "", required = false) String dir,
                                @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(imageFileService.getFileServer(dir, pageNum, pageSize));
    }

    @RequestMapping(value = "/getByName", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getByName(@RequestParam(value = "name") String name) {
        return BaseResponse.ok(imageFileService.getByName(name));
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@ModelAttribute Image image) {
        int result = imageFileService.insert(image);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@PathVariable("id") int id, @ModelAttribute Image image) {
        int result = imageFileService.update(image);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse delete(@PathVariable("id") int id) {
        int result = imageFileService.delete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }
}

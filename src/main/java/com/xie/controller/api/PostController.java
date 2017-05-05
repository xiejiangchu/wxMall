package com.xie.controller.api;

import com.xie.bean.Image;
import com.xie.bean.Post;
import com.xie.request.PostDto;
import com.xie.response.BaseResponse;
import com.xie.service.ImageFileService;
import com.xie.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/post")
public class PostController extends BaseController {

    @Autowired
    private PostService postService;

    @Autowired
    private ImageFileService imageFileService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        return BaseResponse.ok(postService.getById(id));
    }

    /**
     * 后台管理
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse getAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(postService.getAll(pageNum, pageSize));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(postService.getAllCanShow(pageNum, pageSize));
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@RequestBody PostDto postDto) {
        Post post = postDto.getPost();
        if (postDto.getImage().size() > 0) {
            Image image = imageFileService.getById(postDto.getImage().get(0));
            if (image != null) {
                post.setSrc(image.getUri());
            }
        }
        int result = postService.insert(post);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/offline", method = RequestMethod.PUT)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse offline2(@RequestParam(value = "id") int id,
                                 @RequestParam(value = "online") int online) {
        int result = postService.offline(id, online);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@PathVariable("id") int id, @RequestBody PostDto postDto) {
        Post post = postDto.getPost();
        if (postDto.getImage().size() > 0) {
            Image image = imageFileService.getById(postDto.getImage().get(0));
            if (image != null) {
                post.setSrc(image.getUri());
            }
        }

        int result = postService.update(post);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse delete(@PathVariable("id") int id) {
        int result = postService.delete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }

    }
}

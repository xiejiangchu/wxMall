package com.xie.request;

import com.xie.bean.Post;

import java.util.List;

/**
 * @Author xie
 * @Date 17/3/4 下午6:59.
 */
public class PostDto {

    private Post post;
    private List<Integer> image;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Integer> getImage() {
        return image;
    }

    public void setImage(List<Integer> image) {
        this.image = image;
    }
}

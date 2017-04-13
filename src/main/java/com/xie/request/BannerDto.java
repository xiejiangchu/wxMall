package com.xie.request;

import com.xie.bean.Banner;

import java.util.List;

/**
 * @Author xie
 * @Date 17/3/4 下午6:59.
 */
public class BannerDto {

    private Banner banner;
    private List<Integer> image;

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public List<Integer> getImage() {
        return image;
    }

    public void setImage(List<Integer> image) {
        this.image = image;
    }
}

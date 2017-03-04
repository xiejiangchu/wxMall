package com.xie.enums;

/**
 * @Author xie
 * @Date 17/3/4 下午8:37.
 */
public enum ImageType {

    详情图片(0),
    主图(1);

    private Integer _value;

    ImageType(Integer value) {
        this._value = value;
    }

    public Integer value() {
        return _value;
    }
}

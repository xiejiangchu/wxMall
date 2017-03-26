package com.xie.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-26 下午4:49
 */
public enum ItemOrderByType {

    推荐排序(0),
    按商品名称字典正序(10),
    按商品名称字典反序(11),
    按上架时间由新到旧(20),
    按上架时间由旧到新(21),
    按商品类别由低到高(30),
    按商品类别由高到低(31);

    private Integer _value;

    ItemOrderByType(Integer value) {
        this._value = value;
    }

    public Integer value() {
        return _value;
    }
}

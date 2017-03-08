package com.xie.enums;

/**
 * @Author xie
 * @Date 17/2/27 下午3:14.
 */
public enum OrderType {
    所有(0),
    待支付(10),
    待发货(20),
    待收货(30),
    已完成(40);

    private Integer _value;

    OrderType(Integer value) {
        this._value = value;
    }

    public Integer value() {
        return _value;
    }
}

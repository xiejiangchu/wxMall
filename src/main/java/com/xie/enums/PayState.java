package com.xie.enums;

/**
 * @Author xie
 * @Date 17/1/22 下午2:22.
 */
public enum PayState {

    货到付款(00),
    未支付(10),
    已支付(20);

    private Integer _value;

    PayState(Integer value) {
        this._value = value;
    }

    public Integer value() {
        return _value;
    }
}

package com.xie.enums;

/**
 * @Author xie
 * @Date 17/3/2 下午3:44.
 */
public enum BonusQueryType {

    未使用(10),
    已过期(20);

    private Integer _value;

    BonusQueryType(Integer value) {
        this._value = value;
    }

    public Integer value() {
        return _value;
    }
}

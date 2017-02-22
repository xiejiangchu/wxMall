package com.xie.enums;

/**
 * @Author xie
 * @Date 17/1/22 下午2:22.
 */
public enum PackageState {

    已打包(10),
    未打包(20);

    private Integer _value;

    PackageState(Integer value) {
        this._value = value;
    }

    public Integer value() {
        return _value;
    }
}

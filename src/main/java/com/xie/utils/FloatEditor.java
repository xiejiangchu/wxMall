package com.xie.utils;

import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * @Author xie
 * @Date 17/2/24 上午9:00.
 */
public class FloatEditor extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.equals("")) {
            text = "0";
        }
        setValue(Float.parseFloat(text));
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}

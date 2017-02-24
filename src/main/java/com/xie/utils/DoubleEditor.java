package com.xie.utils;

import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * @Author xie
 * @Date 17/2/24 上午8:59.
 */
public class DoubleEditor extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.equals("")) {
            text = "0";
        }
        setValue(Double.parseDouble(text));
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}

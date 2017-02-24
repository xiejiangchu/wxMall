package com.xie.utils;

/**
 * @Author xie
 * @Date 17/2/24 上午8:58.
 */

import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.util.Date;

public class DateEditor extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.equals("")) {
            text = "0";
        }
        setValue(new Date(Long.parseLong(text)));
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
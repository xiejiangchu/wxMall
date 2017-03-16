package com.xie.request;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserRegisterDto implements Serializable {

    @NotNull(message = "用户名为空")
    private String name;
    @NotNull(message = "手机号为空")
    private String mobile;
    @NotNull(message = "电子邮件为空")
    private String email;
    @NotNull(message = "密码为空")
    private String password;
    @NotNull(message = "密码为空")
    private String password_re;
    private String remember_token;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

    public String getPassword_re() {
        return password_re;
    }

    public void setPassword_re(String password_re) {
        this.password_re = password_re;
    }
}


package com.xie.auth;

import com.xie.service.UserService;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary Spring-Boot (四) 集成Spring Security
 *          http://nealma.com/2016/04/30/spring-boot-4-security/
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-23 下午4:16
 */
public class MyPersistentTokenRepository implements PersistentTokenRepository {

    private UserService userService;
    @Override
    public void createNewToken(PersistentRememberMeToken token) {

    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return null;
    }

    @Override
    public void removeUserTokens(String username) {

    }
}

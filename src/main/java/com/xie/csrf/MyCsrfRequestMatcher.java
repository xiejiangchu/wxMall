package com.xie.csrf;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * @Author xie
 * @Date 17/1/20 下午1:06.
 */
@Component
public class MyCsrfRequestMatcher implements RequestMatcher {

    private AntPathRequestMatcher[] requestMatchers = {
//            new AntPathRequestMatcher("/user/*"),
//            new AntPathRequestMatcher("/register")
    };

    private RegexRequestMatcher requestMatcher = new RegexRequestMatcher("^/.*$", null);
    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

    @Override
    public boolean matches(HttpServletRequest request) {
        for (AntPathRequestMatcher rm : requestMatchers) {
            if (rm.matches(request)) {
                return true;
            }
        }

//        if (requestMatcher.matches(request))
//            return true;

//        return !(allowedMethods.matcher(request.getMethod()).matches());
        return false;
    }
}

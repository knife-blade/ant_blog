package com.knife.ant.config.shiro.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.knife.ant.common.constant.AuthConstant;
import com.knife.ant.common.util.ResponseUtil;
import com.knife.ant.common.util.auth.JwtUtil;
import com.knife.ant.config.shiro.entity.JwtToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtFilter extends AuthenticatingFilter {
    /**
     * 所有请求都会到这里来（无论是不是anon）。
     * 返回true：表示允许向下走。
     * 返回false：表示不允许向下走。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest,
                                     ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(AuthConstant.AUTHENTICATION_HEADER);

        if (!StringUtils.hasText(token)) {
            ResponseUtil.jsonResponse((HttpServletResponse) servletResponse,
                    HttpStatus.UNAUTHORIZED.value(), "授权信息不能为空");
            return false;
        } else {
            try {
                JwtUtil.verifyToken(token);
            } catch (TokenExpiredException e) {
                ResponseUtil.jsonResponse((HttpServletResponse) servletResponse,
                        HttpStatus.UNAUTHORIZED.value(), "授权信息已过期，请重新登录");
                return false;
            } catch (Exception e) {
                ResponseUtil.jsonResponse((HttpServletResponse) servletResponse,
                        HttpStatus.UNAUTHORIZED.value(), "授权信息校验失败");
                return false;
            }
        }

        // 此登录并非调用login接口，而是shiro层面的登录。
        // 里边会调用下边的createToken方法
        return executeLogin(servletRequest, servletResponse);
    }

    /**
     * 这里的token会传给AuthorizingRealm子类（本处是AccountRealm）的doGetAuthenticationInfo方法作为参数。
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest,
                                              ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(AuthConstant.AUTHENTICATION_HEADER);
        if (!StringUtils.hasText(token)) {
            return null;
        }
        return new JwtToken(token);
    }
}

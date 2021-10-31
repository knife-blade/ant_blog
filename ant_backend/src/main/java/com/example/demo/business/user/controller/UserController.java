package com.example.demo.business.user.controller;

import com.example.demo.business.user.entity.User;
import com.example.demo.business.user.entity.UserVO;
import com.example.demo.business.user.service.UserService;
import com.example.demo.common.constant.AuthConstant;
import com.example.demo.common.entity.Result;
import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.util.auth.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "用户")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // 默认账号密码：knife 222333
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody User user, HttpServletResponse response) {
        Assert.hasLength(user.getUserName(), "用户名不能为空");
        Assert.hasLength(user.getPassword(), "密码不能为空");
        User userFromDB = userService.lambdaQuery().eq(User::getUserName, user.getUserName()).one();

        Assert.notNull(userFromDB, "用户不存在");

        String calculatedPassword = new SimpleHash(AuthConstant.ALGORITHM_TYPE,
                user.getPassword(), userFromDB.getSalt(), AuthConstant.HASH_ITERATIONS).toString();

        if (!userFromDB.getPassword().equals(calculatedPassword)) {
            throw new BusinessException("用户名或密码不正确");
        }

        String jwt = JwtUtil.createToken(userFromDB.getId().toString());

        response.setHeader(AuthConstant.AUTHENTICATION_HEADER, jwt);
        // response.setHeader("Access-Control-Expose-Headers", TOKEN_HEADER);   // 后端配置跨域时使用

        UserVO userVO = new UserVO();
        userVO.setId(userFromDB.getId());
        userVO.setUserName(userFromDB.getUserName());
        userVO.setNickName(userFromDB.getNickName());
        userVO.setAvatarUrl(userFromDB.getAvatarUrl());
        userVO.setEmail(userFromDB.getEmail());

        return new Result<UserVO>().success().data(userVO);
    }

    @ApiOperation("退出登录")
    @RequiresAuthentication
    @PostMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return new Result();
    }
}
package com.example.demo.business.logout;

import com.example.demo.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "退出")
@RestController
@RequestMapping("/logout")
public class LogoutController {
    @ApiOperation("退出登录")
    @RequiresAuthentication
    @PostMapping
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return new Result();
    }
}
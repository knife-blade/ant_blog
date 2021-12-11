package com.example.demo.business.login;

import com.example.demo.business.user.entity.User;
import com.example.demo.business.user.entity.UserVO;
import com.example.demo.business.user.service.UserService;
import com.example.demo.common.constant.AuthConstant;
import com.example.demo.common.entity.Result;
import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.util.auth.JwtUtil;
import com.example.demo.config.properties.UserProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "登录/注册")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserProperty userProperty;

    /**
     * 若未注册则自动注册，若已注册则登录
     */
    @ApiOperation("登录/注册")
    @PostMapping
    public Result<UserVO> login(@RequestBody User user, HttpServletResponse response) {
        Assert.hasLength(user.getUserName(), "用户名不能为空");
        Assert.hasLength(user.getPassword(), "密码不能为空");

        User userFromDB = userService.lambdaQuery().eq(User::getUserName, user.getUserName()).one();
        if (userFromDB == null) {
            userFromDB = register(user.getUserName(), user.getPassword());
        } else {
            String calculatedPassword = new SimpleHash(AuthConstant.ALGORITHM_TYPE,
                    user.getPassword(), userFromDB.getSalt(), AuthConstant.HASH_ITERATIONS).toString();

            if (!userFromDB.getPassword().equals(calculatedPassword)) {
                throw new BusinessException("用户名或密码不正确");
            }
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

    private User register(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setNickName(userName);

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String calculatedPassword = new SimpleHash(AuthConstant.ALGORITHM_TYPE,
                password, salt, AuthConstant.HASH_ITERATIONS).toString();
        user.setPassword(calculatedPassword);
        user.setAvatarUrl(userProperty.getDefaultAvatarUrl());
        userService.save(user);
        return user;
    }
}
package com.knife.ant.business.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.knife.ant.business.user.entity.User;
import com.knife.ant.business.user.vo.UserProfile;
import com.knife.ant.business.user.vo.UserVO;
import com.knife.ant.business.user.service.UserService;
import com.knife.ant.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("page")
    public IPage<UserVO> page(Page<User> page) {
        Page<User> pageResult = userService.lambdaQuery()
                .orderByDesc(User::getCreateTime)
                .page(page);
        List<User> records = pageResult.getRecords();
        List<UserVO> userVOList = new ArrayList<>();
        for (User user : records) {
            userVOList.add(userService.convertToUserVO(user));
        }

        Page<UserVO> userVOPage = new Page<>();
        userVOPage.setRecords(userVOList);
        userVOPage.setTotal(pageResult.getTotal());
        userVOPage.setSize(pageResult.getSize());
        userVOPage.setCurrent(pageResult.getCurrent());
        return userVOPage;
    }

    @GetMapping("profile")
    public UserProfile profile(@RequestParam String userName) {
        if (!StringUtils.hasText(userName)) {
            throw new BusinessException("用户名不能为空");
        }

        return userService.findUser(userName);
    }
}

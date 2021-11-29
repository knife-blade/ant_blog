package com.example.demo.business.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.business.user.entity.User;
import com.example.demo.business.user.entity.UserVO;
import com.example.demo.business.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        Page<User> pageResult = userService.page(page);
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
}

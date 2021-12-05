package com.example.demo.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.business.user.entity.User;
import com.example.demo.business.user.entity.UserVO;

import java.util.List;

public interface UserService extends IService<User> {
    UserVO convertToUserVO(User user);

    UserVO findUser(String userName);
}

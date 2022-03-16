package com.example.demo.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.business.user.entity.User;
import com.example.demo.business.user.vo.UserProfile;
import com.example.demo.business.user.vo.UserVO;

public interface UserService extends IService<User> {
    UserVO convertToUserVO(User user);

    UserProfile findUser(String userName);
}

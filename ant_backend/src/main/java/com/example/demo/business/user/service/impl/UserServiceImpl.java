package com.example.demo.business.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.business.user.entity.User;
import com.example.demo.business.user.entity.UserVO;
import com.example.demo.business.user.mapper.UserMapper;
import com.example.demo.business.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public UserVO convertToUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setAvatarUrl(user.getAvatarUrl());
        userVO.setUserName(user.getUserName());
        userVO.setNickName(user.getNickName());
        return userVO;
    }
}

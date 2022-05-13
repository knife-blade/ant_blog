package com.knife.ant.business.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knife.ant.business.blog.service.BlogService;
import com.knife.ant.business.user.entity.User;
import com.knife.ant.business.user.vo.UserProfile;
import com.knife.ant.business.user.vo.UserVO;
import com.knife.ant.business.user.mapper.UserMapper;
import com.knife.ant.business.user.service.UserService;
import com.knife.ant.common.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private BlogService blogService;

    @Override
    public UserVO convertToUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setAvatarUrl(user.getAvatarUrl());
        userVO.setUserName(user.getUserName());
        userVO.setNickName(user.getNickName());
        return userVO;
    }

    @Override
    public UserProfile findUser(String userName) {
        if (!StringUtils.hasText(userName)) {
            throw new BusinessException("用户名不能为空");
        }

        User user = lambdaQuery()
                .eq(User::getUserName, userName)
                .orderByDesc(User::getCreateTime)
                .one();
        if (user == null) {
            return null;
        }
        UserVO userVO = convertToUserVO(user);

        Integer blogCount = blogService.blogCount(user.getId());

        UserProfile userProfile = new UserProfile();
        BeanUtils.copyProperties(userVO, userProfile);

        userProfile.setBlogCount(blogCount);

        return userProfile;
    }
}

package com.knife.ant.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knife.ant.business.user.entity.User;
import com.knife.ant.business.user.vo.UserProfile;
import com.knife.ant.business.user.vo.UserVO;

public interface UserService extends IService<User> {
    UserVO convertToUserVO(User user);

    UserProfile findUser(String userName);
}

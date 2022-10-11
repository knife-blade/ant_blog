package com.knife.ant.business.user.facade;

import com.knife.ant.business.user.entity.User;
import com.knife.ant.business.user.vo.UserProfile;
import com.knife.ant.business.user.vo.UserVO;

public interface UserFacade {
    UserVO convertToUserVO(User user);

    UserProfile findUser(String userName);
}

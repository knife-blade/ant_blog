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
public class UserServiceImpl
        extends ServiceImpl<UserMapper, User>
        implements UserService {

}

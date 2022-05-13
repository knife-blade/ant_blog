package com.knife.ant.business.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.knife.ant.business.blog.entity.Blog;

import java.util.List;

public interface BlogService extends IService<Blog> {
    int deleteBlog(List<Long> blogIds);

    Integer blogCount(Long userId);
}

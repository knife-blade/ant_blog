package com.example.demo.business.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.business.blog.entity.Blog;

import java.sql.Wrapper;
import java.util.List;

public interface BlogService extends IService<Blog> {
    int deleteBlog(List<Long> blogIds);

    Integer blogCount(Long userId);
}

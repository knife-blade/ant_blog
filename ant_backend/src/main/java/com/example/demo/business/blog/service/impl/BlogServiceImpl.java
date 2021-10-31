package com.example.demo.business.blog.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.business.blog.entity.Blog;
import com.example.demo.business.blog.mapper.BlogMapper;
import com.example.demo.business.blog.service.BlogService;
import com.example.demo.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Override
    public int deleteBlog(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new BusinessException("博客id不能为空");
        }

        LambdaQueryChainWrapper<Blog> wrapper = lambdaQuery().in(Blog::getId, ids);
        return this.getBaseMapper().deleteBlog(wrapper);
    }
}

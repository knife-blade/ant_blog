package com.knife.ant.business.blog.facade.impl;

import com.knife.ant.business.blog.entity.Blog;
import com.knife.ant.business.blog.facade.BlogFacade;
import com.knife.ant.business.blog.service.BlogService;
import com.knife.ant.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogFacadeImpl implements BlogFacade {
    @Autowired
    private BlogService blogService;

    @Override
    public boolean deleteBlog(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new BusinessException("博客id不能为空");
        }

        return blogService.lambdaUpdate()
                .in(Blog::getId, ids)
                .remove();
    }

    @Override
    public Integer blogCount(Long userId) {
        return blogService.lambdaQuery()
                .eq(Blog::getUserId, userId)
                .count();
    }
}

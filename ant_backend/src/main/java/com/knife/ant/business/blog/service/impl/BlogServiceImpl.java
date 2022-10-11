package com.knife.ant.business.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.knife.ant.business.blog.entity.Blog;
import com.knife.ant.business.blog.mapper.BlogMapper;
import com.knife.ant.business.blog.service.BlogService;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl
        extends ServiceImpl<BlogMapper, Blog>
        implements BlogService {

}

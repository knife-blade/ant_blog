package com.example.demo.business.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.business.blog.entity.Blog;
import com.example.demo.business.blog.mapper.BlogMapper;
import com.example.demo.business.blog.service.BlogService;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}

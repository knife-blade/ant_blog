package com.knife.ant.business.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.knife.ant.business.blog.entity.Blog;
import com.knife.ant.business.blog.service.BlogService;
import com.knife.ant.common.entity.Result;
import com.knife.ant.common.exception.BusinessException;
import com.knife.ant.common.util.auth.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Api(tags = "博客")
@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @ApiOperation("创建博客")
    @RequiresAuthentication
    @PostMapping("/add")
    public Result<Blog> add(@RequestBody Blog blog) {
        Assert.hasText(blog.getTitle(), "标题不能为空");
        Assert.hasText(blog.getDescription(), "摘要不能为空");
        Assert.hasText(blog.getContent(), "内容不能为空");

        Blog temp = new Blog();
        temp.setUserId(Long.parseLong(ShiroUtil.getProfile().getUserId()));
        temp.setUpdateTime(LocalDateTime.now());
        temp.setStatus(0);

        blogService.save(temp);

        return new Result<Blog>().data(temp);
    }

    @ApiOperation("编辑博客")
    @RequiresAuthentication
    @PostMapping("/edit")
    public Result<Blog> edit(@RequestBody Blog blog) {
        Assert.isTrue(blog.getId() != null, "id不能为空");
        Assert.hasText(blog.getTitle(), "标题不能为空");
        Assert.hasText(blog.getDescription(), "摘要不能为空");
        Assert.hasText(blog.getContent(), "内容不能为空");

        Blog temp = blogService.getById(blog.getId());

        // 只能编辑自己的文章
        Assert.isTrue(temp.getUserId().equals(Long.parseLong(ShiroUtil.getProfile().getUserId())), "没有权限编辑");

        BeanUtil.copyProperties(blog, temp, "id", "userId", "updateTime", "status");
        blogService.updateById(temp);

        return new Result<Blog>().data(temp);
    }

    @ApiOperation("博客分页")
    @GetMapping("/page")
    public Result<IPage<Blog>> list(Page<Blog> page, @RequestParam String userName) {
        if (!StringUtils.hasText(userName)) {
            throw new BusinessException("用户名不能为空");
        }

        IPage<Blog> pageData = blogService.lambdaQuery()
                .eq(Blog::getUserName, userName)
                .orderByDesc(Blog::getCreateTime)
                .page(page);

        return new Result<IPage<Blog>>().data(pageData);
    }

    @ApiOperation("查看博客")
    @GetMapping("/getThis")
    public Result<Blog> detail(@RequestParam Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已被删除");

        return new Result<Blog>().data(blog);
    }

    @ApiOperation("删除博客")
    @RequiresAuthentication
    @PostMapping("/delete")
    public Result delete(@RequestParam Long[] ids) {
        Assert.notNull(ids, "博客id不能为空");

        List<Blog> blogList = blogService.lambdaQuery()
                .in(Blog::getId, Arrays.asList(ids))
                .list();

        // 只能删除自己的文章
        for (Blog blog : blogList) {
            if (!ShiroUtil.getProfile().getUserId().equals(blog.getUserId().toString())) {
                throw new BusinessException("您无权删除其他人的文章");
            }
        }

        blogService.deleteBlog(Arrays.asList(ids));
        return new Result();
    }
}
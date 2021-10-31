package com.example.demo.business.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.business.blog.entity.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogMapper extends BaseMapper<Blog> {

    @Update("UPDATE t_blog SET deleted_flag = id ${ew.customSqlSegment}")
    int deleteBlog(@Param("ew") Wrapper<Blog> wrapper);
}

package com.knife.ant.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Slf4j
@Configuration
public class MybatisPlusConfig {
    // 分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    //自动填充插件
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MybatisPlusAutoFillConfig();
    }

    public class MybatisPlusAutoFillConfig implements MetaObjectHandler {
        // 新增时填充
        @Override
        public void insertFill(MetaObject metaObject) {
            log.info("插入：自动填充createTime和updateTime："+ LocalDateTime.now());
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }

        // 修改时填充
        @Override
        public void updateFill(MetaObject metaObject) {
            log.info("更新：自动填充updateTime:" + LocalDateTime.now());
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
package com.example.demo.business.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userName;

    private String nickName;

    private String avatarUrl;

    private String email;

    private String password;

    private String salt;

    // 0:正常 1：被锁定
    private Integer status;

    private LocalDateTime lastLoginTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
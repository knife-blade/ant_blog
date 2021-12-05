package com.example.demo.business.user.entity;

import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String userName;
    private String nickName;
    private String avatarUrl;
    private String email;

    private Integer blogCount;
}


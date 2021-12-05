package com.example.demo.business.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserProfile extends UserVO{
    private Integer blogCount;
}

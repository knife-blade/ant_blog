package com.knife.ant.business.user.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserProfile extends UserVO {
    private Integer blogCount;
}

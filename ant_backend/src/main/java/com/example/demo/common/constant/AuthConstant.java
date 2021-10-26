package com.example.demo.common.constant;

public interface AuthConstant {
    // 密码的加密算法
    String ALGORITHM_TYPE = "md5";

    // 密码加密次数
    int HASH_ITERATIONS = 2;

    // 鉴权的头名称
    String AUTHENTICATION_HEADER = "Authorization";
}

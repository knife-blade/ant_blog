package com.example.demo.config.properties;

import lombok.Data;

@Data
public class JwtProperty {
    private String secret;
    private long expire;
}

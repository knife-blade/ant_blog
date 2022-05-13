package com.knife.ant.config;

import com.knife.ant.config.properties.JwtProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    @ConfigurationProperties(prefix = "custom.jwt")
    public JwtProperty jwtProperty() {
        return new JwtProperty();
    }

}

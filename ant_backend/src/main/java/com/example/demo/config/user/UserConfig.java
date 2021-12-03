package com.example.demo.config.user;

import com.example.demo.config.properties.UserProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    @ConfigurationProperties(prefix = "custom.user")
    public UserProperty userProperty() {
        return new UserProperty();
    }
}

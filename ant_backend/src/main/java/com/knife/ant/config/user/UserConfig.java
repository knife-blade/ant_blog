package com.knife.ant.config.user;

import com.knife.ant.config.properties.UserProperty;
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

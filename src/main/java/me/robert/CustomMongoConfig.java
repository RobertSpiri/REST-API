package me.robert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomMongoConfig {
    @Bean
    public CustomMongoEventListener customMongoEventListener() {
        return new CustomMongoEventListener();
    }
}

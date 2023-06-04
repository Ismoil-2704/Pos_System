package com.example.postsystemforfather.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class BotConfig {

    @Value("${bot.name}")
    private String bot_name;
    @Value("${bot.token}")
    private String token;
}

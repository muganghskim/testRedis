package com.netty.chat.testChat.redisExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisExampleApplication implements CommandLineRunner {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RedisExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        RedisExample redisExample = new RedisExample(redisTemplate);
        redisExample.storeCallbacks();
    }
}
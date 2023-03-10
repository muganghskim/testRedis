package com.netty.chat.testChat.redisExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisExample {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, String> hashOperations;

    public RedisExample(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void storeCallbacks() {
        // Store the callbacks as hashes in Redis
        String[][] callbacks = {
                {"123456789123456", "Corporation", "18", "1"},
                {"123456789123451", "individual", "18", "0"},
                {"123456789123452", "Corporation", "12", "0"},
                {"123456789123453", "individual", "18", "1"},
                {"123456789123454", "Corporation", "13", "1"}
        };

        for (String[] callback : callbacks) {
            String callbackId = callback[0];
            String orgName = callback[1];
            String transcode = callback[2];
            String division = callback[3];

            hashOperations.put(callbackId, "OrgName", orgName);
            hashOperations.put(callbackId, "TransCoCode", transcode);
            hashOperations.put(callbackId, "Division", division);
        }
    }
}

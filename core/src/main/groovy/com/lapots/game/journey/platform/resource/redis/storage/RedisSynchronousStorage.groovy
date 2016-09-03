package com.lapots.game.journey.platform.resource.redis.storage

import org.springframework.data.redis.core.RedisTemplate

import com.lapots.game.journey.platform.CorePlatform;

import org.springframework.beans.factory.annotation.Autowired

abstract class RedisSynchronousStorage {

    @Autowired
    RedisTemplate<String, String> template

    abstract def read(key)

    abstract def write(key, value)
}

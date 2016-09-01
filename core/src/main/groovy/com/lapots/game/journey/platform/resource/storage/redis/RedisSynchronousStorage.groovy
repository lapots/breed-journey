package com.lapots.game.journey.platform.resource.storage.redis

import org.springframework.data.redis.core.RedisTemplate

import com.lapots.game.journey.platform.CorePlatform;

abstract class RedisSynchronousStorage {

    // might want to replace with some AST like @Managed(name='redisTemplate')
    RedisTemplate<String, String> template = CorePlatform.managed["redisTemplate"]

    abstract def read(key)

    abstract def write(key, value)
}

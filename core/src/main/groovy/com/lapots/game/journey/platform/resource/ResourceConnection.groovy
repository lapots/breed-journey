package com.lapots.game.journey.platform.resource

import com.lambdaworks.redis.RedisClient
import com.lambdaworks.redis.api.StatefulRedisConnection
import com.lambdaworks.redis.api.sync.RedisCommands;

@Singleton
class ResourceConnection {

    private static final REDIS_CONNECTION_STRING = "redis://localhost:6379"

    StatefulRedisConnection redis_connection

    {
        RedisClient redisClient =
                RedisClient.create(REDIS_CONNECTION_STRING)
        redis_connection = redisClient.connect()
    }

    def redis() { redis_connection }

    def h2() {}

    def mongo() {}
}

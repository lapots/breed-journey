package com.lapots.game.journey.platform.resource

import com.lambdaworks.redis.RedisClient
import com.lambdaworks.redis.api.StatefulRedisConnection
import com.lambdaworks.redis.api.sync.RedisCommands;

@Singleton
class ResourceConnection {

    private static final REDIS_CONNECTION_STRING = "redis://localhost:6379"

    StatefulRedisConnection redis_connection

    {
        print "Establishing connection to Redis..."
        RedisClient redisClient =
                RedisClient.create(REDIS_CONNECTION_STRING)
        redis_connection = redisClient.connect()
        println "Done!"

        addShutdownHook {
            println "Closing Redis connections!"
            redis_connection.close()
            redisClient.shutdown()
        }
    }

    def redis() { redis_connection }

    def h2() {}

    def mongo() {}
}

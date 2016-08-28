package com.lapots.game.journey.platform.resource.storage.redis

import com.lapots.game.journey.platform.resource.ResourceConnection

abstract class RedisSynchronousStorage {
    def sync_redis_connection = ResourceConnection.instance.redis().sync()

    abstract def read(key)

    abstract def write(key, value)
}

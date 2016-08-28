package com.lapots.game.journey.platform.resource.storage.redis

@Singleton
class RedisSynchronousStringStorage extends RedisSynchronousStorage {

    @Override
    def read(key) {
        sync_redis_connection.get(key)
    }

    @Override
    def write(key, value) {
        sync_redis_connection.set(key, value)
    }
}

package com.lapots.game.journey.platform.resource.storage.redis

@Singleton
class RedisSynchronousStringStorage extends RedisSynchronousStorage {

    @Override
    def read(key) {
        template.boundValueOps(key).get()
    }

    @Override
    def write(key, value) {
        template.boundValueOps(key).set(value)
    }
}

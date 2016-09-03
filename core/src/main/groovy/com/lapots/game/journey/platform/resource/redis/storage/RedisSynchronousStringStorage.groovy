package com.lapots.game.journey.platform.resource.redis.storage

import org.springframework.stereotype.Component

@Component
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

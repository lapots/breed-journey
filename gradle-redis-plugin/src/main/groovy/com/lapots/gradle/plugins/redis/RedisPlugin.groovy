package com.lapots.gradle.plugins.redis

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Redis plugin implementation.
 */
class RedisPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.getExtensions().create("redis", RedisExtension.class, project)
    }
}

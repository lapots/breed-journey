package com.lapots.gradle.plugins.redis

import com.lapots.gradle.plugins.redis.actions.DownloadAction
import org.gradle.api.Project
import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * Extension that configures, downloads and run Redis.
 */
class RedisExtension implements Configurable<RedisExtension> {
    Project project

    public RedisExtension(Project project) { this.project = project }

    @Override
    RedisExtension configure(Closure closure) {
        DownloadAction downloadAction = ConfigureUtil.configure(closure, new DownloadAction(project))
        try { downloadAction.execute() }
        catch (Exception exc) { throw new IllegalStateException("Could not download file", e) }
        return this
    }
}

package com.lapots.gradle.plugins.redis.actions

/**
 * Interface for classes that performs download.
 */
interface DownloadSpec {
    void src(Object src)
    void dest(Object dest)
    void onlyIfNewer(boolean onlyIfNewer)
}
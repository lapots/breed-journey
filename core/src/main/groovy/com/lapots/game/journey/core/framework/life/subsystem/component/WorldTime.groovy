package com.lapots.game.journey.core.framework.life.subsystem.component

import com.badlogic.gdx.Gdx;
import com.lapots.game.journey.core.api.IThreadable
import com.lapots.game.journey.core.platform.CorePlatform;
import com.lapots.game.journey.util.MathUtils;

class WorldTime extends Thread implements IThreadable {

    def worldMillis = 0
    def innerId
    long wait // unit of second

    volatile isEternal = true
    @Override
    public void run() {
        while (isEternal) {
            Thread.sleep(wait)
            worldMillis += 1
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    // change to something like "POST : ui : message "
                    CorePlatform.managed["uiComponentStorage"].registered[innerId].setValue((String)worldMillis)
                }
            })
        }
    }

    def stopThread() {
        println "Stopping thread"
        isEternal = false
    }

    String toString() {
"""
Subsystem
    innerId : $innerId
    wait    : $wait
"""
    }
}

package com.lapots.game.journey.core.framework.life.subsystem

import com.badlogic.gdx.Gdx;
import com.lapots.game.journey.core.api.IThreadable
import com.lapots.game.journey.platform.CorePlatform;
import com.lapots.game.journey.util.MathUtils;

class WorldClock extends Thread implements IThreadable {

    def innerId
    long wait

    volatile isEternal = true
    @Override
    public void run() {
        while (isEternal) {
            Thread.sleep(wait)
            def time = System.currentTimeMillis() as String
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    CorePlatform.managed["uiComponentStorage"].registered[innerId].setValue(time)
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

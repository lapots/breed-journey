package com.lapots.game.journey.core.framework.life.subsystem

import com.badlogic.gdx.Gdx;
import com.lapots.game.journey.core.api.IThreadable
import com.lapots.game.journey.platform.CorePlatform;
import com.lapots.game.journey.util.MathUtils;

class WorldClock extends Thread implements IThreadable {

    def global_time_id = 'GLOBAL_TIME'

    volatile isEternal = true
    @Override
    public void run() {
        while (isEternal) {
            Thread.sleep(10)
            def time = System.currentTimeMillis() as String
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    CorePlatform.managed["uiComponentStorage"].registered[global_time_id].setValue(time)
                }
            })
        }
    }

    def stopThread() {
        println "Stopping thread"
        isEternal = false
    }
}

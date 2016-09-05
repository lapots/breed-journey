package com.lapots.game.journey.core.framework.life.subsystem

import com.badlogic.gdx.Gdx;
import com.lapots.game.journey.core.api.IThreadable
import com.lapots.game.journey.platform.CorePlatform
import com.lapots.game.journey.util.MathUtils;;

class WorldGodsVoice extends Thread implements IThreadable {

    def gods_voice_id = 'GOD_S_VOICE'

    def clock_messages = [
        "The world is living...Without a hero",
        "The world is filled with life",
        "The world is eternal"
    ]

    volatile isEternal = true

    @Override
    public void run() {
        while (isEternal) {
            Thread.sleep(1000);
            def msg = MathUtils.randomFromList(clock_messages)
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    CorePlatform.managed["uiComponentStorage"].registered[gods_voice_id].setValue(msg)
                }
            })
        }
    }

    def stopThread() {
        println "Stopping voice's thread"
        isEternal = false
    }

}

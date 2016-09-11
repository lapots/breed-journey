package com.lapots.game.journey.core.framework.life.subsystem.state

import com.badlogic.gdx.Gdx;
import com.lapots.game.journey.core.api.IThreadable
import com.lapots.game.journey.platform.CorePlatform
import com.lapots.game.journey.util.MathUtils;;

class WorldGodsVoice extends Thread implements IThreadable {

    def innerId
    boolean isSequenced
    long wait
    def messages = []

    volatile isEternal = true

    def seq_id = 0

    @Override
    public void run() {
        seq_id = 0
        def action = randomNotification
        if (isSequenced) {
            action = sequenceNotification
        }

        while (isEternal) {
            Thread.sleep(wait);
            def msg = action()
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    CorePlatform.managed["uiComponentStorage"].registered[innerId].setValue(msg)
                }
            })
        }
    }

    def stopThread() {
        println "Stopping voice's thread"
        isEternal = false
    }

    def sequenceNotification = {
        if (seq_id == messages.length) {
            seq_id = 0
        }
        def r = messages[seq_id]
        seq_id++
        r
    }

    def randomNotification = {
        MathUtils.randomFromList(messages)
    }

    String toString() {
        """
Subsystem
    innerId     : $innerId
    wait        : $wait
    sequenced   : $isSequenced
    messages    : $messages
"""
            }
}

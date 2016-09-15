package com.lapots.game.journey.core.framework.life.subsystem.component

import com.badlogic.gdx.Gdx;
import com.lapots.game.journey.core.api.IThreadable
import com.lapots.game.journey.core.platform.CorePlatform;
import com.lapots.game.journey.util.MathUtils;;

class WorldGodsVoice extends Thread implements IThreadable {

    def innerId
    boolean isSequenced
    long wait
    def messages = []
    def prev, current

    volatile isEternal = true

    def seq_id = 0

    @Override
    public void run() {
        seq_id = 0
        println messages
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
        // prevent multiple duplicates in a row
        def current = MathUtils.randomFromList(messages)
        while(current == prev) {
            current = MathUtils.randomFromList(messages)
        }
        prev = current
        return current
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

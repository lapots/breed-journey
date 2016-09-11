package com.lapots.game.journey.core.framework.life.subsystem.state

import com.lapots.game.journey.core.framework.life.subsystem.state.calendar.saint.SaintCalendarStateMachine

class WorldTimePlatform {

    def clock = new WorldTime()
    def calendar = new SaintCalendarStateMachine()

    def start() {
        clock.start()
    }

    def stop() {
        clock.stopThread()
    }
}

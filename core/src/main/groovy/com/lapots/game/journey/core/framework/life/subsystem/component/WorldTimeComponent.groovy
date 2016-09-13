package com.lapots.game.journey.core.framework.life.subsystem.component

import com.lapots.game.journey.core.framework.life.subsystem.component.calendar.saint.SaintCalendarStateMachine

class WorldTimeComponent {

    def clock = new WorldTime()
    def calendar = new SaintCalendarStateMachine()

    def start() {
        clock.start()
    }

    def stop() {
        clock.stopThread()
    }
}

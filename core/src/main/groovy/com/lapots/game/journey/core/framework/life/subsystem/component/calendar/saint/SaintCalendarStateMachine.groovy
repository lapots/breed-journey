package com.lapots.game.journey.core.framework.life.subsystem.component.calendar.saint

import com.lapots.game.journey.core.api.state.AbstractStateMachine
import com.lapots.game.journey.core.api.state.IRepeatable

class SaintCalendarStateMachine extends AbstractStateMachine {
    def calendar = new SaintCalendar()

    def populateStateMachine() {
        // fill it with states
        // states["ASC"] etc.
    }
}

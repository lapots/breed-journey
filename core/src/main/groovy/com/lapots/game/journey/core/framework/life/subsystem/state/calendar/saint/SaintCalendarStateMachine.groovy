package com.lapots.game.journey.core.framework.life.subsystem.state.calendar.saint

import com.lapots.game.journey.core.api.state.AbstractStateMachine
import com.lapots.game.journey.core.api.state.IRepeatable

class SaintCalendarStateMachine extends AbstractStateMachine {
    def calendar = new SaintCalendar()

    def format_to_string() {
        "${ calendar.dayWithUnit() } : " + 
                "${ calendar.monthWithUnit() } ${ calendar.yearWithUnit() }"
    }

    def populateStateMachine() {
        // fill it with states
        // states["ASC"] etc.
    }
}

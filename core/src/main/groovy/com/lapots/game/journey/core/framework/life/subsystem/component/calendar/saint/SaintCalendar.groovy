package com.lapots.game.journey.core.framework.life.subsystem.component.calendar.saint

import com.lapots.game.journey.core.api.calendar.AbstractCalendar
import com.lapots.game.journey.core.api.calendar.ICalendarApi
import com.lapots.game.journey.core.loader.framework.SaintCalendarConfigLoader


class SaintCalendar extends AbstractCalendar implements ICalendarApi {

    {
        id = "saint"
        // move it to WorldTimeComponent
        new SaintCalendarConfigLoader().load(calendar_config, this)
    }

    String toString() {
"""
Seed    : $seed
Id      : $id
Unit    : $units
Config  : $configurations
"""
    }

    @Override
    def format(unifiedTime) {
        // spread milestones
    }

    @Override
    def convert(unit1, unit2, value) {
        if (unit2 != unit1) {
            def index1 = milestones.indexOf(unit1)
            def index2 = milestones.indexOf(unit2)
            // days, year, 5 => 100 * 5 => 500
            // days, month, 10 => 50 * 10 => 500
            if (index1 > index2) { configurations[unit1][unit2] * value }
            else {
                // year, days
                def result = value.intdiv(configurations[unit2][unit1])
                if (result == 0) { return 1 }
                else return result
            }
        }
        value
    }
}

package com.lapots.game.journey.core.framework.life.subsystem.state.calendar.saint

import com.lapots.game.journey.core.framework.life.subsystem.state.calendar.AbstractCalendar
import com.lapots.game.journey.core.framework.life.subsystem.state.calendar.loader.SaintCalendarConfigLoader

class SaintCalendar extends AbstractCalendar {

    {
        id = "saint"
        // move it to WorldTimePlatform
        new SaintCalendarConfigLoader().load(calendar_config, this)
    }

    def dayWithUnit() {}
    def monthWithUnit() {}
    def yearWithUnit() {}

    String toString() {
"""
Seed    : $seed
Id      : $id
Unit    : $units
Config  : $configurations
"""
    }
}

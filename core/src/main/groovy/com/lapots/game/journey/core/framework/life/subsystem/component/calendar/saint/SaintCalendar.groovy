package com.lapots.game.journey.core.framework.life.subsystem.component.calendar.saint

import com.lapots.game.journey.core.api.calendar.AbstractCalendar
import com.lapots.game.journey.core.loader.framework.SaintCalendarConfigLoader


class SaintCalendar extends AbstractCalendar {

    {
        id = "saint"
        // move it to WorldTimeComponent
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

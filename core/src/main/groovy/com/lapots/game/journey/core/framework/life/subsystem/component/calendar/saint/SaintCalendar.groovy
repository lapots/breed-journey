package com.lapots.game.journey.core.framework.life.subsystem.component.calendar.saint

import com.lapots.game.journey.core.api.calendar.AbstractCalendar
import com.lapots.game.journey.core.api.calendar.ICalendarApi
import com.lapots.game.journey.core.loader.framework.SaintCalendarConfigLoader
import groovy.text.SimpleTemplateEngine


class SaintCalendar extends AbstractCalendar implements ICalendarApi {

    {
        format_pattern = '$year $yearUnit $monthUnit:$month.$day $dayUnit'
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
        def mapping = create_mapping(unifiedTime)
        new SimpleTemplateEngine().createTemplate(format_pattern).make(mapping)
    }

    @Override
    def convert(unit1, unit2, value) {
        if (unit2 != unit1) {
            def inputTypeIndex = milestones.indexOf(unit1)
            def outputTypeIndex = milestones.indexOf(unit2)
            if (outputTypeIndex > inputTypeIndex) {
                if (unit1 == "unifiedTime") { // it is an exception out of rule
                    return value.intdiv(configurations[unit2][unit1]) as int
                } else {
                    return value.intdiv(configurations[unit2][unit1]) as int
                }
            } else {
                return (configurations[unit2][unit1].intdiv(value)) as int
            }
        }
        value as Long
    }

    synchronized def create_mapping(unifiedTime) {
        def mapping = [:]
        // year
        Long value_mapping = convert("unifiedTime", "year", unifiedTime)
        def unit_mapping = units["year"]
        if (unit_mapping in List) { // for now works with two only!!!
            if (value_mapping % 2 == 0) { unit_mapping = unit_mapping[1]}
            else { unit_mapping = unit_mapping[1] }
        }
        mapping << [ "yearUnit" : unit_mapping, "year" : value_mapping ]
        // month
        value_mapping = convert("unifiedTime", "month", unifiedTime)
        unit_mapping = units["month"]
        if (unit_mapping in List) { // for now works with two only!!!
            if (value_mapping % 2 == 0) { unit_mapping = unit_mapping[1]}
            else { unit_mapping = unit_mapping[1] }
        }
        mapping << [ "monthUnit" : unit_mapping, "month" : value_mapping ]
        // day
        value_mapping = convert("unifiedTime", "day", unifiedTime)
        unit_mapping = units["day"]
        if (unit_mapping in List) { // for now works with two only!!!
            if (value_mapping % 2 == 0) { unit_mapping = unit_mapping[1]}
            else { unit_mapping = unit_mapping[1] }
        }
        mapping << [ "dayUnit" : unit_mapping, "day" : value_mapping ]
        return mapping
    }
}

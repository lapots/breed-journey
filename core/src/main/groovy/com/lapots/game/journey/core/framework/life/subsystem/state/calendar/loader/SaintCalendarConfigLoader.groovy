package com.lapots.game.journey.core.framework.life.subsystem.state.calendar.loader

import com.lapots.game.journey.core.framework.life.subsystem.state.calendar.AbstractCalendar;
import com.lapots.game.journey.util.DomainUtils
import com.lapots.game.journey.util.StringUtils;;

class SaintCalendarConfigLoader extends XmlConfigCalendarLoader {

    // move to ext. config
    def calendarId() { "saint" }

    def processXml(xml, calendar) {
        calendar.seed = xml.seed
        xml.units.unit.each { unit ->
            if (Boolean.valueOf(unit.@multiple)) {
                calendar.units[unit.@id] = StringUtils.clearSplit(unit.text(), ",")
            } else {
                calendar.units[unit.@id] = unit.text()
            }
        }
        processConfigurations(xml, calendar)
        println calendar
    }

    def processConfigurations(xml, calendar) {
        def processingStack = []
        // ???
        def highUnit = xml.configurations.@top
        def lowUnit = xml.configurations.@bottom
        xml.configurations.configuration.each { config ->
            def key = config.@id
            if (Boolean.valueOf(config.@equalDivision)) {
                processingStack << config
            } else {
                def mapping = new AbstractCalendar.DateMapping()
                config.mapping.each { map ->
                    mapping.mapping[map.@id] = map.text()
                }
                calendar.configurations[key] = mapping
            }
        }
        processingStack.each { left_config ->
            def unitId = left_config.@id
            // hm map to unit
        }
        processingStack = null
    }
}

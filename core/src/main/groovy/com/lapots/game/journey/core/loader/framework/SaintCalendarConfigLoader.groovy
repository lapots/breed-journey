package com.lapots.game.journey.core.loader.framework

import com.lapots.game.journey.core.api.AbstractXmlConfigObjectLoader
import com.lapots.game.journey.util.DomainUtils
import com.lapots.game.journey.util.StringUtils;;

class SaintCalendarConfigLoader extends AbstractXmlConfigObjectLoader {

    // move to ext. config
    def identifier() { "saint" }

    def processXml(xml, calendar) {
        calendar.seed = xml.seed
        xml.units.unit.each { unit ->
            if (Boolean.valueOf((String)unit.@multiple)) {
                calendar.units[unit.@id] = StringUtils.clearSplit(unit.text(), ",")
            } else {
                calendar.units[unit.@id] = unit.text()
            }
        }
        processConfigurations(xml, calendar)
        println calendar
    }

    def processConfigurations(xml, calendar) {
        def laid_back = []
        def top = xml.configurations.@high
        xml.configurations.configuration.each { config ->
            def key = config.@id
            calendar.configurations["$key"] = [:]
            def isEqual = Boolean.valueOf((String)config.@equalDivision)
            if (isEqual) {
                laid_back << config
                return // same as continue
            }
            config.mapping.each { map ->
                // to create something like calendar.configurations["year"]["day"] = 100, ["year"]["month"] = 2
                calendar.configurations["$key"] << [ (map.@id) : map.text() ]
            }
        }
        println top
        def topUnitConfig = calendar.configurations[top]
        if (laid_back) {
            laid_back.each { config ->
                def key = config.@id
                def units = StringUtils.clearSplit((String) config.@units, ",")
                // the idea is to get all info from the top unit as it knows everything anyway
                units.each { unit ->
                    def requiredUnitConfig = topUnitConfig[key]
                    def currentUnitConfig = topUnitConfig[unit]
                    // as we know that it is the equal division then
                    def integer = (int) currentUnitConfig / (int) requiredUnitConfig
                    calendarConfigurations["$key"] << [ (key) : integer.toString() ]
                }
            }
        }
    }
}

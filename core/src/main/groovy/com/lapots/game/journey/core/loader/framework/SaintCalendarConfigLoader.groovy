package com.lapots.game.journey.core.loader.framework

import com.lapots.game.journey.core.api.AbstractXmlConfigObjectLoader
import com.lapots.game.journey.util.StringUtils
import com.lapots.game.journey.util.XmlUtils

class SaintCalendarConfigLoader extends AbstractXmlConfigObjectLoader {

    static final SEPARATOR = ","

    def identifier() { "saint" }

    def processXml(xml, calendar) {
        calendar.seed = xml.seed
        calendar.milestones = StringUtils.clearSplit(xml.milestones.text(), SEPARATOR)
        def units = calendar.milestones
        units.each { unit ->
            xml.units.unit.each { xmlUnit ->
                if (xmlUnit.@id == unit) {
                    if (! calendar.units[unit]) { calendar.units[unit] = [:] }
                    calendar.units[unit] = [:]
                    if (XmlUtils.attr_boolean(xmlUnit.@multiple)) {
                        calendar.units[unit] = StringUtils.clearSplit(xmlUnit.text(), SEPARATOR)
                    } else {
                        // warning: might need trim one day
                        calendar.units[unit] = xmlUnit.text()
                    }
                    // finish closure iteration
                    return
                }
            }
            xml.configurations.configuration.each { xmlConfig ->
                if (xmlConfig.@id == unit) {
                    xmlConfig.mapping.each { xmlMapping ->
                        if (!calendar.configurations[unit]) { calendar.configurations[unit] = [:] }
                        calendar.configurations[unit] << [ ((String) xmlMapping.@id) : Integer.valueOf(xmlMapping.text()) ]
                    }
                    // finish closure iteration
                    return
                }
            }
        }
        println calendar
    }
}

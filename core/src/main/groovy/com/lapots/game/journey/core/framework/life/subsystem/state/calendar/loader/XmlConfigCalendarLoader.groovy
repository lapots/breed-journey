package com.lapots.game.journey.core.framework.life.subsystem.state.calendar.loader

import com.lapots.game.journey.core.loader.ServiceConfigLoader

abstract class XmlConfigCalendarLoader {

    def load(file, calendar) {
        def xml = new ServiceConfigLoader().load(file)
        processXml(xml.'*'.find { node -> node.@id == calendarId() }, calendar)
    }

    abstract calendarId()
    abstract processXml(xml, calendar)
}

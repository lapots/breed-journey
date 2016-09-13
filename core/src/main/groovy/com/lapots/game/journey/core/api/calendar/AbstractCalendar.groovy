package com.lapots.game.journey.core.api.calendar

abstract class AbstractCalendar {
    static final calendar_config = "config/calendar-config.xml"

    def id
    def seed
    def units = [:]
    def configurations = [:]

    static class DateMapping {
        def mapping = [:]
    }
}

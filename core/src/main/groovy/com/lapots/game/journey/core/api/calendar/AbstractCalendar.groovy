package com.lapots.game.journey.core.api.calendar

abstract class AbstractCalendar {
    static final calendar_config = "config/calendar-config.xml"

    def format_pattern
    def id
    def seed
    def units = [:]
    def configurations = [:]
    def milestones = []

}

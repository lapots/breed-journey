package com.lapots.game.journey.core.platform

import org.springframework.context.support.ClassPathXmlApplicationContext;

class ManagedPlatform {
    static {
        ClassPathXmlApplicationContext.metaClass.getAt = { String bean_name -> getBean(bean_name) }
    }

    static managed = new ClassPathXmlApplicationContext("application-context.xml")

    def getAt(name) {
        managed[name]
    }
}

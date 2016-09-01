package com.lapots.game.journey.platform

import org.springframework.context.support.ClassPathXmlApplicationContext

class CorePlatform {
    static {
        ClassPathXmlApplicationContext.metaClass.getAt = { String bean_name -> getBean(bean_name) }
    }

    static managed = new ClassPathXmlApplicationContext("application-context.xml")
}

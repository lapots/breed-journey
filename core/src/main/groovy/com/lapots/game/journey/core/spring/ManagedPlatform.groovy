package com.lapots.game.journey.core.spring

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Class that provide access to Spring Framework managed beans.
 */
class ManagedPlatform {
    static {
        ClassPathXmlApplicationContext.metaClass.getAt = { String bean_name -> getBean(bean_name) }
    }

    static managed = new ClassPathXmlApplicationContext("application-context.xml")

    // access as ManagedPlatform["beanName"]
    def getAt(name) {
        managed[name]
    }
}

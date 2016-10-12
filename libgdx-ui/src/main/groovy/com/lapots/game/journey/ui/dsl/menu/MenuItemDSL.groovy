package com.lapots.game.journey.ui.dsl.menu

import com.kotcrab.vis.ui.widget.MenuItem
import com.lapots.game.journey.ui.dsl.api.IEventableDSL
import com.lapots.game.journey.ui.dsl.api.IPrimitiveDSL
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.ReflectionUtils;

/**
 * Basic menu item.
 * Support [on_click] events.
 *
 * {@link MenuBarDSL}
 */
class MenuItemDSL implements IPrimitiveDSL, IEventableDSL {

    def itemName
    @Lazy MenuItem item = new MenuItem(itemName)

    //==================DSL specifics============
    @Override
    def onClick(Object closure) {
        def event = closure()
        if (event) {
            def instance = ReflectionUtils.instantiateOne(UiHelper["application.packages.event_packages"],
                    event)
            item.addListener(instance)
        }
    }
    //=============================END=============

    @Override
    Object getParentUid() { return parentUid }

    @Override
    void setParentUid(Object parentUid) { this.parentUid = parentUid }

    @Override
    Object getId() { return id }

    @Override
    void setId(Object id) { this.id = id }

    @Override
    def getInnerComponent() { return item }

    @Override
    def getRawComponent() { return item }
}
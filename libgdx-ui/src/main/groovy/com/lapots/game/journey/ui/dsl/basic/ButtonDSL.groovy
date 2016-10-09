package com.lapots.game.journey.ui.dsl.basic

import com.kotcrab.vis.ui.building.OneRowTableBuilder;
import com.kotcrab.vis.ui.widget.VisTextButton
import com.lapots.game.journey.core.api.IReferenced
import com.lapots.game.journey.core.platform.CorePlatform
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.ui.dsl.traits.ComponentWidthTrait
import com.lapots.game.journey.ui.dsl.traits.IdentifiableTrait
import com.lapots.game.journey.util.DslUtils
import com.lapots.game.journey.util.ReflectionUtils;;

class ButtonDSL implements IReferenced, ComponentWidthTrait, IdentifiableTrait {

    private static final LABEL = UiHelper["dsl.config.label_key"]

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    VisTextButton button

    def call(map, closure) {
        id = uuid()
        def label = map[LABEL]
        button = new VisTextButton(label)

        DslUtils.delegate(closure, this)

        CorePlatform.managed["uiComponentStorage"].registered[id] = this
        oneRowTable.append(roundify(button))
    }

    def on_click(closure) {
        def instance = ReflectionUtils.instantiate("${UiHelper["application.event_package"]}.${ closure() }")
        button.addListener(instance)
    }

    def component_reference() { null }

    def identifiable_instance() { button }

    def bitwiseNegate() { oneRowTable.build() }
}

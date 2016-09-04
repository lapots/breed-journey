package com.lapots.game.journey.ui.dsl

import static com.lapots.game.journey.platform.UiPlatform.Constants

import com.kotcrab.vis.ui.building.OneRowTableBuilder;
import com.kotcrab.vis.ui.widget.VisTextButton
import com.lapots.game.journey.core.api.IReferenced
import com.lapots.game.journey.platform.CorePlatform;
import com.lapots.game.journey.util.DslUtils
import com.lapots.game.journey.util.ReflectionUtils;;

class ButtonDSL implements IReferenced, ComponentWidthTrait, IdentifiableTrait {

    private static final LABEL = "label"

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
        def instance = ReflectionUtils.instantiate("$Constants.EVENT_PACKAGE.${ closure() }")
        button.addListener(instance)
    }

    def component_reference() { null }

    def identifiable_instance() { button }

    def bitwiseNegate() { oneRowTable.build() }
}

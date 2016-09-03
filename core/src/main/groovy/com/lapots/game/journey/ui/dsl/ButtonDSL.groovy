package com.lapots.game.journey.ui.dsl

import static com.lapots.game.journey.platform.UiPlatform.Constants

import com.kotcrab.vis.ui.building.OneRowTableBuilder;
import com.kotcrab.vis.ui.widget.VisTextButton
import com.lapots.game.journey.util.DslUtils
import com.lapots.game.journey.util.ReflectionUtils;;

class ButtonDSL implements IReferenced, ComponentWidthTrait {

    private static final LABEL = "label"

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    VisTextButton button

    def call(map, closure) {
        def label = map[LABEL]
        button = new VisTextButton(label)

        DslUtils.delegate(closure, this)

        oneRowTable.append(roundify(button))
    }

    def on_click(closure) {
        def instance = ReflectionUtils.instantiate("$Constants.EVENT_PACKAGE.${ closure() }")
        button.addListener(instance)
    }

    def component_reference() { null }

    def bitwiseNegate() { oneRowTable.build() }
}

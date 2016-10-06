package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.widget.VisSelectBox
import com.kotcrab.vis.ui.widget.VisTable
import com.lapots.game.journey.core.api.IReferenced
import com.lapots.game.journey.core.platform.CorePlatform;
import com.lapots.game.journey.ui.dsl.traits.ComponentWidthTrait
import com.lapots.game.journey.ui.dsl.traits.DynamicPropertyTrait
import com.lapots.game.journey.ui.dsl.traits.IdentifiableTrait
import com.lapots.game.journey.ui.dsl.traits.ValueReferencedTrait
import com.lapots.game.journey.util.DslUtils

class SelectBoxDSL implements IReferenced, ComponentWidthTrait, DynamicPropertyTrait, IdentifiableTrait, ValueReferencedTrait {

    private static final String LABEL = "label"

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    VisSelectBox selectBox = new VisSelectBox()

    def has_selected
    def label

    def call(map, closure) {
        id = uuid()
        label = map[LABEL]
        DslUtils.delegate(closure, this)

        if (label) {
            oneRowTable.append(roundify(TextLabelDSL.createLabel(label)))
        }

        CorePlatform.managed["uiComponentStorage"].registered[id] = this
        valueRef = selectBox
        oneRowTable.append(roundify(selectBox))
    }

    def on_click(closure) {}

    def value(value) {
        has_selected = value
    }

    def items(items) {
        // temporary invoke ()
        selectBox.setItems(items().toArray())
        if (has_selected) { selectBox.setSelected(has_selected) }
    }

    def getValue() { [ "$label" : selectBox.getSelected() ] }

    def identifiable_instance() { selectBox }
    def component_reference() { null }
    def bitwiseNegate() { oneRowTable.build() }
}

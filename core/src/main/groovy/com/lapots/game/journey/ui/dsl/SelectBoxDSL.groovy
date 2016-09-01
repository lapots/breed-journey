package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.widget.VisSelectBox
import com.kotcrab.vis.ui.widget.VisTable
import com.lapots.game.journey.util.DslUtils

class SelectBoxDSL implements IReferenced, ComponentWidthTrait {

    private static final String LABEL = "label"

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    VisSelectBox selectBox = new VisSelectBox()

    def has_selected

    def call(map, closure) {
        def label = map[LABEL]
        DslUtils.delegate(closure, this)

        if (label) {
            oneRowTable.append(roundify(TextLabel.createLabel(label)))
        }

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

    def component_reference() { null }
    def bitwiseNegate() { oneRowTable.build() }
}
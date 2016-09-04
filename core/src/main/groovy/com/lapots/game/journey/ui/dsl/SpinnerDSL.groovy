package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.spinner.IntSpinnerModel
import com.kotcrab.vis.ui.widget.spinner.Spinner
import com.lapots.game.journey.platform.CorePlatform;
import com.lapots.game.journey.util.DslUtils;

class SpinnerDSL implements IReferenced, ComponentWidthTrait, ValueReferencedTrait, IdentifiableTrait {

    private static final LABEL = "label"

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    // some solution for that is needed without bind to Spinner
    def temp_value
    def temp_u_bound
    def temp_l_bound

    Spinner spinner
    def label

    def call(map, closure) {
        id = uuid()
        label = map[LABEL]
        DslUtils.delegate(closure, this)

        if (label) {
            oneRowTable.append(roundify(TextLabelDSL.createLabel(label)))
        }

        CorePlatform.managed["uiComponentStorage"].registered[id] = this
        valueRef = new IntSpinnerModel(temp_value, temp_l_bound, temp_u_bound)
        spinner = new Spinner("", valueRef)
        oneRowTable.append(roundify(spinner))
    }

    def value(value) { temp_value = value }
    def upperBound(value) { temp_u_bound = value }
    def lowerBound(value) { temp_l_bound = value }

    def getValue() { [ "$label" : valueRef.getValue() ] }

    def identifiable_instance() { spinner }
    def component_reference() { null }
    def bitwiseNegate() { oneRowTable.build() }
}

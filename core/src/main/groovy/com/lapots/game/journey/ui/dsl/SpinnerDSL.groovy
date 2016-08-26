package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.spinner.IntSpinnerModel
import com.kotcrab.vis.ui.widget.spinner.Spinner
import com.lapots.game.journey.util.DslUtils;

class SpinnerDSL implements IReferenced {

    private static final LABEL = "label"

    VisTable table = new VisTable(true)
    // some solution for that is needed without bind to Spinner
    def temp_value
    def temp_u_bound
    def temp_l_bound

    Spinner spinner

    def call(map, closure) {
        def label = map[LABEL]
        DslUtils.delegate(closure, this)

        spinner = new Spinner(label, new IntSpinnerModel(temp_value, temp_l_bound, temp_u_bound))
        table.add(spinner)
    }

    def value(value) { temp_value = value }
    def upperBound(value) { temp_u_bound = value }
    def lowerBound(value) { temp_l_bound = value }

    def component_reference() { null }
    def bitwiseNegate() { table }
}

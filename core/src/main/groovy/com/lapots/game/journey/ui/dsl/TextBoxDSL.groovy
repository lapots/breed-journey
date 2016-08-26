package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.VisTextField;
import com.lapots.game.journey.util.DslUtils;

class TextBoxDSL implements IReferenced {

    private static final LABEL = "label"

    VisTable table = new VisTable(true)
    VisTextField textField = new VisTextField()

    def call(map, closure) {
        def label = map[LABEL]
        DslUtils.delegate(closure, this)

        if (label) { table.add(TextLabel.createLabel(label)) }

        table.add(textField)
    }

    def value(value) {
        textField.setText(value)
    }

    def component_reference() { null }

    def bitwiseNegate() { table }
}

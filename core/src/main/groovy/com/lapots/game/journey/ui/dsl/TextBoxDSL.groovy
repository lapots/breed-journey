package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.building.utilities.Alignment;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.CellWidget.CellWidgetBuilder
import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.VisTextField;
import com.lapots.game.journey.util.DslUtils;

class TextBoxDSL implements IReferenced, ComponentWidthTrait {

    private static final LABEL = "label"

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    VisTextField textField = new VisTextField()

    def call(map, closure) {
        def label = map[LABEL]
        DslUtils.delegate(closure, this)

        if (label) {
            oneRowTable.append(roundify(TextLabel.createLabel(label)))
        }

        oneRowTable.append(roundify(textField))
    }

    def value(value) {
        textField.setText(value)
    }

    def component_reference() { null }

    def bitwiseNegate() { oneRowTable.build() }
}

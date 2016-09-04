package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.building.utilities.Alignment;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.CellWidget.CellWidgetBuilder
import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.VisTextField
import com.lapots.game.journey.platform.CorePlatform;
import com.lapots.game.journey.util.DslUtils;

class TextBoxDSL implements IReferenced, ComponentWidthTrait, ValueReferencedTrait, IdentifiableTrait {

    private static final LABEL = "label"

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    VisTextField textField = new VisTextField()

    def label
    def call(map, closure) {
        id = uuid()
        label = map[LABEL]
        DslUtils.delegate(closure, this)

        if (label) {
            oneRowTable.append(roundify(TextLabel.createLabel(label)))
        }
        CorePlatform.managed["uiComponentStorage"].registered[id] = this
        valueRef = textField
        oneRowTable.append(roundify(textField))
    }

    def value(value) {
        textField.setText(value)
    }

    def getValue() { [ "$label" : valueRef.getText() ] }

    def component_reference() { null }
    def identifiable_instance() { textField }
    def bitwiseNegate() { oneRowTable.build() }
}

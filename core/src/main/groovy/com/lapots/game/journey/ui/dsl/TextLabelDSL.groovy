package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.building.utilities.Alignment;
import com.kotcrab.vis.ui.widget.VisLabel
import com.kotcrab.vis.ui.widget.VisTable
import com.lapots.game.journey.core.api.IReferenced
import com.lapots.game.journey.util.DslUtils;

class TextLabelDSL implements IReferenced, ComponentWidthTrait {

    private static final LABEL = "label"

    static def createLabel(text) {
        new VisLabel(text)
    }

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    def tbl

    def call(closure) {
        DslUtils.delegate(closure, this)

        oneRowTable.append(roundify(tbl))
    }

    def text(text) {
        def label = createLabel(text)
        tbl = new VisTable()
        tbl.add(label)
    }

    def component_reference() { null }

    def bitwiseNegate() { oneRowTable.build() }
}

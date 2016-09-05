package com.lapots.game.journey.ui.dsl

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.building.utilities.Alignment;
import com.kotcrab.vis.ui.widget.VisLabel
import com.kotcrab.vis.ui.widget.VisTable
import com.lapots.game.journey.core.api.IReferenced
import com.lapots.game.journey.platform.CorePlatform;
import com.lapots.game.journey.util.DslUtils;

class TextLabelDSL implements IReferenced, ComponentWidthTrait, IdentifiableTrait  {

    private static final LABEL = "label"

    static def createLabel(text) {
        new VisLabel(text)
    }

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    def tbl
    def lbl

    def call(closure) {
        id = uuid()
        DslUtils.delegate(closure, this)

        CorePlatform.managed["uiComponentStorage"].registered[id] = this
        oneRowTable.append(roundify(tbl))
    }

    def text(text) {
        lbl = createLabel(text)
        tbl = new VisTable()
        tbl.add(lbl)
    }

    def setValue(value) {
        lbl.setText(value)
    }

    def component_reference() { null }

    def bitwiseNegate() { oneRowTable.build() }

    def identifiable_instance() { lbl }
}

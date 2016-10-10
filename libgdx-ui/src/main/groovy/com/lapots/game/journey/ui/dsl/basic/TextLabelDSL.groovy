package com.lapots.game.journey.ui.dsl.basic

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.widget.VisLabel
import com.kotcrab.vis.ui.widget.VisTable
import com.lapots.game.journey.ui.dsl.api.IPrimitiveDSL
import com.lapots.game.journey.ui.dsl.api.traits.ComponentValueTrait
import com.lapots.game.journey.ui.dsl.api.traits.ComponentWidthTrait
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils;

/**
 * DSL for text label.
 * Basically
 *
 * <pre>
 *      textLabel {
 *          text "label text"
 *          width 150
 *      }
 * </pre>
 */
class TextLabelDSL implements IPrimitiveDSL, ComponentWidthTrait, ComponentValueTrait {

    private static final LABEL = UiHelper["dsl.config.label_key"]

    static class TextLabelUtil {
        static def createLabel(text) {
            new VisLabel(text)
        }
    }

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    def tbl
    def lbl

    int c_width

    def call(closure) {
        id = uuid()
        DslUtils.delegate(closure, this)

        if (c_width) { oneRowTable.append(roundify(tbl, c_width)) }
        else { oneRowTable.append(roundify(tbl)) }
    }

    def text(text) {
        lbl = TextLabelUtil.createLabel(text)
        tbl = new VisTable()
        tbl.add(lbl)
    }

    def width(c_width) {
        this.c_width = c_width
    }

    // def setValue(value) {
    // lbl.setText(value)
    //}

    def component_reference() { null }

    def bitwiseNegate() { oneRowTable.build() }

    def identifiable_instance() { lbl }

    @Override
    Object getId() { id }

    @Override
    void setId(Object id) { this.id = id }

    @Override
    def getValue() { lbl.getText() }

    @Override
    def setValue(Object value) { lbl.setText(value) }

    @Override
    def getInnerComponent() {
        return null
    }

    @Override
    def getRawComponent() {
        return null
    }
}

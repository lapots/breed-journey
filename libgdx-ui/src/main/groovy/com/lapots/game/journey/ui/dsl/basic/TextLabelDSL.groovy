package com.lapots.game.journey.ui.dsl.basic

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.widget.VisLabel
import com.lapots.game.journey.ui.dsl.api.IPrimitiveDSL
import com.lapots.game.journey.ui.dsl.api.traits.ComponentValueTrait
import com.lapots.game.journey.ui.dsl.api.traits.ComponentWidthTrait
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils

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

    static class TextLabelUtil {
        static def createLabel(text) {
            new VisLabel(text)
        }
    }

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    VisLabel visLabel
    int labelWidth

    //===========================DSL specifics===================================
    def call(closure) {
        id = uuid()
        DslUtils.delegate(closure, this)

        if (labelWidth) { oneRowTable.append(roundify(visLabel, labelWidth)) }
        else { oneRowTable.append(roundify(visLabel)) }

        UiHelper.componentRegistry[(id)] = this
    }

    def text(text) { visLabel = TextLabelUtil.createLabel(text) }

    def width(labelWidth) { this.labelWidth = labelWidth }
    //=====================================END==================================

    @Override
    Object getId() { id }

    @Override
    void setId(Object id) { this.id = id }

    @Override
    def getValue() { visLabel.getText() }

    @Override
    def setValue(Object value) { visLabel.setText(value) }

    @Override
    def getInnerComponent() { return oneRowTable.build() }

    @Override
    def getRawComponent() { return visLabel }

    @Override
    Object getParentUid() { return parentUid }

    @Override
    void setParentUid(Object parentUid) { this.parentUid = parentUid }
}

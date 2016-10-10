package com.lapots.game.journey.ui.dsl.basic

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.widget.VisTextField
import com.lapots.game.journey.ui.dsl.api.IPrimitiveDSL
import com.lapots.game.journey.ui.dsl.api.traits.ComponentValueTrait
import com.lapots.game.journey.ui.dsl.api.traits.ComponentWidthTrait
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils;

/**
 * DSL for text box.
 * Basically
 *
 * <pre>
 *      textBox($.dsl.config.label_key : 'Text box') {
 *          initial "Some value"
 *      }
 * </pre>
 */
class TextBoxDSL implements IPrimitiveDSL, ComponentWidthTrait, ComponentValueTrait {

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    @Lazy VisTextField textField = new VisTextField()

    //==========================DSL specifics=====================
    def call(map, closure) {
        id = uuid()
        def label = map[UiHelper["dsl.config.label_key"]]
        DslUtils.delegate(closure, this)

        if (label) { oneRowTable.append(roundify(TextLabelDSL.createLabel(label))) }

        oneRowTable.append(roundify(textField))
    }

    def initial(value) { textField.setText(value) }
    //=======================================END=====================

    @Override
    def getValue() { textField.getText() }

    @Override
    def setValue(Object value) { textField.setText(value) }

    @Override
    Object getId() { id }

    @Override
    void setId(Object id) { this.id = id }

    @Override
    def getInnerComponent() { oneRowTable.build() }

    @Override
    def getRawComponent() { textField }
}

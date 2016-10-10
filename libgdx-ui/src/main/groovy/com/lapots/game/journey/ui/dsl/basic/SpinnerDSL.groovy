package com.lapots.game.journey.ui.dsl.basic

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.widget.spinner.AbstractSpinnerModel
import com.kotcrab.vis.ui.widget.spinner.IntSpinnerModel
import com.kotcrab.vis.ui.widget.spinner.Spinner
import com.lapots.game.journey.ui.dsl.api.IPrimitiveDSL
import com.lapots.game.journey.ui.dsl.api.traits.ComponentValueTrait
import com.lapots.game.journey.ui.dsl.api.traits.ComponentWidthTrait
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils;

/**
 * DSL for spinner.
 * Basically
 *
 * <pre>
 *      spinner ($dsl.config.label_key : 'Spinner example') {
 *          // TBD
 *      }
 * </pre>
 */
class SpinnerDSL implements IPrimitiveDSL, ComponentWidthTrait, ComponentValueTrait {

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    // challenge !! Might need different kind of spinners ~~~
    // move to properties
    AbstractSpinnerModel spinnerModel
    Spinner spinner

    //=============================DSL specifics=================================
    def call(map, closure) {
        id = uuid()
        def label = map[UiHelper["dsl.config.label_key"]]
        DslUtils.delegate(closure, this)

        if (label) { oneRowTable.append(roundify(TextLabelDSL.createLabel(label))) }

        // move to other place
        spinnerModel = new IntSpinnerModel(temp_value, temp_l_bound, temp_u_bound)
        spinner = new Spinner("", spinnerModel)
        oneRowTable.append(roundify(spinner))
    }
    //===========================END=====================================

    @Override
    def getValue() { spinnerModel.getText() }

    @Override
    def setValue(Object value) {
        // unsupported I presume...or not?
    }

    @Override
    Object getId() { id }

    @Override
    void setId(Object id) { this.id = id }

    @Override
    def getInnerComponent() { oneRowTable.build() }

    @Override
    def getRawComponent() { spinner }
}

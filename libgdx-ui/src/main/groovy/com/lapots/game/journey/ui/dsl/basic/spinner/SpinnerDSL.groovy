package com.lapots.game.journey.ui.dsl.basic.spinner

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.widget.spinner.AbstractSpinnerModel
import com.kotcrab.vis.ui.widget.spinner.Spinner
import com.lapots.game.journey.ui.dsl.api.IPrimitiveDSL
import com.lapots.game.journey.ui.dsl.api.traits.ComponentValueTrait
import com.lapots.game.journey.ui.dsl.api.traits.ComponentWidthTrait
import com.lapots.game.journey.ui.dsl.basic.TextLabelDSL
import com.lapots.game.journey.ui.exception.UiDSLException
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils;

/**
 * DSL for spinner.
 * Basically
 *
 * <pre>
 *      spinner ($dsl.config.label_key : 'Spinner example') {
 *          type("int") {
 *              initial 5
 *              lowerBound 1
 *              upperBound 10
 *          }
 *      }
 * </pre>
 */
class SpinnerDSL implements IPrimitiveDSL, ComponentWidthTrait, ComponentValueTrait {

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    AbstractSpinnerModel spinnerModel
    Spinner spinner

    //=============================DSL specifics=================================
    def call(map, closure) {
        id = "spinner-" + uuid()
        def label = map[UiHelper["dsl.config.label_key"]]
        DslUtils.delegate(closure, this)

        if (label) {
            oneRowTable.append(roundify(TextLabelDSL.TextLabelUtil.createLabel(label)))
        }

        if (!spinnerModel) { throw new UiDSLException("Spinner config not found!") }
        spinner = new Spinner("", spinnerModel)
        oneRowTable.append(roundify(spinner))

        UiHelper.componentRegistry[(id)] = this
    }

    def type(value, closure) {
        switch (value) {
            case "int":
                def spinnerConfig = new IntSpinnerConfig()
                DslUtils.delegate(closure, spinnerConfig)
                spinnerModel = spinnerConfig.build()
                break
        }
    }
    //===========================END=====================================

    @Override
    def getValue() { spinnerModel.getText() }

    @Override
    def setValue(Object value) {
        // unsupported ???
    }

    @Override
    def getInnerComponent() { oneRowTable.build() }

    @Override
    def getRawComponent() { spinner }
}

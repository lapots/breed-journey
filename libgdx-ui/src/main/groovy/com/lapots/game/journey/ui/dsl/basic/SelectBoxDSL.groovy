package com.lapots.game.journey.ui.dsl.basic

import com.kotcrab.vis.ui.building.OneRowTableBuilder
import com.kotcrab.vis.ui.widget.VisSelectBox
import com.lapots.game.journey.ui.dsl.api.IEventableDSL
import com.lapots.game.journey.ui.dsl.api.IListableDSL
import com.lapots.game.journey.ui.dsl.api.IPrimitiveDSL
import com.lapots.game.journey.ui.dsl.api.traits.ComponentDefaultValueTrait
import com.lapots.game.journey.ui.dsl.api.traits.ComponentValueTrait
import com.lapots.game.journey.ui.dsl.api.traits.ComponentWidthTrait
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils
import com.lapots.game.journey.util.ReflectionUtils

/**
 * DSL for select box.
 * Basically
 *
 * <pre>
 *      selectBox($dsl.config.label_key : 'Select box') {
 *          onClick {
 *              "SomethingChangedEvent"
 *          }
 *          items { ["a", "b", "c"] }
 *          initial "a"
 *      }
 * </pre>
 */
class SelectBoxDSL implements IPrimitiveDSL, IEventableDSL, IListableDSL, ComponentWidthTrait,
        ComponentValueTrait, ComponentDefaultValueTrait {

    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    VisSelectBox selectBox = new VisSelectBox()

    //=================DSL specifics==============================
    // basically dsl specific facilities.
    def call(map, closure) {
        id = "selectbox-" + uuid()
        def label = map[UiHelper["dsl.config.label_key"]]
        DslUtils.delegate(closure, this)

        if (label) { oneRowTable.append(roundify(TextLabelDSL.TextLabelUtil.createLabel(label))) }

        oneRowTable.append(roundify(selectBox))

        UiHelper.componentRegistry[(id)] = this
    }

    def items(closure) { this.setItems(closure()) }

    @Override
    def onClick(Object closure) {
        def event = closure()
        if (event) {
            def instance = ReflectionUtils.instantiateOne(UiHelper["application.packages.event_packages"], event)
            instance.boundId = this.id
            UiHelper.eventRegistry[(this.id)] = instance
            selectBox.addListener(instance)
        }
    }

    def initial(value) { setDefaultValue(value) }
    //========================END===========================================

    @Override
    def getInnerComponent() { oneRowTable.build() }

    @Override
    def getRawComponent() { selectBox }

    @Override
    def getValue() { selectBox.getSelected() }

    @Override
    def setValue(Object value) { selectBox.setSelected(value) }

    @Override
    def setDefaultValue(Object value) { return null }

    @Override
    def setItems(Object list) {
        // assuming list
        if (list) selectBox.setItems(list.toArray())
    }
}
